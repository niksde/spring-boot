package com.luv2code.tdd;

public class FizzBuzz {

    public static String compute(int number) {
        StringBuilder result = new StringBuilder();
        if(number%3 == 0)
            result.append("Fizz");

        if(number%5 == 0)
            result.append("Buzz");

        if(result.isEmpty())
            result.append(number);

        return result.toString();
    }
//    public static String compute(int number) {
//        String result = "";
//        if(number%3 != 0 && number%5 != 0)
//            return Integer.toString(number);
//        if(number%3 == 0)
//            result += "Fizz";
//        if(number%5 == 0)
//            result += "Buzz";
//        return result;
//    }
}
