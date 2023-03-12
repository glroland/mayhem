package com.glroland.mayhem.worker;

public class ConfigManager {
    private static ConfigManager instance;

    ComputeLoadConfig computeLoadConfig;
    MemoryLoadConfig memoryLoadConfig;

    public static ConfigManager getInstance()
    {
        if (instance == null)
        {
            synchronized(ConfigManager.class)
            {
                if (instance == null)
                {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private ConfigManager()
    {
        computeLoadConfig = new ComputeLoadConfig();
        memoryLoadConfig = new MemoryLoadConfig();
    }

    public ComputeLoadConfig getComputeLoadConfig()
    {
        return computeLoadConfig;
    }

    public MemoryLoadConfig getMemoryLoadConfig()
    {
        return memoryLoadConfig;
    }

}
