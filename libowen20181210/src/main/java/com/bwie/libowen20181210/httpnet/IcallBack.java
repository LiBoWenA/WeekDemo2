package com.bwie.libowen20181210.httpnet;

public interface IcallBack {
    void fails(Exception e);
    void sucess(Object data);
}
