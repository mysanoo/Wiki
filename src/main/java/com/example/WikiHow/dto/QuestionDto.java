package com.example.WikiHow.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionDto {

    private Integer postID;
    private Integer userId;
    private Integer questionId;
    private String text;

}
