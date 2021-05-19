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
import com.application.tms.activities.TeacherHolidaysActivity;
import com.application.tms.models.Holidays;
import com.application.tms.models.Holidays;

import java.util.List;

public class HolidaysAdapter extends RecyclerView.Adapter<HolidaysAdapter.AdminHolidaysAdapterVH> {

    private List<Holidays> holidaysResponseList;
    private Context context;
    private ClickedItem clickedItem;
    private TeacherHolidaysActivity teacherHolidaysActivity;
    private boolean isRequested = false;

    public HolidaysAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<Holidays> holidaysResponseList) {
        this.holidaysResponseList = holidaysResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdminHolidaysAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new HolidaysAdapter.AdminHolidaysAdapterVH(LayoutInflater.from(context).inflate(R.layout.room_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHolidaysAdapterVH holder, int position) {

        Holidays holidaysResponse = holidaysResponseList.get(position);

        String number = holidaysResponse.getHolidayDate();
        String availability = holidaysResponse.getHolidayName();

        holder.batchName.setText(number);
        holder.dateofClass.setText(availability);
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

    public int getHolidaysID(int position){
        Holidays holidaysResponse = holidaysResponseList.get(position);

        int holidaysid = holidaysResponse.getId();

        return holidaysid;

    }

    public interface ClickedItem{
        public void ClickedClass(Holidays holidaysResponse);
    }

    @Override
    public int getItemCount() {
        return holidaysResponseList.size();
    }

    public class AdminHolidaysAdapterVH extends RecyclerView.ViewHolder {

        TextView batchName;
        TextView dateofClass,endtime;
        Button btmMore;
        ImageView detailsview;

        public AdminHolidaysAdapterVH(@NonNull View itemView) {
            super(itemView);
            batchName = itemView.findViewById(R.id.batchName);
            dateofClass = itemView.findViewById(R.id.dateofClass);
            btmMore = itemView.findViewById(R.id.btmMore);
            detailsview = itemView.findViewById(R.id.imageTvv);
            endtime = itemView.findViewById(R.id.endtimeclass);


        }
    }
}