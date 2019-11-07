package com.fernando.sensorapp

import android.content.ComponentCallbacks2
import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.ToneGenerator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.util.ObjectsCompat

class MainActivity : AppCompatActivity() {

    private var objectManagerCam : CameraManager? = null
    private var cameraId: String? = null
    private var flashBool : Boolean? = null
    private var toneBool : Boolean? = null
    private var tone : ToneGenerator? = null
    private var vibrate : Vibrator? = null
    private var vibrateBool : Boolean? = null



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        objectManagerCam = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        vibrate = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        cameraId = objectManagerCam!!.cameraIdList[0]

        flashOnOff()

        toneOnOff()

        vibrateOnOff()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun vibrateOnOff() {
        vibrateBool = true

        var buttonVibrate = findViewById<Button>(R.id.button3)
        buttonVibrate!!.setOnClickListener{
            if(vibrateBool!!){
                vibrate!!.vibrate(VibrationEffect.createOneShot(1000, 30))
            } else  {

            }
        }
    }

    private fun toneOnOff() {
        toneBool = true
        tone = ToneGenerator(10,40)
        var buttonTone = findViewById<Button>(R.id.button2)
        buttonTone!!.setOnClickListener{
            if(toneBool!!){
                tone!!.startTone(ComponentCallbacks2.TRIM_MEMORY_MODERATE)
                toneBool = false
            }
            else {
                tone!!.stopTone()
                toneBool = true
            }
        }
    }

    private fun flashOnOff() {
        var buttonFlash = findViewById<Button>(R.id.button)
        flashBool = true


        buttonFlash!!.setOnClickListener {
            if (flashBool!!) {
                objectManagerCam!!.setTorchMode(cameraId!!, true)
                flashBool = false
            } else {
                objectManagerCam!!.setTorchMode(cameraId!!, false)
                flashBool = true
            }
            Toast.makeText(applicationContext, cameraId, Toast.LENGTH_SHORT)
        }
    }
}






