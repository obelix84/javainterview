package safetycounter;

import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter {

    int counter = 0;
    private static Lock lock;

    public Counter(Lock rLock) {
        lock = rLock;
    }

    void increase(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " " + counter);
            counter++;
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock counterLock = new ReentrantLock(true);
        Counter counter = new Counter(counterLock);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        counter.increase();
                    }
                }
            }).start();
        }

        Thread.sleep(2000);
        System.out.println(counter.counter);
    }
}

