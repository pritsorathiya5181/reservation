package com.example.prit.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class flight_selection extends AppCompatActivity {

    public static String source,so,fullSo;
    public static String Full,dest,fullDest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_selection);

        String Names[]={"Mumbai(BOM)","New Delhi(DEL)","Bengaluru(BLR)","Chennail(MAA)","Kolkata(CCU)","Lucknow(LKO)","Pune(PNQ)","Hyderabad(HYD)","Patan(PAT)","Jaipur(JAI)"};

        ListView LV=(ListView) findViewById(R.id.listView1);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.textView1,Names);

        LV.setAdapter(adapter);

        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View v, int arg2,
                                    long arg3) {
                Context context=booking.context;

                TextView tv = (TextView) v.findViewById(R.id.textView1);
                int a = 0;
                int b = 0;
                if(context!=null)
                {
                    booking book = (booking) context;

                    if (a==(book.s-1)){
                        a=book.s;
                        source=tv.getText().toString();
                        fullSo=tv.getText().toString();
                        source=source.substring(source.length()-4,source.length()-1);
                        fullSo=fullSo.substring(0,fullSo.length()-5);
                    }
                    else if (b==(book.d-1)){
                        b=book.d;
                        dest=tv.getText().toString();
                        fullDest=tv.getText().toString();
                        dest=dest.substring(dest.length()-4,dest.length()-1);
                        fullDest=fullDest.substring(0,fullDest.length()-5);
                    }
                    Toast.makeText(getApplicationContext(),tv.getText().toString(),Toast.LENGTH_SHORT).show();
                    //source=tv.getText().toString();
                    //Full=tv.getText().toString();

                    //Full=Full.substring(0,Full.length()-5);

                    startActivity(new Intent(flight_selection.this,booking.class));
                }
            }
        });
    }
}
