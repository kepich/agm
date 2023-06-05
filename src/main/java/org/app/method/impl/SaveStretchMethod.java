package org.app.method.impl;

import com.github.psambit9791.jdsp.transform.InverseShortTimeFourier;
import com.github.psambit9791.jdsp.transform.ShortTimeFourier;
import org.app.method.SimpleChangeMethod;
import org.app.utils.calc.Complex;
import org.app.utils.calc.FFT;
import org.app.utils.wav.WavFile;
import org.app.utils.wav.WavFileException;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SaveStretchMethod extends SimpleChangeMethod {
    public SaveStretchMethod(JSONObject parameters) {
        super(parameters);
    }

    @Override
    protected WavFile generate(WavFile element, Double value) {
        String outputName = getRandomFileName();

        int[] sourceFrames = element.getFrames();
        int[] resultFrames = stretch(sourceFrames, value);

        try {
            WavFile wavFile = WavFile.newWavFile(new File(outputName), 1, resultFrames.length, element.getValidBits(), element.getSampleRate());
            wavFile.writeFrames(resultFrames, resultFrames.length);
            wavFile.close();
            return wavFile;
        } catch (IOException | WavFileException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public static double[] hanning(int windowSize) {
        double[] source = new double[windowSize];
        for (int i = 0; i < source.length; i++) {
            source[i] = sin((PI / 2) * sin(PI * ((float)i / windowSize)));
        }
        return source;
    }

    public static int[] stretch(int[] audio, Double factor) {
        int windowSize = 4096;
        int h = 256;

        double[] phase = new double[windowSize];
        double[] hanningWindow = hanning(windowSize);

        double[] result = new double[(int) (audio.length / factor + windowSize)];

        double offset = h * factor;
        for (int i = 0; i < audio.length - windowSize - h; i += offset) {
            Complex[] a1 = new Complex[windowSize];
            Complex[] a2 = new Complex[windowSize];

            for (int j = 0; j < windowSize; j++) {
                a1[j] = new Complex(hanningWindow[j] * audio[i + j], 0);
                a2[j] = new Complex(hanningWindow[j] * audio[i + h + j], 0);
            }

            Complex[] s1 = FFT.fft(a1);
            Complex[] s2 = FFT.fft(a2);

            for (int j = 0; j < windowSize; j++) {
                phase[j] = (phase[j] + s2[j].divides(s1[j]).phase()) % 2 * Math.PI;
            }

            for (int j = 0; j < windowSize; j++) {
                s2[j] = (new Complex(phase[j], 0)).exp().scale(s2[j].abs());
            }

            Complex[] a2_rephased = FFT.ifft(s2);

            int i2 = (int)(i / factor);
            for (int j = 0; j < windowSize; j++) {
                result[i2 + j] += (a2_rephased[j].scale(hanningWindow[j])).re();
            }
        }

        double max = Arrays.stream(result).max().orElse(1);
        for (int i = 0; i < result.length; i++) {
            result[i] = 4096 * result[i] / max;
        }

        return Arrays.stream(result).mapToInt(d -> (int) d).toArray();
    }
}
