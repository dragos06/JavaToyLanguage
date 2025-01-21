package com.example.a7.model.types;

import com.example.a7.model.value.MyBoolValue;
import com.example.a7.model.value.MyIValue;

public class MyBoolType implements MyIType {
    @Override
    public boolean equals(MyIType object) {
        return object instanceof MyBoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public MyIValue getDefaultValue() {
        return new MyBoolValue(false);
    }

    @Override
    public MyIType deepCopy() {
        return new MyBoolType();
    }
}
