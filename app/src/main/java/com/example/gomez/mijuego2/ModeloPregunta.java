package com.example.gomez.mijuego2;

public class ModeloPregunta {

    private int id;
    private String titulo;


    public ModeloPregunta(int id, String titulo) {

        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
