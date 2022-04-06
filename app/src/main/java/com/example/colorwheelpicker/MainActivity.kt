package com.example.colorwheelpicker

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.colorwheelpicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bitmap:Bitmap
    private var selectedBox:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initializing bitmap
        initBitmap();

        binding.color1Bg.setOnClickListener{
            binding.color1Bg.setBackgroundColor(getColor(R.color.select_element_bg))
            binding.color2Bg.setBackgroundColor(getColor(R.color.element_bg))
            binding.color3Bg.setBackgroundColor(getColor(R.color.element_bg))
            selectedBox = 0;
        }

        binding.color2Bg.setOnClickListener{
            binding.color1Bg.setBackgroundColor(getColor(R.color.element_bg))
            binding.color2Bg.setBackgroundColor(getColor(R.color.select_element_bg))
            binding.color3Bg.setBackgroundColor(getColor(R.color.element_bg))
            selectedBox = 1;
        }

        binding.color3Bg.setOnClickListener{
            binding.color1Bg.setBackgroundColor(getColor(R.color.element_bg))
            binding.color2Bg.setBackgroundColor(getColor(R.color.element_bg))
            binding.color3Bg.setBackgroundColor(getColor(R.color.select_element_bg))
            selectedBox = 2;
        }

        //on touch listener on the color wheel image view
        binding.ivColorWheel.setOnTouchListener{v, event ->
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                bitmap = binding.ivColorWheel.drawingCache
                if(event.x.toInt() < bitmap.width && event.y.toInt() < bitmap.height && event.x.toInt() >= 0 && event.y.toInt() >= 0) {
                    val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                    //get rgb value
                    var r = Color.red(pixel)
                    var g = Color.green(pixel)
                    var b = Color.blue(pixel)

                    //get hex value
                    val hex = "#" + Integer.toHexString(pixel)

                    if(selectedBox == 0){
                        binding.color1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(r, g, b)));
                    }else if(selectedBox == 1){
                        binding.color2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(r, g, b)));
                    }else{
                        binding.color3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(r, g, b)));
                    }
                }

            }

            true
        }

    }

    private fun initBitmap() {
        binding.ivColorWheel.isDrawingCacheEnabled = true
        binding.ivColorWheel.buildDrawingCache(true)
    }
}