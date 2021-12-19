package com.example.mlcamera

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabelerOptionsBase
import com.google.mlkit.vision.label.ImageLabeling
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.button).setOnClickListener {
            dispatchTakePictureIntent()
        }
    }
        val REQUEST_IMAGE_CAPTURE = 1

         // When this is called, intent to take a picture
        private fun dispatchTakePictureIntent() {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
        }
    }
            //Transforms image to thumbnail so it can display in our imageView
            override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                //Add super call to make constructor
                super.onActivityResult(requestCode, resultCode, data)
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    //Transform data image we get into bitmap
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    //Add matrix 90 degrees so image will come back the correct way
                    val matrix = Matrix()
                    matrix.postRotate(90F)
                    val rotatedBitmap = Bitmap.createBitmap(
                        imageBitmap,
                        0,
                        0,
                        imageBitmap.width,
                        imageBitmap.height,
                        matrix,
                        false
                    )
                    val imageView = findViewById<ImageView>(R.id.imageView)
                    imageView.setImageBitmap(rotatedBitmap)

                    //CREATE INPUT IMAGE FROM A BITMAP
                    // val image = InputImage.fromBitmap(rotatedBitmap, 0)
                    //Configure & run image labeler


                }
            }
         }


