package be.tahayasin.menubook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import static be.tahayasin.menubook.SplashActivitySettings.INTENT_AUTHENTICATE;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Integer uid = getIntent().getIntExtra("session_id", -1);
        String session_token = getIntent().getStringExtra("session_token");

        ((TextView) findViewById(R.id.test)).setText("TABLET ID: " + String.valueOf(SharedPrefencesManager.getUid(this)));

        findViewById(R.id.pin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPin();
            }
        });

        getTabletCharacterestics();
    }

    private void showPin(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            KeyguardManager km = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (km.isKeyguardSecure()) {
                Intent authIntent = km.createConfirmDeviceCredentialIntent(getString(R.string.dialog_title_auth), getString(R.string.dialog_msg_auth));
                startActivityForResult(authIntent, INTENT_AUTHENTICATE);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case INTENT_AUTHENTICATE: {
                if (resultCode == RESULT_OK) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(SettingsActivity.this).create();
                    alertDialog.setTitle("Tablet secret pin");
                    alertDialog.setMessage(SharedPrefencesManager.getPin(this));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }
        }
    }

    private void getTabletCharacterestics(){
        final Context context = this;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpPostHandler http = new HttpPostHandler(context, AppStrings.hostWithSlash + "api/login/getsaltandsgallengefortablet.php");
                http.AddGetNameValuePair(new NameValuePair("tablet_id", String.valueOf(SharedPrefencesManager.getUid(context))));

                http.Excecute();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            ResultWithSalt res = new Gson().fromJson(http.getResult(), ResultWithSalt.class);
                            Toast.makeText(context, res.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e){
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }
}