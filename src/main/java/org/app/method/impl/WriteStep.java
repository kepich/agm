package org.app.method.impl;

import org.app.method.AbstractMethod;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class WriteStep extends AbstractMethod<Object> {
    protected WriteStep() {
        super(null, null);
    }

    @Override
    public void run(Object element) {
        String outputName = String.valueOf(UUID.randomUUID());
        try (FileWriter fileWriter = new FileWriter(outputName)) {
//            fileWriter.write(element);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initParameters(JSONObject parameters) {
        // Nothing
    }
}
