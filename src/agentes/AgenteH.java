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
        addBehaviour(new ComportamientoAgenteH());
    }
  
    class ComportamientoAgenteH extends CyclicBehaviour {
        //ACCION
        @Override
        public void action() {
            //Hijo de 3 nace

            entrada entrada = (entrada) getArguments()[0];//Adquirir el conocimiento del agente 3
            entrada.setSensor4(entrada.getSensor4()+1);//Aumentar el valor de sensor 4

            // Enviar mensaje al agente 4
            Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4",getAgent() , "H-4", entrada);

            ACLMessage aclMSJ = blockingReceive();

        }



    }
}
