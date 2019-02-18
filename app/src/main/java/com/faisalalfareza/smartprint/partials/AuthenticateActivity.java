package com.faisalalfareza.smartprint.partials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.faisalalfareza.smartprint.R;
import com.faisalalfareza.smartprint.database.DatabaseHelper;
import com.faisalalfareza.smartprint.database.models.UserModels;

public class AuthenticateActivity extends Activity {
    //Declaration SqliteHelper
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        Button loginAsUsualUser = (Button) findViewById(R.id.loginAsUsualUser);
        Button loginAsMerchant = (Button) findViewById(R.id.loginAsMerchant);
        db = new DatabaseHelper(this);

        if (db.isRoleUserExists() != null) loginAsUsualUser.setText("Login As Usual User");
        else loginAsUsualUser.setText("Register As Usual User");

        if (db.isRoleMerchantExists() != null) loginAsMerchant.setText("Login As Printing Provider");
        else loginAsMerchant.setText("Register As Printing Provider");

        loginAsUsualUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOrReadUser("Usual User");
            }
        });

        loginAsMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOrReadUser("Merchant");
            }
        });
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createOrReadUser(String role) {
        if (!db.isRoleExists(role)) {
            //Role does not exist now add new user to database
            long id = db.insertUser(role);
        }

        final UserModels UserData = db.loggedInByRole(role);
        if (UserData != null) {
            redirectToMenu(UserData.getRole(), 3000);
        }
    }

    private void redirectToMenu(String role, final int time) {
        Toast.makeText(getApplicationContext(), "Logged In as " + role + ". Please Wait ..", Toast.LENGTH_SHORT).show();

        Thread timer = new Thread() {
            public void run() {
                try {
                    //berapalama splashscreen akan ditampilkan dalam milisecond
                    sleep(time);
                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), "Something Wrong. Please Try Again ..", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } finally {
                    //activity yang akan dijalankan setelah splashscreen selesai
                    finish();
                    Intent i = new Intent(AuthenticateActivity.this, MenuActivity.class);
                    startActivity(i);
                }
            }
        };
        timer.start();
    }
}


