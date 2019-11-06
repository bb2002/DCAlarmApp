package kr.saintdev.dcalarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import kr.saintdev.dcalarmapp.modules.notifimanager.NotificationAlarmManager;
import kr.saintdev.dcalarmapp.modules.services.DCAlarmService;
import kr.saintdev.dcalarmapp.modules.utils.SettingFunctions;
import kr.saintdev.dcalarmapp.views.activities.GalleryBrowserActivity;
import kr.saintdev.dcalarmapp.views.activities.GalleryIDActivity;
import kr.saintdev.dcalarmapp.views.activities.GalleryListActivity;
import kr.saintdev.dcalarmapp.views.activities.KeywordActivity;
import kr.saintdev.dcalarmapp.views.activities.NotifiedAlarmActivity;
import kr.saintdev.dcalarmapp.views.alert.AlertFunctions;

public class MainActivity extends AppCompatActivity {

    private TextView checkDelatTime = null;
    private Switch useDataMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set OnClick Listener
        findViewById(R.id.dc_galladd_fromid).setOnClickListener(addGalleryListener);
        findViewById(R.id.dc_galladd_fromweb).setOnClickListener(addGalleryListener);
        findViewById(R.id.dc_settings_gallery).setOnClickListener(addGalleryListener);
        findViewById(R.id.dc_settings_keyword).setOnClickListener(addGalleryListener);
        findViewById(R.id.notified_alarm_button).setOnClickListener(addGalleryListener);
        findViewById(R.id.notified_alarm_clear_button).setOnClickListener(addGalleryListener);

        checkDelatTime = findViewById(R.id.check_delay_time);
        useDataMode = findViewById(R.id.use_data_mode_switch);

        // Time Change Listener
        checkDelatTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int time = Integer.parseInt(s.toString());
                    SettingFunctions.setParseingServiceDelay(getApplicationContext(), time);
                } catch(Exception ex) {}
            }
        });

        useDataMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingFunctions.setWorkOnDataMode(getApplicationContext(), isChecked);
            }
        });

        // Start service.
        startService(new Intent(this, DCAlarmService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkDelatTime.setText(SettingFunctions.getParseingServiceDelay(this) + "");
        ((Switch)findViewById(R.id.use_data_mode_switch)).setChecked(SettingFunctions.isWorkOnDataMode(this));
    }

    private View.OnClickListener addGalleryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.dc_galladd_fromid:
                    startActivity(new Intent(getApplicationContext(), GalleryIDActivity.class));
                    break;
                case R.id.dc_galladd_fromweb:
                    startActivity(new Intent(getApplicationContext(), GalleryBrowserActivity.class));
                    break;
                case R.id.dc_settings_gallery:
                    startActivity(new Intent(getApplicationContext(), GalleryListActivity.class));
                    break;
                case R.id.dc_settings_keyword:
                    startActivity(new Intent(getApplicationContext(), KeywordActivity.class));
                    break;
                case R.id.notified_alarm_button:
                    startActivity(new Intent(getApplicationContext(), NotifiedAlarmActivity.class));
                    break;
                case R.id.notified_alarm_clear_button:
                    alarmClearButtonClick();
                    break;
            }
        }
    };

    private void alarmClearButtonClick() {
        AlertFunctions.openConfirm(this, R.string.ok, R.string.clear_ok, R.string.ok, R.string.cancel, new AlertFunctions.OnAlertConfirmClickListener() {
            @Override
            public void onPositive() {
                NotificationAlarmManager.getInstance(getApplicationContext()).removeAllNotificationAlarm();
                Toast.makeText(getApplicationContext(), R.string.notified_alarm_clear_ok, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegative() {

            }
        });
    }
}
