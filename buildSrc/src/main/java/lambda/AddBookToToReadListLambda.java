package lambda;

import activity.request.AddBookToReadListRequest;
import activity.request.AddBookToToReadListRequest;
import activity.result.AddBookToReadListResult;
import activity.result.AddBookToToReadListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class AddBookToToReadListLambda
        extends LambdaActivityRunner<AddBookToToReadListRequest, AddBookToToReadListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddBookToToReadListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddBookToToReadListRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddBookToToReadListRequest unauthenticatedRequest = input.fromBody(AddBookToToReadListRequest.class);
                    return input.fromUserClaims(claims ->
                            AddBookToToReadListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withIsbn(unauthenticatedRequest.getIsbn())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddBookToToReadListActivity().handleRequest(request)
        );
    }
}
