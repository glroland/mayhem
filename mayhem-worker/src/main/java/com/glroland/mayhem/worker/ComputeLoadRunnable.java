package com.glroland.mayhem.worker;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ComputeLoadRunnable implements Runnable 
{
	private static final Log log = LogFactory.getLog(ComputeLoadRunnable.class);

    private int floor;
    private int ceiling;
    private long delayToStart;
    private long delayBetweenJumps;

    public ComputeLoadRunnable(ComputeLoadConfig config)
    {
        this.floor = config.getFloor();
        this.ceiling = config.getCeiling();
        this.delayToStart = config.getDelayToStart();
        this.delayBetweenJumps = config.getDelayBetweenJumps();
    }
    
    public void run()
    {
        log.info("Launching Compute Load Thread....  ID=" + Thread.currentThread().getId() + " Size=" + floor + "-" + ceiling + " StartDelay=" + delayToStart + " JumpDelay=" + delayBetweenJumps);

        try
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
        finally
        {
            log.info("Compute Load Thread Naturally Terminating.  ID=" + Thread.currentThread().getId());
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
