package capstoneservice.activity.result;

import capstoneservice.models.UserModel;

public class CreateUserResult {
    private final UserModel model;


    public CreateUserResult(UserModel model) {
        this.model = model;
    }

    public UserModel getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "CreateUserResult{" +
                "model=" + model +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UserModel model;

        public Builder withModel(UserModel model) {
            this.model = model;
            return this;
        }

        public CreateUserResult build() {
            return new CreateUserResult(model);
        }
    }
}
