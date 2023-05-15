package org.app.utils.threading;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolSingleton extends ForkJoinPool {
    private static ForkJoinPoolSingleton INSTANCE = null;

    public static ForkJoinPoolSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ForkJoinPoolSingleton();
        }

        return INSTANCE;
    }
}
