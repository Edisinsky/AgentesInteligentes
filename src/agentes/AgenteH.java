package agentes;

import Contenedor.Contenedor;
import Modelo.Entrada;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import Mensaje.Mensajes;
import jade.lang.acl.UnreadableException;

import java.io.IOException;

import static jade.core.MicroRuntime.getAgent;

public class AgenteH extends Agent {
    Contenedor c;
    int numHijo;
    String nombreAgenteHijo;
    Entrada entrada;
    int cont = 0;

    @Override
    protected void takeDown() {
        c.crearHijos(nombreAgenteHijo, new Object[]{entrada, c});
        System.out.println("Agent " + getLocalName() + " terminating.");

    }

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAgenteH());
    }

    class ComportamientoAgenteH extends CyclicBehaviour {
        //ACCION
        @Override
        public void action() {
            if (cont==0){
                entrada= (Entrada) getArguments()[0];
                c= (Contenedor) getArguments()[1];
                entrada.setNumHijo(entrada.getNumHijo() + 1);
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4", getAgent(),
                        "cod"+getLocalName()+"+4", entrada);//Enviar el conocimiento al agente 4
                cont++;
            }

            try {
                ACLMessage aclMSJ = blockingReceive();
                System.out.println("Hola Agente 2, soy "+getLocalName()+"Contenido: "+ aclMSJ.getContentObject()+" "+ aclMSJ.getConversationId());
                entrada= (Entrada) aclMSJ.getContentObject();
                nombreAgenteHijo = "AgenteH" + (entrada.getNumHijo()+1);
                cont++;
            } catch (UnreadableException e) {
                throw new RuntimeException(e);
            }
            if(cont>1) {
                doDelete();
            }

        }


    }
}

