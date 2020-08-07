package com.cristovancamilo.apoiobm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cristovancamilo.apoiobm.R;
import com.cristovancamilo.apoiobm.model.AbatesPecuarista;

import java.util.List;

public class AdapterAbatesPecuarista extends RecyclerView.Adapter<AdapterAbatesPecuarista.MyViewHolder> {

    List<AbatesPecuarista> listaAbatesPecuarista;
    Context context;

    public AdapterAbatesPecuarista(List<AbatesPecuarista> listaAbatesPecuarista, Context context) {
        this.listaAbatesPecuarista = listaAbatesPecuarista;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemAbatesPecuarista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_abates_pecuarista, parent, false);
        return new AdapterAbatesPecuarista.MyViewHolder(itemAbatesPecuarista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        AbatesPecuarista abatesPecuarista = listaAbatesPecuarista.get(position);

        holder.textoLote.setText("Lote de Abate: " + abatesPecuarista.getLote());
        holder.textoDataAbate.setText(abatesPecuarista.getDataAbate());
        holder.textoQuant.setText(abatesPecuarista.getQuant() + " Animais");
        holder.textoStatus.setText(abatesPecuarista.getStatus());
        holder.textoPesoAbatido.setText("Peso Abatido: " + abatesPecuarista.getPesoAbatido());
        if (abatesPecuarista.getStatus().toString() == "Aguardando Abate..." || abatesPecuarista.getStatus().toString().equals("Aguardando Abate...")) {
            holder.textoStatus.setTextColor(context.getResources().getColor(R.color.vermelho_texto_offline));
        }else if (abatesPecuarista.getStatus().toString() == "Em Abate..." || abatesPecuarista.getStatus().toString().equals("Em Abate...")) {
            holder.textoStatus.setTextColor(context.getResources().getColor(R.color.amarelo_status_em_abate));
        }else if (abatesPecuarista.getStatus().toString() == "Finalizado." || abatesPecuarista.getStatus().toString().equals("Finalizado.")) {
        holder.textoStatus.setTextColor(context.getResources().getColor(R.color.verde_status_finalizado));
        }
    }

    @Override
    public int getItemCount() {
        return listaAbatesPecuarista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textoDataAbate, textoLote, textoQuant, textoStatus, textoPesoAbatido;

        public MyViewHolder(View itemView) {
            super(itemView);

            textoLote = itemView.findViewById(R.id.textAbatesPecuaristaLote);
            textoDataAbate = itemView.findViewById(R.id.textAbatesPecuaristaAbate);
            textoQuant = itemView.findViewById(R.id.textAbatesPecuaristaQuant);
            textoStatus = itemView.findViewById(R.id.textAbatesPecuaristaStatus);
            textoPesoAbatido = itemView.findViewById(R.id.textViewAbatesPecuaristaPesoAbatido);

        }
    }

}
