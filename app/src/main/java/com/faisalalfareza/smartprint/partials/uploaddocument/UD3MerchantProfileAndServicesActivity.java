package com.faisalalfareza.smartprint.partials.uploaddocument;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.faisalalfareza.smartprint.BeanclassList;
import com.faisalalfareza.smartprint.ChildAnimationExample;
import com.faisalalfareza.smartprint.ExpandableHeightListView;
import com.faisalalfareza.smartprint.ListviewAdapter;
import com.faisalalfareza.smartprint.R;
import com.faisalalfareza.smartprint.SliderLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class UD3MerchantProfileAndServicesActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {
    SliderLayout mDemoSlider;

    private ExpandableHeightListView listview;
    private ArrayList<BeanclassList> Bean;
    private ListviewAdapter baseAdapter;

    private String curMerTitle;
    private int resCountOfService;

    private String[] TITLEC = {
            "Photo Copy",
            "Penjilidan",
            "Album Photo & Buku Menu",
            "Print Laser Warna A3+",
            "Print Plotter Hitam Putih / Biasa",
            "Print Indoor",
            "Print Outdoor",
            "Scanner Warna"
    };
    private String[] PRICEC = {
            "4,4 / 2,1 KM",
            "4,3 / 2,3 KM",
            "4,1 / 1,2 KM",
            "4,1 / 1,2 KM",
            "4,1 / 1,2 KM",
            "4,1 / 1,2 KM",
            "4,1 / 1,2 KM",
            "4,1 / 1,2 KM"
    };
    private String[] DETAILC = {
            "Hitam Putih & Warna sampai uk A0",
            "Solasi, Langsung, Hard Cover, Ring",
            "Design, Print, Finishing, Jilid, Box",
            "Kartu Nama, Id Card Timbul, Brosur, Sticker, Kalender, Mini X Banner",
            "Print Ukuran A4-A0 : HVS & Kalkir",
            "Photo, Canvas, Wallpaper, Jok, Poster",
            "Y Banner, Roll Banner , Baliho , Spanduk , Papan Ucapan,Umbul",
            "High Resolution Image 90 cm s/d 2.5m"
    };

//    private String[] TITLES = {"Basic Hair Cut", "Advance Hair Cut", "Advance Hair Cut"};
//    private String[] PRICES = {"$ 15", "$ 29", "$ 29"};
//    private String[] DETAILS = {"Trimming, Split removal", "Get fresh look and blow dry look plus spa", "Trimming, Split removal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchantprofandserv);

        if(getIntent().getExtras() != null){
            customfonts.MyTextView findCurrentMerchantTitle = (customfonts.MyTextView) findViewById(R.id.currentMerchantTitle);
            customfonts.MyTextView findCurrentMerchantMoto = (customfonts.MyTextView) findViewById(R.id.currentMerchantMoto);
            TextView findCountRatingReview = (TextView) findViewById(R.id.countRatingReview);
            TextView findCountTransaction = (TextView) findViewById(R.id.countTransaction);
            TextView findCountDistance = (TextView) findViewById(R.id.countDistance);
            customfonts.MyTextView findWhyTitle = (customfonts.MyTextView) findViewById(R.id.whyTitle);
            customfonts.MyTextView findWhyDetail = (customfonts.MyTextView) findViewById(R.id.whyDetail);
            customfonts.MyTextView findWhereTitle = (customfonts.MyTextView) findViewById(R.id.whereTitle);
            customfonts.MyTextView findWhereDetail = (customfonts.MyTextView) findViewById(R.id.whereDetail);
            customfonts.MyTextView findResultCountOfService = (customfonts.MyTextView) findViewById(R.id.resultCountOfService);
            int resultCountOfService;

            Bundle bundle = getIntent().getExtras();
            curMerTitle = bundle.getString("UD2_currentMerchantTitle");
            resCountOfService = TITLEC.length;

            findCurrentMerchantTitle.setText(curMerTitle);
            findCurrentMerchantMoto.setText("Ensure Your Satisfaction With Modern Technology");
            findCountRatingReview.setText("4,4");
            findCountTransaction.setText("298rb");
            findCountDistance.setText("2,1km");
            findWhyTitle.setText("Where can you find " + curMerTitle + " ?");
            findWhyDetail.setText("MAESTRO memberikan berbagai kemudahan, untuk memenuhi segala kebutuhan anda dalam percetakan media promosi dan lain-lain. Dengan harga yang terjangkau, hasil produk dengan kualitas terbaik, dilengkapi dengan kecanggihan teknologi mesin cetak,   serta tenaga kerja yang handal, bertanggung jawab dan professional dibidangnya. Menjadikan MAESTRO sebagai solusi tepat dan terbaik untuk anda.");
            findWhereTitle.setText("Where can you find " + curMerTitle +" ?");
            findWhereDetail.setText("Jl. Brigjend Slamet Riadi No.127 A-D, Oro-oro Dowo, Klojen, Kota Malang, Jawa Timur 65119");
            findResultCountOfService.setText(resCountOfService + " Item(s)");
        }

        // LISTVIEW - HAIR CUT

            listview = (ExpandableHeightListView)findViewById(R.id.haircut_list);
            Bean = new ArrayList<BeanclassList>();

            for (int i= 0; i< TITLEC.length; i++){
                BeanclassList BeanclassList = new BeanclassList(TITLEC[i], PRICEC[i], DETAILC[i]);
                Bean.add(BeanclassList);
            }

            baseAdapter = new ListviewAdapter(UD3MerchantProfileAndServicesActivity.this, Bean) {
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
                            Toast.makeText(getApplicationContext(), Bean.get(i).getTitle() + " service has been selected. Then do your Document Settings" , Toast.LENGTH_LONG).show();

                            Bundle bundle = new Bundle();
                            if(getIntent().getExtras() != null) {
                                Bundle again = getIntent().getExtras();
                                bundle.putString("merchantRole", again.getString("merchantRole"));
                                bundle.putString("UD1_currentLocationTitle", again.getString("UD1_currentLocationTitle"));
                                bundle.putString("UD1_currentLocationCode", again.getString("UD1_currentLocationCode"));
                                bundle.putString("UD2_currentMerchantTitle", again.getString("UD2_currentMerchantTitle"));
                            }
                            bundle.putString("UD3_currentService", Bean.get(i).getTitle());

                            Intent intent = new Intent(UD3MerchantProfileAndServicesActivity.this, UD4DocumentSettingsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            });


        // LISTVIEW - HAIR STYLING

//            listview = (ExpandableHeightListView)findViewById(R.id.hairstyling_list);
//            Bean = new ArrayList<BeanclassList>();
//
//            for (int i= 0; i< TITLES.length; i++){
//                BeanclassList BeanclassList = new BeanclassList(TITLES[i], PRICES[i], DETAILS[i]);
//                Bean.add(BeanclassList);
//            }
//
//            baseAdapter = new ListviewAdapter(UD2MerchantListingByLocationActivity.this, Bean) {
//            };
//            listview.setAdapter(baseAdapter);


        //  SLIDER

            mDemoSlider = (SliderLayout)findViewById(R.id.slider);

            HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
            file_maps.put("1", R.drawable.pic1);
            file_maps.put("2",R.drawable.pic);
            file_maps.put("3",R.drawable.pic2);

            for(String name : file_maps.keySet()){
                TextSliderView textSliderView = new TextSliderView(this);
                // initialize a SliderLayout
                textSliderView
                        //  .description(name)
                        .image(file_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                        .setOnSliderClickListener(this);


                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("extra", name);

                mDemoSlider.addSlider(textSliderView);
            }
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new ChildAnimationExample());
            mDemoSlider.setDuration(4000);
            // mDemoSlider.addOnPageChangeListener(this);

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
    }
}
