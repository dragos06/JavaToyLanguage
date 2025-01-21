package com.example.a7.model.value;

import com.example.a7.model.types.MyIType;

public interface MyIValue {
    MyIType getType();

    boolean equals(MyIValue other);

    String toString();
}
