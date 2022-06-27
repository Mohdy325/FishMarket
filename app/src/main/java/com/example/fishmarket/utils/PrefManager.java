package com.example.fishmarket.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.fishmarket.model.AddressPOJO;
import com.google.gson.Gson;

/**
 * Created by Lincoln on 05/05/16.
 */
public class PrefManager {
    public static final String MY_ADDRESS = "MyAddress";
    public static final String PREV_MY_ADDRESS = "PrevMyAddress";
    public static final String LOCATION_ADDRESS = "LocationAddress";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
  //  public static PostConditionCheckRequest checkRequest=new PostConditionCheckRequest();
    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    public static final String PREF_NAME = "GreenShop";
    public static final String SHARED_PREF ="GreenShop";
    public static final String IS_TOKEN_UPDATED ="isChatOpen";
    public static final String CONSULTATION_ID="ConsultationId";
    public static final String CONSULTATION_MESSAGE="ConsultationId";
    public static final String CONSULTATION_TIME="ConsultationId";
    public static final String ORDER_ID="OrderId";
    public static final String NAME="Name";
    public static final String AGE="Age";
    public static final String PROBLEM="Problem";

    public static final String CART_LIST = "FoodCartList";
    public static final String Wish_List="WishList";
    public static final String EMAIL="userEmail";
    public static final String IMAGE="Organization_Image";
    public static final String REMEMBER_ME="isLoginUser";
    public static final String Language="Language";
    public static final String Lang_Pref="Laungauge_db";
    public static final String SHOP_LATITUDE="ShopLatitude";
    public static final String SHOP_LONGITUDE="ShopLongitude";
    public static final String LATITUDE="Latitude";
    public static final String LONGITUDE="Longitude";

    public static final String PREV_LATITUDE="PrevLatitude";
    public static final String PREV_LONGITUDE="PrevLongitude";


