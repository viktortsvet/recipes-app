package com.viktor.recipebackend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

    @Test
    public void addTest() {
        Assertions.assertEquals(15, Calculator.add(7, 8));
    }
}
