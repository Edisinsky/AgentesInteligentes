
package agentes;

import Mensaje.Mensajes;
import Modelo.Entrada;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente1 extends Agent {
    int cont=0;
    Entrada entrada;

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg1());
    }

    class ComportamientoAg1 extends CyclicBehaviour {

        @Override
        public void action() {
            System.out.println("**********************************************");
            if (cont==0){
                entrada = (Entrada) getArguments()[0];
                entrada.setSensor4(entrada.getSensor4() + 1);
                cont++;
            }

            // Enviar mensaje tipo objeto a agente 2
            Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag2", getAgent(),
                    "cod-1-2", entrada);
            // Recibir mensaje de agente 5
            ACLMessage aclMSJ = blockingReceive();
            try {
                entrada = (Entrada)aclMSJ.getContentObject();
                System.out.println("Hola soy el agente 1, recibido de agente 5: "+entrada.toString() + " " + aclMSJ.getConversationId()+" "+aclMSJ.getSender());

            } catch (UnreadableException e) {
                throw new RuntimeException(e);
            }
            cont++;
           /* if(cont==10) {
                doDelete();
            }*/


        }



    }

}
