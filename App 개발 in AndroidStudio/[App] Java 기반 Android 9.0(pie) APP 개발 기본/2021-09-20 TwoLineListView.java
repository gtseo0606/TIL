package kr.co.softcampus.twolinelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // 문자열
    String [] data1 = {
            "문자열1", "문자열2", "문자열3", "문자열4", "문자열5", "문자열6"
    };

    String [] data2 = {
            "String1", "String2", "String3", "String4", "String5", "String6"
    };

    // 참조변수
    ListView list1;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 주소값
        list1 = (ListView)findViewById(R.id.list1);
        text1 = (TextView)findViewById(R.id.textView);

        // ArrayList
        ArrayList<HashMap<String, String>> data_list = new ArrayList<HashMap<String, String>>();
        // ArrayList에 데이터 담기
        for(int i=0; i < data1.length; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("str1", data1);
            map.put("str2", data2);

            data_list.add(map);
        }

        String [] keys = {"str1", "str2"};
        int [] ids = {android.R.id.text1, android.R.id.text2};

        // 어뎁터
        SimpleAdapter adapter = new SimpleAdapter(this, data_list, android.R.layout.simple_list_item_2, keys, ids);
        list1.setAdapter(adapter);

        // 리스너
        ListListener listener = new ListListener();
        list1.setOnItemClickListener(listener);

    }

    class ListListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            text1.setText(data1[i]);
        }
    }

}
