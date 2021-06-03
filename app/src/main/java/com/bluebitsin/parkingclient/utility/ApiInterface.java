package com.bluebitsin.parkingclient.utility;

import com.bluebitsin.parkingclient.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    /*@GET("parking/verify/{reservationId}")
    Call<QRScanResponse> getQRScanData(@Path("reservationId") String reservationId);

    @GET("parking/gate/{gateStatus}")
    Call<ResponseBody> updateGateStatus(@Path("gateStatus") int gateStatus);

    @POST("parking/update")
    Call<ResponseBody> updateBookingStatus(@Body RequestCheckStatus requestCheckStatus);

*/

    @POST("user/login")
    @FormUrlEncoded
    Call<User> loginUser(@Field("mobile") String mobile,
                         @Field("password") String password);
}


