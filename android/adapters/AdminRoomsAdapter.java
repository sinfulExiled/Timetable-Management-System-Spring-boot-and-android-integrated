package com.application.tms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tms.R;
import com.application.tms.models.Rooms;
import com.application.tms.models.Rooms;

import java.util.List;

public class AdminRoomsAdapter extends RecyclerView.Adapter<AdminRoomsAdapter.AdminAdminRoomsAdapterVH> {

    private List<Rooms> holidaysResponseList;
    private Context context;
    private ClickedItem clickedItem;
    private boolean isRequested = false;

    public AdminRoomsAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<Rooms> holidaysResponseList) {
        this.holidaysResponseList = holidaysResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdminAdminRoomsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new AdminRoomsAdapter.AdminAdminRoomsAdapterVH(LayoutInflater.from(context).inflate(R.layout.room_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdminRoomsAdapterVH holder, int position) {

        Rooms holidaysResponse = holidaysResponseList.get(position);

        String teacher = holidaysResponse.getBookedTeacher();
        String number = holidaysResponse.getNumber();

        holder.batchName.setText(teacher);
        holder.dateofClass.setText(number);
        holder.btmMore.setText("Approve");

        holder.endtime.setVisibility(View.INVISIBLE);

        holder.btmMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holidaysResponseList.remove(position);
                clickedItem.ClickedClass(holidaysResponse);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position,holidaysResponseList.size());

            }
        });


    }

    public int getRoomsID(int position){
        Rooms holidaysResponse = holidaysResponseList.get(position);

        int holidaysid = holidaysResponse.getId();

        return holidaysid;

    }

    public interface ClickedItem{
        public void ClickedClass(Rooms holidaysResponse);
    }

    @Override
    public int getItemCount() {
        return holidaysResponseList.size();
    }

    public class AdminAdminRoomsAdapterVH extends RecyclerView.ViewHolder {

        TextView batchName;
        TextView dateofClass,endtime;
        Button btmMore;
        ImageView detailsview;

        public AdminAdminRoomsAdapterVH(@NonNull View itemView) {
            super(itemView);
            batchName = itemView.findViewById(R.id.batchName);
            dateofClass = itemView.findViewById(R.id.dateofClass);
            btmMore = itemView.findViewById(R.id.btmMore);
            detailsview = itemView.findViewById(R.id.imageTvv);
            endtime = itemView.findViewById(R.id.endtimeclass);


        }
    }
}