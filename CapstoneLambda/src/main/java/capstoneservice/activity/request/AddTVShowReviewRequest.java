package capstoneservice.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = AddTVShowReviewRequest.Builder.class)
public class AddTVShowReviewRequest {

    private final String title;

    private final String userId;

    private final String director;

    private final String UUIDOfEntity;

    public AddTVShowReviewRequest(String title, String director, String userId, String UUIDOfEntity) {
        this.title = title;
        this.director = director;
        this.userId = userId;
        this.UUIDOfEntity = UUIDOfEntity;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {return director;}

    public String getUserId() {
        return userId;
    }

    public String getUUIDOfEntity() {
        return UUIDOfEntity;
    }


    @Override
    public String toString() {
        return "AddTVShowReviewRequest{" +
                "title='" + title + '\'' +
                "director=" + director + '\'' +
                ", userId='" + userId + '\'' +
                ", UUIDOfEntity='" + UUIDOfEntity + '\'' +
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

        private String userId;

        private String UUIDOfEntity;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder withDirector(String director) {
            this.director = director;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUUID(String UUIDOfEntity) {
            this.UUIDOfEntity = UUIDOfEntity;
            return this;
        }

        public AddTVShowReviewRequest build() {
            return new AddTVShowReviewRequest(title, director, userId, UUIDOfEntity);
        }
    }
}
