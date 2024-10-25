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

    @Override
    protected void takeDown() {
        c.crearHijos(nombreAgenteHijo, new Object[]{entrada, c});
        System.out.println(getName() + " Terminado, se crea " + nombreAgenteHijo);

        System.out.println("Agent " + getLocalName() + " terminating.");
    }

        @Override
        protected void setup () {
            addBehaviour(new ComportamientoAgenteH());
        }

        class ComportamientoAgenteH extends CyclicBehaviour {
            //ACCION
            @Override
            public void action() {

                //Adquiere conocimiento de tipo Entrada
                entrada = (Entrada) getArguments()[0];//Adquirir el conocimiento del agente 3
                entrada.setSensor4(entrada.getSensor4() + 1);//Aumentar el valor de sensor 4

                //Adquiere conocimiento del contenedor para poder crear otro hijo
                c = (Contenedor) getArguments()[1];
                numHijo = entrada.getNumHijo();
                nombreAgenteHijo = "AgenteH" + numHijo;
                System.out.println(nombreAgenteHijo + " Nace");
                entrada.setNumHijo(numHijo+1);
                // Enviar mensaje al agente 4
                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4", getAgent(), "H" + numHijo + "-4", entrada);

                //Adquiere el numero de hijo
                numHijo++;
                nombreAgenteHijo = "AgenteH" + numHijo;

                ACLMessage aclMSJ = blockingReceive();
                try {
                    entrada = (Entrada) aclMSJ.getContentObject();
                    System.out.println("Hola soy el agente H, recibido de agente 4: " + entrada.toString() + " " + aclMSJ.getConversationId());
                } catch (UnreadableException e) {
                    throw new RuntimeException(e);
                }

                doDelete();


            }


        }
    }

