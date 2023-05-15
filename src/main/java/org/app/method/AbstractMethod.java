package org.app.method;

import org.json.simple.JSONObject;

import java.util.List;

public abstract class AbstractMethod<T> {

    protected AbstractMethod(JSONObject parameters) {
        this.initParameters(parameters);
    }

    public abstract List<T> run(T element);

    public abstract void initParameters(JSONObject parameters);
}
