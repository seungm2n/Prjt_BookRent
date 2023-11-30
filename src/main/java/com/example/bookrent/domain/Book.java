package com.example.bookrent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookid;

    private String title;

    private String author;

    private LocalDate regDate;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Rent> rentList;

    private boolean state;
}
