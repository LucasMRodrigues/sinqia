package com.sinqia.desafiotecnico;

import com.sinqia.desafiotecnico.service.DirectoryWatcherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTecnicoApplication.class, args);

		DirectoryWatcherService directoryWatcherService = new DirectoryWatcherService();
		directoryWatcherService.watchDirectoryFolder();
	}
}
