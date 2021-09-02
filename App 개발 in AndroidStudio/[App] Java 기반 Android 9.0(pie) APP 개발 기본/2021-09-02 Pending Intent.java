package kr.co.softcampus.pendingintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1Method(View view){
        NotificationCompat.Builder builder = getNotificationBuilder("pending", "pending intent");
        // 타이틀 설정
        builder.setContentTitle("notification 1");
        // 내용 설정
        builder.setContentText("알림메세지 1 입니다.");
        // 작은 아이콘 설정
        builder.setSmallIcon(android.R.drawable.ic_menu_camera);
        builder.setAutoCancel(true);

        // 메세지 터치시 실행될 Activity를 객체화
        Intent intent1 = new Intent(this, Test1Activity.class);
        // 실행될 Activity에 문자 셋팅
        intent1.putExtra("data1", "문자열 데이터1");
        intent1.putExtra("data2", "100");

        // 추가 액션을 설정
        Intent intent1 = new Intent(this, Test1Activity.class);
        PendingIntent pending2 = PendingIntent.getActivity(this, 100, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        // 액션 생성을 위한 빌더 객체 생성
        NotificationCompat.Action.Builder builder2 = new NotificationCompat.Action.Builder(android.R.drawable.ic_menu_compass, Action1, pending2);
        NotificationCompat.Action action2 = builder2.build();
        builder.addAction(action2);

        // Intent 객체를 관리할 PendingIntent 객체를 생성하여 셋팅
        PendingIntent pending1 = PendingIntent.getActivity(this, 10, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pending1);

        // 메세지 객체를 설정
        Notification notification = builder.build();
        // 알림 메세지 객체를 추출
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // 알림 메세지 출력
        manager.notify(10, notification);
    }

    public void btn2Method(View view){
        NotificationCompat.Builder builder = getNotificationBuilder("pending", "pending intent");
        // 타이틀 설정
        builder.setContentTitle("notification 2");
        // 내용 설정
        builder.setContentText("알림메세지 2 입니다.");
        // 작은 아이콘 설정
        builder.setSmallIcon(android.R.drawable.ic_menu_camera);
        // 메세지 객체를 설정
        Notification notification = builder.build();
        // 알림 메세지 객체를 추출
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // 알림 메세지 출력
        manager.notify(20, notification);
    }

    // 안드로이드 8.0이상을 대응하기 위한 Notification.Builder 생성 메서드
    public NotificationCompat.Builder getNotificationBuilder(String id, String name) {
        NotificationCompat.Builder builder = null;

        // OS버전별 분기
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 알림 메세지를 관리하는 객체를 추출한다.
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // 채널 객체생성
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            // 메세지 출력시 LED 사용
            channel.enableLights(true);
            // LED 색상사용
            channel.setLightColor(Color.RED);
            // 진동 사용여부
            channel.enableVibration(true);
            // 알림 메세지를 관리하는 객체에 채널을 등록
            manager.createNotificationChannel(channel);
            // 메세지 생성을 위한 객체 생성
            builder = new NotificationCompat.Builder(this, id);
        } else {
            builder = new NotificationCompat.Builder(this);

        }
        return builder;
    }
}
