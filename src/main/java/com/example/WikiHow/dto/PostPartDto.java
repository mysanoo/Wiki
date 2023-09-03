package com.example.WikiHow.dto;

import jakarta.servlet.annotation.WebServlet;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostPartDto {
    private Integer postPartId;
    private String text;
    private String title;

}
