package mx.edu.itsmt.ejemploseguridad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btllamar, btweb,btemail;
    WebView viewPagina;

    public void vincular() {
        btllamar = findViewById(R.id.bt_llamada);
        btweb = findViewById(R.id.bt_web);
        btemail = findViewById(R.id.bt_email);
        viewPagina = findViewById(R.id.webview);
        btllamar.setOnClickListener(this);
        btweb.setOnClickListener(this);
        btemail.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vincular();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_llamada:
                try {
                    //   Intent llamada = new Intent(Intent.ACTION_DIAL);
                    //  llamada.setData(Uri.parse("tel:7821361270"));
                    if (ActivityCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Log.i("Mensaje", "No se tiene permiso para realizar llamadas telefónicas.");
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CALL_PHONE}, 225);
                        return;
                    }else{
                        Log.i("Mensaje", "Se tiene permiso para realizar llamadas!");
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:7821361271")));
                    }


                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.bt_web:
                WebSettings ajustesVisorWeb = viewPagina.getSettings();
                ajustesVisorWeb.setJavaScriptEnabled(true);
                viewPagina.loadUrl("http://tecmartinez.edu.mx");
                break;
            case R.id.bt_email:
                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.setData(Uri.parse("mailto:"));
                intentEmail.setType("message/rfc822");
                intentEmail.putExtra(Intent.EXTRA_SUBJECT,"Saludos!!");
                intentEmail.putExtra(Intent.EXTRA_EMAIL,"asalas@tecmartinez.edu.mx");
                intentEmail.putExtra(Intent.EXTRA_TITLE,"Prueba");
                startActivity(Intent.createChooser(intentEmail,"Email"));
                break;
        }
    }// intentEmail.setType("text/plain");
}
