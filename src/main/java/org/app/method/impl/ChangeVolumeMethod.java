package org.app.method.impl;

import org.app.method.SimpleChangeMethod;
import org.app.utils.wav.WavFile;
import org.app.utils.wav.WavFileException;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ChangeVolumeMethod extends SimpleChangeMethod {
    public ChangeVolumeMethod(JSONObject parameters) {
        super(parameters);
    }

    @Override
    protected WavFile generate(WavFile element, Double value) {
        String outputName = getRandomFileName();

        try {
            WavFile wavFile = WavFile.newWavFile(new File(outputName), 1, element.getNumFrames(), element.getValidBits(), element.getSampleRate());

            Map<String, Integer> statistic = new HashMap<>();
            Arrays.stream(element.getFrames()).mapToObj(Integer::toHexString).forEach(el -> {
                if (!statistic.containsKey(el)) {
                    statistic.put(el, 1);
                } else {
                    statistic.put(el, statistic.get(el) + 1);
                }
            });
            wavFile.writeFrames(
                    Arrays.stream(element.getFrames()).map(sample -> limitMul(sample * value)).toArray(),
                    element.getNumFrames());
            wavFile.close();
            return wavFile;
        } catch (IOException | WavFileException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    private int limitMul(double value) {
        int maxSampleValue = 32767;

        if (value > maxSampleValue) {
            return maxSampleValue;
        }

        if (value < -maxSampleValue) {
            return -maxSampleValue;
        }

        return (int)value;
    }
}
