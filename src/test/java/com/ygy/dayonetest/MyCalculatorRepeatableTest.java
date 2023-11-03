package com.ygy.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MyCalculatorRepeatableTest {

    @RepeatedTest(5)
    public void addTest() {
        //AAA 패턴

        //Arrange - 준비
        MyCalculator myCalculator = new MyCalculator();

        //Act - 행동
        myCalculator.add(10.0);

        //Assert - 검증
        Assertions.assertEquals(10.0,myCalculator.getResult());
    }

    @DisplayName("Parameter Test")
    @ParameterizedTest
    @MethodSource("parameterizedTestParameters")
    public void parameterizedTest(Double addValue,Double expectValue){
        MyCalculator myCalculator = new MyCalculator(0.0);

        //Act - 행동
        myCalculator.add(addValue);

        //Assert - 검 증
        Assertions.assertEquals(expectValue,myCalculator.getResult());
    }

    public static Stream<Arguments> parameterizedTestParameters(){
        return Stream.of(
                Arguments.of(10.0,10.0),
                Arguments.of(8.0,8.0),
                Arguments.of(4.0,4.0),
                Arguments.of(2.0,2.0)
        );
    }

    @DisplayName("파라미터 테스트")
    @MethodSource("parameterizedComplicateCalculatorTestParameter")
    @ParameterizedTest
    public void parameterizedComplicateCalculator(
            Double addValue,
            Double minusValue,
            Double multipleValue,
            Double divideValue,
            Double expectValue
    ){
        MyCalculator myCalculator = new MyCalculator(0.0);
        //when
        Double result =  myCalculator
                .add(addValue)
                .minus(minusValue)
                .multiply(multipleValue)
                .divide(divideValue)
                .getResult();
        //thenss
        Assertions.assertEquals(expectValue,result);
    }

    public static Stream<Arguments> parameterizedComplicateCalculatorTestParameter(){
        return Stream.of(
                Arguments.of(10.0,4.0,2.0,3.0,4.0),
                Arguments.of(4.0,2.0,4.0,4.0,2.0)
        );
    }
}
