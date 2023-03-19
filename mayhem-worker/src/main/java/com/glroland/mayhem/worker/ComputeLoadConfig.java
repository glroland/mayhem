package com.glroland.mayhem.worker;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mayhem.compute")
public class ComputeLoadConfig 
{
    @Min(1)
    private int floor;
 
    @Min(1)
    private int ceiling;

    @Min(0)
    private long delayToStart;

    @Min(0)
    private long delayBetweenJumps;

    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public int getCeiling() {
        return ceiling;
    }
    public void setCeiling(int ceiling) {
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
}
