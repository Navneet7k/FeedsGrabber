package navneet.com.hackernews;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sree on 27-10-2016.
 */

public interface InterfaceMashable {
    @GET("v1/articles?source=mashable&sortBy=top&apiKey=\"apikey\"")
    Call<JSONResponse> getJSON();
}
