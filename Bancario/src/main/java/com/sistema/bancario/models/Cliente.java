package com.sistema.bancario.models;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(referencedColumnName = "cpersona",name = "clienteid" )
public class Cliente extends Persona {

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado")
    private boolean estado;

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
