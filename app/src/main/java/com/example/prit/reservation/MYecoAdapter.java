package com.example.prit.reservation;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MYecoAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] depart;
    private final String[] Arrive;
    private final String[] dura;
    private final String[] price;
    private final Integer[] imgid;

    public MYecoAdapter(Activity context, String[] depart,String[] Arrive, String[] dura, String[] price,Integer[] imgid) {
        super(context, R.layout.my_ecolist, depart);

        this.context=context;
        this.depart=depart;
        this.Arrive=Arrive;
        this.dura=dura;
        this.price=price;
        this.imgid=imgid;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_ecolist,null,true);

        TextView departText = (TextView)rowView.findViewById(R.id.ecodepart);
        TextView arriveText = (TextView)rowView.findViewById(R.id.ecoarrvie);
        TextView duraText = (TextView)rowView.findViewById(R.id.ecodur);
        TextView priceText = (TextView)rowView.findViewById(R.id.ecoprice);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.ecoplan);

        departText.setText(depart[position]);
        arriveText.setText(Arrive[position]);
        duraText.setText(dura[position]);
        priceText.setText(price[position]);
        imageView.setImageResource(imgid[position]);

        return rowView;
    };
}
