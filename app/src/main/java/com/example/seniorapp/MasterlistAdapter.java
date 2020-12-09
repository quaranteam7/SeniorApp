package com.example.seniorapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MasterlistAdapter extends RecyclerView.Adapter<MasterlistAdapter.ImageViewHolder> {

    private Context mContext;
    private ArrayList<Members> mMembers;
    private LayoutInflater inflater;

    public MasterlistAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Members> getmMembers() {
        return mMembers;
    }

    public void setmMembers(ArrayList<Members> mMembers) {
        this.mMembers = mMembers;
    }

    public MasterlistAdapter(Context context, ArrayList<Members> members){
        mContext = context;
        mMembers = members;

//        inflater = LayoutInflater.from(mContext);
//        this.mMembers = members;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.master_list, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Members members = mMembers.get(position);
        holder.nameView.setText(members.getBirthday());
        holder.birthdayView.setText(members.getGender());
        holder.genderView.setText(members.getMunicipality());
        holder.municipalityView.setText(members.getBarangay());
        holder.barangayView.setText(members.getZone());
        holder.zoneView.setText(members.getmImageUrl());
        Glide.with(mContext)
                .load(mMembers.get(position).getFullname())
                .into(holder.pictureView);

        ImageViewHolder imageViewHolder = (ImageViewHolder)holder;
        imageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("key", members);
//                intent.putExtras(bundle);
//                v.getContext().startActivity(intent);

                Intent profileActivity = new Intent(mContext, ProfileActivity.class);
                profileActivity.putExtra(ProfileActivity.MEMBER, mMembers.get(position));
                mContext.startActivity(profileActivity);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView nameView, birthdayView, genderView, municipalityView, barangayView, zoneView;
        public ImageView pictureView;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.nameView);
            birthdayView = itemView.findViewById(R.id.birthdayView);
            genderView = itemView.findViewById(R.id.genderView);
            municipalityView = itemView.findViewById(R.id.municipalityView);
            barangayView = itemView.findViewById(R.id.barangayView);
            zoneView = itemView.findViewById(R.id.zoneView);
            pictureView = itemView.findViewById(R.id.pictureView);


        }
    }


}
