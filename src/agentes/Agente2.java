
package agentes;

import Mensaje.Mensajes;
import Modelo.entrada;
import jade.core.Agent;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente2 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg2());
        super.setup();
    }

    class ComportamientoAg2 extends Behaviour {

        @Override
        public void action() {

            // Recibir mensaje de Agente1
            try {
                ACLMessage aclMSJ = blockingReceive();

                entrada entrada = (entrada) getArguments()[0];

                System.out.println("Hola Agente 1, RECIBIDO, SOY AGENTE 2: " + aclMSJ.getContentObject());
                // Enviar mensaje al agente 3
                //if ()
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag3", getAgent(),
                        "cod-2-3", entrada);
                // Enviar mensaje a Agente 4
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4", getAgent(),
                        "cod-2-4", entrada);

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
