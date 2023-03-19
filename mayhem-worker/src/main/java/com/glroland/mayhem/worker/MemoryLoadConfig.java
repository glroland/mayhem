package com.glroland.mayhem.worker;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mayhem.memory")
public class MemoryLoadConfig 
{
    @Min(1)
    private int floor;

    @Min(1)
    private long ceiling;

    @Min(0)
    private long delayToStart;

    @Min(0)
    private long delayBetweenJumps;
    
    @Min(0)
    private int jumpSize;

    private static final int MEGABYTE = 1024 * 1024;

    public int getFloorMegabytes() {
        return floor * MEGABYTE;
    }

    public long getCeilingMegabytes() {
        return ceiling * MEGABYTE;
    }

    public int getJumpSizeMegabytes() {
        return jumpSize * MEGABYTE;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public long getCeiling() {
        return ceiling;
    }

    public void setCeiling(long ceiling) {
        this.ceiling = ceiling;
    }

    public long getDelayToStart() {
        return delayToStart;
    }

    public void setDelayToStart(long delayToStart) {
        this.delayToStart = delayToStart;
    }

    public long getDelayBetweenJumps() {
        return delayBetweenJumps;
    }

    public void setDelayBetweenJumps(long delayBetweenJumps) {
        this.delayBetweenJumps = delayBetweenJumps;
    }

    public int getJumpSize() {
        return jumpSize;
    }

    public void setJumpSize(int jumpSize) {
        this.jumpSize = jumpSize;
    }
}
