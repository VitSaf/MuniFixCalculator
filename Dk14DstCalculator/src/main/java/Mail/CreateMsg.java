package Mail;

import Db.Record;
import com.independentsoft.msg.*;

import java.io.IOException;

public class CreateMsg {
    private static final String RECIPIENT_MAIL_ADDRESS = "safonovva@vsk.sibur.ru";
    private static final String MSG_PATH = "logs.msg";

    public static void createLogsMsg(Record[] r ){
        Message msg = new Message();
        Recipient recipient = new Recipient();
        recipient.setAddressType("SMTP");
        recipient.setDisplayType(DisplayType.MAIL_USER);
        recipient.setObjectType(ObjectType.MAIL_USER);
        recipient.setEmailAddress(RECIPIENT_MAIL_ADDRESS);
        recipient.setRecipientType(RecipientType.TO);

        msg.setSubject("DSSK Calculator logs");
        String bodyText = null;
        for (Record rec : r)
            bodyText += rec.toString();
        msg.setBody(bodyText);
        msg.getRecipients().add(recipient);
        msg.getStoreSupportMasks().add(StoreSupportMask.CREATE);

        try {
            msg.save(MSG_PATH, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
