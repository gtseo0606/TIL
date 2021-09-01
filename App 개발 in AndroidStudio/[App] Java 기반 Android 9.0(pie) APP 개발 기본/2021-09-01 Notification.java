package kr.co.softcampus.notificationbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
        NotificationCompat.Builder builder = getNotificationBuilder("channel1", "첫번째 채널");
        // Ticker 메세지 설정
        builder.setTicker("Ticker 메세지");
        // 작은 아이콘 설정
        builder.setSmallIcon(android.R.drawable.ic_menu_search);
        // 큰 아이콘 설정
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        // 숫자 설정
        builder.setNumber(100);
        // 타이틀 설정
        builder.setContentTitle("Content Title");
        // 내용 설정
        builder.setContentText("Content Text");

        // 메세지 객체를 설정
        Notification notification = builder.build();

        // 알림 메세지 객체를 추출
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // 알림 메세지 출력
        manager.notify(10, notification);
    }
    public void btn2Method(View view){
        NotificationCompat.Builder builder = getNotificationBuilder("channel1", "첫번째 채널");
        // Ticker 메세지 설정
        builder.setTicker("Ticker 메세지");
        // 작은 아이콘 설정
        builder.setSmallIcon(android.R.drawable.ic_menu_search);
        // 큰 아이콘 설정
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        // 숫자 설정
        builder.setNumber(100);
        // 타이틀 설정
        builder.setContentTitle("Content Title2");
        // 내용 설정
        builder.setContentText("Content Text2");

        // 메세지 객체를 설정
        Notification notification = builder.build();

        // 알림 메세지 객체를 추출
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // 알림 메세지 출력
        manager.notify(20, notification);
    }

    public void btn3Method(View view){
        NotificationCompat.Builder builder = getNotificationBuilder("channel2", "두번째 채널");
        // Ticker 메세지 설정
        builder.setTicker("Ticker 메세지");
        // 작은 아이콘 설정
        builder.setSmallIcon(android.R.drawable.ic_menu_search);
        // 큰 아이콘 설정
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        // 숫자 설정
        builder.setNumber(100);
        // 타이틀 설정
        builder.setContentTitle("Content Title3");
        // 내용 설정
        builder.setContentText("Content Text3");

        // 메세지 객체를 설정
        Notification notification = builder.build();

        // 알림 메세지 객체를 추출
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // 알림 메세지 출력
        manager.notify(30, notification);
    }

    public void btn4Method(View view){
        NotificationCompat.Builder builder = getNotificationBuilder("channel2", "두번째 채널");
        // Ticker 메세지 설정
        builder.setTicker("Ticker 메세지");
        // 작은 아이콘 설정
        builder.setSmallIcon(android.R.drawable.ic_menu_search);
        // 큰 아이콘 설정
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        // 숫자 설정
        builder.setNumber(100);
        // 타이틀 설정
        builder.setContentTitle("Content Title4");
        // 내용 설정
        builder.setContentText("Content Text4");

        // 메세지 객체를 설정
        Notification notification = builder.build();

        // 알림 메세지 객체를 추출
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // 알림 메세지 출력
        manager.notify(40, notification);
    }

    // 안드로이드 8.0이상을 대응하기 위한 Notification.Builder 생성 메서드
    public NotificationCompat.Builder getNotificationBuilder(String id, String name){
        NotificationCompat.Builder builder = null;

        // OS버전별 분기
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
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
