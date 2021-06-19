package com.estudo.apache.camel.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Administrator
 */
public class Iniciar {

    static final Logger logger = LogManager.getLogger(Iniciar.class.getName());

    // Instância do agendador         
    private Scheduler scheduler;

    /**
     * Status do processo (TRUE - Em execução / FALSE - Parar).
     */
    private static boolean estadoProcesso;

    /**
     * Objeto utilizado para disparar o método de liberação de recursos no
     * encerramento da JVM.
     */
    private Thread shutdownHook;

    /**
     * Inicia a rotina de atualização para busca dos dados de medição.
     */
    public Iniciar() {

        /* Fábrica de instâncias do agendador de tarefas */
        SchedulerFactory schedulerFactory;

        // registando "shutdown hooks"
        this.registraShutdownHook();

        // carregando o arquivo de configuração do agendador
        try {
            schedulerFactory = new StdSchedulerFactory(PathUtil.getConfDir() + "/quartz.properties");
            setScheduler(schedulerFactory.getScheduler());
        } catch (Exception ex) {
            logger.error("Erro ao carregar arquivo de configuração da rotina de atualização..", ex);
            System.exit(-1);
        }

        //Iniciando o scheduler
        logger.info("-----================== INICIO DO SISTEMA ==================-------------");
        try {
            getScheduler().start();
        } catch (SchedulerException ex) {
            logger.error("Erro ao iniciar o agendador. Finalizando aplicativo.", ex);
            System.exit(-2);
        }

        // fica no loop até que o programa seja encerrado
        estadoProcesso = true;
        while (estadoProcesso) {

            // aguarda até a próxima execução
            try {
                Thread.sleep(60000 * 10);
            } catch (InterruptedException ex) {
                logger.error("Erro aguardar a próxima execução do agendador.", ex);
            }
        }

        System.out.println("");
        logger.info("-----================== SAINDO DO SISTEMA ==================-------------");

        //Finalizando o scheduling
        try {
            getScheduler().shutdown(true);
        } catch (SchedulerException ex) {
            logger.error("Erro ao finalizar os jobs do quartz.", ex);
        }
    }

    /**
     * Adiciona um "shutdown hook" à JVM que será executado sempre antes do
     * término da JVM.<p>
     *
     * Para definir os "shutdown hooks" a serem executados em cada caso, o
     * usuário deverá implementar o método liberaRecursosShutdown() para
     * realizar os tratamentos adequados.
     */
    protected void registraShutdownHook() {

        // declaração das variáveis
        Runnable sair;      // thread que executará o shutdown hook

        // início do método
        sair = new Runnable() {

            @Override
            public void run() {
                logger.info("Parando aplicação e liberando recursos alocados.");
                try {
                    getScheduler().shutdown(true);
                } catch (SchedulerException ex) {
                    logger.warn("Erro ao finalizar os jobs do quartz.", ex);
                }
            }
        };
        setShutdownHook(new Thread(sair));
        Runtime.getRuntime().addShutdownHook(getShutdownHook());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Iniciar iniciar = new Iniciar();
    }

    /**
     * Iniciliza o serviço desejado.
     *
     *
     * @param args Parâmetros para inicialização do processo.
     */
    public static void start(String[] args) {

        // início do método
        main(args);
    }

    /**
     * Finaliza o serviço desejado.
     *
     *
     * @param args Parâmetros para encerramento do processo.
     */
    public static void stop(String[] args) {

        // início do método
        estadoProcesso = false;
    }

    /**
     * Altera o valor do atributo scheduler.
     *
     * @param scheduler Novo valor do atributo scheduler.
     */
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * Recupera o valor do atributo scheduler.
     *
     * @return Valor do atributo scheduler.
     */
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Recupera o valor do atributo shutdownHook.
     *
     * @return Valor do atributo shutdownHook
     */
    public Thread getShutdownHook() {
        return shutdownHook;
    }

    /**
     * Altera o valor do atributo shutdownHook.
     *
     * @param shutdownHook Novo valor do atributo shutdownHook
     */
    public void setShutdownHook(Thread shutdownHook) {
        this.shutdownHook = shutdownHook;
    }
}
