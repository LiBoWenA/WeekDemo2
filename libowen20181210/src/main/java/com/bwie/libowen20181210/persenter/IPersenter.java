package com.bwie.libowen20181210.persenter;

import java.util.Map;

public interface IPersenter {
    void showRequestData(String path, Map<String, String> params, Class clazz);
}
