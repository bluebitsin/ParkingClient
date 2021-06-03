package com.bluebitsin.parkingclient.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bluebitsin.parkingclient.MainActivity;
import com.bluebitsin.parkingclient.ParkingConstants;
import com.bluebitsin.parkingclient.R;

import org.jetbrains.annotations.NotNull;

public class BookingConfirmationDialog extends DialogFragment {

    private BookingConfirmationListener listener;
    private TextView textAvailableSlots;
    private Button btnGenerateTicket;

    public interface BookingConfirmationListener{
        void onBookingConfirmation();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (BookingConfirmationListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(MainActivity.class.toString()
                    + " must implement BookingConfirmationListener");
        }
    }

    public BookingConfirmationDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static BookingConfirmationDialog newInstance(String title) {
        BookingConfirmationDialog frag = new BookingConfirmationDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_booking_confirmation, null);
        //initialize views
        textAvailableSlots = (TextView) view.findViewById(R.id.textTicketDetails);
        btnGenerateTicket = (Button) view.findViewById(R.id.btnCheckIn);

        // update views
        String slots = "0";
        textAvailableSlots.setText(slots);

        //add click listener
        btnGenerateTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){

                    listener.onBookingConfirmation();
                    dismiss();

                }
            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commit();
        } catch (IllegalStateException e) {
            Log.d(ParkingConstants.TAG_MAIN_ACTIVITY, "Exception", e);
        }
    }

}
