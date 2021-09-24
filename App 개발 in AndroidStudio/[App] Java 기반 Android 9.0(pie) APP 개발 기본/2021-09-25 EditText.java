package kr.co.softcampus.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 참조변수
    EditText edit1;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText)findViewById(R.id.editText2);
        text1 = (TextView)findViewById(R.id.textView);

        // 리스너
        EnterListener listener = new EnterListener();
        edit1.setOnEditorActionListener(listener);

        WatcherClass watcher = new WatcherClass();
        edit1.addTextChangedListener(watcher);
    }

    public void btn1Method(View view){
        edit1.setText("새롭게 설정한 문자열");
    }

    public void btn2Method(View view){
        String str = edit1.getText().toString();
        text1.setText("입력한 문자열 : " + str);
    }

    class EnterListener implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            String str = textView.getText().toString();
            text1.setText("입력한 문자열 : " + str);
            // false면 키보드 내리기, true면 키보드 유지
            return false;
        }
    }

    class WatcherClass implements TextWatcher{
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            text1.setText("문자열 변경중");
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

    }
}
