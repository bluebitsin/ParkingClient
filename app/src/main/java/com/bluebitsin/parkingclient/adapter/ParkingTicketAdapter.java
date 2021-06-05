package com.bluebitsin.parkingclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluebitsin.parkingclient.R;
import com.bluebitsin.parkingclient.model.ParkingTicket;
import com.bluebitsin.parkingclient.utility.Helper;

import java.util.List;

public class ParkingTicketAdapter extends RecyclerView.Adapter<ParkingTicketAdapter.ViewHolder> {

    private OnItemClickListener itemListener;
    private List<ParkingTicket> ticketList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(int position, int id, ParkingTicket item);
    }

    public ParkingTicketAdapter(Context context, List<ParkingTicket> ticketList,
                                OnItemClickListener itemListener){
        this.context = context;
        this.ticketList = ticketList;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item_ticket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ParkingTicket ticket = ticketList.get(position);

        String indicatorText = (ticket.getReservationStatus() == 0)? "Valid":"Invalid";
        int indicatorRes = (ticket.getReservationStatus() == 0)? R.drawable.ic_valid:R.drawable.ic_invalid;
        String ticketNumber = "#"+Helper.ticketNumber(ticket.getReservationId());
        String bookingTime = ticket.getDate();

        //setting values to our views
        holder.txtIndicator.setText(indicatorText);
        holder.imgIndicator.setImageResource(indicatorRes);
        holder.txtTicketNumber.setText(ticketNumber);
        holder.txtDate.setText(bookingTime);
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private final TextView textView;
        TextView txtTicketNumber, txtIndicator, txtDate;
        ImageView imgIndicator;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            view.setOnClickListener(this);

            //textView = (TextView) view.findViewById(R.id.textView);
            txtTicketNumber = (TextView) view.findViewById(R.id.txtTicketNumber);
            txtIndicator = (TextView) view.findViewById(R.id.txtIndicator);
            txtDate = (TextView) view.findViewById(R.id.txtDate);
            imgIndicator = (ImageView) view.findViewById(R.id.imgIndicator);
        }

        @Override
        public void onClick(View v) {
            itemListener.onItemClick(getAdapterPosition(), itemView.getId(),
                    ticketList.get(getAdapterPosition()));
        }
    }
}
