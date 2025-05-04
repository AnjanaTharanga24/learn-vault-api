package com.example.demo.service.impl;

import com.example.demo.dto.request.CommentRequest;
import com.example.demo.dto.request.LearningPlanRequest;
import com.example.demo.dto.request.LearningPlanStatusUpdateRequest;
import com.example.demo.dto.request.UpdateLearningPlanRequest;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.dto.response.LearningPlanResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.LearningPlan;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LearningPlanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LearningPlanService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LearningPlanServiceImpl implements LearningPlanService {

    private final CommentRepository commentRepository;
    private LearningPlanRepository learningPlanRepository;
    private UserRepository userRepository;

    @Override
    public LearningPlanResponse createLearningPlan(LearningPlanRequest learningPlanRequest) throws NotFoundException {

        Optional<User> userOptional = userRepository.findById(learningPlanRequest.getUserId());

        if (!userOptional.isPresent()){
            throw new NotFoundException("user not found with id : " + learningPlanRequest.getUserId());
        }

        LearningPlan learningPlan = new LearningPlan();

        learningPlan.setUserId(learningPlanRequest.getUserId());
        learningPlan.setTitle(learningPlanRequest.getTitle());
        learningPlan.setTopics(learningPlanRequest.getTopics());
        learningPlan.setResources(learningPlanRequest.getResources());
        learningPlan.setStartDate(learningPlanRequest.getStartDate());
        learningPlan.setEndDate(learningPlanRequest.getEndDate());
        learningPlan.setStatus(learningPlanRequest.getStatus());

        LearningPlan createdLearningPlan = learningPlanRepository.save(learningPlan);

        return LearningPlanResponse.builder()
                .id(createdLearningPlan.getId())
                .userId(createdLearningPlan.getUserId())
                .title(createdLearningPlan.getTitle())
                .topics(createdLearningPlan.getTopics())
                .resources(createdLearningPlan.getResources())
                .startDate(createdLearningPlan.getStartDate())
                .endDate(createdLearningPlan.getEndDate())
                .status(createdLearningPlan.getStatus())
                .build();
    }

    @Override
    public LearningPlanResponse updateLearningPlanStatus(LearningPlanStatusUpdateRequest learningPlanStatusUpdateRequest) throws NotFoundException {

        Optional<LearningPlan> optionalLearningPlan = learningPlanRepository.findById(learningPlanStatusUpdateRequest.getPostId());

        if (!optionalLearningPlan.isPresent()){
            throw new NotFoundException("learning plan not found with id : " + learningPlanStatusUpdateRequest.getPostId());
        }

        LearningPlan learningPlan = optionalLearningPlan.get();
        learningPlan.setStatus(learningPlanStatusUpdateRequest.getStatus());

        LearningPlan updatedLearningPlan = learningPlanRepository.save(learningPlan);

        return LearningPlanResponse.builder()
                .id(updatedLearningPlan.getId())
                .userId(updatedLearningPlan.getUserId())
                .title(updatedLearningPlan.getTitle())
                .topics(updatedLearningPlan.getTopics())
                .resources(updatedLearningPlan.getResources())
                .startDate(updatedLearningPlan.getStartDate())
                .endDate(updatedLearningPlan.getEndDate())
                .status(updatedLearningPlan.getStatus())
                .build();
    }

    @Override
    public LearningPlanResponse updateLearningPlan(UpdateLearningPlanRequest updateLearningPlanRequest , String postId) throws NotFoundException {

        Optional<LearningPlan> optionalLearningPlan = learningPlanRepository.findById(postId);

        if (!optionalLearningPlan.isPresent()){
            throw new NotFoundException("learning plan not found with id : " + postId);
        }

        LearningPlan updateLeaningPlan = optionalLearningPlan.get();

        updateLeaningPlan.setTitle(updateLearningPlanRequest.getTitle());
        updateLeaningPlan.setTopics(updateLearningPlanRequest.getTopics());
        updateLeaningPlan.setResources(updateLearningPlanRequest.getResources());
        updateLeaningPlan.setStartDate(updateLearningPlanRequest.getStartDate());
        updateLeaningPlan.setEndDate(updateLearningPlanRequest.getEndDate());

        learningPlanRepository.save(updateLeaningPlan);

        return LearningPlanResponse.builder()
                .id(updateLeaningPlan.getId())
                .userId(updateLeaningPlan.getUserId())
                .title(updateLeaningPlan.getTitle())
                .topics(updateLeaningPlan.getTopics())
                .resources(updateLeaningPlan.getResources())
                .startDate(updateLeaningPlan.getStartDate())
                .endDate(updateLeaningPlan.getEndDate())
                .status(updateLeaningPlan.getStatus())
                .build();
    }

    @Override
    public List<LearningPlanResponse> getAllLearningPlanByUserId(String userId) throws NotFoundException {

        List<LearningPlan> learningPlans = learningPlanRepository.findAllByUserId(userId);

        if (learningPlans == null){
            throw new NotFoundException("learning plans not found with user id : " + userId);
        }

        List<LearningPlanResponse> learningPlanResponses = new ArrayList<>();

        for(LearningPlan learningPlan : learningPlans){
            LearningPlanResponse response = LearningPlanResponse.builder()
                    .id(learningPlan.getId())
                    .userId(learningPlan.getUserId())
                    .title(learningPlan.getTitle())
                    .topics(learningPlan.getTopics())
                    .resources(learningPlan.getResources())
                    .startDate(learningPlan.getStartDate())
                    .endDate(learningPlan.getEndDate())
                    .status(learningPlan.getStatus())
                    .build();
            learningPlanResponses.add(response);
        }
        return learningPlanResponses;
    }

    @Override
    public String deleteLearningPlanById(String postId) throws NotFoundException {

        Optional<LearningPlan> optionalLearningPlan = learningPlanRepository.findById(postId);

        if(!optionalLearningPlan.isPresent()){
            throw new NotFoundException("post not found with id : " + postId);
        }

        learningPlanRepository.deleteById(postId);

        return "learnin plan delete succefull with id : " + postId;

    }

    @Override
    public List<LearningPlan> getAllLearningPlan() {

        List<LearningPlan> learningPlans = learningPlanRepository.findAll();
        return learningPlans;
    }

    //add comment
    @Override
    public String addComment(String id, CommentRequest commentRequest) {
        // Retrieve the LearningPlan by its ID
        LearningPlan learningPlan = learningPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Learning Plan not found with id: " + id));

        // Look up the user who made the comment (assuming your DTO has a userId field)
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + commentRequest.getUserId()));

        // Create a new Comment instance and populate its fields
        Comment comment = new Comment();
        comment.setLearningPlanId(learningPlan.getId());
        comment.setUser(user);
        comment.setComment(commentRequest.getComment());
        comment.setCommentedDate(new Date());


        commentRepository.save(comment);
        // Add the new comment to the post's comment list
        learningPlan.getComments().add(comment);

        // Save the updated post
        learningPlanRepository.save(learningPlan);


        return "Comment was added successfully";
    }

    @Override
    public CommentResponse updateComment(String id, String userId, String commentId, CommentRequest req) {
        // Retrieve the LearningPlan by its ID
        LearningPlan learningPlan = learningPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Learning Plan not found with id: " + id));

        // load comment
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        // ensure the caller is the author
        if (!comment.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You can only edit your own comments");
        }

        // apply update
        comment.setComment(req.getComment());
        comment.setCommentedDate(new Date());

        Comment saved = commentRepository.save(comment);

        // inline-map to DTO
        return CommentResponse.builder()
                .commentId(saved.getCommentId())
                .comment(saved.getComment())
                .commentedDate(saved.getCommentedDate())
                .postId(saved.getPostId())
                .userId(saved.getUser().getId())
                .build();

    }

    @Override
    public void deleteComment(String id, String commentId, String userId) {
        // Retrieve the LearningPlan by its ID
        LearningPlan learningPlan = learningPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Learning Plan not found with id: " + id));

        // load comment
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        // check permissions: must be either comment’s author OR post’s author
        boolean isCommentAuthor = comment.getUser().getId().equals(userId);
        boolean isPostAuthor    = learningPlan.getUserId().equals(userId);
        if (! (isCommentAuthor || isPostAuthor) ) {
            throw new AccessDeniedException("Only the comment’s author or the post’s author can delete this comment");
        }

        // remove the reference from post.comments
        learningPlan.getComments().removeIf(c -> c.getCommentId().equals(commentId));
        learningPlanRepository.save(learningPlan);

        // delete the comment document
        commentRepository.deleteById(commentId);

    }

    @Override
    public int likePost(String postId, String userId) {
        Optional<LearningPlan> optionalLearningPlan = learningPlanRepository.findById(postId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalLearningPlan.isPresent() && optionalUser.isPresent()) {
            LearningPlan learningPlan = optionalLearningPlan.get();
            User user = optionalUser.get();

            if (!learningPlan.getLikedBy().contains(user)) {
                learningPlan.getLikedBy().add(user);
                learningPlanRepository.save(learningPlan);
            }
            else{
                learningPlan.getLikedBy().remove(user);
                learningPlanRepository.save(learningPlan);
            }
            return learningPlan.getLikedBy().size();
        } else {
            throw new RuntimeException("Post or User not found.");
        }
    }
}
