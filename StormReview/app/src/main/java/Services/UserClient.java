package services;

import java.util.List;

import models.User;
import models.UserBadge;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserClient {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") String id);

    @GET("leaderBoard/getBadges")
    Call<List<UserBadge>> getBadges();

}
