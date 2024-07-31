package fr.avp41.calculate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.avp41.calculate.DbHandler;
import fr.avp41.calculate.R;
import fr.avp41.calculate.model.EnseigneModel;
import fr.avp41.calculate.model.MetrageModel;
import fr.avp41.calculate.repository.EnseigneRepository;
import fr.avp41.calculate.repository.MetrageRepository;

public class EnseigneAdapter extends RecyclerView.Adapter<EnseigneAdapter.EnseigneViewHolder> {

    private EnseigneRepository db;
    private List<EnseigneModel> enseignes;
    private Context context;

    public EnseigneAdapter(List<EnseigneModel> enseignes, Context context) {
        this.enseignes = enseignes;
        this.context = context;
        this.db = new EnseigneRepository(context);
    }

    @NonNull
    @Override
    public EnseigneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_enseigne, parent, false);
        return new EnseigneAdapter.EnseigneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnseigneAdapter.EnseigneViewHolder holder, int position) {
        EnseigneModel enseigne = enseignes.get(position);
        holder.nomEnseigneTextView.setText(enseigne.getNomEnseigne());
        holder.nomContactTextView.setText(enseigne.getNomContactEnseigne());

    }

    @Override
    public int getItemCount() {
        return enseignes.size();
    }

    public static class EnseigneViewHolder extends RecyclerView.ViewHolder {
        TextView nomEnseigneTextView;
        TextView nomContactTextView;
        TextView editButton;
        TextView deleteButton;

        public EnseigneViewHolder(@NonNull View itemView) {
            super(itemView);
            nomEnseigneTextView = itemView.findViewById(R.id.nomEnseigneTextView);
            nomContactTextView = itemView.findViewById(R.id.nomContactTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }

        public EnseigneViewHolder(@NonNull View itemView, TextView nomEnseigneTextView, TextView nomContactTextView) {
            super(itemView);
            this.nomEnseigneTextView = nomEnseigneTextView;
            this.nomContactTextView = nomContactTextView;
        }
    }
}
