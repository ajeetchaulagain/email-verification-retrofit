package net.simplifiedcoding.javamailexample;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RandomNumAPI {
    @FormUrlEncoded
    @POST("/register/register.php")
    Call<ResponseBody> insertRandomNumber(
            @Field("username") String username,
            @Field("randcode") int randomCode
    );
}

