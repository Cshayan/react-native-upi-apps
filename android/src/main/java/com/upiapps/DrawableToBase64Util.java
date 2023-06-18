package com.upiapps;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import android.util.Base64;

public class DrawableToBase64Util {
   public static String drawableToBase64(Drawable drawable) {
     Bitmap bitmap = drawableToBitmap(drawable);
     Bitmap resizedBitmap = scaleBitmap(bitmap, 150, 150);
     ByteArrayOutputStream stream = new ByteArrayOutputStream();
     resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
     byte[] byteArray = stream.toByteArray();
     return Base64.encodeToString(byteArray, Base64.DEFAULT);
   }

   private static Bitmap drawableToBitmap(Drawable drawable) {
     if (drawable instanceof BitmapDrawable) {
       return ((BitmapDrawable) drawable).getBitmap();
     }

     Bitmap bitmap;
     if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
       bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
     } else {
       bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
     }

     Canvas canvas = new Canvas(bitmap);
     drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
     drawable.draw(canvas);

     return bitmap;
   }

   private static Bitmap scaleBitmap(Bitmap bitmap, int width, int height) {
     return Bitmap.createScaledBitmap(bitmap, width, height, true);
   }
}
