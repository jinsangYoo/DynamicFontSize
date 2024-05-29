package com.jinsang.dynamicfontsize

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.jinsang.dynamicfontsize.databinding.ActivityMainBinding

fun Context.deviceHeight(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        windowManager.currentWindowMetrics.bounds.height()
    } else {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        size.y
    }
}
fun Context.deviceWidth(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        windowManager.currentWindowMetrics.bounds.width()
    } else {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        size.x
    }
}

val Number.dpInPx: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, toFloat(), Resources.getSystem().displayMetrics).toInt()

val Number.spInPx: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, toFloat(), Resources.getSystem().displayMetrics).toInt()


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            conf1.text = "resources.configuration.fontScale: ${resources.configuration.fontScale}"
            conf2.text = "width: ${deviceWidth()} height: ${deviceHeight()}"
            spInpx1.setOnClickListener {
//                val textSize = ((deviceWidth() * 0.02) / resources.displayMetrics.scaledDensity).spInPx.toFloat()
                val textSize = (deviceWidth() * 0.02).toFloat()
                text1.textSize = textSize
                text1.text = "Hello ${textSize}"
            }
            spInpx2.setOnClickListener {
//                val textSize = ((deviceWidth() * 0.1) / resources.displayMetrics.scaledDensity).spInPx.toFloat()
                val textSize = (deviceWidth() * 0.03).toFloat()
                text2.textSize = textSize
                text2.text = "Hello ${textSize}"
            }
            spInpx3.setOnClickListener {
                val textSize = (deviceWidth() * 0.04).toFloat()
                text3.textSize = textSize
                text3.text = "Hello ${textSize}"
            }
        }
        setContentView(binding.root)
    }
}