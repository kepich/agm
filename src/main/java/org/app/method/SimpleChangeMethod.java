package org.app.method;

import org.json.simple.JSONObject;

import java.util.List;

public abstract class SimpleChangeMethod extends AbstractMethod<Object> {
    private double from;
    private double to;
    private double step;

    protected SimpleChangeMethod(List<Object> resultQueue, JSONObject parameters) {
        super(resultQueue, parameters);
    }

    @Override
    public void initParameters(JSONObject parameters) {
        this.from = (double) parameters.get("from");
        this.to = (double) parameters.get("to");
        this.step = (double) parameters.get("step");
    }
}
