package activity;

import activity.request.AddToToWatchListRequest;
import activity.result.AddToToWatchListResult;
import converters.ModelConverter;
import dynamodb.MovieDao;
import dynamodb.TVShowDao;
import dynamodb.UserDao;
import dynamodb.models.Movie;
import dynamodb.models.TVShow;
import dynamodb.models.User;
import exceptions.MovieNotFoundException;
import exceptions.TVShowNotFoundException;
import exceptions.UserNotFoundException;
import models.MovieModel;
import models.TVShowModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public class AddToToWatchListActivity {
    private final UserDao userDao;
    private final MovieDao movieDao;
    private final TVShowDao tvShowDao;

    @Inject
    public AddToToWatchListActivity(UserDao userDao, MovieDao movieDao, TVShowDao tvShowDao) {
        this.userDao = userDao;
        this.movieDao = movieDao;
        this.tvShowDao = tvShowDao;
    }

  public AddToToWatchListResult handleRequest(
            final AddToToWatchListRequest addToToWatchListRequest) {

        String title = addToToWatchListRequest.getTitle();
        String userId = addToToWatchListRequest.getUserId();
        User theUser;

        try {
            theUser= userDao.getUser(userId);
        } catch (NullPointerException ex) {
            throw new UserNotFoundException("User does not exist.");
        }

        TVShow tvShow;
        try {
            tvShow = tvShowDao.getTVShow(title);
        } catch (NullPointerException ex) {
            throw new TVShowNotFoundException("TVShow is not in our database.");
        }

        Movie movie;
        String director;
        try {
            director = addToToWatchListRequest.getDirector();
            movie = movieDao.getMovie(title, director);
        } catch (NullPointerException ex) {
            throw new MovieNotFoundException("This is a TV Show");
            // can I use Optionals here?
        }

        Set<Object> watchList;

        if (theUser.getToWatchList() == null) {
            watchList = new HashSet<>();
        } else  {
            watchList = new HashSet<>(theUser.getToWatchList());
        }


        List<Object> models = new ArrayList<>();
        for(Object o : watchList) {
            if(o.getClass().equals(tvShow.getClass())) {
                TVShow show = (TVShow) o;
                watchList.add(show);
                TVShowModel tVModel = new ModelConverter().toTVShowModel(show);
                models.add(tVModel);
            } else {
                Movie theMovie = (Movie) o;
                watchList.add(theMovie);
                MovieModel movieModel = new ModelConverter().toMovieModel(theMovie);
                models.add(movieModel);
            }
        }
      theUser.setToWatchList(watchList);
      userDao.saveUser(theUser);

        return AddToToWatchListResult.builder()
                .withList(models)
                .build();
    }
}