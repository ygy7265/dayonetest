package com.ygy.dayonetest.repository;

import com.ygy.dayonetest.model.StudentPass;
import com.ygy.dayonetest.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScore,Long> {
}
