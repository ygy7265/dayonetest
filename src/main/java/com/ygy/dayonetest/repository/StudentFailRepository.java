package com.ygy.dayonetest.repository;

import com.ygy.dayonetest.model.StudentFail;
import com.ygy.dayonetest.model.StudentPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentFailRepository extends JpaRepository<StudentFail,Long> {
}
