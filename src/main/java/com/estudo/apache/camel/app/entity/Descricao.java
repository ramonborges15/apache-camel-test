package com.estudo.apache.camel.app.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Descricao {

    private String tipoLanche;
    private String sabor;

    public Descricao() {
    }
    
    public Descricao(String tipoLanche, String sabor) {
        this.tipoLanche = tipoLanche;
        this.sabor = sabor;
    }

    public String getTipoLanche() {
        return tipoLanche;
    }

    public void setTipoLanche(String tipoLanche) {
        this.tipoLanche = tipoLanche;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }
}
