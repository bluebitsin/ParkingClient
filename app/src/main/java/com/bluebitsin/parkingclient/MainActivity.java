package com.bluebitsin.parkingclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.bluebitsin.parkingclient.fragments.AccountFragment;
import com.bluebitsin.parkingclient.fragments.BookingConfirmationDialog;
import com.bluebitsin.parkingclient.fragments.ParkingFragment;
import com.bluebitsin.parkingclient.fragments.SearchParkingFragment;
import com.bluebitsin.parkingclient.fragments.TicketFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        BookingConfirmationDialog.BookingConfirmationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading the default fragment
        loadFragment(new ParkingFragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.page_parking:
                fragment = new ParkingFragment();
                break;

            case R.id.page_search:
                fragment = new SearchParkingFragment();
                break;

            case R.id.page_ticket:
                fragment = new TicketFragment();
                break;

            case R.id.page_account:
                fragment = new AccountFragment();
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

        Toast.makeText(this, "Generating Ticket", Toast.LENGTH_SHORT).show();
    }
}