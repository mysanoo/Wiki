package com.example.WikiHow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.IntegralDataTypeHolder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String text;
    @Column
    private Integer helpful;
    @Column
    private Integer notHelpful;
    @ManyToOne
    private Question question;
    @ManyToOne
    private User users;
}
