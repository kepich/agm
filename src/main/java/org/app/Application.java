package org.app;

import org.app.method.impl.WriteStep;
import org.app.method.utils.MethodLoader;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Application {
    private static final MethodLoader methodLoader = new MethodLoader();

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("--||-- AUGMENTATOR --||--");

        ComplexMethod complexMethod = methodLoader.loadConfiguration("default.json");
        complexMethod.addMethod(new WriteStep());
    }
}
