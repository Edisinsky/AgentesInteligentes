
package agentes;

import Mensaje.Mensajes;
import Modelo.entrada;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente4 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg4());
        super.setup();
    }

    class ComportamientoAg4 extends Behaviour {

        @Override
        public void action() {
          
           
            try {
                ACLMessage aclMSJ2 = blockingReceive();
                entrada entrada = (entrada) aclMSJ2.getContentObject();
                System.out.println("Hola agente 2, recibido, SOY  AGENTE 4: "+aclMSJ2.getContentObject()+" "+aclMSJ2.getConversationId());

                ACLMessage aclMSJ = blockingReceive();
                System.out.println("Hola agente H, soy Agente 4, Recibido "+aclMSJ.getConversationId() + " " + aclMSJ.getContentObject());
               
                
                

                // Enviar mensaje a agente 5
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag5", getAgent(),
                "cod-4-5",entrada);
                
                
               
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
