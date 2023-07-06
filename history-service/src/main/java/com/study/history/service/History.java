package com.study.history.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long memberId;
    @Column(nullable = false)
    private long bookId;
    @Column(nullable = false)
    private int quantity;
}
