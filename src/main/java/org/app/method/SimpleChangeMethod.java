package org.app.method;

import org.json.simple.JSONObject;

import java.util.List;

public abstract class SimpleChangeMethod extends AbstractMethod<Object> {
    private float from;
    private float to;
    private float step;

    protected SimpleChangeMethod(List<Object> resultQueue, JSONObject parameters) {
        super(resultQueue, parameters);
    }

    @Override
    public void initParameters(JSONObject parameters) {
        this.from = (float) parameters.get("from");
        this.to = (float) parameters.get("to");
        this.step = (float) parameters.get("step");
    }
}
