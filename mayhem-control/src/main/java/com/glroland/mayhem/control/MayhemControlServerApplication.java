package com.glroland.mayhem.control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MayhemControlServerApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(MayhemControlServerApplication.class, args);
	}
}
