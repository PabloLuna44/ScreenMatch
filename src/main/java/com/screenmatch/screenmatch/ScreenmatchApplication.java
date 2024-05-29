package com.screenmatch.screenmatch;

import com.screenmatch.screenmatch.main.Main;
import com.screenmatch.screenmatch.model.DataEpisode;
import com.screenmatch.screenmatch.model.DataSeason;
import com.screenmatch.screenmatch.model.DataSerie;
import com.screenmatch.screenmatch.repository.SerieRepository;
import com.screenmatch.screenmatch.service.API;
import com.screenmatch.screenmatch.service.DataConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{


	@Autowired
	private SerieRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);

	}

	@Override
	public void run(String... args) throws UnsupportedEncodingException {

		Main main=new Main(repository);
		main.menu();
	}
}
