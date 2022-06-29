package jo.common;

import io.cucumber.spring.CucumberContextConfiguration;
import jo.TransferApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { TransferApplication.class })
public class CucumberSpingConfiguration {}
