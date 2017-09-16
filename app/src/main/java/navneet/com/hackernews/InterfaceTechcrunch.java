package navneet.com.hackernews;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sree on 27-10-2016.
 */

public interface InterfaceTechcrunch {
    @GET("v1/articles?source=techcrunch&sortBy=top&apiKey=\"apikey\"")
    Call<JSONResponse> getJSON();
}
