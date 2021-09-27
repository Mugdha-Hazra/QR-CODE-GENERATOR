package com.example.qrcodegenerator

import android.graphics.Bitmap
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.encoder.QRCode

class MainActivity : AppCompatActivity() {

    private lateinit var ivQRCode: ImageView
    private lateinit var etData : EditText
    private lateinit var btnGenerateQRCode: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivQRCode =findViewById(R.id.ivQRCode)
        etData=findViewById(R.id.etData)
        btnGenerateQRCode=findViewById(R.id.btnGenerateQRCode)

        btnGenerateQRCode.setOnClickListener{
            val data = etData.text.toString().trim()
            if(data.isEmpty())
            {
                Toast.makeText(this,"entersome data",Toast.LENGTH_SHORT).show()
            }
            else{
                val writer=QRCodeWriter()
                try{
                    val bitMatrix=writer.encode(data,BarcodeFormat.QR_CODE,512,512)
                    val width=bitMatrix.width
                    val height=bitMatrix.height
                    val bmp= Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                    for(x in 0 until width)
                    {
                        for(y in 0 until height)
                        {
                            bmp.setPixel(x,y,if (bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    ivQRCode.setImageBitmap(bmp)
                }  catch (e: WriterException){
                    e.printStackTrace()
                }
            }
        }
    }
}