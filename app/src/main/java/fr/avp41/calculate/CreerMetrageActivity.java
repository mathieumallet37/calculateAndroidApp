package fr.avp41.calculate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.avp41.calculate.repository.EnseigneRepository;
import fr.avp41.calculate.repository.MetrageRepository;

public class CreerMetrageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_creer_metrage);

        MetrageRepository metrageRepository = new MetrageRepository(this);
        EnseigneRepository enseigneRepository = new EnseigneRepository(this);

        EditText nomClient = findViewById(R.id.editNomClient);
        EditText prenomClient = findViewById(R.id.editPrenomClient);
        EditText adresseClient = findViewById(R.id.editAdresseClient);
        EditText codePostalClient = findViewById(R.id.editCodePostalClient);
        EditText villeClient = findViewById(R.id.editVilleClient);
        EditText telephoneClient = findViewById(R.id.editTelephoneClient);

        Switch enseigneSwith = findViewById(R.id.switchEnseigne);
        Spinner enseigneSpinner = findViewById(R.id.spinnerEnseigne);
        Button creerEnseigne = findViewById(R.id.créerEnseigneButton);

        Button enregistrerMetrage = findViewById(R.id.saveButton);
        Button homeButton = findViewById(R.id.homeButton);

        // Permet de retourner à l'activité principale
        homeButton.setOnClickListener(v -> startActivity(new Intent(CreerMetrageActivity.this, MainActivity.class)));

        // Récupère la liste des enseignes
        Cursor cursor = enseigneRepository.getAllEnseignes();
        List<String> enseignes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                enseignes.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Ajoute les enseignes au spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, enseignes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enseigneSpinner.setAdapter(adapter);

        enseigneSwith.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (enseignes.isEmpty()) {
                    creerEnseigne.setVisibility(View.VISIBLE);
                } else {
                    enseigneSpinner.setVisibility(View.VISIBLE);
                }
            } else {
                creerEnseigne.setVisibility(View.INVISIBLE);
                enseigneSpinner.setVisibility(View.INVISIBLE);
            }
        });

        // Permet d'enregistrer le métrage en base de données
        enregistrerMetrage.setOnClickListener(v -> runOnUiThread(() -> {
            String nomClientText = nomClient.getText().toString();
            String prenomClientText = prenomClient.getText().toString();
            String adresseClientText = adresseClient.getText().toString();
            String codePostalClientText = codePostalClient.getText().toString();
            String villeClientText = villeClient.getText().toString();
            String telephoneClientText = telephoneClient.getText().toString();
            String enseigneText = "";
            if (enseigneSpinner.getSelectedItem() != null) {
                enseigneText = enseigneSpinner.getSelectedItem().toString();
            }

            if (nomClientText.isEmpty() || prenomClientText.isEmpty() || adresseClientText.isEmpty() || codePostalClientText.isEmpty() || villeClientText.isEmpty() || telephoneClientText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            long id = metrageRepository.addMetrage(nomClientText, prenomClientText, adresseClientText, telephoneClientText, codePostalClientText, villeClientText, enseigneText);

            // affiche une popup pour choisir d'aller à la liste des métrages ou de l'éditer
            AlertDialog.Builder builder = new AlertDialog.Builder(CreerMetrageActivity.this);
            builder.setTitle("Métrage enregistré");
            builder.setMessage("Que voulez-vous faire ?");
            builder.setPositiveButton("Editer", (dialog, which) -> {
                Intent intent = new Intent(CreerMetrageActivity.this, EditMetrageActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            });
            builder.setNegativeButton("Liste des métrages", (dialog, which) -> startActivity(new Intent(CreerMetrageActivity.this, ListeMetrageActivity.class)));
            builder.show();
        }));
    }
}