package com.sistema.bancario.models;

import javax.persistence.*;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "cpersona")
    private Integer cpersona;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "genero",length = 1)
    private String genero;

    @Column(name = "identificaion",length = 10)
    private String identificaion;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono",length = 10)
    private String telefono;

    public Integer getCpersona() {
        return cpersona;
    }

    public void setCpersona(Integer cpersona) {
        this.cpersona = cpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdentificaion() {
        return identificaion;
    }

    public void setIdentificaion(String identificaion) {
        this.identificaion = identificaion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
