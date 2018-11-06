package Services;

import java.util.List;

import Models.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserClient {

    @GET("users")
    Call<List<User>> getUsers();
}
