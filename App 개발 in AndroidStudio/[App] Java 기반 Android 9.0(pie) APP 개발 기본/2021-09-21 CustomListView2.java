package kr.co.softcampus.customlistview2;

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

    // 참조
    ListView list1;
    TextView text3;

    // 데이터
    int [] imgRes = {
            R.drawable.imgflag1, R.drawable.imgflag2, R.drawable.imgflag3, R.drawable.imgflag4,
            R.drawable.imgflag5, R.drawable.imgflag6, R.drawable.imgflag7, R.drawable.imgflag8
    };

    String [] data1 = {
            "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국"
    };

    String [] data2 = {
            "togo", "france", "swiss", "spain", "japan", "germany", "brazil", "korea"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 주소값
        list1 = (ListView)findViewById(R.id.list1);
        text3 = (TextView)findViewById(R.id.text3);

        // Arraylist
        ArrayList<HashMap<String, Object>> data_list = new ArrayList<HashMap<String, Object>>();

        // 데이터를 담는다.
        for(int i = 0; i<imgRes.length; i++){
            // 항목구성을 위해 데이터를 해시맵에 담는다.
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("flag", imgRes[i]);
            map.put("data1", data1[i]);
            map.put("data2", data2[i]);

            data_list.add(map);
        }

        // 해시맵 객체에 데이터를 저장할 때 사용한 이름배열
        String [] keys = {"flag", "data1", "data2"};

        // 뷰 id
        int [] ids = {R.id.imageView, R.id.text3, R.id.textView2};

        // 어뎁터
        SimpleAdapter adapter = new SimpleAdapter(this, data_list, R.layout.row, keys, ids);
        list1.setAdapter(adapter);

        // 리스너
        ListListener listener = new ListListener();
        list1.setOnItemClickListener(listener);
    }

    class ListListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            text3.setText(data1[i]);
        }
    }
}
