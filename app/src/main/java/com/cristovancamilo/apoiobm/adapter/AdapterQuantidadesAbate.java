package com.cristovancamilo.apoiobm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.model.QuantidadesAbate;

import java.util.List;

public class AdapterQuantidadesAbate extends RecyclerView.Adapter<AdapterQuantidadesAbate.MyViewHolder> {

    List<QuantidadesAbate> listaQuantidadesAbate;
    Context context;

    public AdapterQuantidadesAbate(List<QuantidadesAbate> listaQuantidadesAbate, Context context) {
        this.listaQuantidadesAbate = listaQuantidadesAbate;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterQuantidadesAbate.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemQuantidadesAbate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_quantidades_abate, parent, false);
        return new AdapterQuantidadesAbate.MyViewHolder(itemQuantidadesAbate);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterQuantidadesAbate.MyViewHolder holder, int position) {

        QuantidadesAbate quantidadesAbate = listaQuantidadesAbate.get(position);

        holder.textoQuantidade.setText("QTD: " + quantidadesAbate.getQuantidade());
        holder.textoHabilitacao.setText("HAB: " + quantidadesAbate.getHabilitacao());

    }

    @Override
    public int getItemCount() {
        return listaQuantidadesAbate.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textoHabilitacao, textoQuantidade;

        public MyViewHolder(View itemView) {
            super(itemView);

            textoHabilitacao = itemView.findViewById(R.id.textViewQtdAbateHabilitacao);
            textoQuantidade = itemView.findViewById(R.id.textViewQtdAbateQuantidade);

        }
    }

}
