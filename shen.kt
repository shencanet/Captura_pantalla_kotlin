Captura de Pantalla NO OBSOLETA

Puesto que con el tiempo y las actualizaciones algunas funciones pasan a estar obsoletas, aquí les explico un método de capturar pantalla no obsoleto, por supuesto este código habría que adaptarlo a las necesidades de cada proyecto, pero les servirá para conocer esta alternativa



En Kotlin, si deseas capturar la pantalla de un dispositivo Android de una manera que no esté obsoleta, puedes utilizar la API de MediaProjection que se introdujo en Android 5.0 (API nivel 21) y posterior. Esta API permite capturar la pantalla del dispositivo de una manera más efectiva y moderna. Aquí hay un ejemplo básico de cómo puedes usar MediaProjection para capturar la pantalla en Kotlin:


import android.content.Context

import android.content.Intent

import android.hardware.display.DisplayManager

import android.hardware.display.VirtualDisplay

import android.media.ImageReader

import android.media.projection.MediaProjection

import android.media.projection.MediaProjectionManager

import android.os.Bundle

import android.os.Handler

import android.os.HandlerThread

import android.util.DisplayMetrics

import android.util.Log

import androidx.appcompat.app.AppCompatActivity


class ScreenCaptureActivity : AppCompatActivity() {

    private val TAG = "ScreenCaptureActivity"

    private val REQUEST_CODE = 1001

    private lateinit var mediaProjectionManager: MediaProjectionManager

    private lateinit var mediaProjection: MediaProjection

    private lateinit var virtualDisplay: VirtualDisplay

    private lateinit var imageReader: ImageReader

    private lateinit var handler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_screen_capture)


        mediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

        val captureIntent = mediaProjectionManager.createScreenCaptureIntent()

        startActivityForResult(captureIntent, REQUEST_CODE)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data)

            val displayMetrics = DisplayMetrics()

            windowManager.defaultDisplay.getMetrics(displayMetrics)


            // Initialize ImageReader for screen capture

            imageReader = ImageReader.newInstance(

                displayMetrics.widthPixels,

                displayMetrics.heightPixels,

                android.graphics.PixelFormat.RGBA_8888,

                2

            )


            // Create a virtual display

            virtualDisplay = mediaProjection.createVirtualDisplay(

                "ScreenCapture",

                displayMetrics.widthPixels,

                displayMetrics.heightPixels,

                displayMetrics.densityDpi,

                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,

                imageReader.surface,

                null,

                handler

            )


            // Start capturing

            startCapture()

        }

    }


    private fun startCapture() {

        // Implement your screen capture logic here

        // You can use the ImageReader to capture frames

    }


    override fun onDestroy() {

        super.onDestroy()

        if (::mediaProjection.isInitialized) {

            mediaProjection.stop()

        }

    }

}





Este es solo un ejemplo básico para capturar la pantalla utilizando la API de MediaProjection en Android. Deberás implementar la lógica real para capturar los cuadros de pantalla y guardarlos según tus necesidades específicas.

Recuerda que necesitas solicitar el permiso necesario en el dispositivo para capturar la pantalla y que esta funcionalidad solo está disponible en dispositivos Android 5.0 y posteriores. Además, debes manejar los permisos de manera adecuada en tu aplicación.


