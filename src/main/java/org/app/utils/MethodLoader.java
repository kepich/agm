package org.app.utils;

import org.app.method.ComplexMethod;
import org.app.method.AbstractMethod;
import org.app.method.MethodType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class MethodLoader {
    JSONParser parser = new JSONParser();

    public ComplexMethod loadConfiguration(String filename) throws IOException, ParseException {
        JSONArray a = (JSONArray) parser.parse(new FileReader(filename));
        ComplexMethod res = new ComplexMethod();
        res.addMethods((List<AbstractMethod>) a.stream()
                .map(obj -> this.parseMethod((JSONObject) obj))
                .collect(Collectors.toList()));

        return res;
    }

    private AbstractMethod parseMethod(JSONObject obj) {
        try {
            return (AbstractMethod) MethodType.valueOf((String) obj.get("type"))
                    .getCastType()
                    .getDeclaredConstructor(JSONObject.class)
                    .newInstance(obj);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
