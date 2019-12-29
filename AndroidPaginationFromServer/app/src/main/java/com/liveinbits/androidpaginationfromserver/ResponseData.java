package com.liveinbits.androidpaginationfromserver;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData {
    @SerializedName("status")
    private String status;
    @SerializedName("images")
    private List<Images> listimage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Images> getListimage() {
        return listimage;
    }

    public void setListimage(List<Images> listimage) {
        this.listimage = listimage;
    }
}

 class Images{
    @SerializedName("id")
    private int id;
    @SerializedName("image_path")
    private String imagepath;

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getImagepath() {
         return imagepath;
     }

     public void setImagepath(String imagepath) {
         this.imagepath = imagepath;
     }
 }
