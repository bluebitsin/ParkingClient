package com.bluebitsin.parkingclient;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ParkingTicket extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_ticket);


        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap("39275441-36ad-4fdc-bc1c-e43556a881a1", BarcodeFormat.QR_CODE, 500, 500);
            ImageView imageViewQrCode = (ImageView) findViewById(R.id.img_qr_code);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch(Exception e) {

        }
    }
}
