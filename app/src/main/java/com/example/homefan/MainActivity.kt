package com.example.homefan

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.*


class MainActivity : AppCompatActivity() {

    lateinit var toogleButton: ToggleButton
    lateinit var imageView: ImageView
    lateinit var rotateAnimator: ObjectAnimator
    lateinit var switchButton: Switch
    lateinit var seekBar: SeekBar
    final val  SPEED = intArrayOf(0, 5000, 3000, 1000)
    var gd: GradientDrawable = GradientDrawable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toogleButton = findViewById<ToggleButton>(R.id.powerButton)
        toogleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            val index = seekBar.progress
            if (isChecked) {
                rotateAnimator.duration = SPEED[index].toLong()
                rotateAnimator.start()
            } else {
                rotateAnimator.end()
            }
        }
        seekBar = findViewById<SeekBar>(R.id.speedBar)
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean){
                rotateAnimator.duration = SPEED[progress].toLong()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        switchButton = findViewById<Switch>(R.id.lightSwitch)
        switchButton.setOnCheckedChangeListener { buttonView, isChecked ->
            switchLight(isChecked)
        }
        imageView = findViewById<ImageView>(R.id.fanImage)

        rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE)
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT)
        gd.setGradientRadius(330f)
    }

    private fun switchLight(isChecked: Boolean){
        if(isChecked){
            gd.setColors(intArrayOf(Color.YELLOW, Color.TRANSPARENT))
            imageView.setBackground(gd)
        }
        else{
            imageView.setBackgroundColor(Color.TRANSPARENT)
        }
    }
}