package com.maesgik.sample.airwallex.operator;

import java.util.Stack;

public class RPNClearOperator extends RPNAbstractOperator {

    public RPNClearOperator(Stack<Double> operandStack, Stack<RPNAbstractOperator> history) {
        super(operandStack, history);
    }

    @Override
    public void perform() {
        this.operandStack.clear();
        this.history.clear();
    }
}