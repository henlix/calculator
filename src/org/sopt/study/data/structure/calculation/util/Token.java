package org.sopt.study.data.structure.calculation.util;

public class Token {

    public TokenType type = TokenType.UNDEFINED;
    public Object value;

    public Token() {}
    public Token(TokenType type, Object value) {

        this.type = type;
        this.value = value;
    }

    public boolean isOperand() { return this.type == TokenType.OPERAND; }
    public boolean isOperator() {

        return this.type == TokenType.OPERATOR_ADD || this.type == TokenType.OPERATOR_SUB || this.type == TokenType.OPERATOR_MUL || this.type == TokenType.OPERATOR_DIV || this.type == TokenType.OPERATOR_MOD;
    }

    public int isp() {

        switch (type) {

            case BRACKET_RIGHT: return 0;

            case OPERATOR_MUL:
            case OPERATOR_DIV:
            case OPERATOR_MOD:  return 1;

            case OPERATOR_ADD:
            case OPERATOR_SUB:  return 2;

            case BRACKET_LEFT:
            case SEPERATOR:     return 3;
        }

        return -1;
    }

    public int icp() {

        switch (type) {

            case BRACKET_LEFT:
            case BRACKET_RIGHT: return 0;

            case OPERATOR_MUL:
            case OPERATOR_DIV:
            case OPERATOR_MOD:  return 1;

            case OPERATOR_ADD:
            case OPERATOR_SUB:  return 2;

            case SEPERATOR:     return 3;
        }

        return -1;
    }

    @Override
    public String toString() {

        return "Type : " + type + " | Value : " + value;
    }
}
