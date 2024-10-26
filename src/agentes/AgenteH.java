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
        System.out.println(getName() + " Terminado, se crea " + nombreAgenteHijo);
        System.out.println("Agent " + getLocalName() + " terminating.");
        System.out.println("Hola, morí");

    }

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAgenteH());
    }

    class ComportamientoAgenteH extends CyclicBehaviour {
        //ACCION
        @Override
        public void action() {
            System.out.println("Soy el "+getAgent().getLocalName()+" y he nacido");
            if (!(getAgent().getLocalName().equals("AgenteH1"))) {
                ACLMessage aclMSJ = blockingReceive();
                entrada = (Entrada) getArguments()[0];
                entrada.setSensor4(entrada.getSensor4() + 1);
                entrada.setNumHijo(entrada.getNumHijo() + 1);
                nombreAgenteHijo = "AgenteH" + entrada.getNumHijo();
                //Enviar mensaje a Agente 4
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4", getAgent(),
                        "cod-H" + entrada.getNumHijo() + "-4", entrada);//Enviar el conocimiento al agente 4
                try {
                    entrada = (Entrada) aclMSJ.getContentObject();
                    System.out.println("Hola soy el agente:" + nombreAgenteHijo + " , recibido de agente 2: " + entrada.toString() + " " + aclMSJ.getConversationId());
                } catch (UnreadableException e) {
                    throw new RuntimeException(e);
                }
                doDelete();

            } else if (cont == 0) {
                entrada = (Entrada) getArguments()[0];
                entrada.setSensor4(entrada.getSensor4() + 1);
                entrada.setNumHijo(entrada.getNumHijo() + 1);
                c = (Contenedor) getArguments()[1];
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4", getAgent(),
                        "cod-H" + entrada.getNumHijo() + "-4", entrada);//Enviar el conocimiento al agente 4
                cont++;
            } else if (cont == 1) {
                ACLMessage aclMSJ = blockingReceive();
                try {
                    System.out.println("Hola soy el agente:" + nombreAgenteHijo + " , recibido de agente 2: " + aclMSJ.getContentObject() + " " + aclMSJ.getConversationId());
                } catch (UnreadableException e) {
                    throw new RuntimeException(e);
                }
                try {
                    entrada = (Entrada) aclMSJ.getContentObject();
                } catch (UnreadableException e) {
                    throw new RuntimeException(e);
                }
                numHijo = entrada.getNumHijo();
                numHijo++;
                nombreAgenteHijo = "AgenteH" + numHijo;
                entrada.setNumHijo(numHijo);
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4", getAgent(), "cod-H" + entrada.getNumHijo() + "-4", entrada);
                doDelete();
            }


        }


    }
}

