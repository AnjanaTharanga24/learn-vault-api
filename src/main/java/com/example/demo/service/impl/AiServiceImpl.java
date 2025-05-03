package com.example.demo.service.impl;

import com.example.demo.dto.response.MotivationMessageResponse;
import com.example.demo.service.AiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;

@Service
public class AiServiceImpl implements AiService {


    private final String openAiApiKey;
    private final WebClient webClient;

    public AiServiceImpl(@Value("${openai.api.key}") String openAiApiKey) {
        this.openAiApiKey = openAiApiKey;

        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + openAiApiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Override
    public MotivationMessageResponse getMotivationMessage() {
        System.out.println("🔑 Using OpenAI key: " + openAiApiKey);

        Map<String, Object> request = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(Map.of(
                        "role", "user",
                        "content", "Give a short motivational quote for someone who hasn't studied in 10 days."
                )),
                "max_tokens", 60,
                "temperature", 0.9
        );

        try {
            Map<?, ?> response = webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List<?> choices = (List<?>) response.get("choices");
            Map<?, ?> choice = (Map<?, ?>) choices.get(0);
            Map<?, ?> message = (Map<?, ?>) choice.get("message");
            String content = (String) message.get("content");

            return new MotivationMessageResponse(content != null ? content.trim() : "No message generated.");
        } catch (Exception e) {
            e.printStackTrace();  // Optional: For debugging
            return new MotivationMessageResponse("⚠️ Could not generate motivational message.");
        }
    }
}

