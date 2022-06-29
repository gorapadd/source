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
//@RequestMapping(value = "/acctInfos")
@Transactional
public class AcctInfoController {

    @Autowired
    AcctInfoRepository acctInfoRepository;
    // keep

    @Autowired
    AcctInfoService acctInfoService;
//    AcctInfoServiceImpl AcctInfoServiceImpl;

    // @GetMapping("/acctInfo/{acctNo}")
    // int acctInfoSize(@PathVariable(value = "acctNo") String acctNo) {
    //     return  this.acctInfoService.acctNoCnt(acctNo);
    // }

    // public int registerCheck(AcctInfo acctInfo)
    // {
    //     System.out.println("==============================")
    //     System.out.println(acctInfo.getAcctNo());
    //     return 0;
    // }    

    @PostMapping("/registerCheck")
    int registerCheck(@RequestBody AcctInfo acctInfo) {
        System.out.println("==============================[registerCheck]"+acctInfo.getAcctNo());
        return this.acctInfoService.acctInfoSize(acctInfo.getAcctNo());
    }

    @PostMapping("/test")
    void test(@PathVariable(value = "acctNo") String acctNo) {
        System.out.println("==============================[acctNo]"+acctNo);
        //return this.acctInfoService.acctInfoSize(acctInfo.getAcctNo());
    }
}
