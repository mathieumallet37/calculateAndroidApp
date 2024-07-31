package fr.avp41.calculate;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.avp41.calculate.adapter.EnseigneAdapter;
import fr.avp41.calculate.model.EnseigneModel;
import fr.avp41.calculate.repository.EnseigneRepository;

public class ListeEnseigneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_enseigne);

        List<EnseigneModel> enseigneList = getEnseigneList();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView emptyView = findViewById(R.id.emptyView);

        if (enseigneList.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            EnseigneAdapter adapter = new EnseigneAdapter(enseigneList, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }
    }

    private List<EnseigneModel> getEnseigneList() {
        EnseigneRepository enseigneRepository = new EnseigneRepository(this);
        Cursor cursor = enseigneRepository.getAllEnseignes();
        List<EnseigneModel> enseignes = new ArrayList<>();

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        long id = cursor.getLong(0);
                        String nomEnseigne = cursor.getString(cursor.getColumnIndexOrThrow("nom_enseigne"));
                        String villeEnseigne = cursor.getString(cursor.getColumnIndexOrThrow("ville_enseigne"));

                        EnseigneModel enseigne = new EnseigneModel(id, nomEnseigne, villeEnseigne);
                        enseignes.add(enseigne);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
        return enseignes;
    }
}