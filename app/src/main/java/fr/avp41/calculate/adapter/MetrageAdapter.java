package fr.avp41.calculate.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fr.avp41.calculate.DbHandler;
import fr.avp41.calculate.R;
import fr.avp41.calculate.model.MetrageModel;
import fr.avp41.calculate.repository.MetrageRepository;

public class MetrageAdapter extends RecyclerView.Adapter<MetrageAdapter.MetrageViewHolder> {

    private MetrageRepository db;
    private List<MetrageModel> metrages;
    private Context context;

    public MetrageAdapter(List<MetrageModel> metrages, Context context) {
        this.metrages = metrages;
        this.context = context;
        this.db = new MetrageRepository(context);
    }

    @NonNull
    @Override
    public MetrageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metrage, parent, false);
        return new MetrageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MetrageViewHolder holder, int position) {
        MetrageModel metrage = metrages.get(position);
        holder.nomClientTextView.setText(metrage.getNomClient());

        String date = metrage.getDateMetrage();
        holder.dateMetrageTextView.setText(date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date.substring(0, 4));

        holder.editButton.setOnClickListener(v -> {
            // TODO
        });

        holder.deleteButton.setOnClickListener(v -> {
            showDeleteConfirmationDialog(metrage.getId(), position);
        });
    }

    @Override
    public int getItemCount() {
        return metrages.size();
    }

    public static class MetrageViewHolder extends RecyclerView.ViewHolder {
        TextView nomClientTextView;
        TextView dateMetrageTextView;
        TextView editButton;
        TextView deleteButton;

        public MetrageViewHolder(@NonNull View itemView) {
            super(itemView);
            nomClientTextView = itemView.findViewById(R.id.nomClientTextView);
            dateMetrageTextView = itemView.findViewById(R.id.dateMetrageTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    private void showDeleteConfirmationDialog(long id, int position) {
        new AlertDialog.Builder(context)
                .setTitle("Suppression")
                .setMessage("Êtes-vous certain de vouloir supprimer ce métrage ?")
                .setPositiveButton("Oui", (dialog, which) -> {
                    deleteMetrageById(id, position);
                })
                .setNegativeButton("Non", null)
                .show();
    }

    private void deleteMetrageById(long id, int position) {
        db.deleteMetrageById(id);
        metrages.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, metrages.size());
    }

}