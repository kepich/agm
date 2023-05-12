package org.app.method.impl;

import org.app.method.SimpleChangeMethod;
import org.json.simple.JSONObject;

import java.util.List;

public class SaveStretchMethod extends SimpleChangeMethod {
    protected SaveStretchMethod(List<Object> resultQueue, JSONObject parameters) {
        super(resultQueue, parameters);
    }

    @Override
    public void run(Object element) {

    }
}
