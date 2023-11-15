package com.ygy.dayonetest.controller;

import com.ygy.dayonetest.request.SaveExamScoreRequest;
import com.ygy.dayonetest.response.ExamFailStudentResponse;
import com.ygy.dayonetest.response.ExamPassStudentResponse;
import com.ygy.dayonetest.service.StudentScoreService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
public class ScoreApi {

    private final StudentScoreService studentScoreService;

    @PutMapping("/exam/{exam}/score")
    public void save(@RequestBody SaveExamScoreRequest request, @PathVariable String exam){
        studentScoreService.saveScore(request.getStudentName(),exam,request.getKorScore(),request.getEnglishScore(),request.getMathScore());

    }

    @GetMapping("/exam/{exam}/pass")
    public List<ExamPassStudentResponse> pass(@PathVariable String exam){


        return studentScoreService.getPassStudentsList(exam);
    }

    @GetMapping("/exam/{exam}/fail")
    public List<ExamFailStudentResponse> fail(@PathVariable("exam") String exam){
        return studentScoreService.getFailStudentsList(exam);
    }


}
