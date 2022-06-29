package jo.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import jo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/requests")
@Transactional
public class RequestController {

    @Autowired
    RequestRepository requestRepository;
    // keep

    @PostMapping("/test")
    void test(@PathVariable(value = "acctNo") String acctNo) {
        System.out.println("==============================[acctNo]"+acctNo);
        //return this.acctInfoService.acctInfoSize(acctInfo.getAcctNo());    
    }
    
}
