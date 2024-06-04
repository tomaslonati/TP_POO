package Programa.Model;

import java.io.Serializable;
import java.util.List;

public class Cliente implements Serializable{
    private int idCliente;
    private String nombre;
    private String direccion;
    private int telefono;
    private String localidad;
    private String provincia;
    private String email;

    // Constructor
    public Cliente(int idCliente, String nombre, String direccion, int telefono, String localidad, String provincia, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.localidad = localidad;
        this.provincia = provincia;
        this.email = email;
    }

    // Getters
    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
