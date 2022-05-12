package com.axmor.uitest.framework.core;

import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class PageCore {
    public static <T> void fromModel(Class<T> pageClass, Object model) throws IllegalAccessException, InvocationTargetException {
        var pageProps = new HashMap<String, Method>();
        for(var m : pageClass.getMethods()) {
            var methodName = m.getName().toLowerCase();
            if(!methodName.endsWith("field")) {
                // processing only methods named like 'someField' where 'some' - is a target field
                continue;
            }

            var propName = methodName.substring(0, methodName.length() - 5);
            pageProps.put(propName, m);
        }

        for(var f : model.getClass().getDeclaredFields()) {
            var fieldName = f.getName().toLowerCase();
            if(!pageProps.containsKey(fieldName)) {
                // there is no corresponding field in PageClass
                continue;
            }

            f.setAccessible(true);
            var value = (String)f.get(model);
            if(value == null) {
                // skip props with null value
                continue;
            }

            var prop = pageProps.get(fieldName);
            var webElem = (WebElement)prop.invoke(null);
            PageElementCore pageElem;
            if(webElem instanceof PageElementCore) {
                pageElem = (PageElementCore) webElem;
            } else {
                pageElem = new PageElementCore(webElem);
            }
            pageElem.setModelValue(value);

        }
    }


    public static <T> void toModel(Class<T> pageClass, Object model) throws InvocationTargetException, IllegalAccessException {
        var pageProps = new HashMap<String, Method>();
        for (var m : pageClass.getMethods()) {
            var methodName = m.getName().toLowerCase();
            if (!methodName.endsWith("field")) {
                // processing only methods named like 'someField' where 'some' - is a target field
                continue;
            }

            var propName = methodName.substring(0, methodName.length() - 5);
            pageProps.put(propName, m);
        }

        for (var f : model.getClass().getDeclaredFields()) {
            var fieldName = f.getName().toLowerCase();
            if (!pageProps.containsKey(fieldName)) {
                // there is no corresponding field in PageClass
                continue;
            }

            var prop = pageProps.get(fieldName);
            var webElem = (WebElement) prop.invoke(null);
            PageElementCore pageElem;
            if (webElem instanceof PageElementCore) {
                pageElem = (PageElementCore) webElem;
            } else {
                pageElem = new PageElementCore(webElem);
            }
            var value = pageElem.getModelValue();


            f.setAccessible(true);
            f.set(model, value);
        }
    }

}
