package com.cristovancamilo.apoiobm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.model.EstoqueBloqueado;

import java.util.List;

public class AdapterEstoqueBloqueado extends RecyclerView.Adapter<AdapterEstoqueBloqueado.MyViewHolder> {

    List<EstoqueBloqueado> listaEstoqueBloqueado;

    public AdapterEstoqueBloqueado(List<EstoqueBloqueado> listaEstoqueBloqueado) {
        this.listaEstoqueBloqueado = listaEstoqueBloqueado;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemEstoqueBloqueado = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_estoque_bloqueado, parent, false);
        return new MyViewHolder(itemEstoqueBloqueado);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        EstoqueBloqueado estoqueBloqueado = listaEstoqueBloqueado.get(position);

        holder.textoQuantidade.setText("QTD: " + estoqueBloqueado.getQuantidade());
        holder.textoProduto.setText("PROD: " + estoqueBloqueado.getProduto());
        holder.textoHabilitacao.setText("HABILITAÇÃO: " + estoqueBloqueado.getHabilitacao());
        holder.textoAbate.setText("ABATE: " + estoqueBloqueado.getDataAbate());
    }

    @Override
    public int getItemCount() {
        return listaEstoqueBloqueado.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textoProduto, textoAbate, textoHabilitacao, textoQuantidade;

        public MyViewHolder(View itemView) {
            super(itemView);

            textoAbate = itemView.findViewById(R.id.textViewDataAbate);
            textoHabilitacao = itemView.findViewById(R.id.textViewHabilitacaoCamara);
            textoProduto = itemView.findViewById(R.id.textViewProduto);
            textoQuantidade = itemView.findViewById(R.id.textViewQuantidade);

        }
    }
}
