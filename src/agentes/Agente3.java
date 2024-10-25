
package agentes;

import Contenedor.Contenedor;
import Modelo.entrada;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente3 extends Agent {
    Contenedor contenedor;
    entrada entrada;

    @Override
    protected void takeDown() {
        contenedor.crearHijos(new Object[]{entrada, contenedor});
        System.out.println("Agente 3 terminado, el conocimiento previo del hijo es: " + entrada.toString());

    }

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg3());
    }


    class ComportamientoAg3 extends Behaviour {
        // El agente realiza una ultima accion antes de ser eliminado

        @Override
        public void action() {
            // Recibir mensaje de agente 2

            try {
                ACLMessage aclMSJ = blockingReceive();
                entrada = (entrada) aclMSJ.getContentObject();
                entrada.setSensor4(entrada.getSensor4() + 1);
                int sensor4 = entrada.getSensor4();
                contenedor = (Contenedor) getArguments()[0];
                System.out.println("MENSAJE DE AGENTE 2 RECIBIDO, SOY AGENTE 3 " + aclMSJ.getSender().getLocalName() + " " + sensor4);
                doDelete();
            } catch (UnreadableException e) {
                e.printStackTrace();
            }

        }

        @Override
        public boolean done() {
            return false;
        }

    }




    ;

}
