package com.example.looperandhandler;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleWorker extends Thread{

    private static final String TAG = SimpleWorker.class.getSimpleName();
    private AtomicBoolean alive;
    private ConcurrentLinkedQueue<Runnable> taskQueue;

    public SimpleWorker() {
        super(TAG);
        start();
        alive = new AtomicBoolean(true);
        taskQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void run() {
        while (alive.get()) {
            Runnable task = taskQueue.poll();
            if (task != null) {
                task.run();
            }
        }

        Log.d(TAG, "SimpleWorker terminated");
    }

    public SimpleWorker execute(Runnable task){
        taskQueue.add(task);
        return this;
    }

    public void quit(){
        alive.set(false);
    }
}
