package kr.co.softcampus.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 참조변수
    SeekBar seek1, seek2;
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seek1 = (SeekBar)findViewById(R.id.seekBar);
        seek2 = (SeekBar)findViewById(R.id.seekBar2);
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

        // 리스너
        SeekBarListener listener = new SeekBarListener();
        seek1.setOnSeekBarChangeListener(listener);
        seek2.setOnSeekBarChangeListener(listener);
    }

    public void btn1Method(View view){
        seek1.incrementProgressBy(1);
        seek2.incrementProgressBy(1);
    }

    public void btn2Method(View view){
        seek1.incrementProgressBy(-1);
        seek2.incrementProgressBy(-1);
    }
    public void btn3Method(View view){
        seek1.setProgress(8);
        seek2.setProgress(3);
    }
    public void btn4Method(View view){
        int value1 = seek1.getProgress();
        int value2 = seek2.getProgress();

        text1.setText("seek1 : " + value1);
        text2.setText("seek2 : " + value2);
    }

    class SeekBarListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            int id = seekBar.getId();

            switch (id){
                case R.id.seekBar:
                    text1.setText("첫번째 SeekBar : " + i);
                    break;
                case R.id.seekBar2:
                    text1.setText("두번째 SeekBar : " + i);
                    break;
            }

            if(b == true){
                text2.setText("사용자에 의해 변경되었습니다.");
            }else{
                text2.setText("코드를 통해 변경되었습니다.");
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            int id = seekBar.getId();
            switch (id) {
                case R.id.seekBar:
                    text2.setText("첫번째 SeekBar를 터치했습니다.");
                    break;
                case R.id.seekBar2:
                    text2.setText("두번째 SeekBar를 터치했습니다.");
                    break;
            }
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int id = seekBar.getId();
            switch (id){
                case R.id.seekBar:
                    text2.setText("첫번째 SeekBar를 떼었습니다.");
                    break;
                case R.id.seekBar2:
                    text2.setText("두번째 SeekBar를 떼었습니다.");
                    break;
            }
        }

    }
}
