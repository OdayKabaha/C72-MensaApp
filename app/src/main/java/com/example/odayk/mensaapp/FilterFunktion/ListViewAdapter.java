package com.example.odayk.mensaapp.FilterFunktion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.odayk.mensaapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListViewAdapter extends BaseAdapter {

    Context cont;
    LayoutInflater layinf;
    List<MealName> meallist;
    ArrayList<MealName> mealarray;

    public ListViewAdapter(Context con, List<MealName> meal){
        cont = con;
        meallist = meal;
        this.layinf = LayoutInflater.from(cont);
        this.mealarray = new ArrayList<MealName>();
        this.mealarray.addAll(meal);
    }

    public class ViewHolder {
        TextView textView;
    }

    @Override
    public int getCount() {
        return meallist.size();
    }

    @Override
    public MealName getItem(int position) {
        return meallist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = layinf.inflate(R.layout.model_item, null);
            holder.textView = (TextView)convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setText(meallist.get(position).getName());

        return convertView;
    }

    public void myFilter(String name){
        name = name.toLowerCase(Locale.getDefault());
        meallist.clear();
        if (name.length() == 0){
            meallist.addAll(mealarray);
        } else {
            for (MealName PL : mealarray){
                if (PL.getName().toLowerCase(Locale.getDefault()).contains(name)){
                    meallist.add(PL);
                }
            }
        }
        notifyDataSetChanged();
    }
}
