package kr.co.softcampus.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    //참조변수
    ProgressBar bar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar4 = (ProgressBar)findViewById(R.id.progressBar4);
    }

    public void btn1Method(View view){
        bar4.incrementProgressBy(5);
    }
    public void btn2Method(View view){
        bar4.incrementProgressBy(-5);
    }
    public void btn3Method(View view){
        bar4.setProgress(80);
    }
}
