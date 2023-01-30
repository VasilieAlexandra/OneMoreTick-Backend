package com.app.onemoretick.model.dto;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private Integer id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer isDone;
    private Integer idCategory;
    private Integer idUser;

//    private TaskDto convertToDto(Task post) {
//        TaskDto postDto = modelMapper.map(post, TaskDto.class);
//
//        return postDto;
//    }
//
//    private Post convertToEntity(PostDto postDto) throws ParseException {
//        Post post = modelMapper.map(postDto, Post.class);
//        post.setSubmissionDate(postDto.getSubmissionDateConverted(
//                userService.getCurrentUser().getPreference().getTimezone()));
//
//        if (postDto.getId() != null) {
//            Post oldPost = postService.getPostById(postDto.getId());
//            post.setRedditID(oldPost.getRedditID());
//            post.setSent(oldPost.isSent());
//        }
//        return post;
//    }
}
