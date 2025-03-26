package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class PostResponse {

    private String postId;
    private Date postDate;
    private String description;
    private List<String> imageUrls;
    private String videoUrl;
    private String username;
    private int likeCount;
    private List<CommentResponse> comments;
}
