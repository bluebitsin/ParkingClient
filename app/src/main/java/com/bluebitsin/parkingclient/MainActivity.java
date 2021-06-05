package com.bluebitsin.parkingclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.bluebitsin.parkingclient.adapter.ParkingTicketAdapter;
import com.bluebitsin.parkingclient.fragments.AccountFragment;
import com.bluebitsin.parkingclient.fragments.BookingConfirmationDialog;
import com.bluebitsin.parkingclient.fragments.ParkingFragment;
import com.bluebitsin.parkingclient.fragments.SearchParkingFragment;
import com.bluebitsin.parkingclient.fragments.TicketFragment;
import com.bluebitsin.parkingclient.model.ParkingSlot;
import com.bluebitsin.parkingclient.model.ParkingTicket;
import com.bluebitsin.parkingclient.model.RequestReservation;
import com.bluebitsin.parkingclient.model.User;
import com.bluebitsin.parkingclient.utility.ApiClient;
import com.bluebitsin.parkingclient.utility.ApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        BookingConfirmationDialog.BookingConfirmationListener {

    private User user;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {

        // get user details
        user = (User) getIntent().getSerializableExtra(ParkingConstants.OBJECT_USER);

        //loading the default fragment
        Fragment fragment = new ParkingFragment();
        Bundle args = new Bundle();
        args.putInt(ParkingConstants.CUSTOMER_ID, user.getUserId());
        fragment.setArguments(args);
        loadFragment(fragment);

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (item.getItemId()) {
            case R.id.page_parking:
                fragment = new ParkingFragment();
                args.putInt(ParkingConstants.CUSTOMER_ID, user.getUserId());
                fragment.setArguments(args);
                break;

            case R.id.page_search:
                fragment = new SearchParkingFragment();
                break;

            case R.id.page_ticket:
                fragment = new TicketFragment();
                args.putInt(ParkingConstants.CUSTOMER_ID, user.getUserId());
                fragment.setArguments(args);
                break;

            case R.id.page_account:
                fragment = new AccountFragment();
                args.putSerializable(ParkingConstants.OBJECT_USER, user);
                fragment.setArguments(args);
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public void onBookingConfirmation() {

        //make api call booking and generate ticket
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ParkingTicket> call = apiService.addBooking(new RequestReservation(user.getUserId()));
        progressDialog.show();
        call.enqueue(new Callback<ParkingTicket>() {
            @Override
            public void onResponse(Call<ParkingTicket> call, Response<ParkingTicket> response) {

                progressDialog.dismiss();
                int responseCode = response.code();
                if(responseCode == 200){
                    ParkingTicket parkingTicket = response.body();

                    //go to Ticket Activity
                    Intent intent = new Intent(MainActivity.this, ParkingTicketActivity.class);
                    intent.putExtra(ParkingConstants.PARKING_TICKET, parkingTicket);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ParkingTicket> call, Throwable t) {

                progressDialog.dismiss();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Parking Reservation")
                        .setMessage("Something went wrong, unable to process your request. Please try later. Thank you!")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("OK", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

    }


    @Override
    public void onBackPressed() {

        // show logout alert dialog
        new AlertDialog.Builder(this)
                .setTitle("Parking Client")
                .setMessage("Are you sure you want to exit?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        //super.onBackPressed();
    }


}