package com.mycompany.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "books")
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, unique = true, length = 100)
    private String title;

    @Column (nullable = false, length = 45)
    private String author;

    @Column (nullable = false, length = 45)
    private String genre;
}
