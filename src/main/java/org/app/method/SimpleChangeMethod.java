package org.app.method;

import org.app.utils.wav.WavFile;
import org.json.simple.JSONObject;

public abstract class SimpleChangeMethod extends AbstractMethod<WavFile> {
    private double from;
    private double to;
    private double step;

    protected SimpleChangeMethod(JSONObject parameters) {
        super(parameters);
    }

    @Override
    public void initParameters(JSONObject parameters) {
        this.from = (double) parameters.get("from");
        this.to = (double) parameters.get("to");
        this.step = (double) parameters.get("step");
    }
}
