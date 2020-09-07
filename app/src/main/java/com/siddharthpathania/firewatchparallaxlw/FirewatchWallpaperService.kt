package com.siddharthpathania.firewatchparallaxlw

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager


class FireWatchWallpaperService: WallpaperService() {
    override fun onCreateEngine(): Engine {
        return FireWatchWallpaperEngine()
    }
    private inner class FireWatchWallpaperEngine : Engine() {
        private val handler = Handler(Looper.getMainLooper())
        private val drawRunner = Runnable { draw() }
        private lateinit var layer1:Bitmap
        private lateinit var layer2:Bitmap
        private lateinit var layer3:Bitmap
        private lateinit var layer4:Bitmap
        private lateinit var layer5:Bitmap
        private lateinit var layer6:Bitmap
        private lateinit var layer7:Bitmap
        private var width: Int = 0
        private var height: Int = 0
        private var src1:Rect = Rect(0, 0, 0, 0)
        private var src2:Rect = Rect(0, 0, 0, 0)
        private var src3:Rect = Rect(0, 0, 0, 0)
        private var src4:Rect = Rect(0, 0, 0, 0)
        private var src5:Rect = Rect(0, 0, 0, 0)
        private var src6:Rect = Rect(0, 0, 0, 0)
        private var src7:Rect = Rect(0, 0, 0, 0)
        private var visible: Boolean = true
        private var l7:Float = 0F
        private var l6:Float = 0F
        private var l5:Float = 0F
        private var l4:Float = 0F
        private var l3:Float = 0F
        private var l2:Float = 0F
        private var l1:Float = 0F
        val listener =
            SharedPreferences.OnSharedPreferenceChangeListener { prefs, _ ->
                updatePreference(prefs)
            }

            private fun updatePreference(preference: SharedPreferences?) {
            if (preference != null) {
                l7 = preference.getInt("L7",100).toFloat()/100F
                l6 = preference.getInt("L6",85).toFloat()/100F
                l5 = preference.getInt("L5",70).toFloat()/100F
                l4 = preference.getInt("L4",55).toFloat()/100F
                l3 = preference.getInt("L3",40).toFloat()/100F
                l2 = preference.getInt("L2",25).toFloat()/100F
                l1 = preference.getInt("L1",10).toFloat()/100F
            }
        }

        init {
            updatePreference(PreferenceManager.getDefaultSharedPreferences(this@FireWatchWallpaperService))
            PreferenceManager.getDefaultSharedPreferences(this@FireWatchWallpaperService).registerOnSharedPreferenceChangeListener(listener)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onCreate(surfaceHolder: SurfaceHolder?) {
            super.onCreate(surfaceHolder)
            layer1 = getDrawable(R.drawable.layer1)?.let { drawableToBitmap(it) }!!
            layer2 = getDrawable(R.drawable.layer2)?.let { drawableToBitmap(it) }!!
            layer3 = getDrawable(R.drawable.layer3)?.let { drawableToBitmap(it) }!!
            layer4 = getDrawable(R.drawable.layer4)?.let { drawableToBitmap(it) }!!
            layer5 = getDrawable(R.drawable.layer5)?.let { drawableToBitmap(it) }!!
            layer6 = getDrawable(R.drawable.layer6)?.let { drawableToBitmap(it) }!!
            layer7 = getDrawable(R.drawable.layer7)?.let { drawableToBitmap(it) }!!
            handler.post(drawRunner)
        }
        override fun onVisibilityChanged(visible: Boolean) {
            this.visible = visible
            handler.removeCallbacks(drawRunner)
            if (visible) {
                handler.post(drawRunner)
            }
        }
        override fun onSurfaceCreated(holder: SurfaceHolder?) {
            handler.removeCallbacks(drawRunner)
            if (visible) {
                handler.post(drawRunner)
            }
        }
        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            this.visible = false
            handler.removeCallbacks(drawRunner)
        }
        override fun onSurfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int){
            this.width = width
            this.height = height
            handler.removeCallbacks(drawRunner)
            handler.post(drawRunner)
            super.onSurfaceChanged(holder, format, width, height)
        }
        override fun onOffsetsChanged(
            xOffset: Float,
            yOffset: Float,
            xOffsetStep: Float,
            yOffsetStep: Float,
            xPixelOffset: Int,
            yPixelOffset: Int
        ) {
            super.onOffsetsChanged(
                xOffset,
                yOffset,
                xOffsetStep,
                yOffsetStep,
                xPixelOffset,
                yPixelOffset
            )
            var x = layer7.height*this.width/this.height
            var a = layer7.width/2 - x/2
            src7 = Rect((a+a*l7*(xOffset*2-1)).toInt(),0,(a+a*l7*(xOffset*2-1)+x).toInt(),layer7.height)
            src6 = Rect((a+a*l6*(xOffset*2-1)).toInt(),0,(a+a*l6*(xOffset*2-1)+x).toInt(),layer7.height)
            src5 = Rect((a+a*l5*(xOffset*2-1)).toInt(),0,(a+a*l5*(xOffset*2-1)+x).toInt(),layer7.height)
            src4 = Rect((a+a*l4*(xOffset*2-1)).toInt(),0,(a+a*l4*(xOffset*2-1)+x).toInt(),layer7.height)
            src3 = Rect((a+a*l3*(xOffset*2-1)).toInt(),0,(a+a*l3*(xOffset*2-1)+x).toInt(),layer7.height)
            src2 = Rect((a+a*l2*(xOffset*2-1)).toInt(),0,(a+a*l2*(xOffset*2-1)+x).toInt(),layer7.height)
            src1 = Rect((a+a*l1*(xOffset*2-1)).toInt(),0,(a+a*l1*(xOffset*2-1)+x).toInt(),layer7.height)
            handler.removeCallbacks(drawRunner)
            handler.post(drawRunner)
        }

        private fun draw() {
            val holder = surfaceHolder
            var canvas: Canvas? = null
            try {
                canvas = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    holder.lockHardwareCanvas()
                } else {
                    holder.lockCanvas()
                }
            } catch (e: Exception){}
            if(canvas!=null) { val dst:Rect = Rect(0, 0, this.width, this.height)
                canvas.drawBitmap(layer1, src1, dst, null)
                canvas.drawBitmap(layer2, src2, dst, null)
                canvas.drawBitmap(layer3, src3, dst, null)
                canvas.drawBitmap(layer4, src4, dst, null)
                canvas.drawBitmap(layer5, src5, dst, null)
                canvas.drawBitmap(layer6, src6, dst, null)
                canvas.drawBitmap(layer7, src7, dst, null)
            }
            if (canvas != null) {
                try {
                    holder.unlockCanvasAndPost(canvas)
                } catch (e: Exception){}
            }
            handler.removeCallbacks(drawRunner)
        }


        fun drawableToBitmap(drawable: Drawable): Bitmap {
            var bitmap: Bitmap? = null
            if (drawable is BitmapDrawable) {
                if (drawable.bitmap != null) {
                    return drawable.bitmap
                }
            }
            bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
                Bitmap.createBitmap(
                    1,
                    1,
                    Bitmap.Config.ARGB_8888
                ) // Single color bitmap will be created of 1x1 pixel
            } else {
                Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
            }
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }


    }
}