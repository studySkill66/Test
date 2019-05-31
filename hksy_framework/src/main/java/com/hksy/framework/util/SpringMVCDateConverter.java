package com.hksy.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
public class SpringMVCDateConverter implements WebBindingInitializer
{
    @Override
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    }
}