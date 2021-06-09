package com.bluebitsin.parkingclient;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bluebitsin.parkingclient.model.ParkingTicket;
import com.bluebitsin.parkingclient.utility.Helper;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ParkingTicketActivity extends AppCompatActivity {

    private ImageView imgQRCode;
    private TextView txtTicketNumber;
    private TextView txtSlotNumber;
    private TextView txtTime;
    private TextView txtDate;
    private Button btnGetDirection;

    private ParkingTicket ticket;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_ticket);

        initViews();
        updateViews();
        getParkingDirection();
        
    }

    private void initViews() {
        // get Ticket Details
        ticket = getIntent().getParcelableExtra(ParkingConstants.PARKING_TICKET);

        imgQRCode = findViewById(R.id.img_qr_code);
        txtTicketNumber = findViewById(R.id.txt_ticket_number);
        txtSlotNumber = findViewById(R.id.txtSlotNumber);
        txtTime = findViewById(R.id.txtTime);
        txtDate = findViewById(R.id.txtDate);
        btnGetDirection = findViewById(R.id.btnGetDirection);
    }

    private void updateViews() {

        if(ticket != null){

            String ticketNumber = "#"+Helper.ticketNumber(ticket.getReservationId());
            Bitmap qrCode = generateQRCode(ticket.getReservationId());
            String slotNumber = ticket.getSlotNumber()+ticket.getWing();
            String [] dateTime = Helper.exploadDateTime(Helper.getFormattedDateTime(ticket.getDate()));

            imgQRCode.setImageBitmap(qrCode);
            txtTicketNumber.setText(ticketNumber);
            txtSlotNumber.setText(slotNumber);
            txtDate.setText(dateTime[0]);
            txtTime.setText(dateTime[1]);

        }
    }

    private Bitmap generateQRCode(String reservationId){

        try{
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(reservationId, BarcodeFormat.QR_CODE,
                    500, 500);
            return bitmap;
        }catch (Exception e){
            return null;
        }
    }

    private void getParkingDirection() {

        btnGetDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ParkingTicketActivity.this, "Feature in progress. Thank you!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
