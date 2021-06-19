package com.estudo.apache.camel.app.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"numeroPedido", "remetente", "valor", "detalhesPedido"})
public class Pedido {
    
    private String numeroPedido;
    private String remetente;
    private List<Descricao> detalhesPedido;
    private Float valor;
    
    public Pedido() {
    }
    
    public Pedido(String numeroPedido, String remetente, List<Descricao> detalhesPedido, Float valor) {
        this.numeroPedido = numeroPedido;
        this.remetente = remetente;
        this.detalhesPedido = detalhesPedido;
        this.valor = valor;
    }
    
    @XmlElement
    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
    
    @XmlElement
    public String getRemetente() {
        return remetente;
    }
    
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }
    
    @XmlElementWrapper(name = "descricao")
    @XmlElement
    public List<Descricao> getDetalhesPedido() {
        return detalhesPedido;
    }

    public void setDetalhesPedido(List<Descricao> detalhesPedido) {
        this.detalhesPedido = detalhesPedido;
    }
    
    @XmlElement
    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
