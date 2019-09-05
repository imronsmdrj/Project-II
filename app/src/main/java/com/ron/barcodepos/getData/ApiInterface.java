package com.ron.barcodepos.getData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("getcontact.php")
    Call<List<Contacts>> getContacts(
            @Query("bid") String bid,
            @Query("key") String keyword
    );

    @FormUrlEncoded
    @POST("insert.php")
    Call<Contacts> input(@Field("bid") String bid,
                      @Field("bnama") String bnama,
                      @Field("bsatuan") String bsatuan,
                      @Field("bharga_pokok") String bharga_pokok,
                      @Field("bharga_jual") String bharga_jual,
                      @Field("bstok") String bstok,
                      @Field("bkategori_id") String bkategori_id);
//                    Callback<Response> callback);
}