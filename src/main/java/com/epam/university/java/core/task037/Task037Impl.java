package com.epam.university.java.core.task037;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task037Impl implements Task037 {
    @Override
    public Collection<String> switcher(Callable<String> ticker, Callable<String> tacker) {

        if (ticker == null || tacker == null) {
            throw new IllegalArgumentException();
        }

        List<String> clock = new ArrayList<>();

        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            for (int i = 0; i < 5; i++) {
                clock.add(executorService.submit(ticker).get());
                clock.add(executorService.submit(tacker).get());
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        return clock;
    }
}
