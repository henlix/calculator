package org.sopt.study.data.structure.calculation.util;

import java.util.ArrayList;

public class Analyzer {

    public ArrayList<Token> analyze(String str) {

        ArrayList<Token> tokens = new ArrayList<>();

        StringBuffer buffer = new StringBuffer();

        for (char ch : str.toCharArray()) {

            if (ch == ' ')
                continue;

            if (Character.isDigit(ch) || ch == '.') {

                buffer.append(ch);
                continue;
            }

            if (buffer.length() > 0) {

                tokens.add(new Token(TokenType.OPERAND, Float.valueOf(buffer.toString())));
                buffer.delete(0, buffer.length());
            }

            tokens.add(createNoneOperandToken(ch));
        }

        tokens.add(new Token(TokenType.SEPERATOR, "#"));
        return tokens;
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
}