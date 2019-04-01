package com.maesgik.sample.airwallex;

import com.maesgik.sample.airwallex.exception.RPNExecutionException;
import com.maesgik.sample.airwallex.operator.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Stack;

public class RPNCalculator {

    private Stack<Double> operandStack = new Stack<>();
    private Stack<RPNAbstractOperator> history = new Stack<>();

    public RPNCalculator() {}

    public void parse(String input) {
        String[] itemArray = input.split(" ");
        int position = 1;
        for (String item : itemArray) {
            RPNAbstractOperator operator = null;
            switch (item) {
                case "":
                    break;
                case "+":
                    operator = new RPNAddOperator(operandStack, history);
                    break;
                case "-":
                    operator = new RPNSubtractOperator(operandStack, history);
                    break;
                case "*":
                    operator = new RPNMultiplyOperator(operandStack, history);
                    break;
                case "/":
                    operator = new RPNDivideOperator(operandStack, history);
                    break;
                case "sqrt":
                    operator = new RPNSqrtOperator(operandStack, history);
                    break;
                case "undo":
                    operator = new RPNUndoOperator(operandStack, history);
                    break;
                case "clear":
                    operator = new RPNClearOperator(operandStack, history);
                    break;
                default: {
                    double realNumber = 0;
                    try {
                        realNumber = Double.valueOf(item);
                    } catch (Exception e) {
                        printError(item, position, new RPNExecutionException("unable to parse"));
                        return;
                    }
                    operator = new RPNLoadOperator(realNumber, operandStack, history);
                    break;
                }
            }
            if (operator != null) {
                try {
                    operator.perform();
                } catch (Exception e) {
                    printError(item, position, e);
                    return;
                }
            }
            position += (item.length() + 1);
        }
        printStack();
    }

    public void printStack() {
        Stack<Double> reverseStack = new Stack<>();
        for (double d : operandStack) {
            reverseStack.push(d);
        }

        System.out.print("stack: ");
        for (double reverse : reverseStack) {
            DecimalFormat df = new DecimalFormat("0.##########");
            df.setRoundingMode(RoundingMode.DOWN);
            System.out.print(df.format(reverse) + " ");
        }
        System.out.println();
    }

    public void printError(String operator, int position, Exception e) {
        System.err.format("operator %s (position: %d): %s\n", operator, position, e.getMessage());
        printStack();
    }

}
