package com.faisalalfareza.smartprint.partials.uploaddocument;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.faisalalfareza.smartprint.FileChooser;
import com.faisalalfareza.smartprint.R;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UD5DocumentInfoActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Button inputDocFinishDate;
    private customfonts.MyTextView selectedFileName;
    private static final int REQUEST_CHOOSER = 1234;

    private String outputDocumentName, outputAtachmentFile,
            outputNotes, outputFinishLimitDate, outputBookDate;

    private String currentMerchantTitle, currentService;
    private boolean outputIsToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docinfo);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        inputDocFinishDate = (Button) findViewById(R.id.inputDocFinishDate);
        Button inputDocAttachment = (Button) findViewById(R.id.inputDocAttachment);
        final EditText inputDocTitle = (EditText) findViewById(R.id.inputDocTitle);
        final EditText inputDocNote = (EditText) findViewById(R.id.inputDocNote);
        final CheckBox inputDocFinishByDate = (CheckBox) findViewById(R.id.inputDocFinishByDate);
        Button submitDocInfo = (Button) findViewById(R.id.submitDocInfo);
        selectedFileName = (customfonts.MyTextView) findViewById(R.id.selectedFileName);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            customfonts.MyTextView findCurrentMerchantTitle = (customfonts.MyTextView) findViewById(R.id.currentMerchantTitle);
            currentMerchantTitle = bundle.getString("UD2_currentMerchantTitle");
            findCurrentMerchantTitle.setText(currentMerchantTitle);
        }

        /* Start: Submit Document Info */
            /* Start: Form Document Info - Attachment File */
                inputDocAttachment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        processFile();
                    }
                });
            /* End: Form Document Info - Attachment File */

            /* Start: Form Document Info - Finish By Date */
                inputDocFinishDate.setEnabled(false);
                inputDocFinishByDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (inputDocFinishByDate.isChecked()) inputDocFinishDate.setEnabled(true);
                        else {
                            inputDocFinishDate.setEnabled(false);
                            inputDocFinishDate.setText("Select completion limit");
                        }
                    }
                });
                inputDocFinishDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDateDialog();
                    }
                });
            /* End: Form Document Info - Finish By Date */
        /* End: Submit Document Info */

        /* Start: Submit Document Info */
            final Handler objHandler = new Handler();
            submitDocInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar newDate = Calendar.getInstance();

                    outputDocumentName = inputDocTitle.getText().toString();
                    outputAtachmentFile = selectedFileName.getText().toString();
                    outputNotes = inputDocNote.getText().toString();
                    outputBookDate = dateFormatter.format(newDate.getTime());

                    if (inputDocFinishByDate.isChecked()) {
                        outputFinishLimitDate = inputDocFinishDate.getText().toString();
                        outputIsToday = false;
                    } else {
                        outputFinishLimitDate = dateFormatter.format(newDate.getTime());
                        outputIsToday = true;
                    }

//                    Toast.makeText(getApplicationContext(), "outputDocumentName is : " + outputDocumentName , Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "outputAtachmentFile is : " + outputAtachmentFile , Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "outputNotes is : " + outputNotes , Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "outputFinishLimitDate is : " + outputFinishLimitDate , Toast.LENGTH_LONG).show();

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
                            }
                            bundle.putString("UD5_settingDocumentName", outputDocumentName);
                            bundle.putString("UD5_settingAttachmentFileDir", outputAtachmentFile);
                            bundle.putString("UD5_settingNotes", outputNotes);
                            bundle.putString("UD5_settingFinishLimitDate", outputFinishLimitDate);
                            bundle.putString("UD5_settingBookDate", outputBookDate);
                            bundle.putBoolean("UD5_isToday", outputIsToday);

                            Intent intent = new Intent(UD5DocumentInfoActivity.this, UD6SummaryActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            });
        /* End: Submit Document Info */
    }

    private void processFile(){
        FileChooser fileChooser = new FileChooser(UD5DocumentInfoActivity.this);

        fileChooser.setFileListener(new FileChooser.FileSelectedListener() {
            @Override
            public void fileSelected(final File file) {
                // ....do something with the file
                String filename = file. getAbsolutePath();
                Log.i("File Name", filename);
                Toast.makeText(getApplicationContext(), "File Name is : " + filename , Toast.LENGTH_LONG).show();
                // then actually do something in another module

                selectedFileName.setText(filename);
                selectedFileName.setVisibility(View.VISIBLE);
            }
        });
        // Set up and filter my extension I am looking for
        //fileChooser.setExtension("pdf");
        fileChooser.showDialog();
    }

    private void showDateDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                inputDocFinishDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
