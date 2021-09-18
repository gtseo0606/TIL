package kr.co.softcampus.popupmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 참조변수
    TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 주소값
        text1 = (TextView)findViewById(R.id.textView);

    }

    public void btnMethod(View view){
        // popupMenu 객체 생성
        // 버튼과 뷰 연결 + 버튼을 눌럿을때 나올 것(객체) 생성
        PopupMenu pop = new PopupMenu(this, text1);
        // popupmenu의 메뉴를 관리하는 객체를 추출
        // 관리 객체도 생성
        Menu menu = pop.getMenu();
        // 메뉴
        // 구성(관리 객체 + 다른뷰를 inflater로 연결)
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        // 리스너
        PopupListener listener = new PopupListener();
        pop.setOnMenuItemClickListener(listener);
        // 메뉴 보기
        pop.show();
    }

    // 리스터 중 클릭리스너 선택
    class PopupListener implements PopupMenu.OnMenuItemClickListener{
        @Override
        // 클릭메소드
        public boolean onMenuItemClick(MenuItem menuItem) {
            // id값 추출
            int id = menuItem.getItemId();
            // 분기
            switch (id){
                case R.id.item1:
                    text1.setText("메뉴 1을 선택하였습니다.");
                    break;
                case R.id.item2:
                    text1.setText("메뉴 2을 선택하였습니다.");
                    break;
                case R.id.item3:
                    text1.setText("메뉴 3을 선택하였습니다.");
                    break;
            }
            return false;
        }
    }
}
