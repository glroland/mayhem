package com.glroland.mayhem.worker;

public class ComputeLoadRunnable implements Runnable 
{
    private int floor;
    private int ceiling;
    private long delayToStart;
    private long delayBetweenJumps;

    public ComputeLoadRunnable(int floor, int ceiling, long delayToStart, long delayBetweenJumps)
    {
        this.floor = floor;
        this.ceiling = ceiling;
        this.delayToStart = delayToStart;
        this.delayBetweenJumps = delayBetweenJumps;
    }
    
    public void run()
    {
        // begin with floor
        Thread [] threads = new Thread[ceiling];
        int threadCount;
        for (threadCount=0; threadCount<floor; threadCount++)
        {
            // create thread
            ConsumeVCPURunnable vcpuRunnable = new ConsumeVCPURunnable();
            Thread thread = new Thread(vcpuRunnable);
            threads[threadCount] = thread;

            // kick off load
            thread.start();
        }

        // delay before starting work
        sleep(delayToStart);
        
        // spin up the rest of the threads
        while (true)
        {
            if (threadCount < ceiling)
            {
                // create thread
                ConsumeVCPURunnable vcpuRunnable = new ConsumeVCPURunnable();
                Thread thread = new Thread(vcpuRunnable);
                threads[threadCount] = thread;
                threadCount++;

                // start thread
                thread.start();
            }

            sleep(delayBetweenJumps);
        }

    }

    private void sleep(long delay)
    {
        try
        {
            Thread.sleep(delay);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException("Load generation interrupted while awaiting next bump.", e);
        }
    }
}
