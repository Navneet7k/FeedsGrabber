package navneet.com.hackernews;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("v1/articles?source=hacker-news&sortBy=latest&apiKey=9bce785607fc41c3b24cb48efe043d2f")
    Call<JSONResponse> getJSON();

}
