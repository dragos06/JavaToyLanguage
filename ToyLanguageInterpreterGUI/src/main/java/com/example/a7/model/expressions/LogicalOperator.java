package com.example.a7.model.expressions;

public enum LogicalOperator {
    AND{
        @Override
        public String toString() {
            return "&&";
        }
    },
    OR{
        @Override
        public String toString() {
            return "||";
        }
    }
}
