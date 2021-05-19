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
import com.application.tms.activities.TeacherReserveRoomsActivity;
import com.application.tms.models.Rooms;

import java.util.List;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomsAdapterVH> {

    private List<Rooms> roomsResponseList;
    boolean hidebtns = false;
    private Context context;
    private ClickedItem clickedItem;
    private TeacherReserveRoomsActivity teacherReserveRoomsActivity;
    private boolean isRequested = false;

    public RoomsAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<Rooms> roomsResponseList,String hide) {
        this.roomsResponseList = roomsResponseList;
        if(hide.equals("hide")){
            hidebtns = true;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new RoomsAdapter.RoomsAdapterVH(LayoutInflater.from(context).inflate(R.layout.room_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsAdapterVH holder, int position) {

        Rooms roomsResponse = roomsResponseList.get(position);

        String number = roomsResponse.getNumber();
        String start = roomsResponse.getStartTime();
        String end = roomsResponse.getEndTime();
        String teacher = roomsResponse.getBookedTeacher();


        holder.batchName.setText(number);
        holder.dateofClass.setText(start);
        holder.endclasstime.setText(end);

        holder.btmMore.setText("Request");



        if(hidebtns){
            holder.btmMore.setVisibility(View.INVISIBLE);

            holder.teacherbtn.setVisibility(View.VISIBLE);
            holder.teacherbtn.setText(teacher);
        }

        holder.btmMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickedItem.ClickedClass(roomsResponse);
                if(!isRequested){

                    isRequested=true;
                }else{

                    isRequested=false;
                }

            }
        });


    }

    public int getRoomsID(int position){
        Rooms roomsResponse = roomsResponseList.get(position);

        int roomsid = roomsResponse.getId();

        return roomsid;

    }

    public interface ClickedItem{
        public void ClickedClass(Rooms roomsResponse);
    }

    @Override
    public int getItemCount() {
        return roomsResponseList.size();
    }

    public class RoomsAdapterVH extends RecyclerView.ViewHolder {

        TextView batchName;
        TextView dateofClass,endclasstime;
        Button btmMore,teacherbtn;
        ImageView detailsview;

        public RoomsAdapterVH(@NonNull View itemView) {
            super(itemView);
            batchName = itemView.findViewById(R.id.batchName);
            dateofClass = itemView.findViewById(R.id.dateofClass);
            btmMore = itemView.findViewById(R.id.btmMore);
            detailsview = itemView.findViewById(R.id.imageTvv);
            endclasstime = itemView.findViewById(R.id.endtimeclass);
            teacherbtn = itemView.findViewById(R.id.teacherbtn);



        }
    }
}