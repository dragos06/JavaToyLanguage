package com.example.a7.model.types;

import com.example.a7.model.value.MyIValue;

public interface MyIType {
    boolean equals(MyIType object);

    MyIValue getDefaultValue();

    String toString();

    MyIType deepCopy();
}
