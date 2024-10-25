
package agentes;

import Mensaje.Mensajes;
import Modelo.entrada;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente1 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg1());
    }

    class ComportamientoAg1 extends CyclicBehaviour {

        @Override
        public void action() {

            entrada entrada = (entrada) getArguments()[0];
            entrada.setSensor4(entrada.getSensor4() + 1);
            // Enviar mensaje tipo objeto a agente 2
            Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag2", getAgent(),
                    "cod-1-2", entrada);
            // Recibir mensaje de agente 5
            ACLMessage aclMSJ = blockingReceive();
            doDelete();


        }



    }

}
