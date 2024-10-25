
package agentes;

import Mensaje.Mensajes;
import Modelo.entrada;
import jade.core.Agent;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente2 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg2());
        super.setup();
    }

    class ComportamientoAg2 extends CyclicBehaviour {

        @Override
        public void action() {


            try {
                // Recibir mensaje de Agente1
                ACLMessage aclMSJ = blockingReceive();
                entrada entrada = (entrada) aclMSJ.getContentObject();//Recibir conocimiento del agente 1
                entrada.setSensor4(entrada.getSensor4() + 1);   //Aumentar el valor de sensor 4
                System.out.println("Hola Agente 1, RECIBIDO, SOY AGENTE 2: " + aclMSJ.getContentObject());//Confirmar la recepcion del mensaje
                // Enviar mensaje al agente 3
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag3", getAgent(),
                        "cod-2-3", entrada);//Enviar el conocimiento al agente 3

                // Enviar mensaje a Agente 4
                entrada.setSensor1(entrada.getSensor1() + ".2");//Editar version de los sensores
                entrada.setSensor2(entrada.getSensor2() + ".2");
                entrada.setSensor3(entrada.getSensor3() + ".2");

                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4", getAgent(),
                        "cod-2-4", entrada);//Enviar el conocimiento al agente 4

            } catch (UnreadableException e) {
                e.printStackTrace();
            }

        }


    }

}
