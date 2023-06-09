package capstoneservice.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class GetTVShowRequest {

    private final String title;

    private final String director;

    public GetTVShowRequest(String title, String director) {
        this.title = title;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {return director;}

    @Override
    public String toString() {
        return "GetTVShowRequest{" +
                "title='" + title + '\'' +
                "director=" + director + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String title;

        private String director;

        public Builder withTitle(String title) {
            this.title= title;
            return this;
        }

        public Builder withDirector(String director) {
            this.director = director;
            return this;
        }
        public GetTVShowRequest build() {
            return new GetTVShowRequest(title, director);
        }
    }
}
