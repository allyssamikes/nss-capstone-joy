package activity.request;

public class GetTVShowRequest {

    private final String title;

    public GetTVShowRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "GetTVShowRequest{" +
                "title='" + title + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetMovieRequest.Builder builder() {
        return new GetMovieRequest.Builder();
    }

    public static class Builder {
        private String title;

        public Builder withTitle(String title) {
            this.title= title;
            return this;
        }
        public GetTVShowRequest build() {
            return new GetTVShowRequest(title);
        }
    }
}
