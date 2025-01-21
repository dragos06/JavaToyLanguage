package com.example.a7.model.types;

import com.example.a7.model.value.MyIValue;
import com.example.a7.model.value.RefValue;

public class RefType implements MyIType {
    MyIType inner;

    public RefType(MyIType inner) {
        this.inner = inner;
    }

    public MyIType getInner() {
        return inner;
    }

    @Override
    public boolean equals(MyIType object) {
        return object instanceof RefType && this.inner.equals(((RefType) object).getInner());
    }

    @Override
    public MyIValue getDefaultValue() {
        return new RefValue(0, this.inner);
    }

    @Override
    public MyIType deepCopy() {
        return new RefType(this.inner.deepCopy());
    }

    @Override
    public String toString() {
        return "Ref(" + this.inner.toString() + ")";
    }
}
