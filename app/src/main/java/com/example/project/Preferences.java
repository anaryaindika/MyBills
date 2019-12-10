package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Preferences {

    private static ArrayList<Bills> MyBills = new ArrayList<>();

    private static ArrayList<UserAccount> User = new ArrayList<>();

    private static ArrayList<Friends> Friends = new ArrayList<>();

    public static void setArrayListSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyBills", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MyBills);
        editor.putString("Bills", json);
        editor.apply();
    }

    public static void getArrayListSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyBills", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Bills", null);
        Type type = new TypeToken<ArrayList<Bills>>() {}.getType();
        MyBills = gson.fromJson(json, type);
        if (MyBills == null) {
            MyBills = new ArrayList<>();
        }
    }

    public static void setUserSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(User);
        editor.putString("User's Account", json);
        editor.apply();
    }

    public static void getUserSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User's Account", null);
        Type type = new TypeToken<ArrayList<UserAccount>>() {}.getType();
        User = gson.fromJson(json, type);
        if (User == null) {
            insertUser("user", "pass");
            setUserSharedPreferences(context);
        }
    }

    public static void setFriendsSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Friends", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Friends);
        editor.putString("User's Friends", json);
        editor.apply();
    }

    public static void getFriendsSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Friends", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("User's Friends", null);
        Type type = new TypeToken<ArrayList<Friends>>() {}.getType();
        Friends = gson.fromJson(json, type);
        if (Friends == null) {
            Friends = new ArrayList<>();
        }
    }

    public static void insertItem(String billTitle, String billTotalAmount, String billItemAmount, String billDateDay, String billDateMonth, String billDateYear) {
        MyBills.add(new Bills(billTitle, billTotalAmount, billItemAmount, billDateDay, billDateMonth, billDateYear));
        HomeFragment.adapter.notifyItemInserted(MyBills.size());
    }

    public static void editItem(Integer index, String billTitle, String billTotalAmount, String billItemAmount, String billDateDay, String billDateMonth, String billDateYear) {
        MyBills.set(index, new Bills(billTitle, billTotalAmount, billItemAmount, billDateDay, billDateMonth, billDateYear));
        HomeFragment.adapter.notifyItemChanged(index);
    }

    public static void insertUser(String username, String password) {
        User.add(new UserAccount(username, password));
    }

    public static void insertFriend(String name) {
        Friends.add(new Friends(name));
    }

    public static ArrayList<Bills> getMyBills() {
        return MyBills;
    }

    public static ArrayList<UserAccount> getUserAccount() {
        return User;
    }

    public static ArrayList<com.example.project.Friends> getFriends() {
        return Friends;
    }
}
