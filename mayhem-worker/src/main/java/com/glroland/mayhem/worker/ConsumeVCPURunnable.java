package com.glroland.mayhem.worker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConsumeVCPURunnable implements Runnable 
{
	private static final Log log = LogFactory.getLog(ConsumeVCPURunnable.class);
    
    public void run()
    {
        log.info("VCPU Thread Starting....  ID=" + Thread.currentThread().getId());

        try
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
        finally
        {
            log.info("VCPU Thread Ending.  ID=" + Thread.currentThread().getId());
        }
    }
}
