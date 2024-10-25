
package agentes;

import Mensaje.Mensajes;
import Modelo.Entrada;
import jade.core.Agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Agente2 extends Agent {
    int versionSensor = 2; //Version de los sensores
    String nombreAgenteHijo;
    Entrada entrada;
    int cont = 0;

    @Override
    protected void setup() {
        addBehaviour(new ComportamientoAg2());
    }

    class ComportamientoAg2 extends CyclicBehaviour {
        @Override
        public void action() {

            try {


                //Se recibe el mensaje de Agente1
                ACLMessage aclMSJ = blockingReceive();
                entrada = (Entrada)aclMSJ.getContentObject();//Recibir conocimiento del agente 1
                entrada.setSensor4(entrada.getSensor4() + 1);   //Aumentar el valor de sensor 4
                System.out.println("Hola Agente 1, RECIBIDO, SOY AGENTE 2: " + aclMSJ.getContentObject() + " " + aclMSJ.getConversationId());//Confirmar la recepcion del mensaje
                nombreAgenteHijo = "AgenteH" + entrada.getNumHijo();

                if (cont==0){
                    // Se envia el mensaje a 3 una unica vez
                    Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag3", getAgent(),
                            "cod-2-3", entrada);//Enviar el conocimiento al agente 3
                    cont++;
                }else {
                    // Se envia el mensaje a H
                    Mensajes.send_msj_Object(ACLMessage.INFORM, nombreAgenteHijo, getAgent(),
                            "cod-2-H", entrada);//Enviar el conocimiento al agente H
                }




                // Enviar mensaje a Agente 4
                entrada.setSensor1(entrada.getSensor1() + "." + versionSensor);//Editar version de los sensores
                entrada.setSensor2(entrada.getSensor2() + "." + versionSensor);
                entrada.setSensor3(entrada.getSensor3() + "." + versionSensor);

                Mensajes.send_msj_Object(ACLMessage.INFORM, "Ag4", getAgent(),
                        "cod-2-4", entrada);//Enviar el conocimiento al agente 4
                versionSensor++;//Aumentar la version de los sensores


            } catch (UnreadableException e) {
                e.printStackTrace();
            }

        }


    }

}
