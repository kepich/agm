package org.app.method.impl;

import org.app.method.AbstractMethod;
import org.app.utils.wav.WavFile;
import org.app.utils.wav.WavFileException;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class WriteStep extends AbstractMethod {
    public WriteStep() {
        super(null);
    }

    @Override
    public List<WavFile> run(WavFile element) {
        System.out.println("WriteStep " + element.toString());
        String outputName = "output/" + UUID.randomUUID() + ".wav";

        try {
            WavFile wavFile = WavFile.newWavFile(new File(outputName), 1, element.getNumFrames(), element.getValidBits(), element.getSampleRate());
            wavFile.writeFrames(element.getFrames(), element.getNumFrames());
            wavFile.close();
            return List.of(element);
        } catch (IOException | WavFileException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initParameters(JSONObject parameters) {
        // Nothing
    }
}
