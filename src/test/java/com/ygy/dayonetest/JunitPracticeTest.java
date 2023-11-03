package com.ygy.dayonetest;

import org.assertj.core.api.AbstractUrlAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JunitPracticeTest {

    @Test
    public void assretEqualsTest(){
        String except = "Something";
        String actual = "Something";

        Assertions.assertEquals(except, actual);
    }

    @Test
    public void assertNotEqaulsTest(){
        String except = "Something";
        String actual = "Hello";

        Assertions.assertNotEquals(except,actual);
    }

    @Test
    public void assertTrueTest(){
        Integer a = 10;
        Integer b = 10;
        Assertions.assertTrue(a.equals(b));
    }

    @Test
    public void assertFalseTest(){
        Integer a = 10;
        Integer b = 15;

        Assertions.assertFalse(a.equals(b));
    }

    @Test
    public void assertThrowsTest(){
        Assertions.assertThrows(RuntimeException.class,()->{
            throw new RuntimeException("Runtime");
        });
    }

    @Test
    public void assertNotNullTest(){
        int a = 3;
        Assertions.assertNotNull(a);
    }

    @Test
    public void assertNullTest(){
        String value = null;
        Assertions.assertNull(value);
    }

    @Test
    public void assertIterableEquals(){
        List<Integer> list1 = List.of(1,2);
        List<Integer> list2 = List.of(1,2);

        Assertions.assertIterableEquals(list1,list2);
    }

    @Test
    public void assertAllTest(){
        String except = "Something";
        String acturl = "Something";

        List<Integer> list = List.of(1,2);
        List<Integer> list2= List.of(1,2);

        Assertions.assertAll("Assert All", List.of(
                () -> {Assertions.assertEquals(except,acturl);},
                () -> {Assertions.assertIterableEquals(list,list2);}
        ));
    }
}
