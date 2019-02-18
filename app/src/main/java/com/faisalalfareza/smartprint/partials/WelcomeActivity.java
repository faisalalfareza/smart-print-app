package com.faisalalfareza.smartprint.partials;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.Toast;

import com.faisalalfareza.smartprint.R;
import com.faisalalfareza.smartprint.database.DatabaseHelper;
import com.faisalalfareza.smartprint.database.models.UserModels;
import com.faisalalfareza.smartprint.partials.uploaddocument.UD5DocumentInfoActivity;
import com.faisalalfareza.smartprint.partials.uploaddocument.UD7FinishOrderActivity;

public class WelcomeActivity extends Activity {
//    LinearLayout l1, l2;
//    Animation uptodown, downtoup;

    //Declaration SqliteHelper
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//        l1 = (LinearLayout) findViewById(R.id.l1);
//        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
//        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
//        l1.setAnimation(uptodown);

        db = new DatabaseHelper(this);
        db.reInitializeTable();

        if (db.isLoggedInExists()) {
            UserModels loggedInByActiveUser = db.loggedInByActiveUser();
             redirectByCondition(loggedInByActiveUser.getRole(), MenuActivity.class);
//            redirectByCondition(loggedInByActiveUser.getRole(), UD7FinishOrderActivity.class);
        } else {
            redirectByCondition(null, AuthenticateActivity.class);
        }
    }

    private void redirectByCondition(String role, final Class redirectClass) {
        if (role != null)
            Toast.makeText(getApplicationContext(), "Logged In as " + role + ". Please Wait ..", Toast.LENGTH_SHORT).show();

        Thread timer = new Thread() {
            public void run() {
                try {
                    //berapalama splashscreen akan ditampilkan dalam milisecond
                    sleep(5000);
                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), "Something Wrong. Please Try Again ..", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } finally {
                    //activity yang akan dijalankan setelah splashscreen selesai
                    finish();
                    Intent i = new Intent(WelcomeActivity.this, redirectClass);
                    startActivity(i);
                }
            }
        };
        timer.start();
    }
}
