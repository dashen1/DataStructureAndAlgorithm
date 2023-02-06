package com.example.generic_paradigm.generic;

public class NormalGeneric<T> {

    private T data;
    public NormalGeneric(){

    }


    public NormalGeneric(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData(){
        return data;
    }

}
