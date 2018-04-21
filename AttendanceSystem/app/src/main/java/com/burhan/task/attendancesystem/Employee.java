package com.burhan.task.attendancesystem;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by burhan on 12.04.2018.
 */

public class Employee {
    public String name;
    public String dailyWorkHour;
    public String mounthlyWorkHour;
    public static Button button1;
    public static Button button2;
    public TextView workingTime;
    public int workingTimeInt;
    public int workingSeconds;
    public int leaveMinute;
    public int leaveHour;
    public String  selectedReasonText;

    public CountDownTimer a;
    public int reason;
    public int enterHour;
    public int enterMinute;

    public int[] dayOfYears = new int[360];
    public int[] dayOfYearsM= new int[360];


    public String getName(){
        return name;
    }
    Employee(){
        for(int i=0;i<360;i++){
            dayOfYears[i]=0;
        }
        for(int i=0;i<360;i++){
            dayOfYearsM[i]=0;
        }
    }


}
