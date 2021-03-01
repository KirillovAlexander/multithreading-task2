package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            System.out.println("Введите '1' чтобы запустить один поток\n" +
                    "Введите '2' чтобы запустить несколько потоков\n" +
                    "Введите '3' чтобы запустить самый быстрый поток из нескольких\n" +
                    "Введите '0' чтобы выйти.");
            int input = scanner.nextInt();
            switch (input) {
                case 0: {
                    return;
                }
                case 1: {
                    Callable<Integer> myCallable1 = new MyCallable();
                    Future<Integer> task1 = executor.submit(myCallable1);
                    Integer resultOfTask = task1.get();
                    System.out.println("Количество итераций: " + resultOfTask);
                    executor.shutdown();
                    break;
                }
                case 2: {
                    Callable<Integer> myCallable1 = new MyCallable();
                    Callable<Integer> myCallable2 = new MyCallable();
                    Callable<Integer> myCallable3 = new MyCallable();

                    List<Callable<Integer>> taskList = new ArrayList<>();
                    taskList.add(myCallable1);
                    taskList.add(myCallable2);
                    taskList.add(myCallable3);
                    List<Future<Integer>> taskResults = executor.invokeAll(taskList);
                    for (Future<Integer> taskResult : taskResults
                    ) {
                        System.out.println("Количество итераций: " + taskResult.get());
                    }
                    executor.shutdown();
                    break;
                }
                case 3: {
                    Callable<Integer> myCallable1 = new MyCallable();
                    Callable<Integer> myCallable2 = new MyCallable();
                    Callable<Integer> myCallable3 = new MyCallable();
                    List<Callable<Integer>> taskList = new ArrayList<>();
                    taskList.add(myCallable1);
                    taskList.add(myCallable2);
                    taskList.add(myCallable3);

                    int taskResult = executor.invokeAny(taskList);
                    executor.shutdown();
                    System.out.println("Количество итераций: " + taskResult);
                    break;
                }
            }
        }
    }
}
