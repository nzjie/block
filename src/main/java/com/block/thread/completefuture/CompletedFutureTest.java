package com.block.thread.completefuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * java8异步编排
 */
public class CompletedFutureTest {

    public static void main(String[] args) throws Exception {
        /***********以下任务1和任务4是属于并行，任务1和任务2,3是属于串行（2,3依赖1），2和3并行******************/
        /*CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务");
            return "a";
        });
        //上面执行完成后进来这里，且吧上步结果传进来，并无返回值
        CompletableFuture<Void> future2 = future1.thenAcceptAsync((res) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二个任务");
            System.out.println(res + "2");
        });
        CompletableFuture<String> future3 = future1.thenApplyAsync((res) -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第三个任务");
            System.out.println(res + "3");
            return res + "3";
        });
        //假设第四个任务不依赖上面的任务
        CompletableFuture<Void> future4 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第四个任务");
        });
        //异步编排，等待所有的异步完成
        CompletableFuture.allOf(future1, future2, future3,future4).get();*/

        /***********一下任务2和任务4是属于并行，任务1和任务2,4是属于串行（2,4依赖1），2和3串行（3依赖2）******************/
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个任务");
            return "1";
        });
        CompletableFuture<Long> future2 = future1.thenApplyAsync((res) -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第2个任务");
            return Long.valueOf(res);
        });
        //第三个任务以来第二个任务
        CompletableFuture<Long> future3 = future2.thenApplyAsync((res) -> {
            System.out.println("第3个任务");
            return res + 1;
        });
        CompletableFuture<Void> future4 = future1.thenAcceptAsync((res) -> {
            System.out.println("第4个任务");
        });
        CompletableFuture.allOf(future1, future2, future3, future4).get();
    }
}
