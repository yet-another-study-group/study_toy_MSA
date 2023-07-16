package com.study.history.service.repository;

import com.study.history.service.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUserId(long userId);

    List<History> findAllByBookId(long bookId);
}
