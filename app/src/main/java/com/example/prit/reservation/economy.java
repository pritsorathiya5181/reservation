package com.example.prit.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class economy extends AppCompatActivity {
    ListView list;
    String[] depart = {"22:25","21:10;","21:30","21:55","21:25","21:35","21:25","21:10","21:10","23:00","22:55"};
    String[] Arrive = {"00:40","07:15","23:45","00:20","21:25","23:50","07:40","23:25","09:50","01:15","02:55"};
    String[] dura = {"2h 15m","10h 05m","2h 15m","2h 25m","  24h  ","2h 15m","10h 15m","26h 15m","12h 40m","2h 15m","3h 12m"};
    String[] price = {"₹ 5452","₹ 6990","₹ 7515","₹ 8199","₹ 10036","₹ 11643","₹ 14289","₹ 15442","₹ 19642","₹ 22642","₹ 26642"};
    Integer[] imgid = {R.drawable.plane,R.drawable.plane,R.drawable.plane,R.drawable.plane,R.drawable.plane,R.drawable.plane,R.drawable.plane,R.drawable.plane,R.drawable.plane,R.drawable.plane,R.drawable.plane};
    EditText book;
    Button btnbook;
    Integer selectimage;
    public static Context contexteco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy);

        contexteco = economy.this;
        MYecoAdapter adapter=new MYecoAdapter(this, depart, Arrive,dura,price,imgid);
        list=(ListView)findViewById(R.id.listeco);
        list.setAdapter(adapter);
        book = (EditText)findViewById(R.id.bookprice);
        btnbook = (Button)findViewById(R.id.ecobook);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                book.setText(price[position]);
                selectimage = imgid[position];
            }
        });
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(economy.this,payment.class));
            }
        });
    }
}
