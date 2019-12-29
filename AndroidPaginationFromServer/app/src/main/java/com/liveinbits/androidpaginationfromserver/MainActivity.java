package com.liveinbits.androidpaginationfromserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterCustom adapter;
    private GridLayoutManager manager;
    private int startpage=1;
    private int endpage=7;
    private int pagethreshhold=7;
    private int totalitem,visibleitem,hiddenitem,previoustotal=0;
    private boolean status=true;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        manager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);

        ApiInterface call=ApiClient.getInstance().create(ApiInterface.class);
        Call<List<ResponseData>> data=call.getListData(startpage,endpage);

        data.enqueue(new Callback<List<ResponseData>>() {
            @Override
            public void onResponse(Call<List<ResponseData>> call, Response<List<ResponseData>> response) {
                List<Images> images=response.body().get(1).getListimage();
                adapter=new AdapterCustom(images);
                recyclerView.setAdapter(adapter);
                Toast.makeText(MainActivity.this,"Page loaded : "+startpage,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<ResponseData>> call, Throwable t) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
               // super.onScrolled(recyclerView, dx, dy);
                visibleitem=manager.getChildCount();
                totalitem=manager.getItemCount();
                hiddenitem=manager.findFirstVisibleItemPosition();
                if(dy>0){
                    if(status) {
                        if(totalitem>previoustotal) {
                            status = false;
                            previoustotal = totalitem;
                        }
                    }
                    if(!status && (totalitem-visibleitem)<=hiddenitem+pagethreshhold){
                        startpage++;
                        getMoreData();
                        status=true;


                    }
                }
            }
        });
    }

    public void getMoreData(){
        ApiInterface call=ApiClient.getInstance().create(ApiInterface.class);
        Call<List<ResponseData>> data=call.getListData(startpage,endpage);

        data.enqueue(new Callback<List<ResponseData>>() {
            @Override
            public void onResponse(Call<List<ResponseData>> call, Response<List<ResponseData>> response) {
                if (response.body().get(0).getStatus().equals("ok")) {
                    List<Images> images = response.body().get(1).getListimage();
                    adapter.bindImage(images);
                   // recyclerView.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, "Page loaded : " + startpage, Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"no more image availbae",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<ResponseData>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"no more image availbae",Toast.LENGTH_LONG).show();
            }
        });

    }

}
