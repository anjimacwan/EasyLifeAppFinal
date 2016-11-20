package com.anjalimacwan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.anjalimacwan.activity.MapActivity;
import com.anjalimacwan.activity.MoneyTracker;
import com.anjalimacwan.activity.Reminder;

public class WelcomeToApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_to_app);
    }

    public void openNotepad(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openNavigation(View view)
    {
        Intent intent = new Intent(this,MapActivity.class);
        startActivity(intent);
    }

    public void openMoneyTracker(View view)
    {
        Intent intent = new Intent(this,MoneyTracker.class);
        startActivity(intent);
    }

    public void openReminder(View view)
    {
        Intent intent = new Intent(this,Reminder.class);
        startActivity(intent);
    }
}
