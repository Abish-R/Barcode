package com.aipl.leecodemoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by INDP on 16-Jun-16.
 */
public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MyViewHolder> {
    private List<GetSetAttandance> items;
    Context context;

    public MainPageAdapter(Context con, List<GetSetAttandance> itemslist) {
        context=con;
        this.items = itemslist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowitem, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView barcode,title;
        Button edit;

        public MyViewHolder(View view) {
            super(view);
            barcode = (TextView) view.findViewById(R.id.barcode);
            title = (TextView) view.findViewById(R.id.title);
            edit = (Button) view.findViewById(R.id.edit);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Ready to edit",Toast.LENGTH_SHORT).show();
                }
            });
        }

//        @Override
//        public void onClick(View v) {
//            int position = (int) v.getTag();
//            switch (position) {
//                case 0:
//                    Toast.makeText(context, v.getTag() + "", Toast.LENGTH_SHORT).show();
//                    context.startActivity(new Intent(context, CricketActivity.class));
//                    break;
//                case 1:
//                    context.startActivity(new Intent(context, UrlHandlerActivity.class));
//                    Toast.makeText(context, v.getTag() + "", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//
//        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if(position>0 && items.get(position).getAttandenceDate().equals(""))
            holder.title.setVisibility(View.GONE);
        else
            holder.title.setText(items.get(position).getAttandenceDate());

        holder.barcode.setText(items.get(position).getBarcode());
    }

    private void animate(View view, final int pos) {
        view.animate().cancel();
        view.setTranslationX(-200);
        view.setTranslationY(300);
        view.setAlpha(0);
        view.animate().alpha(1.0f).translationX(0).translationY(0).setDuration(500).setStartDelay(100);//pos * 100
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
