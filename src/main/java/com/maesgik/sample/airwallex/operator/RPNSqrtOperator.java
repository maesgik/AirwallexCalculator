package com.maesgik.sample.airwallex.operator;

import com.maesgik.sample.airwallex.RPNStackOperation;
import com.maesgik.sample.airwallex.exception.RPNExecutionException;

import java.util.Stack;

public class RPNSqrtOperator extends RPNAbstractOperator {

    public RPNSqrtOperator(Stack<Double> operandStack, Stack<RPNAbstractOperator> history) {
        super(operandStack, history);
    }

    @Override
    public void perform() throws RPNExecutionException {
        if (this.operandStack.size() < 1) {
            throw new RPNExecutionException("insufficient parameters");
        } else {
            // Push to stack
            double firstOperand = operandStack.peek();
            if (firstOperand < 0) {
                throw new RPNExecutionException("sqrt on a negative number");
            }
            operandStack.pop();
            double result = Math.sqrt(firstOperand);
            operandStack.push(result);
            // Push to history to prepare for undo operation
            this.stackOperations.add(new RPNStackOperation(RPNStackOperation.Action.POP, firstOperand));
            this.stackOperations.add(new RPNStackOperation(RPNStackOperation.Action.PUSH, result));
            history.push(this);
        }
    }
}
