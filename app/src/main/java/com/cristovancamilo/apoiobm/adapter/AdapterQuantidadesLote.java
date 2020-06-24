package com.cristovancamilo.apoiobm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.model.QuantidadesLote;

import java.util.List;

public class AdapterQuantidadesLote extends RecyclerView.Adapter<AdapterQuantidadesLote.MyViewHolder> {

    List<QuantidadesLote> listaQuantidadesLote;
    Context context;

    public AdapterQuantidadesLote(List<QuantidadesLote> listaQuantidadesLote, Context context) {
        this.listaQuantidadesLote = listaQuantidadesLote;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemQuantidadesLote = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_quantidades_lote, parent, false);
        return new AdapterQuantidadesLote.MyViewHolder(itemQuantidadesLote);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        QuantidadesLote quantidadesLote = listaQuantidadesLote.get(position);

        holder.textoNumLote.setText("LOTE: " + quantidadesLote.getNumLote());
        holder.textoQuantidade.setText("QUANTIDADE: " + quantidadesLote.getQuantidade());
        holder.textoHabilitacao.setText("HABILITAÇÃO: " + quantidadesLote.getHabilitacao());

    }

    @Override
    public int getItemCount() {
        return listaQuantidadesLote.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textoNumLote, textoHabilitacao, textoQuantidade;

        public MyViewHolder(View itemView) {
            super(itemView);

            textoNumLote = itemView.findViewById(R.id.textViewQtdLoteNumLote);
            textoHabilitacao = itemView.findViewById(R.id.textViewQtdLoteHabilitacao);
            textoQuantidade = itemView.findViewById(R.id.textViewQtdLoteQuantidade);

        }
    }

}
