package com.example.prit.reservation;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import java.util.Calendar;

public class booking extends AppCompatActivity implements View.OnClickListener {

    TextView tvS,tvD,tvfs,tvfd,tvReturn,tvDepart,tvadult,tvchild,tvinfant;
    public static Context context;
    int s=0,d=0;
    Button btnR,btnO,btnS;
    RelativeLayout relativeR,relativeD;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ImageView am,cm,im,ap,cp,ip;
    int a=0,c=0,i=0;
    private Spinner spinner;
    String search;
    private FirebaseAuth firebaseAuth;
    private Button btnout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        context=booking.this;
        relativeR = (RelativeLayout)findViewById(R.id.returnDate);
        relativeD = (RelativeLayout)findViewById(R.id.DepartDate);
        tvS = (TextView)findViewById(R.id.tvSource);
        tvD = (TextView)findViewById(R.id.tvDest);
        tvfs = (TextView)findViewById(R.id.tvSname);
        tvfd = (TextView)findViewById(R.id.tvDname);
        tvadult = (TextView)findViewById(R.id.textadult);
        tvchild = (TextView)findViewById(R.id.textchild);
        tvinfant= (TextView)findViewById(R.id.textinfants);
        btnR = (Button)findViewById(R.id.btnreset);
        btnO = (Button)findViewById(R.id.btnoneway);
        tvReturn = (TextView)findViewById(R.id.txtReturS);
        tvDepart = (TextView)findViewById(R.id.txtDepS);
        am = (ImageView)findViewById(R.id.aM);
        cm = (ImageView)findViewById(R.id.cM);
        im = (ImageView)findViewById(R.id.iM);
        ap = (ImageView)findViewById(R.id.aP);
        cp = (ImageView)findViewById(R.id.cP);
        ip = (ImageView)findViewById(R.id.iP);
        spinner = (Spinner)findViewById(R.id.spinnerClass);
        btnS = (Button)findViewById(R.id.btnSearch);
        btnout = (Button)findViewById(R.id.btnlogout);
        firebaseAuth = FirebaseAuth.getInstance();
        btnout.setOnClickListener(this);

        btnO.setOnClickListener(this);
        btnR.setOnClickListener(this);

        tvS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s++;
                startActivity(new Intent(booking.this,flight_selection.class));

            }
        });

        try
        {
            tvS.setText(flight_selection.source.toString());
            tvfs.setText(flight_selection.fullSo.toString());

        }

        catch (Exception e)
        { }

        tvD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d++;
                startActivity(new Intent(booking.this,flight_selection.class));
            }
        });
        try{
            tvD.setText(flight_selection.dest.toString());
            tvfd.setText(flight_selection.fullDest.toString());
        }
        catch (Exception e){

        }
        relativeD.setOnClickListener(this);
        relativeR.setOnClickListener(this);
        cp.setOnClickListener(this);
        ap.setOnClickListener(this);
        ip.setOnClickListener(this);
        cm.setOnClickListener(this);
        am.setOnClickListener(this);
        im.setOnClickListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                search = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(),
                        "OnItemSelectedListener : " + search,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnS.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnoneway: {
                relativeR.setClickable(false);
                btnO.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                btnR.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                relativeR.setBackgroundColor(ContextCompat.getColor(booking.this, R.color.unselect));
                break;
            }
            case R.id.btnreset: {
                relativeR.setClickable(true);
                btnO.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                btnR.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                relativeR.setBackgroundColor(ContextCompat.getColor(booking.this, R.color.select));
                break;
            }
            case R.id.returnDate: {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tvReturn.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            }
            case R.id.DepartDate: {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tvDepart.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            }
            case R.id.cP: {
                tvchild.setText(Integer.toString(c++));
                break;
            }
            case R.id.aP: {
                tvadult.setText(Integer.toString(a++));
                break;
            }
            case R.id.iP: {
                tvinfant.setText(Integer.toString(i++));
                break;
            }
            case R.id.aM: {
                if (a == 0)
                    am.setEnabled(false);
                else
                    tvadult.setText(Integer.toString(a--));
                break;
            }

            case R.id.iM: {
                if (i == 0)
                    im.setEnabled(false);
                else
                    tvinfant.setText(Integer.toString(i--));
                break;
            }
            case R.id.cM: {
                if (c == 0)
                    cm.setEnabled(false);
                else
                    tvchild.setText(Integer.toString(c--));
                break;
            }
            case R.id.btnSearch: {
                if (search.equals("Economy")) {
                    startActivity(new Intent(booking.this, economy.class));
                } else if (search.equals("Business")) {
                    startActivity(new Intent(booking.this, Business.class));
                } else {
                    startActivity(new Intent(booking.this, premium.class));
                }
                break;
            }
            case R.id.btnlogout:{
                Logout();
                break;
            }
        }
    }

    public void minus(View view) {

        switch(view.getId()){
            case R.id.aM:{
                if (a==0){
                    am.setClickable(false);
                }
                else{
                    am.setClickable(true);
                    tvadult.setText(a--);
                }
                break;
            }
            case R.id.cM:{
                if (a==0){
                    cm.setClickable(false);
                }
                else{
                    cm.setClickable(true);
                    tvadult.setText(c--);
                }
                break;
            }
            case R.id.iM:{
                if (a==0){
                    im.setClickable(false);
                }
                else{
                    im.setClickable(true);
                    tvadult.setText(i--);
                }
                break;
            }
        }
    }
    public void Plus(View view){
        switch (view.getId()){
            case R.id.aP:{
                if (a==0){
                    am.setClickable(false);
                }
                else{
                    am.setClickable(true);
                    tvadult.setText(a++);
                }
                break;
            }
            case R.id.cP:{

            }
            case R.id.iP:{
                if (a==0){
                    im.setClickable(false);
                }
                else{
                    im.setClickable(true);
                    tvadult.setText(i++);
                }
                break;
            }
        }
    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(booking.this,MainActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutmenu:
                Logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
