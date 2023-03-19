package com.glroland.mayhem.worker;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
@Scope("singleton")
public class MemoryLoadConfig 
{
    @Value("${mayhem.memory.floor}")
    private int floor;

    @Value("${mayhem.memory.ceiling}")
    private long ceiling;

    @Value("${mayhem.delayToStart}")
    private long delayToStart;

    @Value("${mayhem.delayBetweenJumps}")
    private long delayBetweenJumps;

    @Value("${mayhem.jumpSize}")
    private int jumpSize;

    private static final int MEGABYTE = 1024 * 1024;

    public int getFloor() {
        return floor * MEGABYTE;
    }

    public long getCeiling() {
        return ceiling * MEGABYTE;
    }

    public long getDelayToStart() {
        return delayToStart;
    }

    public long getDelayBetweenJumps() {
        return delayBetweenJumps;
    }

    public int getJumpSize() {
        return jumpSize * MEGABYTE;
    }
}
