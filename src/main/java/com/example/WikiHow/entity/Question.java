package com.example.WikiHow.entity;

import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    @Column
    private String text;
    @Column
    private Integer helpful;
    @Column
    private Integer not_helpful;
}
