package com.likandr.condortestapp._common.misc;

import android.content.Context;
import android.util.Log;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

public class PicassoTrustAll {
    private static Picasso mInstance = null;

    private PicassoTrustAll(Context context) {
        OkHttpClient client = new OkHttpClient();

        client.newBuilder().hostnameVerifier((s, sslSession) -> true);

        mInstance = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .listener((picasso, uri, exception) -> Log.e("PICASSO", exception.toString())).build();
    }

    public static Picasso getInstance(Context context) {
        if (mInstance == null) {
            new PicassoTrustAll(context);
        }
        return mInstance;
    }
}
