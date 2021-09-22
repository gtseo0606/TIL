package kr.co.softcampus.customlistview1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 참조변수
    ListView list1;
    TextView text2;

    // 문자열
    String [] data = {
            "데이터1", "데이터2", "데이터3", "데이터4", "데이터5", "데이터6",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 주소값
        list1 = (ListView)findViewById(R.id.list1);

        // 어뎁터
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.textView2, data);

        // 리스트뷰(주소값) + 어뎁터
        list1.setAdapter(adapter);

        // 리스너
        ListListener listener = new ListListener();
        list1.setOnItemClickListener(listener);

    }

    class ListListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            text2.setText(data[i]);
        }
    }

}
