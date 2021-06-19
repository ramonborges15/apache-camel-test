package com.estudo.apache.camel.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author ramon.sales
 */
@DisallowConcurrentExecution
public class PedidosRotas implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        Logger.getLogger(PedidosRotas.class.getName()).log(Level.INFO, "Acessando diretório XML...");
        
        //Criar um novo camel context.
        CamelContext context = new DefaultCamelContext();

        context.setMessageHistory(true);

        try {
            //Adicionar rotas
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:../pedidos").
                            log("${body}").
                            split().
                            xpath("/pedidos/pedido").
                            setHeader("CamelFileName", simple("${file:name.noext}_${id}.xml")).
                            to("file:../saida");
                }
            });

            context.start();
            Thread.sleep(2000);
            context.stop();

        } catch (Exception ex) {
            Logger.getLogger(PedidosRotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Logger.getLogger(PedidosRotas.class.getName()).log(Level.INFO, "Análise do diretório finalizado.");
    }
}
