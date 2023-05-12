package org.app.method;

import org.json.simple.JSONObject;

import java.util.List;

public abstract class AbstractMethod<T> {
    private final List<T> resultQueue;

    protected AbstractMethod(List<T> resultQueue, JSONObject parameters) {
        this.resultQueue = resultQueue;
        this.initParameters(parameters);
    }

    public abstract void run(T element);

    public abstract void initParameters(JSONObject parameters);
}
