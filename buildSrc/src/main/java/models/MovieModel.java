package models;

import dynamodb.models.GENRE;
import dynamodb.models.Review;
import dynamodb.models.STREAMING_SERVICE;

import java.util.List;
import java.util.UUID;

public class MovieModel {
    private String title;
    private String director;
    private List<String> mainActors;
    private Integer lengthInMinutes;
    private GENRE genre;
    private STREAMING_SERVICE streamingService;
    private List<Review> reviews;
    private String UUIDAsString;

    private  UUID uuid;

    public MovieModel(String title, String director, List<String> mainActors, Integer lengthInMinutes, GENRE genre, STREAMING_SERVICE streamingService, List<Review> reviews, String UUIDAsString, UUID uuid) {
        this.title = title;
        this.director = director;
        this.mainActors = mainActors;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
        this.streamingService = streamingService;
        this.reviews = reviews;
        this.UUIDAsString = UUIDAsString;
        this.uuid = uuid;
    }


    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getMainActors() {
        return mainActors;
    }

    public Integer getLengthInMinutes() {
        return lengthInMinutes;
    }

    public GENRE getGenre() {
        return genre;
    }

    public STREAMING_SERVICE getStreamingService() {
        return streamingService;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getUUIDAsString() {
        return UUIDAsString;
    }

    public UUID getUuid() {
        return uuid;
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String title;
        private String director;
        private List<String> mainActors;
        private Integer lengthInMinutes;
        private GENRE genre;
        private STREAMING_SERVICE streamingService;
        private List<Review> reviews;
        private String UUIDAsString;
        private  UUID uuid;


    public Builder withTitle(String title) {
        this.title = title;
        return this;
    }

    public Builder withDirector(String director) {
        this.director = director;
        return this;
    }

    public Builder withMainActors(List<String> mainActors) {
        this.mainActors = mainActors;
        return this;
    }

    public Builder withLengthInMinutes(Integer lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
        return this;
    }

    public Builder withGenre(GENRE genre) {
        this.genre = genre;
        return this;
    }

    public Builder withStreamingService(STREAMING_SERVICE streamingService) {
        this.streamingService = streamingService;
        return this;
    }

    public Builder withReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

        public Builder withUUIDAsString(String UUIDAsString) {
            this.UUIDAsString = UUIDAsString;
            return this;
        }
        public Builder withUUID(java.util.UUID uuid) {
            this.uuid = uuid;
            return this;
        }

    public MovieModel build() {
        return new MovieModel(title, director, mainActors, lengthInMinutes, genre, streamingService, reviews, UUIDAsString, uuid);
    }

}}
