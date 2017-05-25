package com.keepmeposted.utility.adapter;;

import android.content.Context;
import android.content.SharedPreferences;

/*import com.firebase.client.Firebase;*/
import com.keepmeposted.BuildConfig;

/**
 * Created by dsraj on 4/28/2016.
 */
public class Constants {

    public static final String FIREBASE_URL = "https://keepmeposted-d20e4.firebaseio.com/";

    /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String KEY_LIST_NAME = "LIST_NAME";
    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";
    public static final String KEY_LIST_ID = "LIST_ID";
    public static final String KEY_SIGNUP_EMAIL = "SIGNUP_EMAIL";
    public static final String KEY_LIST_ITEM_NAME = "ITEM_NAME";
    public static final String KEY_LIST_ITEM_ID = "LIST_ITEM_ID";
    public static final String KEY_PROVIDER = "PROVIDER";
    public static final String KEY_ENCODED_EMAIL = "ENCODED_EMAIL";
    public static final String KEY_LIST_OWNER = "LIST_OWNER";
    public static final String KEY_GOOGLE_EMAIL = "GOOGLE_EMAIL";
    public static final String KEY_PREF_SORT_ORDER_LISTS = "PERF_SORT_ORDER_LISTS";
    public static final String KEY_SHARED_WITH_USERS = "SHARED_WITH_USERS";


    /**
     * Constants for Firebase login
     */
    public static final String PASSWORD_PROVIDER = "password";
    public static final String GOOGLE_PROVIDER = "google";
    public static final String PROVIDER_DATA_DISPLAY_NAME = "displayName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + "users";

    //Chat

    private static final String FIREBASE_CHILD = "chat";
    private static final String FIREBASE_MAIL = "email";
    public static final String USER_MAIL = "user_mail";

    private static final String PREFERENCE_MAIL = "pref_email";
    private static final String PREFERENCE_USER_MAIL = "pref_user_email";

    public static String getFirebaseMail() {
        return FIREBASE_MAIL;
    }

    public static void getFirebaseInitialize(Context context) {
        /*Firebase.setAndroidContext(context);*/
    }

   /* public static Firebase getFirebaseReference(){
        return new Firebase(FIREBASE_URL).child(FIREBASE_CHILD);
    }*/

    public static void setMail(Context context ,String mail){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MAIL, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(PREFERENCE_USER_MAIL, mail).apply();
    }

    public static String getMail(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MAIL, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PREFERENCE_USER_MAIL, "soy_un_mail");
    }

}
