package org.app;

import org.app.method.impl.WriteStep;
import org.app.utils.DataLoader;
import org.app.utils.MethodLoader;
import org.app.utils.threading.ForkJoinPoolSingleton;
import org.app.utils.wav.WavFile;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Application {
    private static final MethodLoader methodLoader = new MethodLoader();

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        System.out.println("--||-- AUGMENTATOR --||--");

        ComplexMethod complexMethod = methodLoader.loadConfiguration("default.json");
        complexMethod.addMethod(new WriteStep());

        List<WavFile> inputData = DataLoader.loadData("input");
        List<CompletableFuture<List<?>>> res = complexMethod.execute(inputData);

        ForkJoinPoolSingleton tpe = ForkJoinPoolSingleton.getInstance();
        tpe.awaitQuiescence(1000, TimeUnit.SECONDS);

        System.out.println("--||-- END --||--");
    }
}
