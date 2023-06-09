package capstoneservice.lambda;

import capstoneservice.activity.request.AddToWatchedListRequest;
import capstoneservice.activity.result.AddToWatchedListResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;


public class AddToWatchedListLambda
        extends LambdaActivityRunner<AddToWatchedListRequest, AddToWatchedListResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddToWatchedListRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddToWatchedListRequest> input, Context context) {
        AddToWatchedListRequest unauthenticatedRequest = input.fromBody(AddToWatchedListRequest.class);
        return super.runActivity(
                () -> {
                           return AddToWatchedListRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDirector(unauthenticatedRequest.getDirector())
                                    .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddToWatchedListActivity().handleRequest(request)
        );
    }

}