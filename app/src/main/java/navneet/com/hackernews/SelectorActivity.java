package navneet.com.hackernews;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.adroitandroid.chipcloud.ChipCloud;
import com.adroitandroid.chipcloud.ChipListener;


public class SelectorActivity extends AppCompatActivity {

    private String[] someStringArray={"Hacker News","Engadget","IGN","TechCrunch","Mashable"};
    private Button ChipOkay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        final ChipCloud chipCloud = (ChipCloud) findViewById(R.id.chip_cloud);
        ChipOkay=(Button) findViewById(R.id.okay);

        ChipOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectorActivity.this,MainActivity.class));
            }
        });

        new ChipCloud.Configure()
                .chipCloud(chipCloud)
                .selectedColor(Color.parseColor("#ff00cc"))
                .selectedFontColor(Color.parseColor("#ffffff"))
                .deselectedColor(Color.parseColor("#e1e1e1"))
                .deselectedFontColor(Color.parseColor("#333333"))
                .selectTransitionMS(500)
                .deselectTransitionMS(250)
                .labels(someStringArray)
                .mode(ChipCloud.Mode.MULTI)
                .allCaps(false)
                .gravity(ChipCloud.Gravity.CENTER)
                .textSize(getResources().getDimensionPixelSize(R.dimen.default_textsize))
                .verticalSpacing(getResources().getDimensionPixelSize(R.dimen.vertical_spacing))
                .minHorizontalSpacing(getResources().getDimensionPixelSize(R.dimen.min_horizontal_spacing))
                .chipListener(new ChipListener() {
                    @Override
                    public void chipSelected(int index) {
                        //...
                        if(index==0){
                            Toast.makeText(SelectorActivity.this,"Hacker News",Toast.LENGTH_SHORT).show();
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.HCK_NEWS,"true");
                        } else if (index==1){
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.ENGADGET,"true");
                        }else if (index==2){
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.IGN,"true");
                        }else if (index==3){
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.TECHCRUNCH,"true");
                        }
                        else if (index==4){
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.MASHABLE,"true");
                        }
                    }
                    @Override
                    public void chipDeselected(int index) {
                        if(index==0){
                            Toast.makeText(SelectorActivity.this,"Hacker News",Toast.LENGTH_SHORT).show();
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.HCK_NEWS,"false");
                        } else if (index==1){
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.ENGADGET,"false");
                        }else if (index==2){
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.IGN,"false");
                        }else if (index==3){
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.TECHCRUNCH,"false");
                        }
                        else if (index==4){
                            PreferencesHelper.getInstance(SelectorActivity.this).storeUnencryptedSetting(Constants.MASHABLE,"false");
                        }
                        //...
                    }
                })
                .build();
    }
}
