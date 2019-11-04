package kr.saintdev.dcalarmapp.modules.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import kr.saintdev.dcalarmapp.R;

public class DCAlarmService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1234") // channel ID
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("Test");
        startForeground(1, builder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }
}
