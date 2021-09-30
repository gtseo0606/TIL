package kr.co.softcampus.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 뷰의 주소값을 받을 참조변수 선언
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id 속성값이 text2인 뷰 주소값을 불러온다.
        text1 = (TextView)findViewById(R.id.text2);
        // 새로운 문자열을 설정한다.
        text1.setText("새로운 문자열");
    }
}
