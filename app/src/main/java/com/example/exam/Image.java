package com.example.exam;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Image {
    public static Drawable fromUrl(String url) {
        Drawable d = null;
        try {
            InputStream data = (InputStream)new URL(url).getContent();
            d = Drawable.createFromStream(data, "");
        }
        catch(MalformedURLException ex) {
            Log.e("Error", ex.getMessage());
        }
        catch(IOException ex) {
            Log.e("Error", ex.getMessage());
        }
        return d;
    }
}
