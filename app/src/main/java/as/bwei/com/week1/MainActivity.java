package as.bwei.com.week1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView = (ImageView) findViewById(R.id.logo);
        WaterView mWaterView = (WaterView) findViewById(R.id.wave_view);
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        mWaterView.animation(new WaterView.AnimationListener() {
            @Override
            public void animation(float y) {
                params.setMargins(0,0,0, (int) (y-15));
                imageView.setLayoutParams(params);
            }
        });
    }
}
