package com.maesgik.sample.airwallex.operator;

import com.maesgik.sample.airwallex.RPNStackOperation;

import java.util.Stack;

public class RPNLoadOperator extends RPNAbstractOperator {

    private double load;

    public RPNLoadOperator(double load, Stack<Double> operandStack, Stack<RPNAbstractOperator> history) {
        super(operandStack, history);
        this.load = load;
    }

    @Override
    public void perform() {
        // Push to stack
        operandStack.push(load);
        // Push to history to prepare for undo operation
        this.stackOperations.add(new RPNStackOperation(RPNStackOperation.Action.PUSH, load));
        history.push(this);
    }
}


