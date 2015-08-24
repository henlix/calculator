package org.sopt.study.data.structure.calculation.impl;


import org.sopt.study.data.structure.calculation.util.Analyzer;
import org.sopt.study.data.structure.calculation.util.Token;
import org.sopt.study.data.structure.calculation.util.TokenType;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

    public static float calculate(String str) {

        Analyzer analyzer = new Analyzer();

        analyzer.load(str);
        analyzer.analyze();

        ArrayList<Token> postfix = analyzer.postfix();
        Stack<Token> stack = new Stack<>();

        for (Token token : postfix) {

            if (token.isOperand()) {

                stack.push(token);
            }
            else {

                Token operand1 = stack.pop();
                Token operand2 = stack.pop();

                Token result = operate(operand1, operand2, token);
                stack.push(result);
            }
        }

        return (float) stack.pop().value;
    }

    private static Token operate(Token operand1, Token operand2, Token operator) {

        Token token = new Token(TokenType.OPERAND, 0);

        if ((operator.type == TokenType.OPERATOR_DIV || operator.type == TokenType.OPERATOR_MOD) && (float) operand1.value == 0)
            throw new IllegalArgumentException("Cannot do divide or mode operation by 0.");

        switch (operator.type) {

            case OPERATOR_ADD: token.value = (float) operand2.value + (float) operand1.value; break;
            case OPERATOR_SUB: token.value = (float) operand2.value - (float) operand1.value; break;

            case OPERATOR_MUL: token.value = (float) operand2.value * (float) operand1.value; break;
            case OPERATOR_DIV: token.value = (float) operand2.value / (float) operand1.value; break;
            case OPERATOR_MOD: token.value = (float) operand2.value % (float) operand1.value; break;
        }

        return token;
    }
}