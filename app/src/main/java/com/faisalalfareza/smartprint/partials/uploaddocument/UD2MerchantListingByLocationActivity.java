package com.faisalalfareza.smartprint.partials.uploaddocument;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.faisalalfareza.smartprint.BeanclassList;
import com.faisalalfareza.smartprint.ExpandableHeightListView;
import com.faisalalfareza.smartprint.ListviewAdapter;
import com.faisalalfareza.smartprint.R;
import android.widget.Toast;

import java.util.ArrayList;

public class UD2MerchantListingByLocationActivity extends AppCompatActivity {
    private ExpandableHeightListView listview;
    private ArrayList<BeanclassList> Bean;
    private ListviewAdapter baseAdapter;

    private String curLocTitle, curLocCode;
    private int resultCountOfMerchant;

    private String[] TITLEC = {"Maestro Printing", "Majesty Printing", "Glow Digital Printing"};
    private String[] PRICEC = {"4,4 / 2,1 KM", "4,3 / 2,3 KM","4,1 / 1,2 KM"};
    private String[] DETAILC = {
            "Jl. Brigjend Slamet Riadi No.127 A-D, Oro-oro Dowo, Klojen, Kota Malang, Jawa Timur 65119",
            "Jl. Brigjend Slamet Riadi No.44, Oro-oro Dowo, Klojen, Kota Malang, Jawa Timur 65112",
            "Ruko Laguna, Jl. Sunandar Priyo Sudarmo No.31, Blimbing, Kota Malang, Jawa Timur 65122"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchantlistbyloc);

        if(getIntent().getExtras() != null){
            customfonts.MyTextView findCurrentLocCode = (customfonts.MyTextView) findViewById(R.id.currentLocCode);
            customfonts.MyTextView findCurrentLocTitle = (customfonts.MyTextView) findViewById(R.id.currentLocTitle);
            customfonts.MyTextView findResultCountOfMerchantDesc = (customfonts.MyTextView) findViewById(R.id.resultCountOfMerchantDesc);

            Bundle bundle = getIntent().getExtras();
            curLocTitle = bundle.getString("UD1_currentLocationTitle");
            curLocCode = bundle.getString("UD1_currentLocationCode");
            resultCountOfMerchant = TITLEC.length;

            findCurrentLocTitle.setText(curLocTitle);
            findCurrentLocCode.setText(curLocCode);
            findResultCountOfMerchantDesc.setText(resultCountOfMerchant + " Search Result for Merchant in " + curLocTitle);
        }

        // LISTVIEW - HAIR CUT

            listview = (ExpandableHeightListView)findViewById(R.id.haircut_list);
            Bean = new ArrayList<BeanclassList>();

            for (int i= 0; i< TITLEC.length; i++){
                BeanclassList BeanclassList = new BeanclassList(TITLEC[i], PRICEC[i], DETAILC[i]);
                Bean.add(BeanclassList);
            }

            baseAdapter = new ListviewAdapter(UD2MerchantListingByLocationActivity.this, Bean) {
            };
            listview.setAdapter(baseAdapter);
            listview.setExpanded(true);

            final Handler objHandler = new Handler();
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                    objHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "See detailed information and services in " + Bean.get(i).getTitle() , Toast.LENGTH_LONG).show();

                            Bundle bundle = new Bundle();
                            if(getIntent().getExtras() != null) {
                                Bundle again = getIntent().getExtras();
                                bundle.putString("merchantRole", again.getString("merchantRole"));
                                bundle.putString("UD1_currentLocationTitle", again.getString("UD1_currentLocationTitle"));
                                bundle.putString("UD1_currentLocationCode", again.getString("UD1_currentLocationCode"));
                            }
                            bundle.putString("UD2_currentMerchantTitle", Bean.get(i).getTitle());

                            Intent intent = new Intent(UD2MerchantListingByLocationActivity.this, UD3MerchantProfileAndServicesActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            });
    }
}
