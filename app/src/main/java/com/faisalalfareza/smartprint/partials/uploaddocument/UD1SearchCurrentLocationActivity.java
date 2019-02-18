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

public class UD1SearchCurrentLocationActivity extends AppCompatActivity {
    private ExpandableHeightListView listview;
    private ArrayList<BeanclassList> Bean;
    private ListviewAdapter baseAdapter;

    private String[] TITLEC = {"Malang", "Jakarta Selatan","Yogyakarta"};
    private String[] PRICEC = {"$ 15", "$ 29","$ 15"};
    private String[] DETAILC = {"Trimming, Split removal", "Get fresh look and blow dry look plus spa","Trimming, Split removal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchcurloc);

        // LISTVIEW - HAIR CUT

            listview = (ExpandableHeightListView)findViewById(R.id.haircut_list);
            Bean = new ArrayList<BeanclassList>();

            for (int i= 0; i< TITLEC.length; i++){
                BeanclassList BeanclassList = new BeanclassList(TITLEC[i], PRICEC[i], DETAILC[i]);
                Bean.add(BeanclassList);
            }

            baseAdapter = new ListviewAdapter(UD1SearchCurrentLocationActivity.this, Bean) {
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
                            Toast.makeText(getApplicationContext(), "Your location is " + Bean.get(i).getTitle() , Toast.LENGTH_LONG).show();

                            Bundle bundle = new Bundle();
                            if(getIntent().getExtras() != null) {
                                Bundle again = getIntent().getExtras();
                                bundle.putString("merchantRole", again.getString("merchantRole"));
                            }
                            bundle.putString("UD1_currentLocationTitle", Bean.get(i).getTitle());
                            bundle.putString("UD1_currentLocationCode", Bean.get(i).getTitle().substring(0, 3).toUpperCase());

                            Intent intent = new Intent(UD1SearchCurrentLocationActivity.this, UD2MerchantListingByLocationActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            });
    }
}
