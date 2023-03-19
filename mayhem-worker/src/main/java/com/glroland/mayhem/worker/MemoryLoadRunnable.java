package com.glroland.mayhem.worker;

import java.util.LinkedList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MemoryLoadRunnable implements Runnable
{
	private static final Log log = LogFactory.getLog(MemoryLoadRunnable.class);

    private int floor;
    private long ceiling;
    private long delayToStart;
    private long delayBetweenJumps;
    private int jumpSize;

    public MemoryLoadRunnable(MemoryLoadConfig config) 
    {
        this.floor = config.getFloorMegabytes();
        this.ceiling = config.getCeilingMegabytes();
        this.delayToStart = config.getDelayToStart();
        this.delayBetweenJumps = config.getDelayBetweenJumps();
        this.jumpSize = config.getJumpSizeMegabytes();
    }

    public void run()
    {
        log.info("Launching Memory Load Thread....  ID=" + Thread.currentThread().getId() + " Size=" + floor + "-" + ceiling + "+" + jumpSize + " StartDelay=" + delayToStart + " JumpDelay=" + delayBetweenJumps);

        try
        {
            // initialize load
            LinkedList<byte[]> load = new LinkedList<byte[]>();
            long currentSize = 0;
            load.add(createChunk(floor));
            currentSize += floor;

            // pause work until provided inital delay has passed
            sleep(delayToStart);

            while(true)
            {
                if(currentSize < ceiling)
                {
                    load.add(createChunk(jumpSize));
                    currentSize += jumpSize;
                }

                sleep(delayBetweenJumps);
            }
        }
        finally
        {
            log.info("Memory Load Thread Naturally Terminating.  ID=" + Thread.currentThread().getId());
        }
    }

    private byte[] createChunk(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Invalid size provided.  Cannot be zero or negative");
        }

        return new byte[size];
    }

    private void sleep(long delay)
    {
        if (delay > 0)
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
}
