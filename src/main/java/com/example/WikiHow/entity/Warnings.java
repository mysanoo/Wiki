package com.example.WikiHow.entity;

import com.example.WikiHow.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Warnings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @Column
    private String text;
    @Column
    private int helpful;
    @Column
    private int notHelpful;
}
