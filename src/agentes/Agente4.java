
package agentes;

import Mensaje.Mensajes;
import Modelo.Entrada;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente4 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg4());
    }

    class ComportamientoAg4 extends Behaviour {

        @Override
        public void action() {
          
           
            try {
                //Recibir mensaje de Agente 2
                ACLMessage aclMSJ2 = blockingReceive();
                Entrada entrada1 = (Entrada) aclMSJ2.getContentObject();//Recibir conocimiento del agente 2
                System.out.println("Hola agente 2, recibido, SOY  AGENTE 4: "+aclMSJ2.getContentObject()+" "+aclMSJ2.getConversationId());

                //Recibir mensaje de Agente H
                ACLMessage aclMSJ = blockingReceive();
                Entrada entrada2 = (Entrada) aclMSJ.getContentObject();//Recibir conocimiento del agente H
                entrada2.setSensor4(entrada2.getSensor4() + 1);   //Aumentar el valor de sensor 4
                System.out.println("Hola agente H, soy Agente 4, Recibido "+aclMSJ.getConversationId() + " " + aclMSJ.getContentObject());
               
                //Unir ambas entradas para enviar una sola al agente 5
                Entrada entrada = new Entrada(entrada1.getSensor1(), entrada1.getSensor2(), entrada1.getSensor3(), entrada2.getSensor4(),entrada2.getNumHijo());

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
