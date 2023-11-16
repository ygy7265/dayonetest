package com.ygy.dayonetest.service;


import com.ygy.dayonetest.MyCalculator;
import com.ygy.dayonetest.model.*;
import com.ygy.dayonetest.repository.StudentFailRepository;
import com.ygy.dayonetest.repository.StudentPassRepository;
import com.ygy.dayonetest.repository.StudentScoreRepository;
import com.ygy.dayonetest.response.ExamFailStudentResponse;
import com.ygy.dayonetest.response.ExamPassStudentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

public class StudentScoreServiceMockTest {

    private StudentScoreService studentScoreService;
    private StudentScoreRepository studentScoreRepository;
    private StudentPassRepository studentPassRepository;
    private StudentFailRepository studentFailRepository;

    @BeforeEach
    public void beforeEach(){
        studentScoreService = new StudentScoreService(
                studentScoreRepository = Mockito.mock(StudentScoreRepository.class),
                studentPassRepository = Mockito.mock(StudentPassRepository.class),
                studentFailRepository = Mockito.mock(StudentFailRepository.class)
        );
    }

    @Test
    @DisplayName("First Mock Test")
    public void firstSaveScoreMockTest(){
        String givenStudentName = "ygy";
        String givenExam = "testExam";
        Integer givenKorScore = 90;
        Integer givenEnglishScore = 100;
        Integer givenMathScore = 70;

        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );

    }

    @Test
    @DisplayName("성적저장 로직/평균 60점 이상")
    public void saveScoreMockTest(){
        //given
        StudentScore expectStudentScore = StudentScoreTestDataBuilder.passed().build();
        StudentPass expcetStudentPass = StudentPassFixture.create(expectStudentScore);

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass.class);

        //when
        studentScoreService.saveScore(
                expectStudentScore.getStudentName(),
                expectStudentScore.getExam(),
                expectStudentScore.getKorScore(),
                expectStudentScore.getEnglishScore(),
                expectStudentScore.getMathScore()
        );

        Mockito.verify(studentScoreRepository,Mockito.times(1)).save(studentScoreArgumentCaptor.capture());
        StudentScore captureStudentScore = studentScoreArgumentCaptor.getValue();
        Assertions.assertEquals(expectStudentScore.getStudentName(),captureStudentScore.getStudentName());
        Assertions.assertEquals(expectStudentScore.getExam(),captureStudentScore.getExam());
        Assertions.assertEquals(expectStudentScore.getKorScore(),captureStudentScore.getKorScore());
        Assertions.assertEquals(expectStudentScore.getEnglishScore(),captureStudentScore.getEnglishScore());
        Assertions.assertEquals(expectStudentScore.getMathScore(),captureStudentScore.getMathScore());

        //then
        Mockito.verify(studentPassRepository,Mockito.times(1)).save(studentPassArgumentCaptor.capture());
        Mockito.verify (studentFailRepository,Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("합격자명단 가져오기")
    public void getPassStudentsListTest(){

        String givenTestExam = "testexam";

        StudentPass expectStudent1 =  StudentPassFixture.create("ygy",givenTestExam);
        StudentPass expectStudent2 =  StudentPassFixture.create("Test2",givenTestExam);
        StudentPass notExpectStudent3 = StudentPassFixture.create("anotherPeople","anotherTest");
        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
                expectStudent1,
                expectStudent2,
                notExpectStudent3
        ));


        var expectResponse = List.of(expectStudent1,expectStudent2)
                .stream()
                .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(),pass.getAvgScore()))
                .toList();
        List<ExamPassStudentResponse> responses= studentScoreService.getPassStudentsList(givenTestExam);
        responses.forEach((response)-> System.out.println(response.getStudentName() + " " + response.getAvgScore()));
        System.out.println("==================");
        expectResponse.forEach((response)-> System.out.println(response.getStudentName() + " " + response.getAvgScore()));

        Assertions.assertIterableEquals(expectResponse,responses);
    }

    @Test
    @DisplayName("불합격자명단 가져오기")
    public void getFailStudentsListTest(){

        String givenTestExam = "testexam";
        StudentFail notExpectStudent1 =  StudentFail.builder().id(1L).studentName("ygy").exam("ygy").avgScore(90.0).build();
        StudentFail expectStudent2 =  StudentFail.builder().id(2L).studentName("test").exam(givenTestExam).avgScore(40.0).build();
        StudentFail expectStudent1 =  StudentFail.builder().id(3L).studentName("iamnot").exam(givenTestExam).avgScore(50.0).build();
        Mockito.when(studentFailRepository.findAll()).thenReturn(List.of(
                expectStudent1,
                notExpectStudent1,
                expectStudent2
        ));

        var expectResponse = List.of(expectStudent1,expectStudent2)
                .stream()
                .map((fail) -> new ExamFailStudentResponse(fail.getStudentName(),fail.getAvgScore()))
                .toList();
        List<ExamFailStudentResponse> responses= studentScoreService.getFailStudentsList(givenTestExam);
        responses.forEach((response)-> System.out.println(response.getStudentName() + " " + response.getAvgScore()));
        System.out.println("==================");
        expectResponse.forEach((response)-> System.out.println(response.getStudentName() + " " + response.getAvgScore()));

        Assertions.assertIterableEquals(expectResponse,responses);
    }

}
