package com.example.feelyou;

public class Cancion {
    private String nombre;
    private String rutaImagen;
    private String rutaAudio;

    public Cancion(String nombre, String rutaImagen, String rutaAudio) {
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        this.rutaAudio = rutaAudio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public String getRutaAudio() {
        return rutaAudio;
    }
}
