package com.example.demo.dto.response;

import com.example.demo.model.Comment;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private String postId;
    private Date postDate;
    private String description;
    private List<String> imageUrls;
    private String videoUrl;
    private User user;
    private int likeCount;
    private ArrayList<Comment> comments;
}
