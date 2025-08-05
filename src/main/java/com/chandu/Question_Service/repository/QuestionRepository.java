package com.chandu.Question_Service.repository;

import com.chandu.Question_Service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);

    @Query(value = """
    SELECT q.id
    FROM question q
    WHERE category = :category
    ORDER BY RANDOM()
    LIMIT :numOfQuestions
    """, nativeQuery = true)
    List<Long> findRandomQAuestionsByCategory(String category, int numOfQuestions);
}