package kr.co.softcampus.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioButton radio3, radio4;
    RadioGroup group1, group2;
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radio3 = (RadioButton)findViewById(R.id.radioButton3);
        radio4 = (RadioButton)findViewById(R.id.radioButton4);
        group1 = (RadioGroup)findViewById(R.id.group1);
        group2 = (RadioGroup)findViewById(R.id.group2);
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

        // 라디오 그룹에 리스너 설정
        RadioListener listener = new RadioListener;
        group1.setOnCheckedChangeListener(listener);
        group2.setOnCheckedChangeListener(listener);

    }

    public void btn1Method(View view){
        radio3.setChecked(true);
        radio4.setChecked(true);
    }

    public void btn2Method(View view){
        // 각 라디오 그룹내에서 선택되어 있는 라디오버튼의 id값을 가져온다.
        int id1 = group1.getCheckedRadioButtonId();
        int id2 = group2.getCheckedRadioButtonId();

        switch (id1) {
            case R.id.radioButton:
                text1.setText("1-1 라디오버튼이 선택되었습니다.");
                break;

            case R.id.radioButton2:
                text1.setText("1-2 라디오버튼이 선택되었습니다.");
                break;

            case R.id.radioButton3:
                text1.setText("1-3 라디오버튼이 선택되었습니다.");
                break;
        }

        switch (id2){
            case R.id.radioButton4:
                text2.setText("2-1 라디오버튼이 선택되었습니다.");
                break;

            case R.id.radioButton5:
                text2.setText("2-2 라디오버튼이 선택되었습니다.");
                break;

            case R.id.radioButton6:
                text2.setText("2-3 라디오버튼이 선택되었습니다.");
                break;
        }
    }

    class RadioListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            // 체크 상태가 변경된 라디오그룹의 아이디를 추출한다.
            int id = radioGroup.getId();

            switch (id){
                case R.id.group1:
                    switch (i){
                        case R.id.radioButton:
                            text1.setText("라디오 버튼 이벤트 : 1-1");
                            break;
                        case R.id.radioButton2:
                            text1.setText("라디오 버튼 이벤트 : 1-2");
                            break;
                        case R.id.radioButton3:
                            text1.setText("라디오 버튼 이벤트 : 1-3");
                            break;
                    }
                    break;

                case R.id.group2:
                    switch (i){
                        case R.id.radioButton:
                            text2.setText("라디오 버튼 이벤트 : 2-1");
                            break;
                        case R.id.radioButton2:
                            text2.setText("라디오 버튼 이벤트 : 2-2");
                            break;
                        case R.id.radioButton3:
                            text2.setText("라디오 버튼 이벤트 : 2-3");
                            break;
                    }
                    break;

            }

        }
    }
}
