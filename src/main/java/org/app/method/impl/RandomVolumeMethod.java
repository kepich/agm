package org.app.method.impl;

import org.app.method.SimpleChangeMethod;
import org.json.simple.JSONObject;

import java.util.List;

public class RandomVolumeMethod extends SimpleChangeMethod {
    protected RandomVolumeMethod(List<Object> resultQueue, JSONObject parameters) {
        super(resultQueue, parameters);
    }

    @Override
    public void run(Object element) {

    }
}
