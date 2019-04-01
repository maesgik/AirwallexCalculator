package com.maesgik.sample.airwallex.operator;

import com.maesgik.sample.airwallex.RPNStackOperation;
import com.maesgik.sample.airwallex.exception.RPNExecutionException;

import java.util.Stack;

public abstract class RPNAbstractOperator {

    protected Stack<RPNStackOperation> stackOperations = new Stack<>();

    protected Stack<Double> operandStack;
    protected Stack<RPNAbstractOperator> history;

    public RPNAbstractOperator(Stack<Double> operandStack, Stack<RPNAbstractOperator> history) {
        this.operandStack = operandStack;
        this.history = history;
    }

    public void undo() {
        while (stackOperations.size() > 0) {
            RPNStackOperation last = stackOperations.pop();
            last.undo(operandStack);
        }
    }

    public abstract void perform() throws RPNExecutionException;
}
