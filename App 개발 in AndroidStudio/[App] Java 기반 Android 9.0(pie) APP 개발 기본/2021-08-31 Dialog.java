package kr.co.softcampus.basicdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.AlteredCharSequence;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // 참조변수
    TextView text1;
    ProgressDialog pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 주소값
        text1 = (TextView)findViewById(R.id.textView);
        
    }


    public void btn1Method(View view){
        // 다이얼로그 정보를 관리객체
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 제목
        builder.setTitle("기본 다이얼로그");
        // 본문 내용
        builder.setMessage("다이얼로그의 본문입니다.");
        // 아이콘
        builder.setIcon(R.mipmap.ic_launcher);

        // 리스너
        Dialog1Listener listener = new Dialog1Listener();

        //다이얼로그의 버튼
        builder.setPositiveButton("Positive", listener);
        builder.setNeutralButton("Neutral", listener);
        builder.setNegativeButton("Negative", listener);

        builder.show();

    }



    // 리스너
    class Dialog1Listener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            //분기
            switch (i){
                case DialogInterface.BUTTON_POSITIVE:
                    text1.setText("Positive");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    text1.setText("Neutral");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    text1.setText("Negative");
                    break;

            }
        }
    }

    public void Btn2Method(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("커스텀 다이얼로그");
        builder.setIcon(R.mipmap.ic_launcher);

        // 다이얼로그를 통해 보여줄 뷰 생성
        LayoutInflater inflater = getLayoutInflater();
        View v1 = inflater.inflate(R.layout.dialog, null);
        // 생성한 뷰를 다이얼로그 셋팅
        builder.setView(v1);

        builder.setPositiveButton("확인", null);
        builder.setNegativeButton("취소", null);

        builder.show();
    }

    class Dialog2Listener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            // AlertDialog로 형변환
            AlertDialog alert = (AlertDialog)dialog;
            // 뷰 추출
            EditText edit1 = (EditText)alert.findViewById(R.id.editText);
            EditText edit2 = (EditText)alert.findViewById(R.id.editText2);
            // 사용자가 입력한 문자열을 가져온다.
            String str1 = edit1.getText().toString();
            String str2 = edit2.getText().toString();

            text1.setText("edit1 : " + str1 + "\n");
            text1.append("edit2 : " + str2);

        }
    }

    public void btn3Method(View view){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Dialog3Listener listener = new Dialog3Listener();

        DatePickerDialog picker = new DatePickerDialog(this, listener, year, month, day);
        picker.show();
    }

    class Dialog3Listener implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            text1.setText(year + "년 " + (month+1) + "월 " + dayOfMonth + "일");
        }
    }

    public void btn4Method(View view){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        Dialog4Listener listener = new Dialog4Listener();

        TimePickerDialog picker = new TimePickerDialog(this, listener, hour, minute, false);
        picker.show();
    }

    class Dialog4Listener implements TimePickerDialog.OnTimeSetListener{
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            text1.setText(hourOfDay + "시 " + minute + "분");
        }
    }

    public void btn5Method(View view){
        // progressDialog 생성
        pro = ProgressDialog.show(this, "제목입니다.", "본문 메세지입니다.");
        // 5초후 progressDialog 제거를 위해 쓰래드 가동
        Handler handler = new Handler();
        ThreadClass thread = new ThreadClass();
        handler.postDelayed(thread, 5000);
    }

    class ThreadClass extends Thread{
        @Override
        public void run() {
            // g현재 나타나있는 progressDialog 제거
            pro.cancel();
        }
    }


}
