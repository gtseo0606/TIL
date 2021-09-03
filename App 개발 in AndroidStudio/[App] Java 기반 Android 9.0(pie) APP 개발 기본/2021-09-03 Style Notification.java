package kr.co.softcampus.stylenotification;

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

        NotificationCompat.Builder builder = getNotificationBuilder("style", "style Notification");
        builder.setContentTitle("Big Picture");
        builder.setContentText("Big Picture Notification");
        builder.setSmallIcon(android.R.drawable.ic_menu_agenda);

        //BigPicture Notification 객체를 생성
        NotificationCompat.BigPictureStyle big = new NotificationCompat.BigPictureStyle(builder);
        // 보여줄 이미지를 설정
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_android);
        big.bigPicture(bitmap);
        big.setBigContentTitle("Big Content Title");
        big.setSummaryText("Summary Text");

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(10, notification);
    }

    public void btn2Method(View view){
        NotificationCompat.Builder builder = getNotificationBuilder("style", "style Notification");
        builder.setContentTitle("Big Text");
        builder.setContentText("Big Text Notification");
        builder.setSmallIcon(android.R.drawable.ic_menu_call);

        // BigTextStyle 객체를 생성
        NotificationCompat.BigTextStyle big = new NotificationCompat.BigTextStyle(builder);
        big.setSummaryText("Summary Text");
        big.setBigContentTitle("Big Content Title");
        big.bigText("동해물과 백두산이");

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(20, notification);
    }

    public void btn3Method(View view){
        NotificationCompat.Builder builder = getNotificationBuilder("style", "style Notification");
        builder.setContentTitle("Content Title");
        builder.setContentText("Content Text");
        builder.setSmallIcon(android.R.drawable.ic_menu_day);

        // BigTextStyle 객체를 생성
        NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle(builder);
        inbox.setSummaryText("Summary Text");

        // 문자열
        inbox.addLine("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        inbox.addLine("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        inbox.addLine("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        inbox.addLine("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(30, notification);
    }

    public NotificationCompat.Builder getNotificationBuilder(String id, String name){
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);

            builder = new NotificationCompat.Builder(this, id);
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        return builder;
    }
}
