package com.example.bolteco.ModeClass;

public class Address_mode_class {
     String address;
     boolean isSelected;


    public Address_mode_class() {
    }

    public Address_mode_class(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address_mode_class{" +
                "address='" + address + '\'' +
                '}';
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
