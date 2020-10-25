package com.epam.university.java.core.task053;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task053Impl implements Task053 {

    private static final char DELIMITER = ' ';
    private static final char OPEN = '(';
    private static final char CLOSE = ')';
    private static final String DIGIT_PATTERN = "(\\d+)";


    @Override
    public double calculate(String input) {

        if (input == null || input.length() < 3 || input.matches("\\s")) {
            throw new IllegalArgumentException();
        }
        if (!input.matches("[\\d-+*/()^]+")) {
            throw new IllegalArgumentException();
        }

        String rpn = getExpression(input);
        return counting(rpn);
    }

    private String getExpression(String input) {


        StringBuilder result = new StringBuilder();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < input.length(); i++) {

            if (Character.isDigit(input.charAt(i))) {

                Pattern pattern = Pattern.compile(DIGIT_PATTERN);
                Matcher matcher = pattern.matcher(input.substring(i));
                matcher.find();

                String number = matcher.group();

                result.append(number);
                result.append(DELIMITER);

                i = i + number.length() - 1;
            }

            if (isOperator(input.charAt(i))) {

                if (input.charAt(i) == OPEN) {
                    operators.push(input.charAt(i));

                } else if (input.charAt(i) == CLOSE) {
                    char currentOperator = operators.pop();

                    while (currentOperator != OPEN) {
                        result.append(currentOperator);
                        result.append(DELIMITER);

                        currentOperator = operators.pop();
                    }
                } else {
                    if (!operators.empty()
                            && getPriority(input.charAt(i))
                            <= getPriority(operators.peek())) {

                        result.append(operators.pop());
                        result.append(DELIMITER);
                    }
                    operators.push(input.charAt(i));
                }
            }
        }

        while (!operators.empty()) {
            result.append(operators.pop()).append(DELIMITER);
        }
        return result.toString();
    }

    private double counting(String input) {

        List<String> inputList = Arrays.asList(input.split(" "));
        Stack<Double> stack = new Stack<>();

        double result = 0;

        for (int i = 0; i < inputList.size(); i++) {

            if (inputList.get(i).matches(DIGIT_PATTERN)) {
                stack.push(Double.parseDouble(inputList.get(i)));

            } else {
                double a = stack.pop();
                double b = stack.pop();

                switch (inputList.get(i)) {
                    case "+":
                        result = b + a;
                        break;
                    case "-":
                        result = b - a;
                        break;
                    case "*":
                        result = b * a;
                        break;
                    case "/":
                        result = b / a;
                        break;
                    case "^":
                        result = Math.pow(b, a);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                stack.push(result);
            }
        }
        return stack.peek();
    }


    private boolean isOperator(char ch) {
        Set<Character> set = new HashSet<>();

        set.add('+');
        set.add('-');
        set.add('*');
        set.add('/');
        set.add('^');
        set.add(')');
        set.add('(');

        return set.contains(ch);
    }

    private int getPriority(char s) {

        switch (s) {
            case '(':
                return 0;
            case ')':
                return 1;
            case '+':
                return 2;
            case '-':
                return 3;
            case '*':
            case '/':
                return 4;
            case '^':
                return 5;
            default:
                return 6;
        }
    }
}
