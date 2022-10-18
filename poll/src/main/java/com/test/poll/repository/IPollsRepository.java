package com.test.poll.repository;

import com.test.poll.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPollsRepository extends JpaRepository<Poll, Integer> {
    Poll findByIdEquals(Integer id);
}
