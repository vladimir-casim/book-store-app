package com.library.domain;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Book book;

    @Column(name = "value")
    private int value;

    @Column(name = "username")
    private String username;

    public Vote() {
    }

}
