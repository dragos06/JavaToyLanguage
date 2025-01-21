package com.example.a7.model.expressions;

public enum RelationalOperator {
    LESS(){
        @Override
        public String toString(){
            return "<";
        }
    },
    LESS_EQUAL{
        @Override
        public String toString(){
            return "<=";
        }
    },
    EQUAL{
        @Override
        public String toString(){
            return "==";
        }
    },
    DIFFERENT{
        @Override
        public String toString(){
            return "!=";
        }
    },
    GREATER{
        @Override
        public String toString(){
            return ">";
        }
    },
    GREATER_EQUAL{
        @Override
        public String toString(){
            return ">=";
        }
    }
}
