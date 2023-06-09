package capstoneservice.lambda;

import capstoneservice.activity.request.CreateUserRequest;
import capstoneservice.activity.result.CreateUserResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class CreateUserLambda
        extends LambdaActivityRunner<CreateUserRequest, CreateUserResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateUserRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateUserRequest> input, Context context) {
        CreateUserRequest unauthenticatedRequest = input.fromBody(CreateUserRequest.class);
        return super.runActivity(
                () -> {
                    return CreateUserRequest.builder()
                            .withUserId(unauthenticatedRequest.getUserId())
                            .withName(unauthenticatedRequest.getName())
                            .withToReadList(unauthenticatedRequest.getToReadList())
                            .withToWatchList(unauthenticatedRequest.getToWatchList())
                            .withCurrentlyReading(unauthenticatedRequest.getCurrentlyReading())
                            .withCurrentlyWatching(unauthenticatedRequest.getCurrentlyWatching())
                            .withReadList(unauthenticatedRequest.getReadList())
                            .withWatchedList(unauthenticatedRequest.getWatchedList())
                            .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateUserActivity().handleRequest(request)
        );
    }
}

