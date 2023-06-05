package org.app.method.impl;

import org.app.method.SimpleChangeMethod;
import org.app.utils.wav.WavFile;
import org.app.utils.wav.WavFileException;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public class ChangeToneMethod extends SimpleChangeMethod {
    public ChangeToneMethod(JSONObject parameters) {
        super(parameters);
    }

    @Override
    protected WavFile generate(WavFile element, Double value) {
        String outputName = getRandomFileName();

        int resultFramesNumber = (int) (element.getNumFrames() * value);

        int[] resultFrames = StretchMethod.modify(element, value);
        resultFrames = SaveStretchMethod.stretch(resultFrames, 1 / value);

        try {
            WavFile wavFile = WavFile.newWavFile(new File(outputName), 1, resultFramesNumber, element.getValidBits(), element.getSampleRate());
            wavFile.writeFrames(resultFrames, resultFrames.length);
            wavFile.close();
            return wavFile;
        } catch (IOException | WavFileException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
