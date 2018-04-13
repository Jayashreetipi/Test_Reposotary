package pushnotification;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.userone.myapplication.R;

/**
 * Created by userone on 4/12/2018.
 */

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        TextView mTVPushNot = findViewById(R.id.tv_push_notification);
        mTVPushNot.setText("Welcome to Result Page");
    }
}
