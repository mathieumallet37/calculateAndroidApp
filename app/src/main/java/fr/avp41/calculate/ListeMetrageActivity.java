package fr.avp41.calculate;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.avp41.calculate.adapter.MetrageAdapter;
import fr.avp41.calculate.model.MetrageModel;
import fr.avp41.calculate.repository.MetrageRepository;

public class ListeMetrageActivity extends AppCompatActivity {

    private int scrollSpeed = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_metrage);

        List<MetrageModel> metrageList = getMetrageList();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MetrageAdapter adapter = new MetrageAdapter(metrageList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> startActivity(new Intent(ListeMetrageActivity.this, MainActivity.class)));


    }

    @NonNull
    private List<MetrageModel> getMetrageList() {
        MetrageRepository metrageRepository = new MetrageRepository(this);
        Cursor cursor = metrageRepository.getAllMetrages();
        List<MetrageModel> metrages = new ArrayList<>();



        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        long id = cursor.getLong(0);
                        String nomClient = cursor.getString(cursor.getColumnIndexOrThrow("nom_client"));
                        String dateMetrage = cursor.isNull(2) ? null : cursor.getString(cursor.getColumnIndexOrThrow("date_metrage"));

                        MetrageModel metrage = new MetrageModel(id, nomClient, dateMetrage);
                        metrages.add(metrage);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
        return metrages;
    }
}