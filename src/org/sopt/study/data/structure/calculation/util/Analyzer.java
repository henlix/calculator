package org.sopt.study.data.structure.calculation.util;

import java.util.ArrayList;
import java.util.Stack;

public class Analyzer {

    private ArrayList<Token> analyzed = new ArrayList<>();

    private String content;
    public void load(String content) { this.content = content; }

    public void analyze() {

        if (content == null)
            throw new IllegalStateException("Content cannot be null.");

        analyzed.clear();

        StringBuilder operand = new StringBuilder();

        for (char ch : content.toCharArray()) {

            if (ch == ' ')
                continue;

            if (Character.isDigit(ch) || ch == '.') {

                operand.append(ch);
                continue;
            }

            if (operand.length() > 0) {

                analyzed.add(new Token(TokenType.OPERAND, Float.valueOf(operand.toString())));
                operand.delete(0, operand.length());
            }

            analyzed.add(createNoneOperandToken(ch));
        }

        if (operand.length() > 0)
            analyzed.add(new Token(TokenType.OPERAND, Float.valueOf(operand.toString())));
    }

    private Token createNoneOperandToken(char ch) {

        TokenType type = TokenType.UNDEFINED;

        switch (ch) {

            case '(' : type = TokenType.BRACKET_LEFT; break;
            case ')' : type = TokenType.BRACKET_RIGHT; break;

            case '+' : type = TokenType.OPERATOR_ADD; break;
            case '-' : type = TokenType.OPERATOR_SUB; break;
            case '*' : type = TokenType.OPERATOR_MUL; break;
            case '/' : type = TokenType.OPERATOR_DIV; break;
            case '%' : type = TokenType.OPERATOR_MOD; break;
        }

        return new Token(type, ch);
    }


    public ArrayList<Token> postfix() {

        if (analyzed.size() == 0)
            throw new IllegalStateException("Cannot find analyzed result. Please analyze first.");

        Stack<Token> stack = new Stack<>();
        stack.push(new Token(TokenType.SEPERATOR, "#"));

        ArrayList<Token> result = new ArrayList<>();

        for (Token token : analyzed) {

            if (token.type == TokenType.OPERAND) {

                result.add(token);
            }
            else if (token.type == TokenType.BRACKET_LEFT) {

                stack.push(token);
            }
            else if (token.type == TokenType.BRACKET_RIGHT) {

                while (stack.peek().type != TokenType.BRACKET_LEFT)
                    result.add(stack.pop());

                stack.pop(); // pop '('
            }
            else {

                while (stack.peek().isp() <= token.icp())
                    result.add(stack.pop());

                stack.push(token);
            }
        }



        while (stack.peek().type != TokenType.SEPERATOR)
            result.add(stack.pop());

        return result;
    }
}