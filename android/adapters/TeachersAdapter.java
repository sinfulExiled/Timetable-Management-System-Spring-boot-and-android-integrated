package com.application.tms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.tms.R;
import com.application.tms.models.Teachers;

import java.util.List;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.TeachersAdapterVH> {

    private List<Teachers> teachersResponseList;
    private Context context;
    private ClickedItem clickedItem;

    public TeachersAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<Teachers> teachersResponseList) {
        this.teachersResponseList = teachersResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeachersAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new TeachersAdapter.TeachersAdapterVH(LayoutInflater.from(context).inflate(R.layout.note_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeachersAdapterVH holder, int position) {

        Teachers teachersResponse = teachersResponseList.get(position);

        String teachersname = teachersResponse.getUserName();
        String batchName = teachersResponse.getTeachingModel();




        holder.batchName.setText(batchName);
        holder.dateofClass.setText(teachersname);
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedClass(teachersResponse);
            }
        });

    }

    public int deleteClassCall(int position){
        Teachers teachersResponse = teachersResponseList.get(position);

        int teachersid = teachersResponse.getId();

        return teachersid;

    }

    public interface ClickedItem{
        public void ClickedClass(Teachers teachersResponse);
    }

    @Override
    public int getItemCount() {
        return teachersResponseList.size();
    }

    public class TeachersAdapterVH extends RecyclerView.ViewHolder {

        TextView batchName;
        TextView dateofClass;
        ImageView imageMore;

        public TeachersAdapterVH(@NonNull View itemView) {
            super(itemView);
            batchName = itemView.findViewById(R.id.batchName);
            dateofClass = itemView.findViewById(R.id.dateofClass);
            imageMore = itemView.findViewById(R.id.imageMore);


        }
    }
}