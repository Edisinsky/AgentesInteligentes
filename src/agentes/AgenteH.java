package agentes;

import Contenedor.Contenedor;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import Mensaje.Mensajes;
import Modelo.entrada;

public class AgenteH extends Agent {
    Contenedor c;
    int hijo=0;
    String nombreAgenteHijo = "";
    boolean done = false;
    @Override
    protected void setup() {
        // es para abrir cerrar puertos y liberar recursos
      // addBehaviour(new ComportamientoAgente1());
        addBehaviour(new ComportamientoAgenteH());
        super.setup(); //To change body of generated methods, choose Tools | Templates.
    }
  
    class ComportamientoAgenteH extends Behaviour {
        //ACCION
        @Override
        public void action() {
            entrada entrada = (entrada) getArguments()[0];
            entrada.setSensor4(entrada.getSensor4()+1);
            //System.out.println("Soy AgH mi informacion es "+ entrada);
            // Enviar mensaje al agente 4
            Contenedor c = (Contenedor) getArguments()[1];
            Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4",getAgent() , "H-4", entrada);
            System.out.println("Mensaje enviado a Agente 4");
            done = true;

        }

        @Override
        public boolean done() {
            return done;
        }

    }
}
