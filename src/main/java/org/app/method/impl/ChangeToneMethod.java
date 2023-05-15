package org.app.method.impl;

import org.app.method.SimpleChangeMethod;
import org.app.utils.wav.WavFile;
import org.json.simple.JSONObject;

import java.util.List;

public class ChangeToneMethod extends SimpleChangeMethod {
    public ChangeToneMethod(JSONObject parameters) {
        super(parameters);
    }

    @Override
    public List<WavFile> run(WavFile element) {
        System.out.println("ChangeToneMethod " + element.toString());
        return List.of(element, element);
    }
}
