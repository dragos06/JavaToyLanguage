package com.example.a7.model.types;

import com.example.a7.model.value.MyIValue;
import com.example.a7.model.value.MyIntValue;

public class MyIntType implements MyIType {
    @Override
    public boolean equals(MyIType object) {
        return object instanceof MyIntType;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public MyIValue getDefaultValue() {
        return new MyIntValue(0);
    }

    @Override
    public MyIType deepCopy() {
        return new MyIntType();
    }
}
