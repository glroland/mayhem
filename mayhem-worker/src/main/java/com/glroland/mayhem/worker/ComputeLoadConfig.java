package com.glroland.mayhem.worker;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
@Scope("singleton")
public class ComputeLoadConfig 
{
    @Value("${mayhem.compute.floor}")
    private int floor;

    @Value("${mayhem.compute.ceiling}")
    private int ceiling;

    @Value("${mayhem.compute.delayToStart}")
    private long delayToStart;

    @Value("${mayhem.compute.delayBetweenJumps}")
    private long delayBetweenJumps;

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
