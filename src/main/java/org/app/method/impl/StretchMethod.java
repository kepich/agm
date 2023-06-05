package org.app.method.impl;

import org.app.method.SimpleChangeMethod;
import org.app.utils.wav.WavFile;
import org.app.utils.wav.WavFileException;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public class StretchMethod extends SimpleChangeMethod {
    public StretchMethod(JSONObject parameters) {
        super(parameters);
    }

    @Override
    protected WavFile generate(WavFile element, Double value) {
        String outputName = getRandomFileName();

        int resultFramesNumber = (int) (element.getNumFrames() * value);

        int[] resultFrames = modify(element, value);

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

    public static int[] modify(WavFile element, Double value) {
        int resultFramesNumber = (int) (element.getNumFrames() * value);

        int[] resultFrames = new int[resultFramesNumber];
        int[] sourceFrames = element.getFrames();

        for (int i = 0; i < resultFramesNumber; i++) {
            resultFrames[i] = sourceFrames[(int) (i / value)];
        }
        return resultFrames;
    }
}
