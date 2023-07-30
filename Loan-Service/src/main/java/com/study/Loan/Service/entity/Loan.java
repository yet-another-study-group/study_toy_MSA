package com.study.Loan.Service.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long userId;
    @Column(nullable = false)
    private long bookId;
}
