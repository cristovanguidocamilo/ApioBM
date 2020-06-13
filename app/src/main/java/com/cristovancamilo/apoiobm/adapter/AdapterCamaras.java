package com.cristovancamilo.apoiobm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.model.Camaras;
import com.cristovancamilo.apoiobm.model.EstoqueBloqueado;

import java.util.List;

public class AdapterCamaras extends RecyclerView.Adapter<AdapterCamaras.MyViewHolder> {

    List<Camaras> listaCamaras;
    Context context;

    public AdapterCamaras(List<Camaras> listaCamaras, Context context) {
        this.listaCamaras = listaCamaras;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemCamaras = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_camaras, parent, false);
        return new AdapterCamaras.MyViewHolder(itemCamaras);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Camaras camaras = listaCamaras.get(position);

        holder.textoPeriodo.setText("PERÍODO: " + camaras.getPeriodo());
        holder.textoQuantidade.setText("QTD: " + camaras.getQuantidade());
        holder.textoHabilitacao.setText("HABILITAÇÃO: " + camaras.getHabilitacao());
        holder.textoCodCamara.setText("CÂMARA: " + camaras.getCodCamara());

        if(camaras.getPeriodo().length() >= 16) {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.verde_camara_fechada));
        }

    }

    @Override
    public int getItemCount() {
        return listaCamaras.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textoCodCamara, textoHabilitacao, textoQuantidade, textoPeriodo;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            textoCodCamara = itemView.findViewById(R.id.textViewCodCamara);
            textoHabilitacao = itemView.findViewById(R.id.textViewHabilitacaoCamara);
            textoQuantidade = itemView.findViewById(R.id.textViewQuantidadeCamara);
            textoPeriodo = itemView.findViewById(R.id.textViewPeriodoCamara);
            linearLayout = itemView.findViewById(R.id.linearLayoutFundoCamaras);

        }
    }

}
