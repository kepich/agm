package org.app.method.utils;

import org.app.ComplexMethod;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class MethodLoader {
    JSONParser parser = new JSONParser();

    public ComplexMethod loadConfiguration(String filename) throws IOException, ParseException {
        JSONArray a = (JSONArray) parser.parse(new FileReader(filename));

        return new ComplexMethod();
    }
}
