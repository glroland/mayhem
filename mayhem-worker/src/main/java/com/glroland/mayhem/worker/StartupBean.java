package com.glroland.mayhem.worker;

import org.springframework.stereotype.Component;

import com.glroland.mayhem.worker.MemoryLoadRunnable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

@Component
public class StartupBean
{
    @Autowired
    private Environment environment;

    @Value("DIRECTIVE")
    private String directive;
    private static final String DIRECTIVE_COMPUTE = "compute";
    private static final String DIRECTIVE_MEMORY = "memory";
    private static final String DIRECTIVE_ALL = "all";

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) 
    {
        Runnable load = null;

        if (DIRECTIVE_ALL.equalsIgnoreCase(directive) || DIRECTIVE_COMPUTE.equalsIgnoreCase(directive))
        {
            System.out.println("Compute");
            ComputeLoadConfig config = ConfigManager.getInstance().getComputeLoadConfig();
            load = new ComputeLoadRunnable(config);
            spawn(load);
        }
        
        if (DIRECTIVE_ALL.equalsIgnoreCase(directive) || DIRECTIVE_MEMORY.equalsIgnoreCase(directive))
        {
            System.out.println("Memory");
            MemoryLoadConfig config = ConfigManager.getInstance().getMemoryLoadConfig();
            load = new MemoryLoadRunnable(config);
            spawn(load);
        }

        if (load == null)
        {
            throw new RuntimeException("No directive passed to startup module");
        }
    }

    private Thread spawn(Runnable r)
    {
        Thread t = new Thread(r);
        t.start();

        return t;
    }
}
