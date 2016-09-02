package com.aipl.leecodemoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.MyViewHolder>{

        private List<Competition> competitionList;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView brand;
            public EditText comments;

            public MyViewHolder(View view) {
                super(view);
                brand = (TextView) view.findViewById(R.id.brand);
                comments=(EditText) view.findViewById(R.id.comments);
            }
        }


        public CompetitionAdapter(List<Competition> competitionList) {
            this.competitionList = competitionList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.competition_list, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Competition competition = competitionList.get(position);
            holder.brand.setText(competition.getBrand());
            holder.comments.setText(competition.getComments());
        }

        @Override
        public int getItemCount()
        {
            return competitionList.size();
        }

    }

