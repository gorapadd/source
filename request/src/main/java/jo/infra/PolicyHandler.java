package jo.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.joran.conditional.ElseAction;

import java.util.List;

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
    RequestRepository requestRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverTransfered_UpdateTranstatus(
    //     @Payload Transfered transfered
    // ) {
    //     if (!transfered.validate()) return;
    //     Transfered event = transfered;
    //     System.out.println(
    //         "\n\n##### listener UpdateTranstatus : " +
    //         transfered.toJson() +
    //         "\n\n"
    //     );

    //     // Sample Logic //
    //     Request.updateTranstatus(event);
    // }

    // 이체완료후 
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTransfered_UpdateTranstatus(@Payload Transfered transfered ){

        System.out.println("[wheneverTransfered_UpdateTranstatus-1]-----------------------------------------------"+transfered.getTranId());

        List<Request> reqList = requestRepository.findByTranId(transfered.getTranId());
        System.out.println("[wheneverTransfered_UpdateTranstatus-2]-----------------------------------------------"+reqList.size());
        if (reqList.size()>0)
        {
            System.out.println("[wheneverTransfered_UpdateTranstatus-3]-----------------------------------------------"+reqList.size());
            Request req1=reqList.get(0);   
            
            if(req1.getTranType().equals("transfer"))
            {
                req1.setOriTranId(req1.getOriTranId());                     //원래거래고유번호 (oriTranId)               
                req1.setTranStatus("transfered=====");
                
            }else if(req1.getTranType().equals("cancel")){
                req1.setTranStatus("completed=====");            //명칭은 생각해보장.
            }else{
                System.out.println("[wheneverTransfered_UpdateTranstatus-99]-----------------------------------------------"+reqList.size());
            }
                        
            requestRepository.save(req1);
        }
            
    }


    // keep

}
