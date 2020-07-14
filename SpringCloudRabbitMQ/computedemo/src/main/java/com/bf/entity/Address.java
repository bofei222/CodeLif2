package com.bf.entity;

/**
 * @author bofei
 * @date 2018/7/31 9:56
 */
public class Address {
    String first;
    String last;

    public Address(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
