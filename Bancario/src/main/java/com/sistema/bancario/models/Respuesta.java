package com.sistema.bancario.models;

public class Respuesta {

    private String titulo;
    private boolean correcto;
    private String mensaje;

    public Respuesta() {
        super();
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


}
