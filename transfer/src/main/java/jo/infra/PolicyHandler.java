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
    TransferRepository transferRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReqInquired_Inquire(@Payload ReqInquired reqInquired) {
        if (!reqInquired.validate()) return;
        ReqInquired event = reqInquired;
        System.out.println(
            "\n\n##### listener Inquire : " + reqInquired.toJson() + "\n\n"
        );

        // Sample Logic //
        Transfer.inquire(event);
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReqTransferred_Transfer(@Payload ReqTransferred reqTransferred ) {
        if (!reqTransferred.validate()) return;
        ReqTransferred event = reqTransferred;
              
        System.out.println(
            "\n\n##### listener acctInfo reqRegistered : " + reqTransferred.toJson() + "\n\n"
        );

        Transfer transfer = new Transfer();
        transfer.setCusName(reqTransferred.getCusName());
        transfer.setAcctNo(reqTransferred.getAcctNo());
        transfer.setCusTelNo(reqTransferred.getCusTelNo());
        transfer.setTranType("transfer");
        transfer.setTranId(reqTransferred.getTranId());
//        transfer.setTr(event.getTranId());        
        // Sample Logic //
        transferRepository.save(transfer);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReqCancelled_Cancel(@Payload ReqCancelled reqCancelled ) {
         if (!reqCancelled.validate()) return;
        ReqCancelled event = reqCancelled;

        System.out.println(
            "\n\n##### listener Cancel : " + reqCancelled.toJson() + "\n\n"
        );

        Transfer transfer = new Transfer();

        // ?????????????????? ?????????????????? UPDATE ??? ??????????????? ?????? 
        // ??? ???1?????? 1??? INSERT ??? ?????? ?????? 
        // ????????? ????????? ???????????? ?????? ???.
        transfer.setId(reqCancelled.getOriTranId());

        transfer.setTranId(reqCancelled.getTranId());
        // transfer.setCusName(reqCancelled.getCusName());
        // transfer.setAcctNo(reqTransferred.getAcctNo());
        // transfer.setCusTelNo(reqTransferred.getCusTelNo());
        // transfer.set(reqTransferred.getTranType());
        // transfer.setTr(event.getTranId());        
        // Sample Logic //
        transfer.setTranType("cancel");    // ??????????????? ???????????????.        
        transferRepository.save(transfer);


        // Sample Logic //
        //Transfer.cancel(event);
    }


    

}
