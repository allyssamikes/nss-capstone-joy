package activity;

import capstoneservice.activity.RemoveFromCurrentlyWatchingActivity;
import capstoneservice.activity.request.RemoveFromCurrentlyWatchingRequest;
import capstoneservice.activity.result.RemoveFromCurrentlyWatchingResult;
import capstoneservice.converters.ModelConverter;
import capstoneservice.dynamodb.MovieDao;
import capstoneservice.dynamodb.TVShowDao;
import capstoneservice.dynamodb.UserDao;
import capstoneservice.dynamodb.models.TVShow;
import capstoneservice.dynamodb.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RemoveFromCurrentlyWatchingActivityTest {

    @Mock
    private UserDao userDao;

    @Mock
    private MovieDao movieDao;

    @Mock
    private TVShowDao tvShowDao;

    @InjectMocks
   private RemoveFromCurrentlyWatchingActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.activity = new RemoveFromCurrentlyWatchingActivity(userDao, tvShowDao,movieDao);
    }

    @Test
    void handleRequest_removeFromList_updatesList() {
        // GIVEN
        User user = new User();
        user.setUserId("abcd");
        when(userDao.getUser("abcd")).thenReturn(user);
        TVShow tvShow = new TVShow();
        tvShow.setTitle("Friends");
        tvShow.setDirector("James-Burrows");
        when(tvShowDao.getTVShow("Friends", "James-Burrows")).thenReturn(tvShow);
        TVShow show = new TVShow();
        show.setTitle("The Office");
        show.setDirector("Ken-Kwapis");
        when(tvShowDao.getTVShow("The Office", "Ken-Kwapis")).thenReturn(show);
        Set<Object> shows = new HashSet<>();
        shows.add(tvShow);
        shows.add(show);
        user.setCurrentlyWatching(shows);

        List<Object> showModels = new ArrayList<>();
        showModels.add(new ModelConverter().toTVShowModel(tvShow));


        RemoveFromCurrentlyWatchingRequest request = RemoveFromCurrentlyWatchingRequest.builder()
                .withUserId("abcd")
                .withTitle("The Office")
                .withDirector("Ken-Kwapis")
                .build();


        // WHEN
        RemoveFromCurrentlyWatchingResult result = activity.handleRequest(request);
        List<Object> models = result.getModels();

        // THEN
        assertEquals(showModels, models);
    }
}
