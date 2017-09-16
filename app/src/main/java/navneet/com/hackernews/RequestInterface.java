package navneet.com.hackernews;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("v1/articles?source=hacker-news&sortBy=latest&apiKey=\"apikey\"")
    Call<JSONResponse> getJSON();

}
