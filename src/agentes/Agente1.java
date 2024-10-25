
package agentes;

import Mensaje.Mensajes;
import Modelo.entrada;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente1 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg1());
        super.setup();
    }

    class ComportamientoAg1 extends Behaviour {

        @Override
        public void action() {


            // Enviar mensaje tipo objeto a agente 2
            Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag2", getAgent(),
                    "cod-1-2", new entrada("v1", "v2", "v3", 7));
            // Recibir mensaje de agente 5
            ACLMessage aclMSJ = blockingReceive();
            doDelete();


        }

        @Override
        public boolean done() {
            return false;
        }

    }

}
