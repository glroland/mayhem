package com.glroland.mayhem.worker;

public class MemoryLoadConfig 
{
    private int floor = 256 * 1024 * 1024;
    private long ceiling = 16 * 1024 * 1024 * 1024;
    private long delayToStart = 60 * 1000;
    private long delayBetweenJumps = 15 * 1000;
    private int jumpSize = 50 * 1024 * 1024;

    public MemoryLoadConfig()
    {
    }

    public int getFloor() {
        return floor;
    }

    public long getCeiling() {
        return ceiling;
    }

    public long getDelayToStart() {
        return delayToStart;
    }

    public long getDelayBetweenJumps() {
        return delayBetweenJumps;
    }

    public int getJumpSize() {
        return jumpSize;
    }
}
