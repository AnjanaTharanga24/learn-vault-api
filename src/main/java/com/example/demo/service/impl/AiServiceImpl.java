package com.example.demo.service.impl;

import com.example.demo.dto.response.MotivationMessageResponse;
import com.example.demo.service.AiService;
import com.theokanning.openai.service.OpenAiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AiServiceImpl implements AiService {

    private final OpenAiService openAiService;

    @Override
    public MotivationMessageResponse getMotivationMessage() {
        return null;
    }
}
