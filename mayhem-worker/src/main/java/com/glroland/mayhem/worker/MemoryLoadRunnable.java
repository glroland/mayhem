package com.glroland.mayhem.worker;

import java.util.LinkedList;

public class MemoryLoadRunnable implements Runnable
{
    private int floor;
    private long ceiling;
    private long delayToStart;
    private long delayBetweenJumps;
    private int jumpSize;

    public MemoryLoadRunnable(int floor, long ceiling, long delayToStart, long delayBetweenJumps, int jumpSize) 
    {
        this.floor = floor;
        this.ceiling = ceiling;
        this.delayToStart = delayToStart;
        this.delayBetweenJumps = delayBetweenJumps;
        this.jumpSize = jumpSize;
    }

    public void run()
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