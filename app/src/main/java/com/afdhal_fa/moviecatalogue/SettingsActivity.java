package com.afdhal_fa.moviecatalogue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.afdhal_fa.moviecatalogue.broadcast.ReminderReceiver;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    private Switch swDailyRemainder;
    private Switch swReleaseRemainder;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String DAILYREMINDER = "daily_reminder";
    public static final String RELEASEREMINDER = "release_reminder";
    private boolean dailyReminderOnOff;
    private boolean releaseReminderOnOff;

    private ReminderReceiver reminderReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        swDailyRemainder = (Switch) findViewById(R.id.sw_daily_remainder);
        swReleaseRemainder = (Switch) findViewById(R.id.sw_release_remainder);

        RelativeLayout setting_language = (RelativeLayout) findViewById(R.id.layout_language);
        setting_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }
        });
        TextView tvDescLanguage = findViewById(R.id.tv_desc_language);
        String getCounty = Locale.getDefault().getCountry();
        tvDescLanguage.setText(getCounty);

        reminderReceiver = new ReminderReceiver();

        loadData();
        updateViews();

        swReleaseRemainder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (swReleaseRemainder.isChecked()) {
                    setPreferences();
                    setReleaseReminder();
                } else {
                    setPreferences();
                    reminderReceiver.cancelReleaseDateTodayAlarm(SettingsActivity.this, ReminderReceiver.TYPE_RELEASE_REMINDER);
                }
            }
        });

        swDailyRemainder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (swDailyRemainder.isChecked()) {
                    setPreferences();
                    setDailyReminder();
                } else {
                    setPreferences();
                    reminderReceiver.cancelAlarm(SettingsActivity.this, ReminderReceiver.TYPE_DAILY_REMINDER);
                }
            }
        });

        if(getSupportActionBar()!= null){
            getSupportActionBar().setTitle(R.string.menu_setting);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        dailyReminderOnOff = sharedPreferences.getBoolean(DAILYREMINDER, false);
        releaseReminderOnOff = sharedPreferences.getBoolean(RELEASEREMINDER, false);
    }

    public void updateViews() {
        swDailyRemainder.setChecked(dailyReminderOnOff);
        swReleaseRemainder.setChecked(releaseReminderOnOff);
    }

    void setPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DAILYREMINDER, swDailyRemainder.isChecked());
        editor.putBoolean(RELEASEREMINDER, swReleaseRemainder.isChecked());
        editor.apply();
    }

    private void setDailyReminder() {
        String onceTitle = getResources().getString(R.string.app_name);
        String onceMessage = getResources().getString(R.string.daily_remainder_msg);
        reminderReceiver.setDailyRepeatingAlarm(getApplicationContext(), ReminderReceiver.TYPE_DAILY_REMINDER,
                onceTitle,
                onceMessage);
    }

    private void setReleaseReminder() {
        reminderReceiver.setReleaseDateTodayReminderAlarm(getApplicationContext(),
                ReminderReceiver.TYPE_RELEASE_REMINDER
        );
    }

}
