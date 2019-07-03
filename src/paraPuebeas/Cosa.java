package paraPuebeas;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cosa {
    private int valor;
    private double precio;
    private String nombre;
    private LocalDate fecha;
    private LocalTime hora;
    private boolean activo;

    public Cosa(String nombre, int valor, double precio, LocalDate fecha, LocalTime hora, boolean activo) {
        this.valor = valor;
        this.precio = precio;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.activo = activo;
    }

    public static Cosa of(String nombre, int valor, double precio, LocalDate fecha, LocalTime hora, boolean activo){
        return new Cosa(nombre,valor, precio, fecha, hora, activo);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cosa{" +
                "valor=" + valor +
                ", precio=" + precio +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", activo=" + activo +
                '}';
    }
}
