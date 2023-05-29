package org.app.utils;

import org.app.utils.wav.WavFile;
import org.app.utils.wav.WavFileException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataLoader {
    public static List<WavFile> loadData(String directoryPath) {
        final File folder = new File(directoryPath);
        return Arrays.stream(folder.listFiles()).map(file -> {
            try {
                WavFile res = WavFile.openWavFile(file);
                int[] buffer = new int[Math.toIntExact(res.getNumFrames() * res.getNumChannels())];
                res.readFrames(buffer, res.getNumFrames());
                res.close();
                return res;
            } catch (IOException | WavFileException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
