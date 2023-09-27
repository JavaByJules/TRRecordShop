package com.example.trrecordshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    String artist;

    @Column
    String title;

    @Column
    int releaseYear;

    @Column
    @Enumerated (EnumType.STRING)
    Genre genre;

    @Column
    int quantity;

}
