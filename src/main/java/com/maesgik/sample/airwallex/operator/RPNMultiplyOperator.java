package com.maesgik.sample.airwallex.operator;

import com.maesgik.sample.airwallex.RPNStackOperation;
import com.maesgik.sample.airwallex.exception.RPNExecutionException;

import java.util.Stack;

public class RPNMultiplyOperator extends RPNAbstractOperator {

    public RPNMultiplyOperator(Stack<Double> operandStack, Stack<RPNAbstractOperator> history) {
        super(operandStack, history);
    }

    @Override
    public void perform() throws RPNExecutionException {
        if (this.operandStack.size() < 2) {
            throw new RPNExecutionException("insufficient parameters");
        } else {
            // Push to stack
            double secondOperand = operandStack.pop();
            double firstOperand = operandStack.pop();
            double result = firstOperand * secondOperand;
            operandStack.push(result);
            // Push to history to prepare for undo operation
            this.stackOperations.add(new RPNStackOperation(RPNStackOperation.Action.POP, secondOperand));
            this.stackOperations.add(new RPNStackOperation(RPNStackOperation.Action.POP, firstOperand));
            this.stackOperations.add(new RPNStackOperation(RPNStackOperation.Action.PUSH, result));
            history.push(this);
        }
    }
}
