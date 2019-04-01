package com.maesgik.sample.airwallex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class RPNCalculatorTest {

    private RPNCalculator calculator;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public RPNCalculatorTest() {}

    @Before
    public void initialize() {
        calculator = new RPNCalculator();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanup() {
        calculator = null;
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void example1() {
        calculator.parse("5 2");
        assertEquals("stack: 5 2", outContent.toString().trim());
        outContent.reset();
    }

    @Test
    public void example2() {
        calculator.parse("2 sqrt");
        assertEquals("stack: 1.4142135623", outContent.toString().trim());
        outContent.reset();

        calculator.parse("clear 9 sqrt");
        assertEquals("stack: 3", outContent.toString().trim());
        outContent.reset();
    }

    @Test
    public void example3() {
        calculator.parse("5 2 -");
        assertEquals("stack: 3", outContent.toString().trim());
        outContent.reset();

        calculator.parse("3 -");
        assertEquals("stack: 0", outContent.toString().trim());
        outContent.reset();

        calculator.parse("clear");
        assertEquals("stack:", outContent.toString().trim());
        outContent.reset();
    }

    @Test
    public void example4() {
        calculator.parse("5 4 3 2");
        assertEquals("stack: 5 4 3 2", outContent.toString().trim());
        outContent.reset();

        calculator.parse("undo undo *");
        assertEquals("stack: 20", outContent.toString().trim());
        outContent.reset();

        calculator.parse("5 *");
        assertEquals("stack: 100", outContent.toString().trim());
        outContent.reset();

        calculator.parse("undo");
        assertEquals("stack: 20 5", outContent.toString().trim());
        outContent.reset();
    }

    @Test
    public void example5() {
        calculator.parse("7 12 2 /");
        assertEquals("stack: 7 6", outContent.toString().trim());
        outContent.reset();

        calculator.parse("*");
        assertEquals("stack: 42", outContent.toString().trim());
        outContent.reset();

        calculator.parse("4 /");
        assertEquals("stack: 10.5", outContent.toString().trim());
        outContent.reset();
    }

    @Test
    public void example6() {
        calculator.parse("1 2 3 4 5");
        assertEquals("stack: 1 2 3 4 5", outContent.toString().trim());
        outContent.reset();

        calculator.parse("*");
        assertEquals("stack: 1 2 3 20", outContent.toString().trim());
        outContent.reset();

        calculator.parse("clear 3 4 -");
        assertEquals("stack: -1", outContent.toString().trim());
        outContent.reset();
    }

    @Test
    public void example7() {
        calculator.parse("1 2 3 4 5");
        assertEquals("stack: 1 2 3 4 5", outContent.toString().trim());
        outContent.reset();

        calculator.parse("* * * *");
        assertEquals("stack: 120", outContent.toString().trim());
        outContent.reset();
    }

    @Test
    public void example8() {
        calculator.parse("1 2 3 * 5 + * * 6 5");
        String a = errContent.toString();
        assertEquals("operator * (position: 15): insufficient parameters",
                errContent.toString().trim());
        assertEquals("stack: 11", outContent.toString().trim());
        outContent.reset();
        errContent.reset();
    }

}
