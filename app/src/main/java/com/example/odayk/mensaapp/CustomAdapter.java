package com.example.odayk.mensaapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Activity activity;
    List<BestMeals> bMeals;
    LayoutInflater inflater;

    // short to create constroctor Alt + Insert
    public CustomAdapter(Activity activity) {
        this.activity = activity;
    }

    public CustomAdapter(Activity activity, List<BestMeals> bestMeals){
        this.activity = activity;
        this.bMeals = bestMeals;

        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount(){ return bMeals.size();}

    @Override
    public Object getItem(int i){ return i;}

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder viewHolder = null;

        if (view == null){
            view = inflater.inflate(R.layout.list_view_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMealName = (TextView) view.findViewById(R.id.tv_meal_name);
            viewHolder.ivCheckbox = (ImageView) view.findViewById(R.id.iv_check_box);

            view.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder)view.getTag();
        BestMeals bestMeals = bMeals.get(i);
        viewHolder.tvMealName.setText(bestMeals.getMealName());

        if (bestMeals.isSelected())
            viewHolder.ivCheckbox.setBackgroundResource(R.drawable.ic_check_box_black_24dp);
        else
            viewHolder.ivCheckbox.setBackgroundResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        return view;
    }

    public void updateRecords(List<BestMeals> bMeals){
        this.bMeals = bMeals;
        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView tvMealName;
        ImageView ivCheckbox;
    }
}
