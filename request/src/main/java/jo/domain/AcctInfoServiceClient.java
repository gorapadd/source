package jo.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Service
@FeignClient(name ="acctInfo", url="${api.url.acctInfo}")
public interface AcctInfoServiceClient {

    // @RequestMapping(method = RequestMethod.PUT, value = "/product/decreaseStock/{productId}", consumes = "application/json")
    // void decreaseStock(@PathVariable("productId") Long productId, int quantity);

    // @RequestMapping(method = RequestMethod.PUT, value = "/product/increaseStock/{productId}", consumes = "application/json")
    // void increaseStock(@PathVariable("productId") Long productId, int quantity);

    //GET 방식으로 처리함.
     //@GetMapping(value = "/acctInfo/infosize/")
     //@GetMapping(value = "/acctInfos/search/findByAcctNo/{acctNo}")
     //int acctInfoSize(@PathVariable("acctNo") String acctNo); 

    //@RequestMapping(method = RequestMethod.PUT, value = "/acctInfos/sizecnt/acctNo={acctNo}", consumes = "application/json")
    //int acctInfoSize(@PathVariable("acctNo") String acctNo);

    // @RequestMapping(method = RequestMethod.PUT, value = "/acctInfos/sizecnt", consumes = "application/json")
    // int sizecnt(@PathVariable("acctNo") String acctNo);
    // request Event 에서 하는 경우
    @RequestMapping(method = RequestMethod.POST, path = "/registerCheck")
    public int registerCheck(@RequestBody AcctInfo acctInfo);

    // request Command 에서 하는 경우
    @RequestMapping(method = RequestMethod.GET, path = "/acctInfos/{id}")
    public AcctInfo getAcctInfo(@PathVariable("id") Long id);
     
}
