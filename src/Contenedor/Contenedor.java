
package Contenedor;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.entrada;
import agentes.Agente1;
import agentes.Agente2;
import agentes.Agente3;
import agentes.Agente4;
import agentes.Agente5;
import agentes.AgenteH;

public class Contenedor {
    AgentContainer mainContenedor;
    AgentController agentController;

    public void configurarContenedor() {

        // Rutina de jade
        // importacion local
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        // NO PUEDO CORRER DOS VECES EL MISMO CODIGO
        runtime.setCloseVM(true);
        // DEBO CREAR UN PROFILE
        // parametros null corre en la maquina que estas
        // identificador por defecto se autoincrementa
        Profile profile = new ProfileImpl(null, 1099, null);
        mainContenedor = runtime.createMainContainer(profile);
        agregarAgentes();
    }

    private void agregarAgentes() {
        try {
        
            mainContenedor.createNewAgent("Ag5", Agente5.class.getName(), null).start();
            mainContenedor.createNewAgent("Ag4", Agente4.class.getName(), null).start();
            mainContenedor.createNewAgent("Ag3", Agente3.class.getName(), new Object[] { this }).start();
            mainContenedor.createNewAgent("Ag2", Agente2.class.getName(),null).start();
            mainContenedor.createNewAgent("Ag1", Agente1.class.getName(),  new Object[] { new entrada("v1", "v2", "v3", 1)}).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearHijos(Object conocimiento) {
        try {
            mainContenedor.createNewAgent("AgH", AgenteH.class.getName(), new Object[] {conocimiento}).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Contenedor().configurarContenedor();
    }

}
