package jo.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import jo.config.kafka.KafkaProcessor;
import jo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    NotificationRepository notificationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRegistered_Notify(@Payload Registered registered) {
        if (!registered.validate()) return;
        Registered event = registered;
        System.out.println(
            "\n\n##### listener notification Notify : " + registered.toJson() + "\n\n"
        );

        Notification notification = new Notification();
        notification.setCusName(event.getCusName());
        notification.setAcctNo(event.getAcctNo());
        notification.setCusTelNo(event.getCusTelNo());
        notification.setTranType(event.getTranType());
        
        // Sample Logic //
        notificationRepository.save(notification);        

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTransfered_Notify(@Payload Transfered transfered) {
        if (!transfered.validate()) return;
        Transfered event = transfered;
        System.out.println(
            "\n\n##### listener Notify : " + transfered.toJson() + "\n\n"
        );

        Notification notification = new Notification();
        notification.setCusName(event.getCusName());
        notification.setAcctNo(event.getAcctNo());
        notification.setCusTelNo(event.getCusTelNo());
        notification.setTranType(event.getTranType());
        
        // Sample Logic //
        notificationRepository.save(notification);   
        // Sample Logic //
        //Notification.notify(event);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCancelled_Notify(@Payload Cancelled cancelled) {
        if (!cancelled.validate()) return;
        Cancelled event = cancelled;
        System.out.println(
            "\n\n##### listener Notify : " + cancelled.toJson() + "\n\n"
        );

        Notification notification = new Notification();
        notification.setCusName(event.getCusName());
        notification.setAcctNo(event.getAcctNo());
        notification.setCusTelNo(event.getCusTelNo());
        notification.setTranType(event.getTranType());
        
        // Sample Logic //
        notificationRepository.save(notification); 
        // Sample Logic //
        //Notification.notify(event);
    }
    // keep

}
