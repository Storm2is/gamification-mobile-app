package services;

import java.util.List;

import models.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserClient {

    @GET("users")
    Call<List<User>> getUsers();
}
