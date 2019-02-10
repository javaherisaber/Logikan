package ir.logicfan.core.util;

import android.graphics.*;
import ir.logicfan.core.di.scope.PerActivity;

import java.util.Random;

@PerActivity
public class GraphicUtils {

    public static Bitmap addShadowToCircularBitmap(Bitmap srcBitmap, int shadowWidth, int shadowColor) {
        // Calculate the circular bitmap width with shadow
        int dstBitmapWidth = srcBitmap.getWidth() + shadowWidth * 2;
        Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888);

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(dstBitmap);

        // Paint to draw circular bitmap shadow
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new RadialGradient(canvas.getWidth() / 2, canvas.getHeight() / 2,
                dstBitmapWidth / 2 - shadowWidth / 2,
                shadowColor, Color.TRANSPARENT, Shader.TileMode.CLAMP));

        // Draw the shadow around circular bitmap
        canvas.drawCircle(
                dstBitmapWidth / 2, // cx
                dstBitmapWidth / 2, // cy
                dstBitmapWidth / 2 - shadowWidth / 2, // Radius
                paint // Paint
        );

        canvas.drawBitmap(srcBitmap, shadowWidth, shadowWidth, null);

        srcBitmap.recycle();

        // Return the circular bitmap with shadow
        return dstBitmap;
    }

    public static int generateRandomBrightColor() {
        Random random = new Random(System.currentTimeMillis());

        final int red = random.nextInt(256);
        final int green = random.nextInt(256);
        final int blue = random.nextInt(256);

        int variant1 = Color.argb(255, red, 0, 255);
        int variant2 = Color.argb(255, red, 255, 0);
        int variant3 = Color.argb(255, 255, green, 0);
        int variant4 = Color.argb(255, 0, green, 255);
        int variant5 = Color.argb(255, 255, 0, blue);
        int variant6 = Color.argb(255, 0, 255, blue);

        int[] choices = {variant1, variant2, variant3, variant4, variant5, variant6};
        return choices[random.nextInt(6)];
    }
}
