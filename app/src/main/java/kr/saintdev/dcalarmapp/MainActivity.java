package kr.saintdev.dcalarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.saintdev.dcalarmapp.views.activities.GalleryBrowserActivity;
import kr.saintdev.dcalarmapp.views.activities.GalleryIDActivity;
import kr.saintdev.dcalarmapp.views.activities.GalleryListActivity;
import kr.saintdev.dcalarmapp.views.activities.KeywordActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set OnClick Listener
        findViewById(R.id.dc_galladd_fromid).setOnClickListener(addGalleryListener);
        findViewById(R.id.dc_galladd_fromweb).setOnClickListener(addGalleryListener);
        findViewById(R.id.dc_settings_gallery).setOnClickListener(addGalleryListener);
        findViewById(R.id.dc_settings_keyword).setOnClickListener(addGalleryListener);
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
            }
        }
    };
}
