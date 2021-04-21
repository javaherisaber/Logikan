package ir.logicbase.logikan.ui.util

import android.graphics.*
import java.io.InputStream
import java.util.*

object GraphicUtils {

    @JvmStatic
    fun addShadowToCircularBitmap(inputStream: InputStream): Bitmap {
        val bitmap = BitmapFactory.decodeStream(inputStream)
        return addShadowToCircularBitmap(bitmap)
    }

    @JvmStatic
    fun addShadowToCircularBitmap(
        srcBitmap: Bitmap,
        shadowWidth: Int = 36,
        shadowColor: Int = generateRandomBrightColor()
    ): Bitmap {
        // Calculate the circular bitmap width with shadow
        val dstBitmapWidth = srcBitmap.width + shadowWidth * 2
        val dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888)

        // Initialize a new Canvas instance
        val canvas = Canvas(dstBitmap)

        // Paint to draw circular bitmap shadow
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = RadialGradient(
            (canvas.width / 2).toFloat(), (canvas.height / 2).toFloat(),
            (dstBitmapWidth / 2 - shadowWidth / 2).toFloat(),
            shadowColor, Color.TRANSPARENT, Shader.TileMode.CLAMP
        )

        // Draw the shadow around circular bitmap
        canvas.drawCircle(
            (dstBitmapWidth / 2).toFloat(), // cx
            (dstBitmapWidth / 2).toFloat(), // cy
            (dstBitmapWidth / 2 - shadowWidth / 2).toFloat(), // Radius
            paint // Paint
        )

        canvas.drawBitmap(srcBitmap, shadowWidth.toFloat(), shadowWidth.toFloat(), null)

        srcBitmap.recycle()

        // Return the circular bitmap with shadow
        return dstBitmap
    }

    @JvmStatic
    fun generateRandomBrightColor(): Int {
        val random = Random(System.currentTimeMillis())

        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)

        val variant1 = Color.argb(255, red, 0, 255)
        val variant2 = Color.argb(255, red, 255, 0)
        val variant3 = Color.argb(255, 255, green, 0)
        val variant4 = Color.argb(255, 0, green, 255)
        val variant5 = Color.argb(255, 255, 0, blue)
        val variant6 = Color.argb(255, 0, 255, blue)

        val choices = intArrayOf(variant1, variant2, variant3, variant4, variant5, variant6)
        return choices[random.nextInt(6)]
    }
}
