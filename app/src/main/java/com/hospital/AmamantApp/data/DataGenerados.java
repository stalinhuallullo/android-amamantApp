package com.hospital.AmamantApp.data;

import android.content.Context;
import android.content.res.TypedArray;

import com.hospital.AmamantApp.R;
import com.hospital.AmamantApp.model.Tecnica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ResourceType")
public class DataGenerados {

    private static Random r = new Random();

    public static int randInt(int max) {
        int min = 0;
        return r.nextInt((max - min) + 1) + min;
    }



    /**
     * Generate dummy data News
     *
     * @param ctx   android context
     * @param count total result data
     * @return list of object
     */
    public static List<Tecnica> getListaTecnicasData(Context ctx, int count) {

        List<Tecnica> items = new ArrayList<>();

        List<String> id = getTecnicaId(ctx);
        List<Integer> images = getTecnicaImagenes(ctx);
        List<String> titles = getTecnicaTitulo(ctx);
        int limit_count = id.size();
        for (int i = 0; i < limit_count; i++) {
            Tecnica obj = new Tecnica();
            obj.id = id.get(i);
            obj.image = images.get(i);
            obj.title = titles.get(i);
            items.add(obj);
        }
        return items;
    }

    public static List<String> getTecnicaId(Context ctx) {
        List<String> items = new ArrayList<>();
        String name_arr[] = ctx.getResources().getStringArray(R.array.tecnica_id);
        for (String s : name_arr) items.add(s);
        return items;
    }

    public static List<Integer> getTecnicaImagenes(Context ctx) {
        List<Integer> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.tecnica_imagenes);
        for (int i = 0; i < drw_arr.length(); i++) items.add(drw_arr.getResourceId(i, -1));
        return items;
    }



    public static List<String> getTecnicaTitulo(Context ctx) {
        List<String> items = new ArrayList<>();
        String name_arr[] = ctx.getResources().getStringArray(R.array.tecnica_titulo);
        for (String s : name_arr) items.add(s);
        return items;
    }

    private static int getRandomIndex(int max) {
        return r.nextInt(max - 1);
    }
}
