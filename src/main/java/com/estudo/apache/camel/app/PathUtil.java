package com.estudo.apache.camel.app;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramon.sales
 */
public class PathUtil {

    /**
     * Retorna o path completo da pasta "conf" do sistema.
     *
     * @return Path da pasta conf
     */
    public static String getConfDir() throws Exception {

        // início do método
        return getRootDir() + getSeparador() + "conf";
    }

    /**
     * Retorna o path completo da pasta base do sistema.
     *
     * @return Path da aplicação
     */
    public static String getRootDir() throws Exception {

        try {
            // início do método
            return new File("..").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(PathUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Erro ao recuperar o caminho base da aplicação. " + ex);
        }

    }

    /**
     * Verifica se o sistema operacional é Windows.
     *
     * @return true se o sistema operacional é Windows.
     */
    public static boolean isWindows() {

        // início do método        
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));
    }

    /**
     * Retorna o separador do caminho do arquivo dependendo do sistema
     * operacional.
     *
     * @return O separador do sistema operacional.
     */
    public static String getSeparador() {

        // início do método        
        return (isWindows() ? "\\" : "/");
    }
}
