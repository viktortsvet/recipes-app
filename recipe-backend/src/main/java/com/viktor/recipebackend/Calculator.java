package com.viktor.recipebackend;

public class Calculator {

    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(recursiveFactorial(10));
        System.out.println(cycleFactorial(10));
    }

    private static int recursiveFactorial(int x) {
        if (x == 1) {
            return x;
        } else {
            return x * recursiveFactorial(x - 1);
        }
    }

    private static int cycleFactorial(int x) {
        int y = x;
        while (x > 1) {
            y *= (x - 1);
            x--;
        }
        return y;
    }

    public static int sub(int a, int b) {
        return a - b;
    }
}
