package com.tomoima.realmendlessview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tomoima.realmendlessview.R;
import com.tomoima.realmendlessview.domain.models.SimpleData;

import java.util.List;


/**
 * Created by tomoaki on 2015/06/01.
 */
public class SimpleArrayAdapter extends ArrayAdapter<SimpleData> {
    LayoutInflater mInflater;
    public SimpleArrayAdapter(Context context, List<SimpleData> dataList) {
        super(context, -1, dataList);
        mInflater = LayoutInflater.from(context);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.row_endless, null);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        convertView.setTag(viewHolder);
        bindView(convertView, position,getItem(position));
        return convertView;
    }

    public void bindView(View view, int position, SimpleData data){
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.idTv.setText(String.valueOf(data.getId()));
        viewHolder.nameTv.setText(data.getName());
    }

    public static class ViewHolder{
        TextView idTv;
        TextView nameTv;

        ViewHolder(View v){
            idTv = (TextView)v.findViewById(R.id.id);
            nameTv = (TextView) v.findViewById(R.id.name);

        }
    }
}
