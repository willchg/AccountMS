package co.edu.unal.account_ms.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Transaction {
    @Id
    private String id;
    private String productoOrigin;
    private String productoDestiny;
    private Integer value;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductoOrigin() {
        return productoOrigin;
    }

    public void setProductoOrigin(String productoOrigin) {
        this.productoOrigin = productoOrigin;
    }

    public String getProductoDestiny() {
        return productoDestiny;
    }

    public void setProductoDestiny(String productoDestiny) {
        this.productoOrigin = productoDestiny;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Transaction(String id, String productoOrigin, String productoDestiny, Integer value, Date date) {
        this.id = id;
        this.productoOrigin = productoOrigin;
        this.productoDestiny = productoDestiny;
        this.value = value;
        this.date = date;

    }
}
