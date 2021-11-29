package co.edu.unal.account_ms.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Account {
    @Id
    private String producto;
    private Integer precio;
    private Date lastChange;

    public Account(String producto, Integer precio, Date lastChange) {
        this.producto= producto;
        this.precio = precio;
        this.lastChange = lastChange;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

}
