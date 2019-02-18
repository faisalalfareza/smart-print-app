package com.faisalalfareza.smartprint;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ListviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<BeanclassList> bean;
    Typeface fonts1,fonts2;
    Activity main;

    public ListviewAdapter( Context context, ArrayList<BeanclassList> bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        fonts1 =  Typeface.createFromAsset(context.getAssets(),
                "fonts/MavenPro-Regular.ttf");

        fonts2 = Typeface.createFromAsset(context.getAssets(),
                "fonts/MavenPro-Regular.ttf");

        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row,null);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.detail = (TextView) convertView.findViewById(R.id.detail);
            viewHolder.title.setTypeface(fonts1);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        BeanclassList bean = (BeanclassList)getItem(position);

        viewHolder.title.setText(bean.getTitle());
        viewHolder.price.setText(bean.getPrice());
        viewHolder.detail.setText(bean.getDetail());

        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView price;
        TextView detail;
    }
}



