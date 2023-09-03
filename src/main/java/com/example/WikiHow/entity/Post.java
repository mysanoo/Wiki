package com.example.WikiHow.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User creator;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDateTime timestamp = LocalDateTime.now();

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> references = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToMany(targetEntity = Tag.class,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER,mappedBy = "posts")
    private Set<Tag> tags;
}
