package com.screenmatch.screenmatch;

import com.screenmatch.screenmatch.main.Main;
import com.screenmatch.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;


@SpringBootApplication
public class ScreenmatchApplicationConsole implements CommandLineRunner{


	@Autowired
	private SerieRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplicationConsole.class, args);

	}

	@Override
	public void run(String... args) throws UnsupportedEncodingException {

		Main main=new Main(repository);
		main.menu();
	}
}