    public static void setLanguage(Context context, String language){
        SharedPreferences sp = context.getSharedPreferences(Lang_Pref, MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(Language,language);
        editor.apply();
    }
    public static String getLanguage(Context context){
        SharedPreferences sp = context.getSharedPreferences(Lang_Pref, MODE_PRIVATE);
        return sp.getString(Language,"en");
    }

/*
    public static void SaveNotifications(Context context, NotificationsPOJO userData) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            ArrayList<NotificationsPOJO> notificationsPOJOS=new ArrayList<>();
            if (GetNotificationList(context)!=null){
                notificationsPOJOS.addAll(GetNotificationList(context));
                notificationsPOJOS.add(userData);
            }else {
                notificationsPOJOS.add(userData);
            }
            String json = null;
            if (userData != null) {
                json = new Gson().toJson(notificationsPOJOS);
            }

            editor.putString("NotificationList", json).apply();
            Log.d("NotificationList", json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<NotificationsPOJO> GetNotificationList(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String json = sharedPref.getString("NotificationList", null);
        if (json == null) {
            return null;
        }
        Log.d("Json", json);
        List<NotificationsPOJO> objectList = Arrays.asList(new Gson().fromJson(json, NotificationsPOJO[].class));
        ArrayList<NotificationsPOJO> objects = new ArrayList<>();
        objects.addAll(objectList);
        Collections.reverse(objects);
        return objects;
    }
*/

    public static void setSkip(Context context,String value){
        SharedPreferences sp = context.getSharedPreferences(Lang_Pref, MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("SkipClck",value);
        editor.apply();
    }

    public static String getSkipClcik(Context context){
        SharedPreferences sp = context.getSharedPreferences(Lang_Pref, MODE_PRIVATE);
        return sp.getString("SkipClck","");
    }

    public static String getLanguageFirstime(Context context){
        SharedPreferences sp = context.getSharedPreferences(Lang_Pref, MODE_PRIVATE);
        return sp.getString(Language,"");
    }



/*
    public static ArrayList<GetUserInfoPOJO> getUserInfoPOJOS=new ArrayList<>();
    public static ArrayList<GetUserInfoPOJO> filteredUserList=new ArrayList<>();
*/

    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


/*





  public static void SaveLoginData(Context context, LoginPOJO responseData)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=new Gson().toJson(responseData);
        sp.edit().putString("LoginData",json).apply();
        Log.d("LoginData",json);
    }

    public static LoginPOJO GetLoginData(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=sp.getString("LoginData","");
        Log.d("LoginData",json);
        if (json.equalsIgnoreCase(""))
        {
            return null;
        }else {
            LoginPOJO responseData = new Gson().fromJson(json, LoginPOJO.class);
            return responseData;
        }
        // sp.edit().putString("LoginData",json).apply();
    }
    public static BusinessProfilePOJO GetBusinessProfile(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=sp.getString(BUSINESS_PROFILE,"");
        Log.d("LoginData",json);
        if (json.equalsIgnoreCase(""))
        {
            return null;
        }else {
            BusinessProfilePOJO responseData = new Gson().fromJson(json, BusinessProfilePOJO.class);
            return responseData;
        }
        // sp.edit().putString("LoginData",json).apply();
    }


    public static void SaveProfileData(Context context, ProfilePOJO responseData)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=new Gson().toJson(responseData);
        sp.edit().putString("ProfileData",json).apply();
        Log.d("ProfileData",json);
    }

    public static ProfilePOJO GetProfileData(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=sp.getString("ProfileData","");
        Log.d("ProfileData",json);
        if (json.equalsIgnoreCase(""))
        {
            return null;
        }else {
            ProfilePOJO responseData = new Gson().fromJson(json, ProfilePOJO.class);
            return responseData;
        }
        // sp.edit().putString("LoginData",json).apply();
    }












 public static void SaveAreaList(Context context, List<AreaPOJO> responseData)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=new Gson().toJson(responseData);
        sp.edit().putString("AreaList",json).apply();
        Log.d("AreaList",json);
    }

    public static ArrayList<AreaPOJO> GetAreaList(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=sp.getString("AreaList","");
        Log.d("AreaList",json);
        if (json.equalsIgnoreCase(""))
        {
            return new ArrayList<>();
        }else {
            List<AreaPOJO> responseData = Arrays.asList(new Gson().fromJson(json, AreaPOJO[].class));
            ArrayList<AreaPOJO> areaPOJOS=new ArrayList<>();
            areaPOJOS.addAll(responseData);
            return areaPOJOS;
        }
        // sp.edit().putString("LoginData",json).apply();
    }*/


    public static void clearWholePreference(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        pref.edit().clear().apply();
    }
    public static void clearLoginData(Context context){
       // SaveLoginData(context,null);
        //SaveDefualtAddressData(context,null);
    }


    public static void setString(Context context, String KEY, String value) {

      SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY,value);
        editor.apply();
    }
    public static String getString(Context context, String KEY) {

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(KEY,"");
    }


    public static void setInteger(Context context, String KEY, Integer value) {

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(KEY,value);
        editor.apply();
    }
    public static Integer getInteger(Context context, String KEY) {

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getInt(KEY,1);
    }

    public static void setBoolean(Context context, String KEY, Boolean value) {

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(KEY,value);
        editor.apply();
    }
    public static Boolean getBoolean(Context context, String KEY) {

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getBoolean(KEY,false);
    }


    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
    public static void SaveDeviceToken(Context context, String Value) {
        SharedPreferences sp = context.getSharedPreferences("gpsdevicetoken.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("devicetoken", Value);
        editor.apply();
    }

    public static String GetDeviceToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("gpsdevicetoken.txt", MODE_PRIVATE);
        return sp.getString("devicetoken", "");
    }


  /*    public static void SaveUserInfo(Context context, GetUserInfoPOJO userInfoPOJO)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=new Gson().toJson(userInfoPOJO);
        sp.edit().putString("UserInfo",json).apply();
        Log.d("UserInfo",json);
    }*/
 /*    public static GetUserInfoPOJO GetUserInfo(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=sp.getString("UserInfo","");
        Log.d("UserInfo",json);
        if (json.equalsIgnoreCase(""))
        {
            return null;
        }else {
            GetUserInfoPOJO responseData = new Gson().fromJson(json, GetUserInfoPOJO.class);
            return responseData;
        }
        // sp.edit().putString("LoginData",json).apply();

    }*/
  /*public static void SaveLoginData(Context context, LoginPOJO responseData)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=new Gson().toJson(responseData);
        sp.edit().putString("LoginData",json).apply();
        Log.d("LoginData",json);
    }


    public static LoginPOJO GetLoginData(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=sp.getString("LoginData","");
        Log.d("LoginData",json);
        if (json.equalsIgnoreCase(""))
        {
            return null;
        }else {
            LoginPOJO responseData = new Gson().fromJson(json, LoginPOJO.class);
            return responseData;
        }
        // sp.edit().putString("LoginData",json).apply();
    }
     public static void SaveCartList(Context context, ArrayList<CartPOJO> responseData)
    {
        if (responseData==null || responseData.size()==0) {
            setString(context, CART_LIST, "");
        }else {
            String json = new Gson().toJson(responseData);
            setString(context, CART_LIST, json);
        }
    }


    public static ArrayList<CartPOJO> GetCartList(Context context)
    {
        String json=getString(context,CART_LIST);
        ArrayList<CartPOJO> cart_models1 = new ArrayList<>();
        if (json.equals("")){
            return cart_models1;
        }else {
            List<CartPOJO> cart_models = Arrays.asList(new Gson().fromJson(json, CartPOJO[].class));
            cart_models1.addAll(cart_models);
            return cart_models1;
        }
    }
     public static void SaveWishList(Context context, ArrayList<ProductPOJO> responseData)
    {
        if (responseData==null || responseData.size()==0) {
            setString(context, Wish_List, "");
        }else {
            String json = new Gson().toJson(responseData);
            setString(context, Wish_List, json);
        }
    }


    public static ArrayList<ProductPOJO> GetWishList(Context context)
    {
        String json=getString(context,Wish_List);
        if (json.equals("")){
            return null;
        }else {
            List<ProductPOJO> cart_models = Arrays.asList(new Gson().fromJson(json, ProductPOJO[].class));
            ArrayList<ProductPOJO> cart_models1 = new ArrayList<>();
            cart_models1.addAll(cart_models);
            return cart_models1;
        }
    }


    public static AddressPOJO GetAddressData(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=sp.getString("Address","");
        Log.d("Address",json);
        if (json.trim().equalsIgnoreCase(""))
        {
            return null;
        }else {
            AddressPOJO responseData = new Gson().fromJson(json, AddressPOJO.class);
            return responseData;
        }
        //sp.edit().putString("LoginData",json).apply();

    }*/
    public static void SaveDefualtAddressData(Context context, AddressPOJO responseData)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=new Gson().toJson(responseData);
        if (responseData==null){
            sp.edit().putString("DefaultAddress","").apply();
        }else {
            sp.edit().putString("DefaultAddress",json).apply();
        }

        Log.d("AddressData",json);
    }
    public static AddressPOJO GetDefaultAddressData(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String json=sp.getString("DefaultAddress","");
        Log.d("DefaultAddress",json);
        if (json.trim().equalsIgnoreCase(""))
        {
            return null;
        }else {
            AddressPOJO responseData = new Gson().fromJson(json, AddressPOJO.class);
            return responseData;
        }
        //sp.edit().putString("LoginData",json).apply();

    }

}
