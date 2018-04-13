package com.example.userone.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by userone on 4/11/2018.
 */

public class UninstallForAllUser extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unistall_for_all_user_layout);

        Button unistall = findViewById(R.id.unistall);
        unistall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Uri packageURI = Uri.parse("package:" + "com.pyarinc.pyar");
                final Intent uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageURI);
                uninstallIntent.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", true);
                startActivity(uninstallIntent);
            }
        });
    }
}
