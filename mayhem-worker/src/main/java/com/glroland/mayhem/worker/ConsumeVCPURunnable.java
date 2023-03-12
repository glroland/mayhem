package com.glroland.mayhem.worker;

public class ConsumeVCPURunnable implements Runnable {
    
    public ConsumeVCPURunnable()
    {
    }

    public void run()
    {
        double reallyRandom = 0;
        while (true)
        {
            reallyRandom = Math.random() * Math.random();
            if (reallyRandom > 0)
            {
            }
        }
    }
}
