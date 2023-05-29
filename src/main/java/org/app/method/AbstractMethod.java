package org.app.method;

import org.app.utils.wav.WavFile;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.UUID;

public abstract class AbstractMethod {

    protected AbstractMethod(JSONObject parameters) {
        this.initParameters(parameters);
    }

    public abstract List<WavFile> run(WavFile element);

    public abstract void initParameters(JSONObject parameters);

    public String getRandomFileName() {
        return "temp/" + UUID.randomUUID() + ".wav";
    }
}
