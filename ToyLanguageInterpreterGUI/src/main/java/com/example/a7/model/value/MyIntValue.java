package com.example.a7.model.value;

import com.example.a7.model.types.MyIType;
import com.example.a7.model.types.MyIntType;

public class MyIntValue implements MyIValue {
    private int value;

    public MyIntValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(MyIValue other) {
        return other instanceof MyIntValue && ((MyIntValue) other).value == this.value;
    }

    @Override
    public MyIType getType() {
        return new MyIntType();
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
