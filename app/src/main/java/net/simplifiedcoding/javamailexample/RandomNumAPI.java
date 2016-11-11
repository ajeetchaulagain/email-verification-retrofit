package net.simplifiedcoding.javamailexample;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RandomNumAPI {
    @FormUrlEncoded
    @POST("/yokitab/random.php")
    Call<ResponseBody> insertUser(
            @Field("randcode") String randomCode,
            @Field("email") String email,
            @Field("level") String level,
            @Field("faculty") String faculty,
            @Field("year") String year,
            @Field("password") String password
    );
}