package com.example.gomez.mijuego2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PreguntasAdapter extends RecyclerView.Adapter<PreguntasAdapter.PreguntaViewHolder>{

    BD baseDatos;
    ArrayList<ModeloPregunta> preguntas;


    public PreguntasAdapter(BD baseDatos){
        this.baseDatos = baseDatos;
        preguntas = baseDatos.getPreguntas();
    }

    @Override
    public PreguntaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PreguntaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pregunta, parent, false));
    }

    @Override
    public void onBindViewHolder(PreguntaViewHolder holder, int position) {
        holder.pregunta.setText(preguntas.get(position).getTitulo());
    }

    @Override
    public int getItemCount() {
        return preguntas.size();
    }

    public void actualizar() {
        preguntas = baseDatos.getPreguntas();
        notifyDataSetChanged();
    }


    class PreguntaViewHolder extends RecyclerView.ViewHolder{

        TextView pregunta;
        ImageView borrar;

        public PreguntaViewHolder(View itemView) {
            super(itemView);
            pregunta = (TextView) itemView.findViewById(R.id.pregunta);
            borrar = (ImageView) itemView.findViewById(R.id.borrar);

            borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    baseDatos.borrarPregunta(preguntas.get(getAdapterPosition()));
                    preguntas = baseDatos.getPreguntas();
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }

}
