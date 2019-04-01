package com.maesgik.sample.airwallex;

import java.util.Stack;

public class RPNStackOperation {
    public enum Action {
        PUSH, POP
    }

    private Action action;
    private double load;

    public RPNStackOperation(Action action, double load) {
        this.action = action;
        this.load = load;
    }

    public void undo(Stack<Double> operandStack) {
        if (action == Action.POP) {
            operandStack.push(load);
        } else if (action == Action.PUSH)
            operandStack.pop();
    }
}

