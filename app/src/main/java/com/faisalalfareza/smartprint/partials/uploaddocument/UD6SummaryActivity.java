package com.faisalalfareza.smartprint.partials.uploaddocument;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.faisalalfareza.smartprint.R;
import com.faisalalfareza.smartprint.database.DatabaseHelper;

public class UD6SummaryActivity extends AppCompatActivity {
    //Declaration SqliteHelper
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        db = new DatabaseHelper(this);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();

            customfonts.MyTextView findCurrentMerchantTitle = (customfonts.MyTextView) findViewById(R.id.currentMerchantTitle);
            findCurrentMerchantTitle.setText(bundle.getString("UD2_currentMerchantTitle"));

            /* Start : Document Settings */
                customfonts.MyTextView inputService = (customfonts.MyTextView) findViewById(R.id.inputService);
                customfonts.MyTextView inputDocPrintedPage = (customfonts.MyTextView) findViewById(R.id.inputDocPrintedPage);
                customfonts.MyTextView inputDocSidesOfPrint = (customfonts.MyTextView) findViewById(R.id.inputDocSidesOfPrint);
                customfonts.MyTextView inputDocPaperSize = (customfonts.MyTextView) findViewById(R.id.inputDocPaperSize);
                customfonts.MyTextView inputDocPaperMargin = (customfonts.MyTextView) findViewById(R.id.inputDocPaperMargin);
                customfonts.MyTextView inputDocOrientation = (customfonts.MyTextView) findViewById(R.id.inputDocOrientation);
                customfonts.MyTextView inputDocPagePerSheet = (customfonts.MyTextView) findViewById(R.id.inputDocPagePerSheet);
//                customfonts.MyTextView inputDocPrintedPage = (customfonts.MyTextView) findViewById(R.id.inputDocPrintedPage);
                RadioGroup inputGroupBuildQuality = (RadioGroup) findViewById(R.id.inputGroupBuildQuality);
                    RadioButton inputLowQuality = (RadioButton) findViewById(R.id.inputLowQuality);
                    RadioButton inputStandardQuality = (RadioButton) findViewById(R.id.inputStandardQuality);
                    RadioButton inputHighQuality = (RadioButton) findViewById(R.id.inputHighQuality);
                RadioGroup inputGroupBaseColor = (RadioGroup) findViewById(R.id.inputGroupBaseColor);
                    RadioButton inputMonochrome = (RadioButton) findViewById(R.id.inputMonochrome);
                    RadioButton inputMultiColor = (RadioButton) findViewById(R.id.inputMultiColor);

                inputService.setText(bundle.getString("UD3_currentService"));
                inputDocPrintedPage.setText(bundle.getString("UD4_settingPrintedPage"));
                inputDocSidesOfPrint.setText(bundle.getString("UD4_settingSidesOfPrint"));
                inputDocPaperSize.setText(bundle.getString("UD4_settingPaperSize"));
                inputDocPaperMargin.setText(bundle.getString("UD4_settingPaperMargin"));
                inputDocOrientation.setText(bundle.getString("UD4_settingOrientation"));
                inputDocPagePerSheet.setText(bundle.getString("UD4_settingPagePerSheet"));

                String buildQuality = bundle.getString("UD4_settingBuildQuality");
//                Toast.makeText(getApplicationContext(), "Build Quality is " + buildQuality , Toast.LENGTH_LONG).show();
                if (buildQuality == "Low Quality") inputLowQuality.setChecked(true);
                else if (buildQuality == "Standard Quality") inputStandardQuality.setChecked(true);
                else if (buildQuality == "High Quality") inputHighQuality.setChecked(true);

                String baseColor = bundle.getString("UD4_settingBaseColor");
                if (baseColor == "Monochrome") inputMonochrome.setChecked(true);
                else if (baseColor == "Multi Color") inputMultiColor.setChecked(true);

            /* End : Document Settings */

            /* Start : Document Information */
                customfonts.MyTextView inputDocAttachment = (customfonts.MyTextView) findViewById(R.id.inputDocAttachment);
                customfonts.MyTextView inputDocTitle = (customfonts.MyTextView) findViewById(R.id.inputDocTitle);
                customfonts.MyTextView inputDocNote = (customfonts.MyTextView) findViewById(R.id.inputDocNote);
                customfonts.MyTextView inputDocFinishToday = (customfonts.MyTextView) findViewById(R.id.inputDocFinishToday);
                customfonts.MyTextView inputDocFinishNotToday = (customfonts.MyTextView) findViewById(R.id.inputDocFinishNotToday);

                inputDocTitle.setText(bundle.getString("UD5_settingDocumentName"));
                inputDocNote.setText(bundle.getString("UD5_settingNotes"));
                inputDocAttachment.setText(bundle.getString("UD5_settingAttachmentFileDir"));
                inputDocFinishToday.setText("TODAY");
                inputDocFinishNotToday.setText("NOT TODAY");

                if (bundle.getBoolean("UD5_isToday") == true) {
                    inputDocFinishNotToday.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else if (bundle.getBoolean("UD5_isToday") == false) {
                    inputDocFinishToday.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    inputDocFinishNotToday.setText(bundle.getString("UD5_settingFinishLimitDate"));
                }
            /* End : Document Information */
        }

        /* Start: Submit Document Info */
            final Handler objHandler = new Handler();
            Button submitDocInfo = (Button) findViewById(R.id.submitDocInfo);
            submitDocInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    objHandler.post(new Runnable() {
                        @Override
                        public void run() {
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

                                try {
                                    boolean isSuccessInsertDocument = db.insertDocument(
                                            again.getString("merchantRole"),
                                            again.getString("UD1_currentLocationTitle"),
                                            again.getString("UD1_currentLocationCode"),
                                            again.getString("UD2_currentMerchantTitle"),
                                            again.getString("UD3_currentService"),
                                            again.getString("UD4_currentSubService"),
                                            again.getString("UD4_settingBuildQuality"),
                                            again.getString("UD4_settingPrintedPage"),
                                            again.getString("UD4_settingSidesOfPrint"),
                                            again.getString("UD4_settingPaperSize"),
                                            again.getString("UD4_settingPaperMargin"),
                                            again.getString("UD4_settingOrientation"),
                                            again.getString("UD4_settingPagePerSheet"),
                                            again.getString("UD4_settingBaseColor"),
                                            again.getString("UD5_settingDocumentName"),
                                            again.getString("UD5_settingAttachmentFileDir"),
                                            again.getString("UD5_settingNotes"),
                                            again.getString("UD5_settingFinishLimitDate"),
                                            again.getString("UD5_settingBookDate"),
                                            again.getString("UD5_isToday"),
                                            "requested"
                                    );
                                    if (isSuccessInsertDocument) {
                                        try {
                                            db.updateRatingAndTransaction(again.getString("merchantRole"));
                                            Toast.makeText(getApplicationContext(), "Temporarily store summary document information for orders. waiting to process payment .." , Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(UD6SummaryActivity.this, UD7FinishOrderActivity.class);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                        catch (Exception e) {
                                            Toast.makeText(getApplicationContext(), "Error : " + e.getMessage() , Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error : " + e.getMessage() , Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            });
        /* End: Submit Document Info */
    }
}
