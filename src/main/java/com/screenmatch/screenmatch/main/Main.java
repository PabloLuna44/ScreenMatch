package com.screenmatch.screenmatch.main;

import com.screenmatch.screenmatch.model.*;
import com.screenmatch.screenmatch.repository.SerieRepository;
import com.screenmatch.screenmatch.service.API;
import com.screenmatch.screenmatch.service.DataConvert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private Scanner keyboard= new Scanner(System.in);
    private API api= new API();
    private final String URL="https://www.omdbapi.com/?t=";
    private String API_KEY="&apikey="+System.getenv("API_KEY_OMDB");;
    private DataConvert dataConvert= new DataConvert();
    private List<Serie> series;
    private SerieRepository repository;
    private Optional<Serie> serieByTitle;

    public Main(SerieRepository repository) {
        this.repository = repository;
    }

    public void menu() throws UnsupportedEncodingException {

        String menu= """
               --------------------
               Enter series name 
               --------------------
               [1] Search Serie 
               [2] Search Episodes By Serie 
               [3] previously searched series
               [4] Serach Serie By Title
               [5] Top 5 best Series
               [6] Search By Genre
               [7] Personalized search
               [8] Episode By Title
               [9] Top 5 Episodes By Serie
               
               [0] Exit
                """;

        Integer opc=1;

        while(opc!=0) {
            System.out.println(menu);
            opc=keyboard.nextInt();

            switch (opc){

                case 1:{
                    findSerie();
                    break;
                }
                case 2:{
                    findEpisodesBySerie();
                    break;
                }
                case 3:{
                    showPrevious();
                    break;
                }
                case 4:{
                    findSerieByTitle();
                    break;
                }
                case 5:{
                    topFive();
                    break;
                }
                case 6:{
                    findByGenre();
                    break;
                }
                case 7:{
                    findGreaterThanThreeSeason();
                    break;
                }
                case 8:{
                    findEpisodeByTitle();
                    break;
                }
                case 9:{
                    topFiveEpisodesBySerie();
                    break;
                }
                case 0:{
                    System.out.println("See you later");
                    break;
                }

                default:{
                    System.out.println("Invalid option");
                    break;
                }






            }

        }



    }

    private DataSerie getDataSerie(String serie) throws UnsupportedEncodingException {

        try {
            String serieEncode = URLEncoder.encode(serie, "UTF-8");

            var json = api.getData(URL + serieEncode + API_KEY);
            DataSerie dataSerie = dataConvert.getData(json, DataSerie.class);
            System.out.println(dataSerie);

            ArrayList<DataSeason> seasons = new ArrayList<>();

            for (int i = 1; i < dataSerie.totalSeasons() + 1; i++) {
                json = api.getData(URL + serieEncode + API_KEY + "&season=" + i);
                DataSeason dataSeason = dataConvert.getData(json, DataSeason.class);
                seasons.add(dataSeason);
            }

            seasons.forEach(System.out::println);
            return dataSerie;
        }catch (NullPointerException e){
            System.out.println("Not Found");
            return null;
        }


    }


    private void findSerie() throws UnsupportedEncodingException {
        keyboard.nextLine();
        System.out.println("Enter series name: ");
        String serie=keyboard.nextLine();

        DataSerie dataSerie=getDataSerie(serie);
        Serie serieDB = new Serie(dataSerie);

        if (dataSerie!=null) {
             repository.save(serieDB);

        }

    }




    private void findEpisodesBySerie() throws UnsupportedEncodingException{

        showPrevious();
        keyboard.nextLine();
        System.out.println("Enter serie name");
        String serieName=keyboard.nextLine();


        Optional<Serie> serie=series.stream()
                .filter(s -> s.getTitle().toUpperCase().contains(serieName.toUpperCase()))
                .findFirst();

        if(serie.isPresent()){
            var foundSerie=serie.get();
            try {

                ArrayList<DataSeason> seasons = new ArrayList<>();

                for (int i = 1; i < foundSerie.getTotalSeasons() + 1; i++) {
                    var  json = api.getData(URL + URLEncoder.encode(foundSerie.getTitle(),"UTF-8") + API_KEY + "&season=" + i);
                    DataSeason dataSeason = dataConvert.getData(json, DataSeason.class);
                    seasons.add(dataSeason);
                }

                List<Episode> episodes = seasons.stream()
                        .flatMap(t -> t.episodes().stream()
                                .map(d -> new Episode(t.season(), d)))
                        .collect(Collectors.toList());

                System.out.println(episodes);
                foundSerie.setEpisodes(episodes);
                repository.save(foundSerie);


            }catch (NullPointerException e){
                System.out.println("Not Found");
            }
        }else{
            System.out.println("Not Found");
        }

     }

    private void showPrevious(){
        series=repository.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);

    }

    private void findSerieByTitle(){
        keyboard.nextLine();
        System.out.println("Enter serie title: ");
        String serieTitle=keyboard.nextLine();

        serieByTitle=repository.findByTitleContainsIgnoreCase(serieTitle);

        if(serieByTitle.isPresent()){
            System.out.println(serieByTitle);
        }
        else{
            System.out.println("Not Found");
        }

    }

    private void topFive(){

        List<Serie> bestSeries=repository.findTop5ByOrderByRatingDesc();

        bestSeries.forEach(s-> System.out.println("Serie: "+s.getTitle()+" Rating: "+s.getRating()));

    }


    private void findByGenre(){
        keyboard.nextLine();
        System.out.println("Enter gender of serie");
        String genre=keyboard.nextLine();
        var category= Category.fromString(genre);
        List<Serie> serieByGenre=repository.findByGenre(category);
        System.out.println("Series with Gender "+genre+" are: ");
        serieByGenre.forEach(System.out::println);
    }

    private void findGreaterThanThreeSeason(){

        System.out.println("Enter season");
        Integer season = keyboard.nextInt();

        System.out.println("Enter Rating");
        Double rating = keyboard.nextDouble();

        List<Serie> seriesS=repository.SerieBySeasonAndRating(season,rating);

        seriesS.forEach(System.out::println);

    }

    private void findEpisodeByTitle(){
        keyboard.nextLine();
        System.out.println("Enter episode title");
        String episodeTitle=keyboard.nextLine();

        List<Episode> episode=repository.EpisodeByTitle(episodeTitle);

        episode.forEach(e->
                System.out.printf("Serie:%s , Season:%s, Episode:%d, Rating:%.2f \n",
                        e.getSerie().getTitle(),e.getSeason(),e.getEpisode(),e.getRating()));


    }

    private void topFiveEpisodesBySerie(){

        findSerieByTitle();
        if(serieByTitle.isPresent()) {
            Serie serie=serieByTitle.get();
            List<Episode> episodes = repository.Top5EpisodesBySerie(serie);
            episodes.forEach(e->
                    System.out.printf("Serie:%s , Season:%s, Episode:%d, Rating:%.2f \n",
                            e.getSerie().getTitle(),e.getSeason(),e.getEpisode(),e.getRating()));
        }else{
            System.out.println("Not Found");
        }

   }



}
