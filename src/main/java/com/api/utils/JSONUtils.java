package com.api.utils;

import com.google.gson.Gson;

public class JSONUtils {

    private static final Gson gson = new Gson();

    public static String serialize(Object obj) {
        return gson.toJson(obj);
    }



}

