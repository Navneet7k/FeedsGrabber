package navneet.com.hackernews;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sree on 27-10-2016.
 */

public interface InterfaceIGN {
    @GET("v1/articles?source=ign&sortBy=top&apiKey=9bce785607fc41c3b24cb48efe043d2f")
    Call<JSONResponse> getJSON();
}
