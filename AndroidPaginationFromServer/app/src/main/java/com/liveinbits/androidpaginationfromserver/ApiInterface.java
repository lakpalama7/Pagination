package com.liveinbits.androidpaginationfromserver;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("imagedownload.php")
    Call<List<ResponseData>> getListData(@Query("pageno") int pageno, @Query("itemcount") int itemcount);
}
