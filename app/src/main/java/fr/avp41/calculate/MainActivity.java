package fr.avp41.calculate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button calculBtn, metrageRecentBtn, creerMetrageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button calculBtn = findViewById(R.id.buttonCalculer);

        Button creerMetrageBtn = findViewById(R.id.buttonCreerMetrage);
        Button listeMetrageBtn = findViewById(R.id.buttonMetrageRecent);

        Button creerEnseigneBtn = findViewById(R.id.buttonCreerEnseigne);
        Button listeEnseigneBtn = findViewById(R.id.buttonListeEnseigne);

        calculBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CalculeActivity.class));
            }
        });

        listeMetrageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListeMetrageActivity.class));
            }
        });

        creerMetrageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreerMetrageActivity.class));
            }
        });

        creerEnseigneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreerEnseigneActivity.class));
            }
        });

        listeEnseigneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListeEnseigneActivity.class));
            }
        });

    }


}