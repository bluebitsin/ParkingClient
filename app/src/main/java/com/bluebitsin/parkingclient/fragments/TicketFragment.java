package com.bluebitsin.parkingclient.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluebitsin.parkingclient.LoginActivity;
import com.bluebitsin.parkingclient.MainActivity;
import com.bluebitsin.parkingclient.ParkingConstants;
import com.bluebitsin.parkingclient.ParkingTicketActivity;
import com.bluebitsin.parkingclient.R;
import com.bluebitsin.parkingclient.adapter.ParkingTicketAdapter;
import com.bluebitsin.parkingclient.model.ParkingTicket;
import com.bluebitsin.parkingclient.model.User;
import com.bluebitsin.parkingclient.utility.ApiClient;
import com.bluebitsin.parkingclient.utility.ApiInterface;
import com.google.android.gms.maps.SupportMapFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketFragment extends Fragment implements ParkingTicketAdapter.OnItemClickListener {

    private RecyclerView listTicketsRecycler;
    private LinearLayout emptyView;
    private int customerId;
    private ProgressDialog progressDialog;
    private Context context;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            this.context = context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(MainActivity.class.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public TicketFragment() {
        super(R.layout.fragment_ticket);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        initViews(view);
        getAllParkingTickets();
        return view;
    }

    private void initViews(View view) {

        // get Arguments
        customerId = getArguments().getInt(ParkingConstants.CUSTOMER_ID);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait...");

        emptyView = view.findViewById(R.id.emptyView);
        listTicketsRecycler = view.findViewById(R.id.listTickets);


    }

    private void updateViewVisibility(boolean isListEmpty) {
        if (isListEmpty) {
            listTicketsRecycler.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            listTicketsRecycler.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private void getAllParkingTickets() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<ParkingTicket>> call = apiService.getParkingTickets(customerId);

        call.enqueue(new Callback<List<ParkingTicket>>() {
            @Override
            public void onResponse(Call<List<ParkingTicket>> call, Response<List<ParkingTicket>> response) {

                progressDialog.dismiss();
                int responseCode = response.code();
                if (responseCode == 200) {

                    List<ParkingTicket> ticketList = response.body();
                    Log.d("Ticket List Size: ", ticketList.size() + "");
                    ParkingTicketAdapter adapter = new ParkingTicketAdapter(getContext(), ticketList,
                            TicketFragment.this);
                    listTicketsRecycler.setAdapter(adapter);
                    listTicketsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    updateViewVisibility(false);

                } else if (responseCode == 404) {
                    updateViewVisibility(true);
                }

            }

            @Override
            public void onFailure(Call<List<ParkingTicket>> call, Throwable t) {

                progressDialog.dismiss();
                Log.e(ParkingConstants.TAG_LOGIN_ACTIVITY, t.toString());
            }
        });
    }

    @Override
    public void onItemClick(int position, int id, ParkingTicket item) {

        Intent intent = new Intent(context, ParkingTicketActivity.class);
        intent.putExtra(ParkingConstants.PARKING_TICKET, item);
        startActivity(intent);
        //Toast.makeText(context, item.getReservationId(), Toast.LENGTH_SHORT).show();
    }
}
