package com.ygy.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorTest {

    @Test
    void addTest() {
        //AAA 패턴

        //Arrange - 준비
        MyCalculator myCalculator = new MyCalculator();

        //Act - 행동
        myCalculator.add(10.0);

        //Assert - 검증
        Assertions.assertEquals(10.0,myCalculator.getResult());
    }

    @Test
    void minus() {
        //Given
        MyCalculator myCalculator = new MyCalculator(10.0);

        //When - 행동 - Act
        myCalculator.minus(5.0);

        //Then - 검증/비교 /단언 - Assert
        Assertions.assertEquals(5.0,myCalculator.getResult());
    }

    @Test
    void multiply() {

        //given
        MyCalculator myCalculator = new MyCalculator(2.0);
        //when
        myCalculator.multiply(2.0);
        //then
        Assertions.assertEquals(4.0,myCalculator.getResult());
    }

    @Test
    void divide() {
        MyCalculator myCalculator = new MyCalculator(8.0);
        myCalculator.multiply(2.0);
        Assertions.assertEquals(4.0,myCalculator.getResult());
    }

    @Test
    void complicatedCalculatorTest(){
        //given
        MyCalculator myCalculator = new MyCalculator(0.0);


        //when
        Double result =  myCalculator
                                .add(10.0)
                                .minus(4.0)
                                .multiply(2.0)
                                .divide(3.0)
                                .getResult();
        //then
        Assertions.assertEquals(4.0,result);
    }

    @Test
    void divideZeroTest(){
        //given
        MyCalculator myCalculator  = new MyCalculator(10.0);

        //when
        Assertions.assertThrows(MyCalculator.ZeroDivisionException.class, ()->{
            myCalculator.divide(0.0);
        });
        //then
    }
}