package com.example.a7.model.value;

import com.example.a7.model.types.MyBoolType;
import com.example.a7.model.types.MyIType;

public class MyBoolValue implements MyIValue {
    private boolean value;

    public MyBoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(MyIValue other) {
        return other instanceof MyBoolValue && ((MyBoolValue) other).value == this.value;
    }

    @Override
    public MyIType getType() {
        return new MyBoolType();
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
