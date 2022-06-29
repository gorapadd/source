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
    AcctInfoRepository acctInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReqRegistered_Register(
        @Payload ReqRegistered reqRegistered
    ) {
        if (!reqRegistered.validate()) return;
        ReqRegistered event = reqRegistered;
       
       
       
        System.out.println(
            "\n\n##### listener acctInfo reqRegistered : " + reqRegistered.toJson() + "\n\n"
        );

        AcctInfo acctInfo = new AcctInfo();
        acctInfo.setCusName(event.getCusName());
        acctInfo.setAcctNo(event.getAcctNo());
        acctInfo.setCusTelNo(event.getCusTelNo());
        acctInfo.setTranType(event.getTranType());
        
        // Sample Logic //
        acctInfoRepository.save(acctInfo);
    }
    // keep

}
