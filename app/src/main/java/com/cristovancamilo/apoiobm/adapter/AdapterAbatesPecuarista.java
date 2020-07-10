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

        holder.textoNome.setText(abatesPecuarista.getNome());
        holder.textoDataAbate.setText(abatesPecuarista.getDataAbate());
        holder.textoQuant.setText(abatesPecuarista.getQuant());
    }

    @Override
    public int getItemCount() {
        return listaAbatesPecuarista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textoDataAbate, textoNome, textoQuant;

        public MyViewHolder(View itemView) {
            super(itemView);

            textoNome = itemView.findViewById(R.id.textAbatesPecuaristaNome);
            textoDataAbate = itemView.findViewById(R.id.textAbatesPecuaristaAbate);
            textoQuant = itemView.findViewById(R.id.textAbatesPecuaristaQuant);

        }
    }

}
