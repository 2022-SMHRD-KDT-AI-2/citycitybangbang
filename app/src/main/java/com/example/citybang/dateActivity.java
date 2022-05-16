package com.example.citybang;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class dateActivity extends Activity implements OnClickListener {

    private int myYear, myMonth, myDay;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

//        Button datePickerButton = (Button) findViewById(R.id.autoBtnArea);
//        datePickerButton.setOnClickListener(this);

    }

    public void onClick(View v) {

        final Calendar c = Calendar.getInstance();

        switch (v.getId()) {
            case R.id.autoBtnArea:
                myYear = c.get(Calendar.YEAR);
                myMonth = c.get(Calendar.MONTH);
                myDay = c.get(Calendar.DAY_OF_MONTH);
                Dialog dlgDate = new DatePickerDialog(this, myDateSetListener,
                        myYear, myMonth, myDay);
                dlgDate.show();
                break;

//            case R.id.timepickerbutton:
//                myHour = c.get(Calendar.HOUR_OF_DAY);
//                myMinute = c.get(Calendar.MINUTE);
//                Dialog dlgTime = new TimePickerDialog(this, myTimeSetListener,
//                        myHour, myMinute, false);
//                dlgTime.show();
//                break;

            default:
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener myDateSetListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String date = String.valueOf(year) + "년 " +
                    String.valueOf(monthOfYear + 1) + "월 "
                    + String.valueOf(dayOfMonth) + "일";
            TextView autoTvArea;
            autoTvArea = findViewById(R.id.autoTvArea);
            autoTvArea.setText(date);
        }
    };


}