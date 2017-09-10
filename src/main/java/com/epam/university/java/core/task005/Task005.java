package com.epam.university.java.core.task005;

/**
 * Computations.
 */
public interface Task005 {
    /**
     * Find two numbers, quotient of which will be closest to PI number. Ex. if digit is 1,
     * holder values should be between 1 and 9, if digits is equals to 2, values should
     * be between 10 and 99 and so on.
<<<<<<< HEAD
     *
     * Tip: Math.abs((a / b) - Math.PI) -> min
=======
     * <p>
     *     Tip: Math.abs((a / b) - Math.PI) -> min
     * </p>
>>>>>>> 4e9da890e062f06720ea99b50acb0cae9aee500a
     *
     * @param digits amount of digits in numbers.
     * @return holder which contains both numbers
     * @throws IllegalArgumentException if digits less or equals to the zero, or more than ten
     */
    PiHolder findPi(int digits);
}
