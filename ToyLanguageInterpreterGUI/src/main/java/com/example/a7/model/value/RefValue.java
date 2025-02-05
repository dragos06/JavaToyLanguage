package com.example.a7.model.value;

import com.example.a7.model.types.MyIType;
import com.example.a7.model.types.RefType;

public class RefValue implements MyIValue {
    int address;
    MyIType locationType;

    public RefValue(int address, MyIType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public MyIType getLocationType() {
        return locationType;
    }

    public void setLocationType(MyIType locationType) {
        this.locationType = locationType;
    }

    @Override
    public boolean equals(MyIValue other) {
        return other instanceof RefValue && ((RefValue) other).getAddress() == this.address && ((RefValue) other).getLocationType().equals(this.locationType);
    }

    @Override
    public MyIType getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return "(" + this.address + ", " + this.locationType.toString() + ")";
    }

}
