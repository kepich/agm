package org.app.method.impl;

import org.app.method.SimpleChangeMethod;
import org.app.utils.wav.WavFile;
import org.json.simple.JSONObject;

public class InterferenceMethod extends SimpleChangeMethod {
    public InterferenceMethod(JSONObject parameters) {
        super(parameters);
    }

    @Override
    protected WavFile generate(WavFile element, Double value) {
        return element;
    }
}
