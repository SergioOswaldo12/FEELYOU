package com.example.feelyou;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class CancionStorage {
    private static final String PREF_NAME = "canciones_pref";
    private static final String KEY_CANCIONES = "lista_canciones";

    public static void guardarCanciones(Context context, List<Cancion> lista) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lista);
        editor.putString(KEY_CANCIONES, json);
        editor.apply();
    }

    public static List<Cancion> cargarCanciones(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_CANCIONES, null);
        if (json == null) return new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Cancion>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
