package jo.common;

import io.cucumber.spring.CucumberContextConfiguration;
import jo.AcctInfoApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { AcctInfoApplication.class })
public class CucumberSpingConfiguration {
  

    
    
}
