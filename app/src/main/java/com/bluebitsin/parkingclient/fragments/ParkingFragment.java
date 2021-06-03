package com.bluebitsin.parkingclient.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bluebitsin.parkingclient.MainActivity;
import com.bluebitsin.parkingclient.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import org.jetbrains.annotations.NotNull;

public class ParkingFragment extends Fragment implements OnMapReadyCallback {

    private TextView txtAvailableTickets;
    private Button btnBooking;

    private OnClickBookingListener listener;

    public interface OnClickBookingListener{
        void onClickBooking();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (OnClickBookingListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(MainActivity.class.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public ParkingFragment() {
        super(R.layout.fragment_parking);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_parking, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initViews(view);
        booking();

        return view;
    }

    private void initViews(View view) {
        txtAvailableTickets = view.findViewById(R.id.txtAvailableTickets);
        btnBooking = view.findViewById(R.id.btnBooking);
    }

    private void booking() {

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClickBooking();
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng sydney = new LatLng(-33.88, 151.21);
        LatLng mountainView = new LatLng(37.4, -122.1);

        // Move the camera instantly to Sydney with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomIn());

        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(mountainView)      // Sets the center of the map to Mountain View
                .zoom(17)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
