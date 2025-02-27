package Empresa;

import Cliente.Mail;
import Interfaces.IJson;
import Lugares.*;
import org.json.JSONException;
import org.json.JSONObject;

public class Factura implements IJson, Comparable {
    private int id;
    private String nombre;
    private String dni;
    private Mail mail;
    private double precioFinal;
    private Fecha fecha;
    private String dirInmobiliaria;
    private String dirInmueble;
    private String estadoActual;

    public Factura(int id, String nombre, String dni, Mail mail, double precioFinal, Fecha fecha, String dirInmobiliaria, String dirInmueble, String estadoActual) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.precioFinal = precioFinal;
        this.fecha = fecha;
        this.dirInmobiliaria = dirInmobiliaria;
        this.dirInmueble = dirInmueble;
        this.estadoActual = estadoActual;
    }

    public Factura() {
        id = 0;
        nombre = "";
        dni = "";
        mail = null;
        precioFinal = 0;
        fecha = null;
        dirInmobiliaria = "";
        dirInmueble = "";
        estadoActual = "";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return id == factura.id;
    }

    @Override
    public int compareTo(Object o) {
        int valor = 0;
        if (o != null) {
            if (o instanceof Factura) {
                if (((Factura) o).getId() > id) {
                    valor = 1;
                } else if (((Factura) o).getId() < id) {
                    valor = -1;
                }
            }
        }
        return valor;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni=" + dni +
                ", mail=" + mail +
                ", precioFinal=" + precioFinal +
                ", fecha=" + fecha +
                ", dirInmobiliaria='" + dirInmobiliaria + '\'' +
                ", dirInmueble='" + dirInmueble + '\'' +
                ", estadoActual='" + estadoActual + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public Mail getMail() {
        return mail;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public String getDirInmobiliaria() {
        return dirInmobiliaria;
    }

    public String getDirInmueble() {
        return dirInmueble;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
        return null;
    }

    @Override
    public void fromJsonObj(JSONObject obj) throws JSONException {

    }
}

// imprimir dentro de la factura que reciba el inmueble