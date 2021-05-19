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
import com.application.tms.models.Classes;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.UserAdapterVH> {

    private List<Classes> userResponseList;
    private Context context;
    private ClickedItem clickedItem;

    public ClassesAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<Classes> userResponseList) {
        this.userResponseList = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ClassesAdapter.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.note_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {

        Classes userResponse = userResponseList.get(position);

        String username = userResponse.getDate();
        String batchName = userResponse.getBatch();
        String endTime = userResponse.getEndTime();



        holder.batchName.setText(batchName);
        holder.dateofClass.setText(username);
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedClass(userResponse);
            }
        });

    }

    public int deleteClassCall(int position){
        Classes userResponse = userResponseList.get(position);

        int userid = userResponse.getId();

        return userid;

    }

    public interface ClickedItem{
        public void ClickedClass(Classes userResponse);
    }

    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {

        TextView batchName;
        TextView dateofClass;
        ImageView imageMore;

        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            batchName = itemView.findViewById(R.id.batchName);
            dateofClass = itemView.findViewById(R.id.dateofClass);
            imageMore = itemView.findViewById(R.id.imageMore);


        }
    }
}