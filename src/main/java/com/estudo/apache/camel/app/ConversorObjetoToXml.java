package com.estudo.apache.camel.app;

import com.estudo.apache.camel.app.entity.Descricao;
import com.estudo.apache.camel.app.entity.Pedido;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author ramon.sales
 */
public class ConversorObjetoToXml {

    public static void main(String[] args) throws Exception {

        JAXBContext contextObj = JAXBContext.newInstance(Pedido.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        
        Descricao descricao1 = new Descricao("Hamburguer", "Blend da Casa");
        Descricao descricao2 = new Descricao("Açaí", "Sem adição");
        Descricao descricao3 = new Descricao("Pizza", "Portuguesa");
        Descricao descricao4 = new Descricao("Salada", "Rúcula com agrião");
        
        List<Descricao> listaDescricoes = new ArrayList<>();
        listaDescricoes.add(descricao1);
        listaDescricoes.add(descricao2);
        listaDescricoes.add(descricao3);
        listaDescricoes.add(descricao4);
        
        Pedido primeiroPedido = new Pedido("10001", "Felipe Araujo", listaDescricoes, Float.valueOf("22.154"));
        Pedido segundoPedido = new Pedido("10002", "Ramon Borges", listaDescricoes, Float.valueOf("45.10"));
        Pedido terceiroPedido = new Pedido("10003", "Romulo Sales", listaDescricoes, Float.valueOf("35.14"));
        Pedido quartoPedido = new Pedido("10004", "Gabriel Veron", listaDescricoes, Float.valueOf("105.17"));
        Pedido quintoPedido = new Pedido("10005", "Juan Roman Riquelme", listaDescricoes, Float.valueOf("14.25"));

        List<Pedido> listaPedidos = new ArrayList<>();
        listaPedidos.add(primeiroPedido);
        
        marshallerObj.marshal(primeiroPedido, new FileOutputStream("pedidos/queue_pedidos.xml"));
    }
}
