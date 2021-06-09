package com.bluebitsin.parkingclient.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bluebitsin.parkingclient.MainActivity;
import com.bluebitsin.parkingclient.ParkingConstants;
import com.bluebitsin.parkingclient.R;
import com.bluebitsin.parkingclient.model.User;
import com.google.android.gms.maps.SupportMapFragment;

import org.jetbrains.annotations.NotNull;

public class AccountFragment extends Fragment {

    private TextView txtName;
    private TextView txtMobile;
    private TextView txtCarName;
    private TextView txtNumberPlate;
    private Button btnLogout;

    private OnUserLogoutListener listener;

    public interface OnUserLogoutListener{
        void onLogout();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (OnUserLogoutListener)context;

        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(MainActivity.class.toString()
                    + " must implement OnUserLogoutListener");
        }
    }

    public AccountFragment(){
        super(R.layout.fragment_account);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        // get user
        User user = (User) getArguments().getSerializable(ParkingConstants.OBJECT_USER);

        txtName = view.findViewById(R.id.txtName);
        txtMobile = view.findViewById(R.id.txtMobile);
        txtCarName = view.findViewById(R.id.txtCarName);
        txtNumberPlate = view.findViewById(R.id.txtNumberPlate);
        btnLogout = view.findViewById(R.id.btnLogout);

        //update views
        try{
            txtName.setText(user.getUserName());
            txtMobile.setText(user.getMobileNumber());
            txtCarName.setText(user.getVechileModel());
            txtNumberPlate.setText(user.getVechileNumber());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        logoutUser();
    }

    private void logoutUser() {

        // show alert dialog
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmationDialog();
            }
        });
    }

    private void confirmationDialog(){

        new AlertDialog.Builder(getContext())
                .setTitle("Parking Client")
                .setMessage("Are you sure you want to logout?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(listener != null){
                            listener.onLogout();
                        }

                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
