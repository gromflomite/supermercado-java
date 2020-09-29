package com.ipartek.formacion.modelo.pojo;

public class Usuario {

    private int id;
    private String nombre;
    private String contrasenia;
    private String dob;
    private Rol rol;

    public Usuario() {
	super();
	this.id = 0;
	this.nombre = "";
	this.contrasenia = "";
	this.dob = "";
	this.rol = new Rol();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getContrasenia() {
	return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
	this.contrasenia = contrasenia;
    }

    public String getDob() {
	return dob;
    }

    public void setDob(String dob) {
	this.dob = dob;
    }

    public Rol getRol() {
	return rol;
    }

    public void setRol(Rol rol) {
	this.rol = rol;
    }

    @Override
    public String toString() {
	return "Usuario [id=" + id + ", nombre=" + nombre + ", contrasenia=" + contrasenia + ", dob=" + dob + ", rol=" + rol + "]";
    }

}
