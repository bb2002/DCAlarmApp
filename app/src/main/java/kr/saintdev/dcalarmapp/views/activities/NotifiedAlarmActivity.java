package kr.saintdev.dcalarmapp.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kr.saintdev.dcalarmapp.R;
import kr.saintdev.dcalarmapp.modules.notifimanager.NotificationAlarmManager;
import kr.saintdev.dcalarmapp.modules.notifimanager.NotificationAlarmMeta;
import kr.saintdev.dcalarmapp.modules.utils.DateUtils;

public class NotifiedAlarmActivity extends AppCompatActivity {
    private AlarmAdapter listAdapter = null;
    private ListView alarmListView = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notified_alarm);
        alarmListView = findViewById(R.id.alarm_list);

        listAdapter = new AlarmAdapter();
        alarmListView.setAdapter(listAdapter);
        alarmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setData(Uri.parse(listAdapter.getItem(position).getPost().url));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });
    }

    class AlarmAdapter extends BaseAdapter {
        private ArrayList<NotificationAlarmMeta> arrayItems = new ArrayList<NotificationAlarmMeta>();

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(android.R.layout.simple_list_item_2, parent, false);

            TextView titleView = view.findViewById(android.R.id.text1);
            TextView writerView = view.findViewById(android.R.id.text2);
            NotificationAlarmMeta alarmItem = getItem(position);

            String title;
            if(alarmItem.getPost().title.length() > 20) {
                title = alarmItem.getPost().title.substring(0, 19) + "...";
            } else {
                title =  alarmItem.getPost().title;
            }

            titleView.setText(title);
            writerView.setText(alarmItem.getPost().writer + " 님, " + DateUtils.dateToString(alarmItem.getNdate()));
            return view;
        }



        public NotificationAlarmMeta getItem(int p0) {
            return arrayItems.get(p0);
        }

        public long getItemId(int p0) {
            return p0;
        }

        public int getCount() {
            return arrayItems.size();
        }

        void addItem(NotificationAlarmMeta alarm) {
            this.arrayItems.add(alarm);
        }

        void clear() {
            arrayItems.clear();
        }
    }

    protected void onResume() {
        super.onResume();
        refreshItems();
    }

    private void refreshItems() {
        NotificationAlarmManager notifiManager = NotificationAlarmManager.getInstance(this);
        ArrayList<NotificationAlarmMeta> alarms = notifiManager.getAllNotifiAlarms();

        listAdapter.clear();
        for(NotificationAlarmMeta m : alarms) {
            listAdapter.addItem(m);
        }
    }
}
