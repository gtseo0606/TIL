package kr.co.softcampus.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1Method (View view){
        // 토스트 객체 생성
        Toast t1 = Toast.makeText(this, "토스트 메세지입니다.", Toast.LENGTH_SHORT);
        // 토스트 메세지를 운영체제에 요청
        t1.show();
    }

    public void btn2Method(View view){
        // 토스트를 통해 보여줄 뷰 객체
        LayoutInflater inflater = getLayoutInflater();
        View v1 = inflater.inflate(R.layout.custom_toast, null);

        // 뷰 추출
        ImageView img1 = (ImageView)v1.findViewById(R.id.imageView2);
        TextView text1 = (TextView)v1.findViewById(R.id.textView);
        // 뷰 배경을 토스트 배경으로 바꾼다.
        v1.setBackgroundResource(android.R.drawable.toast_frame);

        img1.setImageResource(R.drawable.img_android);
        text1.setText("커스텀 토스트");
        text1.setTextColor(Color.BLUE);

        // 생성한 뷰를 토스트에 설정
        Toast t2 = new Toast(this);
        t2.setView(v1);

        // 토스트 메세지의 위치
        t2.setGravity(Gravity.CENTER, 0, 100);

        // 시간 설정
        t2.setDuration(Toast.LENGTH_SHORT);

        t2.show();
    }
}
