package activity.request;

import dynamodb.models.Review;
import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.gradle.internal.impldep.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;
@JsonDeserialize(builder = AddToCurrentlyReadingListRequest.Builder.class)
public class AddToCurrentlyReadingListRequest{
    private final String isbn;
    private final String title;
    private final String author;

    private final String userId;

    public AddToCurrentlyReadingListRequest(String isbn, String title, String author, String userId) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.userId = userId;
    }
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }


    public String getUserId() {
        return userId;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String isbn;
        private  String title;
        private  String author;

        private String userId;
        public Builder withIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }


        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        public AddToCurrentlyReadingListRequest build() {
            return new AddToCurrentlyReadingListRequest(isbn, title, author, userId);
        }
    }
}
