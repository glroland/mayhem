package com.glroland.mayhem.worker;

public class ComputeLoadConfig 
{    
    private int floor = 1;
    private int ceiling = 6;
    private long delayToStart = 60 * 1000;
    private long delayBetweenJumps = 60 * 1000;

    public ComputeLoadConfig()
    {
    }

    public int getFloor() {
        return floor;
    }

    public int getCeiling() {
        return ceiling;
    }

    public long getDelayToStart() {
        return delayToStart;
    }

    public long getDelayBetweenJumps() {
        return delayBetweenJumps;
    }
}
