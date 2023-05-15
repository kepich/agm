package org.app.method.impl;

import org.app.method.AbstractMethod;
import org.app.utils.wav.WavFile;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.UUID;

public class WriteStep extends AbstractMethod<WavFile> {
    public WriteStep() {
        super(null);
    }

    @Override
    public List<WavFile> run(WavFile element) {
        System.out.println("WriteStep " + element.toString());
        String outputName = String.valueOf(UUID.randomUUID());
//        try (FileWriter fileWriter = new FileWriter(outputName)) {
//            fileWriter.write(element);
        return List.of(element);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void initParameters(JSONObject parameters) {
        // Nothing
    }
}
