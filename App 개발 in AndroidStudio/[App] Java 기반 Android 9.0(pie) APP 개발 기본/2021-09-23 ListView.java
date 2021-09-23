package kr.co.softcampus.listview;

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
    TextView text1;

    //사용할 문자열
    String [] data1 = {
            "리스트1", "리스트2", "리스트3", "리스트4", "리스트5", "리스트6",
            "리스트7", "리스트8", "리스트9", "리스트10", "리스트11", "리스트12",
            "리스트13", "리스트14", "리스트15", "리스트16", "리스트17", "리스트18"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 주소값
        list1 = (ListView)findViewById(R.id.list1);
        text1 = (TextView)findViewById(R.id.textView);

        // 몇개의 항목을 보여줄건가? => 배열이나 리스트의 항목개수
        // 각 항목에 어떤 데이터를 보여줄 것인가? => 배열이나 리스트에 들어있는 값
        // 각 항목은 어떤 모양으로 보여줄 것인가? => 레이아웃
        // 안드로이드 OS에서 자체 제공
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1);
        // 리스트뷰에 어뎁터 설정
        list1.setAdapter(adapter);

        // 리스너
        Listlistener listener = new Listlistener();
        list1.setOnItemClickListener(listener);
    }

    class Listlistener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            text1.setText(data1[i]);
        }
    }
}
