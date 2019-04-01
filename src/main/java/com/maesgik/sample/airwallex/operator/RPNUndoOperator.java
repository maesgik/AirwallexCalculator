package com.maesgik.sample.airwallex.operator;

import java.util.Stack;

public class RPNUndoOperator extends RPNAbstractOperator {

    public RPNUndoOperator(Stack<Double> operandStack, Stack<RPNAbstractOperator> history) {
        super(operandStack, history);
    }

    @Override
    public void perform() {
        if (history.size() > 0) {
            history.pop().undo();
        }
    }
}