
package agentes;

import Mensaje.Mensajes;
import Modelo.Entrada;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente5 extends Agent {
    Entrada entrada;
    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg5());
    }

    class ComportamientoAg5 extends Behaviour {

        @Override
        public void action() {

            try {
                // Recibir mensaje de agente 4
                ACLMessage aclMSJ = blockingReceive();
                System.out.println("Hola Agente 4, soy agente 5, RECIBIDO" + aclMSJ.getContentObject() +" "+ aclMSJ.getConversationId());
                entrada = (Entrada) aclMSJ.getContentObject();
                // Enviar mensaje a agente 1
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag1", getAgent(),
                        "cod-5-1", entrada);
            } catch (UnreadableException e) {
                e.printStackTrace();
            }

        }

        @Override
        public boolean done() {
            return false;
        }

    }

}
