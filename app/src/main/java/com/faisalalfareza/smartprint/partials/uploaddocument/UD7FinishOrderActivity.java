package com.faisalalfareza.smartprint.partials.uploaddocument;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.faisalalfareza.smartprint.R;
import com.faisalalfareza.smartprint.database.DatabaseHelper;
import com.faisalalfareza.smartprint.database.models.UserModels;
import com.faisalalfareza.smartprint.partials.AuthenticateActivity;
import com.faisalalfareza.smartprint.partials.MenuActivity;

public class UD7FinishOrderActivity extends Activity {
    private DatabaseHelper db;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishorder);

        db = new DatabaseHelper(this);
        TextView userName = (TextView) findViewById(R.id.UserName);
        LinearLayout profileDescLayout = (LinearLayout) findViewById(R.id.ProfileDescLayout);
        Button documentHistory = (Button) findViewById(R.id.documentHistory);
        Button done = (Button) findViewById(R.id.done);
        Animation uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        Animation downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);

        UserModels loggedInByActiveUser = db.loggedInByActiveUser();
        if (loggedInByActiveUser != null) {
            userName.setText(loggedInByActiveUser.getName());
        }
        profileDescLayout.setAnimation(uptodown);
        documentHistory.setAnimation(downtoup);
        done.setAnimation(downtoup);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onClickMenu(view, MenuActivity.class);
            }
        });
        documentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onClickMenu(view, MenuActivity.class);
            }
        });
    }

    public void onClickMenu (View view, Class redirectTo) {
        Bundle bundle = new Bundle();
        if(getIntent().getExtras() != null) {
            Bundle again = getIntent().getExtras();
            bundle.putString("merchantRole", again.getString("merchantRole"));
            bundle.putString("UD1_currentLocationTitle", again.getString("UD1_currentLocationTitle"));
            bundle.putString("UD1_currentLocationCode", again.getString("UD1_currentLocationCode"));
            bundle.putString("UD2_currentMerchantTitle", again.getString("UD2_currentMerchantTitle"));
            bundle.putString("UD3_currentService", again.getString("UD3_currentService"));
            bundle.putString("UD4_currentSubService", again.getString("UD4_currentSubService"));
            bundle.putString("UD4_settingBuildQuality", again.getString("UD4_settingBuildQuality"));
            bundle.putString("UD4_settingPrintedPage", again.getString("UD4_settingPrintedPage"));
            bundle.putString("UD4_settingSidesOfPrint", again.getString("UD4_settingSidesOfPrint"));
            bundle.putString("UD4_settingPaperSize", again.getString("UD4_settingPaperSize"));
            bundle.putString("UD4_settingPaperMargin", again.getString("UD4_settingPaperMargin"));
            bundle.putString("UD4_settingOrientation", again.getString("UD4_settingOrientation"));
            bundle.putString("UD4_settingPagePerSheet", again.getString("UD4_settingPagePerSheet"));
            bundle.putString("UD4_settingBaseColor", again.getString("UD4_settingBaseColor"));
            bundle.putString("UD5_settingDocumentName", again.getString("UD5_settingDocumentName"));
            bundle.putString("UD5_settingAttachmentFileDir", again.getString("UD5_settingAttachmentFileDir"));
            bundle.putString("UD5_settingNotes", again.getString("UD5_settingNotes"));
            bundle.putString("UD5_settingFinishLimitDate", again.getString("UD5_settingFinishLimitDate"));
            bundle.putString("UD5_settingBookDate", again.getString("UD5_settingBookDate"));
            bundle.putString("UD5_isToday", again.getString("UD5_isToday"));
        }

        Intent i = new Intent(UD7FinishOrderActivity.this, redirectTo);
        startActivity(i);
    }
}
