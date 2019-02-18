package com.faisalalfareza.smartprint.partials.uploaddocument;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.faisalalfareza.smartprint.R;

import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Toast;


public class UD4DocumentSettingsActivity extends AppCompatActivity {
    String[] printedPageList = { "Print All Pages", "Print Current Page" };
    String[] sidesOfPrintList = { "Print One Sided", "Print On Both Side" };
    String[] paperSizeList = { "Letter", "A4", "102 x 152 mm", "89 x 127 mm", "Postcard 100 x 148 mm" };
    String[] paperMarginList = { "Normal Margin", "Narrow", "Moderate", "Wide", "Mirrored" };
    String[] orientationList = { "Potrait Orientation", "Landscape Orientation" };
    String[] pagePerSheetList = { "1 Page Per Sheet", "2 Page Per Sheet", "4 Page Per Sheet", "6 Page Per Sheet", "8 Page Per Sheet", "16 Page Per Sheet" };

    int[] selectorList = { R.id.subService, R.id.printedPage, R.id.sidesOfPrint, R.id.paperSize, R.id.paperMargin, R.id.orientation, R.id.pagePerSheet };

    private String outputSubService, outputPrintedPage, outputSidesOfPrint,
            outputPaperSize, outputPaperMargin, outputOrientation, outputPagePerSheet,
            outputBuildQuality, outputBaseColor;
    private String currentMerchantTitle, currentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docsettings);

        // Set Dropdown
        setDropdownValue(printedPageList, R.id.subService);
        setDropdownValue(printedPageList, R.id.printedPage);
        setDropdownValue(sidesOfPrintList, R.id.sidesOfPrint);
        setDropdownValue(paperSizeList, R.id.paperSize);
        setDropdownValue(paperMarginList, R.id.paperMargin);
        setDropdownValue(orientationList, R.id.orientation);
        setDropdownValue(pagePerSheetList, R.id.pagePerSheet);

        if(getIntent().getExtras() != null) {
            customfonts.MyTextView findCurrentMerchantTitle = (customfonts.MyTextView) findViewById(R.id.currentMerchantTitle);
            customfonts.MyTextView findCurrentService = (customfonts.MyTextView) findViewById(R.id.currentService);

            Bundle bundle = getIntent().getExtras();
            currentMerchantTitle = bundle.getString("UD2_currentMerchantTitle");
            currentService = bundle.getString("UD3_currentService");

            findCurrentMerchantTitle.setText(currentMerchantTitle);
            findCurrentService.setText(currentService);
        }

        RadioGroup inputGroupBuildQuality = (RadioGroup) findViewById(R.id.inputGroupBuildQuality);
        inputGroupBuildQuality.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb = (RadioButton)findViewById(checkedId);
                outputBuildQuality = rb.getText().toString();
            }
        });

        RadioGroup inputGroupBaseColor = (RadioGroup) findViewById(R.id.inputGroupBaseColor);
        inputGroupBaseColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb = (RadioButton)findViewById(checkedId);
                outputBaseColor = rb.getText().toString();
            }
        });

        Spinner spinPrintedPage = (Spinner) findViewById(R.id.printedPage);
        spinPrintedPage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                outputPrintedPage = printedPageList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinSidesOfPrint = (Spinner) findViewById(R.id.sidesOfPrint);
        spinSidesOfPrint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                outputSidesOfPrint = sidesOfPrintList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinPaperSize = (Spinner) findViewById(R.id.paperSize);
        spinPaperSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                outputPaperSize = paperSizeList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinPaperMargin = (Spinner) findViewById(R.id.paperMargin);
        spinPaperMargin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                outputPaperMargin = paperMarginList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinOrientation = (Spinner) findViewById(R.id.orientation);
        spinOrientation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                outputOrientation = orientationList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinPagePerSheet = (Spinner) findViewById(R.id.pagePerSheet);
        spinPagePerSheet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                outputPagePerSheet = pagePerSheetList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
                        }
                        bundle.putString("UD4_currentSubService", currentService);
                        bundle.putString("UD4_settingBuildQuality", outputBuildQuality);
                        bundle.putString("UD4_settingPrintedPage", outputPrintedPage);
                        bundle.putString("UD4_settingSidesOfPrint", outputSidesOfPrint);
                        bundle.putString("UD4_settingPaperSize", outputPaperSize);
                        bundle.putString("UD4_settingPaperMargin", outputPaperMargin);
                        bundle.putString("UD4_settingOrientation", outputOrientation);
                        bundle.putString("UD4_settingPagePerSheet", outputPagePerSheet);
                        bundle.putString("UD4_settingBaseColor", outputBaseColor);

                        Intent intent = new Intent(UD4DocumentSettingsActivity.this, UD5DocumentInfoActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void setDropdownValue(String[] model, int selector) {
        Spinner spinModel = (Spinner) findViewById(selector);
        ArrayAdapter<String> aModel = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, model);
        aModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinModel.setAdapter(aModel);
    }

    public void validateDropdownValue(int selector) {
        Spinner spinModel = (Spinner) findViewById(selector);
        String actualPositionOfMySpinner = (String) spinModel.getItemAtPosition(spinModel.getSelectedItemPosition());
        if (actualPositionOfMySpinner.isEmpty()) {
            setSpinnerError(spinModel, "Field can't be empty");
        }
    }

    private void setSpinnerError(Spinner spinner, String error){
        View selectedView = spinner.getSelectedView();
        TextView selectedTextView = (TextView) selectedView;

        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            selectedTextView.setError("error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message
            spinner.performClick(); // to open the spinner list if error is found.
        } else {
            selectedTextView.setError(null);
        }
    }
}
