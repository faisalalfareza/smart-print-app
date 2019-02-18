package com.faisalalfareza.smartprint.partials;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Context;
import android.view.MotionEvent;
import android.view.Window;
import android.view.animation.Animation;

import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Dialog;
import android.widget.Toast;

import com.faisalalfareza.smartprint.R;
import com.faisalalfareza.smartprint.database.DatabaseHelper;
import com.faisalalfareza.smartprint.database.models.UserModels;
import com.faisalalfareza.smartprint.partials.historydocument.HD1DocHistoryActivity;
import com.faisalalfareza.smartprint.partials.uploaddocument.UD1SearchCurrentLocationActivity;

public class MenuActivity extends Activity {
    private DatabaseHelper db;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        db = new DatabaseHelper(this);
        TextView userName = (TextView) findViewById(R.id.UserName);
        TextView userRole = (TextView) findViewById(R.id.UserRole);
        TextView userRoleDesc = (TextView) findViewById(R.id.UserRoleDesc);
        TextView userEmail = (TextView) findViewById(R.id.UserEmail);

        TextView countRatingReview = (TextView) findViewById(R.id.countRatingReview);
        TextView countTransaction = (TextView) findViewById(R.id.countTransaction);

        final LinearLayout actionPrintDoc = (LinearLayout) findViewById(R.id.ActionPrintDoc);
        final LinearLayout actionDocHistory = (LinearLayout) findViewById(R.id.ActionDocHistory);
        final LinearLayout actionManageDoc = (LinearLayout) findViewById(R.id.ActionManageDoc);
        final LinearLayout actionAboutApp = (LinearLayout) findViewById(R.id.ActionAboutApp);
        final LinearLayout actionSettings = (LinearLayout) findViewById(R.id.ActionSettings);
        LinearLayout profileDescLayout = (LinearLayout) findViewById(R.id.ProfileDescLayout);
        LinearLayout menuLayoutTopUser = (LinearLayout) findViewById(R.id.MenuLayoutTopUser);
        LinearLayout menuLayoutTopMerchant = (LinearLayout) findViewById(R.id.MenuLayoutTopMerchant);
        LinearLayout menuLayoutBottom = (LinearLayout) findViewById(R.id.MenuLayoutBottom);

        Animation uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        Animation downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);

        final UserModels loggedInByActiveUser = db.loggedInByActiveUser();
        if (loggedInByActiveUser != null) {
            userName.setText(loggedInByActiveUser.getName());
            userRole.setText(loggedInByActiveUser.getRole());
            countRatingReview.setText(String.valueOf(loggedInByActiveUser.getRating()));
            countTransaction.setText(loggedInByActiveUser.getTransaction() + "x");

            profileDescLayout.setAnimation(uptodown);
            if (loggedInByActiveUser.getRole().equals("Usual User")) {
                menuLayoutTopMerchant.setVisibility(View.GONE);
                menuLayoutTopUser.setAnimation(downtoup);
            }
            else if (loggedInByActiveUser.getRole().equals("Merchant")) {
                menuLayoutTopUser.setVisibility(View.GONE);
                menuLayoutTopMerchant.setAnimation(downtoup);
            }
            menuLayoutBottom.setAnimation(downtoup);

            userRoleDesc.setText(loggedInByActiveUser.getRoleDesc());
            userEmail.setText(loggedInByActiveUser.getEmail());
        }

        actionPrintDoc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchMenu(view, event, R.id.ActionPrintDoc);
                return false;
            }
        });
        actionDocHistory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchMenu(view, event, R.id.ActionDocHistory);
                return false;
            }
        });
        actionManageDoc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchMenu(view, event, R.id.ActionManageDoc);
                return false;
            }
        });
        actionAboutApp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchMenu(view, event, R.id.ActionAboutApp);
                return false;
            }
        });
        actionSettings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchMenu(view, event, R.id.ActionSettings);
                return false;
            }
        });


        actionPrintDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMenu(view, UD1SearchCurrentLocationActivity.class, loggedInByActiveUser.getRole());
            }
        });
        actionDocHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMenu(view, HD1DocHistoryActivity.class, loggedInByActiveUser.getRole());
            }
        });
        actionManageDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMenu(view, HD1DocHistoryActivity.class, loggedInByActiveUser.getRole());
            }
        });
        actionAboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menu_about);

                Button actClose = (Button) dialog.findViewById(R.id.action_close);
                actClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        actionSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menu_logout);

                Button actLogout = (Button) dialog.findViewById(R.id.action_logout);
                actLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.logoutAllUser(); finish();
                        onClickMenu(v, AuthenticateActivity.class, loggedInByActiveUser.getRole());
                    }
                });

                Button actClose = (Button) dialog.findViewById(R.id.action_close);
                actClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    public void onTouchMenu (View view, MotionEvent event, int selector) {
        LinearLayout actionMenu = (LinearLayout) findViewById(selector);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Button Pressed
            actionMenu.setBackgroundColor(Color.parseColor("#ee5879"));
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // Finger was lifted
            actionMenu.setBackgroundResource(R.drawable.uperslider);
        }
    }

    public void onClickMenu (View view, Class redirectTo, String role) {
        Bundle bundle = new Bundle();
        bundle.putString("merchantRole", role);

        Intent intent = new Intent(MenuActivity.this, redirectTo);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
