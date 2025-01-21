package com.example.a7.model.expressions;

public enum AritmeticalOperator {
    ADD {
        @Override
        public String toString() {
            return "+";
        }
    },
    SUBTRACT {
        @Override
        public String toString() {
            return "-";
        }
    },
    MULTIPLY {
        @Override
        public String toString() {
            return "*";
        }
    },
    DIVIDE {
        @Override
        public String toString() {
            return "/";
        }
    }
}
