package kr.co.softcampus.contentmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 참조변수
    TextView text1;
    ListView list1;

    // 문자열
    String [] data1 = {"항목1", "항목2", "항목3", "항목4", "항목5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 주소값
        text1 = (TextView)findViewById(R.id.textView);
        list1 = (ListView)findViewById(R.id.list1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(String)(this, android.R.layout.simple_list_item_1, data1);
        list1.setAdapter(adapter);

        ListListener listener = new ListListener();
        list1.setOnItemClickListener(listener);

        // 뷰에 컨텍스트 메뉴를 설정
        registerForContextMenu(text1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        // 길게 누르면 뷰의 주소값을 가져온다.
        int view_id = v.getId();

        ContextMenu.ContextMenuInfo info = item.getMenuInfo();
        int position = 0;
        if(info != null && instanceof AdapterView.AdapterContextMenuInfo){
            AdapterView.AdapterContextMenuInfo info2 = (AdapterView.AdapterContextMenuInfo) info;
            position = info2.position;
        }

        switch (view_id){
            case R.id.textView:
                menu.setHeaderTitle("텍스트 위의 컨텍스트 메뉴");
                inflater.inflate(R.menu.textview_menu, menu);
                break;
            case R.id.list1:
                menu.setHeaderTitle("리스트 위의 컨텍스트 메뉴");
                inflater.inflate(R.menu.listview_menu, menu);
                break;
        }
    }

    // 컨텍스트 메뉴의 항목을 터치

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // 사용자가 선택한 메뉴 항목의 id
        int id = item.getItemId();

        switch (id){
            case R.id.text_item1:
                text1.setText("텍스트 뷰의 메뉴1을 선택하셧습니다.");
                break;
            case R.id.text_item2:
                text1.setText("텍스트 뷰의 메뉴2을 선택하셧습니다.");
                break;
            case R.id.list_item1:
                text1.setText("리스트 뷰의 메뉴1을 선택하셧습니다.");
                break;
            case R.id.list_item2:
                text1.setText("리스트 뷰의 메뉴2을 선택하셧습니다.");
                break;
        }
        return super.onContextItemSelected(item);
    }
}
