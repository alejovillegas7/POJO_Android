package co.com.compumovil.examplepojo.POJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {

    @GET("data/2.5/weather?")
    //void getWheatherReport(@Query("q") String place, @Query("appid") String appId, Callback<Examplepojo> cb);
    Call<Examplepojo> getCurrentWeatherData(@Query("q") String place, @Query("appid") String appId);
}
