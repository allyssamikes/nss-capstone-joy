package activity.result;

import models.MovieModel;
import models.TVShowModel;

public class GetTVShowResult {

    private final TVShowModel tvShow;

    public GetTVShowResult(TVShowModel tvShow) {
        this.tvShow = tvShow;
    }

    public TVShowModel getTvShow() {
        return tvShow;
    }

    @Override
    public String toString() {
        return "GetTVShowResult{" +
                "tvShow=" + tvShow +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private TVShowModel tvShow;

        public Builder withTVShow(TVShowModel tvShow) {
            this.tvShow = tvShow;
            return this;
        }

        public GetTVShowResult build() {
            return new GetTVShowResult(tvShow);
        }
    }
}
