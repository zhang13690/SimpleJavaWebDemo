package com.webdemo.util;

import org.apache.commons.beanutils.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SimpleDateConverter implements Converter {
    @Override
    public <T> T convert(Class<T> aClass, Object obj) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return (T) df.parse(obj.toString());
        } catch (ParseException e) {
            return null;
        }
    }
}
