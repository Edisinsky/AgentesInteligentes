
package agentes;

import Contenedor.Contenedor;
import Modelo.entrada;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente3 extends Agent {
    Contenedor contenedor;
    entrada entrada;


    // El agente realiza una ultima accion antes de ser eliminado
    @Override
    protected void takeDown() {
        //contenedor.crearHijos(new Object[]{entrada, contenedor});
        contenedor.crearHijos(entrada);
        System.out.println("Agente 3 terminado, el conocimiento previo del hijo es: " + entrada.toString());

    }

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg3());
    }


    class ComportamientoAg3 extends CyclicBehaviour {


        @Override
        public void action() {


            try {
                // Recibir mensaje de agente 2
                ACLMessage aclMSJ = blockingReceive();
                entrada = (entrada) aclMSJ.getContentObject();//Adquirir el conocimiento del agente 2
                entrada.setSensor4(entrada.getSensor4() + 1);//Aumentar el valor de sensor 4

                //Adquirir el contenedor
                contenedor = (Contenedor) getArguments()[0];

                System.out.println("MENSAJE DE AGENTE 2 RECIBIDO, SOY AGENTE 3 " + aclMSJ.getContentObject());

                doDelete();

            } catch (UnreadableException e) {
                e.printStackTrace();
            }

        }


    }




    ;

}
