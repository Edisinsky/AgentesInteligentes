
package agentes;

import Mensaje.Mensajes;
import Modelo.Entrada;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente4 extends Agent {
    Entrada entrada;
    Entrada entrada1;
    Entrada entrada2;

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg4());
    }

    class ComportamientoAg4 extends CyclicBehaviour {

        @Override
        public void action() {


            try {
                //Recibir mensaje de Agente 2
                ACLMessage aclMSJ2 = blockingReceive();
                entrada1 = (Entrada) aclMSJ2.getContentObject();//Recibir conocimiento del agente 2
                System.out.println("Hola agente 2, recibido, SOY  AGENTE 4: " + aclMSJ2.getContentObject() + " " + aclMSJ2.getConversationId()+" "+aclMSJ2.getSender());

                //Recibir mensaje de Agente H
                ACLMessage aclMSJ = blockingReceive();
                entrada2 = (Entrada) aclMSJ.getContentObject();//Recibir conocimiento del agente H
                entrada2.setSensor4(entrada2.getSensor4() + 1);   //Aumentar el valor de sensor 4
                System.out.println("Hola agente H, soy Agente 4, Recibido " + aclMSJ.getConversationId() + " " + aclMSJ.getContentObject()+" "+aclMSJ2.getSender());

                //Unir ambas entradas para enviar una sola al agente 5
                entrada = new Entrada(entrada1.getSensor1(), entrada1.getSensor2(), entrada1.getSensor3(), entrada2.getSensor4(), entrada2.getNumHijo());

                // Enviar mensaje a agente 5
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag5", getAgent(),
                        "cod-4-5", entrada);


            } catch (UnreadableException e) {
                e.printStackTrace();
            }

        }


    }

}
