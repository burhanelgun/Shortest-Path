package com.burhan.task.attendancesystem;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import static com.burhan.task.attendancesystem.Employee.button2;

public class MainActivity extends AppCompatActivity {

    public Employee[] e = new Employee[10];
    public int l;
    int workingSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        for(int j =0;j<10;j++){
            e[j]= new Employee();
        }
        giveName(5);

        ListView listView = (ListView) findViewById(R.id.listView1);
        CustomAdapter customAdapter = new CustomAdapter();
        //listeyi customlayouttaki şekilde oluşturdum hepsini
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        return true;
    }

    public String giveName(int i){
        e[0].name="John";
        e[1].name="Mark";
        e[2].name="Sasha";
        e[3].name="Julıa";
        e[4].name="Adam";
        e[5].name="Jack";
        e[6].name="Martınez";
        e[7].name="Rıck";
        e[8].name="Carol";
        e[9].name="Elızabeth";

        return e[i].name;

    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return e.length ;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_layout,null);
            e[i].button1 = (Button) view.findViewById(R.id.button);
            e[i].button2 = (Button) view.findViewById(R.id.button2);

            e[i].button1.setText(e[i].getName());

            for (int j = 0;j<10;j++)
                e[i].button2.setText("i");
            for (int j = 0;j<10;j++)
                e[i].button1.setTag(i);
            for (int j = 0;j<10;j++)
                e[i].button2.setTag(i);


            e[i].button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=(Integer) e[i].button1.getTag();

                    l=i;

                    setContentView(R.layout.exitorenter);
                    TextView name = (TextView)findViewById(R.id.name);
                    name.setText(e[l].getName());

                    e[l].workingTime=(TextView)findViewById(R.id.workingTime);
                    TextView hourTime= (TextView)findViewById(R.id.hourEnterTime);

                    Button enter= (Button)findViewById(R.id.enterButton);
                    Button leave= (Button)findViewById(R.id.leaveButton);


                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    hourTime.setText(sharedPref.getInt("e["+l+"].enterHour",0)+":"+sharedPref.getInt("e["+l+"].enterMinute",0));
                   // e[l].workingTime.setText(""+sharedPref.getInt("e["+l+"].workingTimes",0)+" hours");


                    enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TextView hourTime= (TextView)findViewById(R.id.hourEnterTime);
                            TextView minuteTime= (TextView)findViewById(R.id.minuteEnterTime);

                            Calendar rightNow=Calendar.getInstance();
                            //int hour=rightNow.get(Calendar.HOUR_OF_DAY);
                            //int minute=rightNow.get(Calendar.MINUTE);


                            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("e["+l+"].enterHour",rightNow.get(Calendar.HOUR_OF_DAY)); //int değer ekleniyor
                            editor.putInt("e["+l+"].enterMinute",rightNow.get(Calendar.MINUTE)); //int değer ekleniyor
                            editor.commit();

                            hourTime.setText(sharedPref.getInt("e["+l+"].enterHour",0)+":"+sharedPref.getInt("e["+l+"].enterMinute",0));


                            e[l].a=new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                  /*  SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putInt("e["+l+"].workingTimes",(int)(30000-millisUntilFinished) / 1000); //int değer ekleniyor
                                    //editor.putInt("e["+l+"].enterMinute",rightNow.get(Calendar.MINUTE)); //int değer ekleniyor
                                    editor.commit();

                                    e[l].workingTime.setText(""+(30000-millisUntilFinished) / 1000+" hours");
                                    e[l].workingSeconds=(int)(30000-millisUntilFinished) / 1000;*/
                                }

                                public void onFinish() {
                                }

                            }.start();

                        }
                    });
                    RadioButton radioButton=findViewById(R.id.radioButton);
                    RadioButton radioButton2=findViewById(R.id.radioButton2);
                    RadioButton radioButton3=findViewById(R.id.radioButton3);

                    if (/*radioButton.isChecked() || radioButton2.isChecked() || radioButton3.isChecked() || radioButton4.isChecked()*/ true){

                        // one of the radio buttons is checked
                      /*  RadioGroup radioGroup=findViewById(R.id.radioGroup);

                        int radioButtonID = radioGroup.getCheckedRadioButtonId();

                        RadioButton radioButtonn = (RadioButton) radioGroup.findViewById(radioButtonID);

                        final String selectedtext = (String) radioButtonn.getText();*/

                        leave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //e[l].a.cancel();
                                RadioButton radioButton=findViewById(R.id.radioButton);
                                RadioButton radioButton2=findViewById(R.id.radioButton2);
                                RadioButton radioButton3=findViewById(R.id.radioButton3);
                                String a =null;
                                if (radioButton.isChecked() || radioButton2.isChecked() || radioButton3.isChecked()) {
                                    RadioGroup radioGroup=findViewById(R.id.radioGroup);

                                    int radioButtonID = radioGroup.getCheckedRadioButtonId();

                                    RadioButton radioButtonn = (RadioButton) radioGroup.findViewById(radioButtonID);

                                    final String selectedtext = (String) radioButtonn.getText();
                                    a=selectedtext;
                                }
                                else{
                                    a="";
                                }
                                Time today = new Time(Time.getCurrentTimezone());
                                today.setToNow();

                                storeIntArray("e[" + l + "].dayOfYears", e[l].dayOfYears);
                                storeIntArray("e[" + l + "].dayOfYearsM", e[l].dayOfYears);


                                TextView hourTime = (TextView) findViewById(R.id.hourLeaveTime);
                                TextView minuteTime = (TextView) findViewById(R.id.minuteLeaveTime);

                                Calendar rightNow = Calendar.getInstance();
                                //int hour=rightNow.get(Calendar.HOUR_OF_DAY);
                                //int minute=rightNow.get(Calendar.MINUTE);

                                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putInt("e[" + l + "].leaveHour", rightNow.get(Calendar.HOUR_OF_DAY)); //int değer ekleniyor
                                editor.putInt("e[" + l + "].leaveMinute", rightNow.get(Calendar.MINUTE)); //int değer ekleniyor
                                editor.putString("e[" + l +"].selectedReasonText", a); //int değer ekleniyor


                                editor.commit();
                                int leaveTime = sharedPref.getInt("e[" + l + "].leaveMinute", 0);
                                int enterTime = sharedPref.getInt("e[" + l + "].enterMinute", 0);

                                hourTime.setText(rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE));
                                if(sharedPref.getInt("e[" + l + "].leaveMinute", 0)>=sharedPref.getInt("e[" + l + "].enterMinute", 0)) {
                                    e[l].workingTime.setText("" + (sharedPref.getInt("e[" + l + "].leaveHour", 0) - sharedPref.getInt("e[" + l + "].enterHour", 0)) + " hours " + (sharedPref.getInt("e[" + l + "].leaveMinute", 0) - sharedPref.getInt("e[" + l + "].enterMinute", 0)) + " minutes");
                                    editor.putInt("e[" + l + "].dayOfYears[today.monthDay+today.month*30]", (sharedPref.getInt("e[" + l + "].leaveHour", 0) - sharedPref.getInt("e[" + l + "].enterHour", 0))); //int değer ekleniyor
                                    editor.putInt("e[" + l + "].dayOfYearsM[today.monthDay+today.month*30]", (sharedPref.getInt("e[" + l + "].leaveMinute", 0) - sharedPref.getInt("e[" + l + "].enterMinute", 0))); //int değer ekleniyor

                                }
                                else{
                                    e[l].workingTime.setText("" + (sharedPref.getInt("e[" + l + "].leaveHour", 0) - sharedPref.getInt("e[" + l + "].enterHour", 0)-1) + " hours " + (sharedPref.getInt("e[" + l + "].leaveMinute", 0) - sharedPref.getInt("e[" + l + "].enterMinute", 0)+60) + " minutes");
                                    editor.putInt("e[" + l + "].dayOfYears[today.monthDay+today.month*30]", (sharedPref.getInt("e[" + l + "].leaveHour", 0) - sharedPref.getInt("e[" + l + "].enterHour", 0))-1); //int değer ekleniyor
                                    editor.putInt("e[" + l + "].dayOfYearsM[today.monthDay+today.month*30]", (sharedPref.getInt("e[" + l + "].leaveMinute", 0) - sharedPref.getInt("e[" + l + "].enterMinute", 0))+60); //int değer ekleniyor

                                }
                                    //e[l].workingTime.setText("" + (sharedPref.getInt("e[" + l + "].leaveMinute", 0) - sharedPref.getInt("e[" + l + "].enterMinute", 0)) + " minutes");

                                editor.commit();

                                //e[l].dayOfYears[today.monthDay+today.month*30]=(sharedPref.getInt("e["+l+"].leaveMinute",0)-sharedPref.getInt("e["+l+"].enterMinute",0));
                                //minuteTime.setText(minute+"");


                            }
                        });
                    }

                    android.support.v7.app.ActionBar actionBar;

                    actionBar = getSupportActionBar();

                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeButtonEnabled(true);
                }
            });

            e[i].button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=(Integer) e[i].button2.getTag();

                    l=i;
                    Time today = new Time(Time.getCurrentTimezone());
                    today.setToNow();

                    setContentView(R.layout.i);
                    android.support.v7.app.ActionBar actionBar;

                    actionBar = getSupportActionBar();

                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeButtonEnabled(true);

                    TextView a1 = findViewById(R.id.a1);
                    TextView a2 = findViewById(R.id.a2);
                    TextView a3 = findViewById(R.id.a3);
                    TextView a4 = findViewById(R.id.a4);
                    TextView a5 = findViewById(R.id.a5);
                    TextView a6 = findViewById(R.id.a6);
                    TextView a7 = findViewById(R.id.a7);

                    TextView name1 = findViewById(R.id.name1);

                    name1.setText(" for "+e[l].getName());

                    e[l].dayOfYears=getFromPrefs("e["+l+"].dayOfYears");


                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    a7.setText(String.valueOf(today.monthDay-6)+"."+String.valueOf(today.month+1)+".2018 :"+String.valueOf((sharedPref.getInt("e["+l+"].dayOfYears[today.monthDay+today.month*30-5]",0))+" hours   Reason : "+"\n"));//"No name defined" is the default value.+"\n"));
                    a6.setText(String.valueOf(today.monthDay-5)+"."+String.valueOf(today.month+1)+".2018 :"+String.valueOf((sharedPref.getInt("e["+l+"].dayOfYears[today.monthDay+today.month*30-4]",0))+" hours   Reason : "+"\n"));
                    a5.setText(String.valueOf(today.monthDay-4)+"."+String.valueOf(today.month+1)+".2018 :"+String.valueOf((sharedPref.getInt("e["+l+"].dayOfYears[today.monthDay+today.month*30-3]",0))+" hours   Reason : "+"\n"));
                    a4.setText(String.valueOf(today.monthDay-3)+"."+String.valueOf(today.month+1)+".2018 :"+String.valueOf((sharedPref.getInt("e["+l+"].dayOfYears[today.monthDay+today.month*30-2]",0))+" hours   Reason : "+"\n"));
                    a3.setText(String.valueOf(today.monthDay-2)+"."+String.valueOf(today.month+1)+".2018 :"+String.valueOf((sharedPref.getInt("e["+l+"].dayOfYears[today.monthDay+today.month*30-2]",0))+" hours   Reason : "+"\n"));
                    a2.setText(String.valueOf(today.monthDay-1)+"."+String.valueOf(today.month+1)+".2018 :"+String.valueOf((sharedPref.getInt("e["+l+"].dayOfYears[today.monthDay+today.month*30-1]",0))+" hours   Reason : "+"\n"));
                    a1.setText(String.valueOf(today.monthDay)+"."+String.valueOf(today.month+1)+".2018 :"+String.valueOf((sharedPref.getInt("e["+l+"].dayOfYears[today.monthDay+today.month*30]",0))+" hours " + sharedPref.getInt("e["+l+"].dayOfYearsM[today.monthDay+today.month*30]",0) + " minutes" +" ==> Reason : "+sharedPref.getString("e[" + l +"].selectedReasonText", " ")+"\n"));
                }
            });
            return view;

        }
        public void storeIntArray(String name, int[] array){
            SharedPreferences.Editor edit= getSharedPreferences("NAME", Context.MODE_PRIVATE).edit();
            edit.putInt("Count_" + name, array.length);
            int count = 0;
            for (int i: array){
                edit.putInt("IntValue_" + name + count++, i);
            }
            edit.commit();
        }
        public int[] getFromPrefs(String name){
            int[] ret;
            SharedPreferences prefs =getSharedPreferences("NAME", Context.MODE_PRIVATE);
            int count = prefs.getInt("Count_" + name, 0);
            ret = new int[count];
            for (int i = 0; i < count; i++){
                ret[i] = prefs.getInt("IntValue_"+ name + i, i);
            }
            return ret;
        }

    }


}
