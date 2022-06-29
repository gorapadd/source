package jo;

import jo.config.kafka.KafkaProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class RequestApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(RequestApplication.class, args);
    }

// 	@RequestMapping(method = RequestMethod.PUT, path="requests/{acctNo}/testprint")
// 	public void testPrint(@PathVariable(value = "acctNo") long acctNo){

// 		System.out.println("sxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+String.valueOf(acctNo));

// //		return "맛있는 거 먹였습니다.";
// 	}    
}
