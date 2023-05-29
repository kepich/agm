package org.app.method.impl;

import org.app.method.SimpleChangeMethod;
import org.app.utils.wav.WavFile;
import org.app.utils.wav.WavFileException;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public class SaveStretchMethod extends SimpleChangeMethod {
    public SaveStretchMethod(JSONObject parameters) {
        super(parameters);
    }

    @Override
    protected WavFile generate(WavFile element, Double value) {
        String outputName = getRandomFileName();

        int resultFramesNumber = (int) (element.getNumFrames() * value);

        int[] resultFrames = new int[resultFramesNumber];
        int[] sourceFrames = element.getFrames();

        for (int i = 0; i < resultFramesNumber; i++) {
            resultFrames[i] = sourceFrames[(int) (i / value)];
        }

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

    public double[] hanning(double[] source, int windowSize) {
        for (int i = 0; i < source.length; i++) {
            source[i] = Math.sin((Math.PI / 2) * Math.sin(Math.PI * ((float)i / windowSize)));
        }
        return source;
    }

    private static Complex w(int k, int N)
    {
        if (k % N == 0) return new Complex(1, 0);
        double arg = -2 * Math.PI * k / N;
        return new Complex(Math.cos(arg), Math.sin(arg));
    }
    public static Complex[] fft(Complex[] x)
    {
        Complex[] X;
        int N = x.length;
        if (N == 2)
        {
            X = new Complex[2];
            X[0] = x[0].add(x[1]);
            X[1] = x[0].sub(x[1]);
        }
        else
        {
            Complex[] x_even = new Complex[N / 2];
            Complex[] x_odd = new Complex[N / 2];
            for (int i = 0; i < N / 2; i++)
            {
                x_even[i] = x[2 * i];
                x_odd[i] = x[2 * i + 1];
            }
            Complex[] X_even = fft(x_even);
            Complex[] X_odd = fft(x_odd);
            X = new Complex[N];
            for (int i = 0; i < N / 2; i++)
            {
                X[i] = X_even[i].add(w(i, N).mul(X_odd[i]));
                X[i + N / 2] = X_even[i].sub(w(i, N).mul(X_odd[i]));
            }
        }
        return X;
    }

    public static Complex[] nfft(Complex[] X)
    {
        int N = X.length;
        Complex[] X_n = new Complex[N];
        for (int i = 0; i < N / 2; i++)
        {
            X_n[i] = X[N / 2 + i];
            X_n[N / 2 + i] = X[i];
        }
        return X_n;
    }
}
