package com.ygy.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LombokTestDataTest {

    @Test
    public void testDataTest(){
        TestData testData = new TestData();
        testData.setName("ygy");

        Assertions.assertEquals("ygy",testData.getName());

    }
}
