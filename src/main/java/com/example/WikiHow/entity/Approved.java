package com.example.WikiHow.entity;

import com.example.WikiHow.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Approved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User approvedBy;
    @Column
    private String why;
    @Column
    private Timestamp time;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Post post;

}
