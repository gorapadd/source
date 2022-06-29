package jo.common;

import io.cucumber.spring.CucumberContextConfiguration;
import jo.RequestApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { RequestApplication.class })
public class CucumberSpingConfiguration {}
