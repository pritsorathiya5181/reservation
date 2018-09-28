package com.example.prit.reservation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class payment extends AppCompatActivity {

    TextView adult,child,infa,tvsource,tvdest,flightname,sourceF,destF,stime,dtime,tvbase,tvother,tvirctc,tvgst,tvtotal,rsource,rdest;
    ImageView image;
    Button continuebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        adult = (TextView)findViewById(R.id.tvadult);
        child = (TextView)findViewById(R.id.tvchild);
        infa = (TextView)findViewById(R.id.tvinf);
        image = (ImageView)findViewById(R.id.setimage);
        tvsource = (TextView)findViewById(R.id.tvsetsource);
        tvdest = (TextView)findViewById(R.id.tvsetDest);
        flightname = (TextView)findViewById(R.id.setPlanename);
        sourceF = (TextView)findViewById(R.id.setSfullname);
        destF = (TextView)findViewById(R.id.setDfullname);
        stime = (TextView)findViewById(R.id.setStime);
        dtime = (TextView)findViewById(R.id.setDtime);
        tvbase = (TextView)findViewById(R.id.baseP);
        tvother = (TextView)findViewById(R.id.otherP);
        tvirctc = (TextView)findViewById(R.id.irctcP);
        tvgst = (TextView)findViewById(R.id.gstP);
        tvtotal = (TextView)findViewById(R.id.totalP);
        rsource = (TextView)findViewById(R.id.setSname);
        rdest = (TextView)findViewById(R.id.setDname);
        continuebook = (Button)findViewById(R.id.continueb);

        Context contextBook = booking.context;
        Context contextecon = economy.contexteco;
        Context contextbus = Business.contextBus;
        Context contextprem = premium.contextpre;

        if (contextBook!=null){
            booking booking = (booking) contextBook;
            tvsource.setText(booking.tvS.getText().toString());
            tvdest.setText(booking.tvD.getText().toString());
            sourceF.setText(booking.tvfs.getText().toString());
            destF.setText(booking.tvfd.getText().toString());
            rsource.setText(booking.tvS.getText().toString());
            rdest.setText(booking.tvD.getText().toString());
            stime.setText(booking.tvDepart.getText().toString());
            dtime.setText(booking.tvDepart.getText().toString());
        }
        if (contextecon!=null){
            economy eco = (economy) contextecon;
            image.setImageResource(eco.selectimage.intValue());
            tvbase.setText("₹ "+eco.book.getText().toString().substring(2,eco.book.getText().toString().length()));
        }

        if (contextbus!=null){
            Business bus = (Business) contextbus;
            image.setImageResource(bus.selectBimage.intValue());
            tvbase.setText("₹ "+bus.book.getText().toString().substring(2,bus.book.getText().toString().length()));
        }

        if (contextprem!=null){
            premium pre = (premium) contextprem;
            image.setImageResource(pre.selectPimage.intValue());
            tvbase.setText("₹ "+pre.book.getText().toString().substring(2,pre.book.getText().toString().length()));
        }

        int gst = Integer.parseInt(String.valueOf(tvbase.getText().toString().substring(2,tvbase.getText().toString().length())));
        gst = (int) (gst*0.18);
        tvgst.setText("₹ "+String.valueOf(gst));
        int total;
        total = Integer.parseInt(String.valueOf(tvbase.getText().toString().substring(2,tvbase.getText().toString().length()))) +
                Integer.parseInt(String.valueOf(tvother.getText().toString().substring(2,tvother.getText().toString().length()))) +
                Integer.parseInt(String.valueOf(tvirctc.getText().toString().substring(2,tvirctc.getText().toString().length()))) + gst ;
        tvtotal.setText("₹ "+String.valueOf(total));
    }
}
