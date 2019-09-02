package com.example.bimbingankonseling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;


public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "BimbinganKonseling";
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID       = "id";
    public static final String KEY_ID_SISWA = "id_siswa";
    public static final String KEY_NAMA     = "nama";
    public static final String KEY_SANDI    = "kata_sandi";
    public static final String KEY_LEVEL    = "level";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String id,String id_siswa,String nama,String kata_sandi,String level){
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_ID, id);
        editor.putString(KEY_ID_SISWA, id_siswa);
        editor.putString(KEY_NAMA, nama);
        editor.putString(KEY_SANDI, kata_sandi);
        editor.putString(KEY_LEVEL, level);

        editor.commit();
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_ID_SISWA, pref.getString(KEY_ID_SISWA, null));
        user.put(KEY_NAMA, pref.getString(KEY_NAMA, null));
        user.put(KEY_SANDI, pref.getString(KEY_SANDI, null));
        user.put(KEY_LEVEL, pref.getString(KEY_LEVEL, null));
        return user;
    }
    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void checkLogin() {
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);
        }
    }


}
