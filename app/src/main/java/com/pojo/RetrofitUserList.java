package com.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RetrofitUserList {
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int per_page;
    @SerializedName("total")
    private int total;
    @SerializedName("total_pages")
    private int total_pages;
    @SerializedName("data")
    private ArrayList<Datum> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Datum> getList() {
        return list;
    }

    public void setList(ArrayList<Datum> list) {
        this.list = list;
    }

    public static class Datum
  {

      @SerializedName("id")
      private int id;
      @SerializedName("email")
      private String email;
      @SerializedName("first_name")
      private String first_name;
      @SerializedName("last_name")
      private String last_name;
      @SerializedName("avatar")
      private String avatar;

      public Datum(String email, String first_name, String last_name) {
          this.email = email;
          this.first_name = first_name;
          this.last_name = last_name;
      }

      public int getId() {
          return id;
      }

      public void setId(int id) {
          this.id = id;
      }

      public String getEmail() {
          return email;
      }

      public void setEmail(String email) {
          this.email = email;
      }

      public String getFirst_name() {
          return first_name;
      }

      public void setFirst_name(String first_name) {
          this.first_name = first_name;
      }

      public String getLast_name() {
          return last_name;
      }

      public void setLast_name(String last_name) {
          this.last_name = last_name;
      }

      public String getAvatar() {
          return avatar;
      }

      public void setAvatar(String avatar) {
          this.avatar = avatar;
      }

      @Override
      public String toString() {
          return "RetrofitUserList{" +
                  "id=" + id +
                  ", email='" + email + '\'' +
                  ", first_name='" + first_name + '\'' +
                  ", last_name='" + last_name + '\'' +
                  ", avatar='" + avatar + '\'' +
                  '}';
      }
  }

    @Override
    public String toString() {
        return "RetrofitUserList{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", list=" + list +
                '}';
    }
}
