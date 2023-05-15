package org.app.method.utils;

import org.app.ComplexMethod;
import org.app.method.AbstractMethod;
import org.app.method.MethodType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MethodLoader {
    JSONParser parser = new JSONParser();

    public ComplexMethod loadConfiguration(String filename) throws IOException, ParseException {
        JSONArray a = (JSONArray) parser.parse(new FileReader(filename));
        ComplexMethod res = new ComplexMethod();
        res.addMethods((List<AbstractMethod<Object>>) a.stream()
                .map(obj -> this.parseMethod((JSONObject) obj))
                .collect(Collectors.toList()));

        return new ComplexMethod();
    }

    private AbstractMethod<Object> parseMethod(JSONObject obj) {
        try {
            return (AbstractMethod<Object>) MethodType.valueOf((String) obj.get("type"))
                    .getCastType()
                    .getDeclaredConstructor(List.class, JSONObject.class)
                    .newInstance(new ArrayList<>(), obj);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
