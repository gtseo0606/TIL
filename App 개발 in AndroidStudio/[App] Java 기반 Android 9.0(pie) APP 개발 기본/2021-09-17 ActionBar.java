package kr.co.softcampus.actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 참조변수
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 주소값
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

    }

    // 옵션메뉴 호출
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // 검색 뷰가 설정되어 있는 메뉴항목객체를 추출
        MenuItem search_item = menu.findItem(R.id.item5);
        // 액션 뷰로 설정된 뷰 추출
        SearchView search_view = (SearchView)search_item.getActionView();

        // 안내 문구
        search_view.setQueryHint("검색어를 입력해주세요.");
        // 서치뷰에 리스너 설정
        SearchViewListener listener = new SearchViewListener();
        search_view.setOnQueryTextListener(listener);
        return true;

    }

    class SearchViewListener implements SearchView.OnQueryTextListener{
        @Override
        // 문자열이 변경될때마다 호출
        public boolean onQueryTextChange(String s) {
            text1.setText(newText);
            return false;
        }

        @Override
        // 엔터치면 호출
        public boolean onQueryTextSubmit(String s) {
            text2.setText(s);
            return false;
        }
    }
    // 옵션메뉴의 항목 터치 시 호출
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 아이디 추출
        int id = item.getItemId();
        // 분기
        switch (id){
            case R.id.item1 :
                text1.setText("메뉴 1을 선택하였습니다.");
                break;
            case R.id.item2 :
                text1.setText("메뉴 2을 선택하였습니다.");
                break;
            case R.id.item3 :
                text1.setText("메뉴 3을 선택하였습니다.");
                break;
            case R.id.item4 :
                text1.setText("메뉴 4을 선택하였습니다.");
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
