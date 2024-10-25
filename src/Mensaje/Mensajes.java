
package Mensaje;

import java.io.IOException;
import java.io.Serializable;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class Mensajes {
    public static void send_msj(int timpoMsj, String receptor,
            Agent emisor, String conversationId, String contenido)  {

        ACLMessage aclm = new ACLMessage(timpoMsj);
        aclm.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        AID aid = new AID();
        aid.setLocalName(receptor);
        aclm.addReceiver(aid);
        aclm.setSender(emisor.getAID());
        aclm.setConversationId(conversationId);
        aclm.setContent(contenido);
        emisor.send(aclm);
    }

    public static void send_msj_Object(int timpoMsj, String receptor,
            Agent emisor, String conversationId, Serializable contenido)  {

        ACLMessage aclm = new ACLMessage(timpoMsj);
        aclm.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        AID aid = new AID();
        aid.setLocalName(receptor);
        aclm.addReceiver(aid);
        aclm.setSender(emisor.getAID());
        aclm.setConversationId(conversationId);
        try {
            aclm.setContentObject(contenido);
        } catch (IOException ex) {
            System.out.println("Error al enviar el mensaje");
        }
        emisor.send(aclm);
    }
}
