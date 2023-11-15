package com.ygy.dayonetest.service;

import com.ygy.dayonetest.MyCalculator;
import com.ygy.dayonetest.model.StudentFail;
import com.ygy.dayonetest.model.StudentPass;
import com.ygy.dayonetest.model.StudentScore;
import com.ygy.dayonetest.repository.StudentFailRepository;
import com.ygy.dayonetest.repository.StudentPassRepository;
import com.ygy.dayonetest.repository.StudentScoreRepository;
import com.ygy.dayonetest.response.ExamFailStudentResponse;
import com.ygy.dayonetest.response.ExamPassStudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentScoreService {

    private final StudentScoreRepository studentScoreRepository;
    private final StudentPassRepository studentPassRepository;
    private final StudentFailRepository studentFailRepository;
    public void saveScore(String studentName,String exam,Integer korScore,Integer englishScore,Integer mathScore){
        StudentScore studentScore = StudentScore.builder()
                .exam(exam)
                .studentName(studentName)
                .korScore(korScore)
                .englishScore(englishScore)
                .mathScore(mathScore)
                .build();

        studentScoreRepository.save(studentScore);

        MyCalculator calculator = new MyCalculator(0.0);
        Double avgScore = calculator
                .add(korScore.doubleValue())
                .add(englishScore.doubleValue())
                .add(mathScore.doubleValue())
                .divide(3.0)
                .getResult();

        if(avgScore >= 60){
            StudentPass studentPass = StudentPass.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build();
            studentPassRepository.save(studentPass);
        }else{
            StudentFail studentFail = StudentFail.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build();
            studentFailRepository.save(studentFail);
        }
    }

    public List<ExamPassStudentResponse> getPassStudentsList(String exam){
        List<StudentPass> studentPasses = studentPassRepository.findAll();

        return studentPasses.stream()
                .filter((pass) -> pass.getExam().equals(exam))
                .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(),pass.getAvgScore()))
                .toList();
    }

    public List<ExamFailStudentResponse> getFailStudentsList(String exam){
        List<StudentFail> studentFails = studentFailRepository.findAll();

        return studentFails.stream()
                .filter((fail) -> fail.getExam().equals(exam))
                .map((fail)-> new ExamFailStudentResponse(fail.getStudentName(),fail.getAvgScore()))
                .toList();
    }
}
