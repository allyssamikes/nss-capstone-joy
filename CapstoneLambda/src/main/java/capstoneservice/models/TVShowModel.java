package capstoneservice.models;

import capstoneservice.dynamodb.models.Review;

import java.util.List;
import java.util.Objects;

public class TVShowModel {

    private String title;
    private String director;
    private List<String> mainActors;
    private Integer  lengthInSeasons;
    private Integer  lengthInMinutes;
    private String genre;
    private String streamingService;
    private List<Review> reviews;
    private String UUIDAsString;

    public TVShowModel(String title, String director, List<String> mainActors, Integer lengthInSeasons, Integer lengthInMinutes, String genre, String streamingService, List<Review> reviews, String UUIDAsString) {
        this.title = title;
        this.director = director;
        this.mainActors = mainActors;
        this.lengthInSeasons = lengthInSeasons;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
        this.streamingService = streamingService;
        this.reviews = reviews;
        this.UUIDAsString = UUIDAsString;
    }


    public String getTitle() {
        return title;
    }

    public String getDirector() {return director;}

    public List<String> getMainActors() {
        return mainActors;
    }

    public Integer getLengthInSeasons() {
        return lengthInSeasons;
    }

    public Integer getLengthInMinutes() {
        return lengthInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public String getStreamingService() {
        return streamingService;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getUUIDAsString() {
        return UUIDAsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVShowModel that = (TVShowModel) o;
        return Objects.equals(title, that.title) && Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director);
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String title;

        private String director;
        private List<String> mainActors;
        private Integer  lengthInSeasons;
        private Integer  lengthInMinutes;
        private String genre;
        private String streamingService;
        private List<Review> reviews;
        private String UUIDAsString;

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

        public Builder withLengthInSeasons(Integer lengthInSeasons) {
            this.lengthInSeasons = lengthInSeasons;
            return this;
        }
        public Builder withLengthInMinutes(Integer lengthInMinutes) {
            this.lengthInMinutes = lengthInMinutes;
            return this;
        }

        public Builder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder withStreamingService(String streamingService) {
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
        public TVShowModel build() {
            return new TVShowModel(title, director, mainActors, lengthInSeasons, lengthInMinutes, genre, streamingService,  reviews,  UUIDAsString);
        }
    }
}
