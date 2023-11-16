package com.ygy.dayonetest.model;

public class StudentScoreFixture {

    public static StudentScore passed(){
        return StudentScore
                .builder()
                .exam("defaultExam")
                .studentName("defaultName")
                .korScore(90)
                .englishScore(80)
                .mathScore(100)
                .build();
    }
}
