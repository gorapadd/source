package jo.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jo.RequestApplication;
import jo.domain.ReqCancelled;
import jo.domain.ReqInquired;
import jo.domain.ReqRegistered;
import jo.domain.ReqTransferred;
import lombok.Data;

@Entity
@Table(name = "Request_table")
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String acctNo;
    private String tranId;

    private String tranType;

    private Double tranAmt;

    private String cusTelNo;

    private String cusName;

    private String recvAcctNo;

    private Long oriTranId;

    public Long getOriTranId() {
        return oriTranId;
    }

    public void setOriTranId(Long oriTranId) {
        this.oriTranId = oriTranId;
    }

    public String getRecvAcctNo() {
        return recvAcctNo;
    }

    public void setRecvAcctNo(String recvAcctNo) {
        this.recvAcctNo = recvAcctNo;
    }

    private String tranStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public Double getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(Double tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getCusTelNo() {
        return cusTelNo;
    }

    public void setCusTelNo(String cusTelNo) {
        this.cusTelNo = cusTelNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }


    
    // public String getRecvAcctNo() {
    //     return recvAcctNo;
    // }

    // public void setRecvAcctNo(String recvAcctNo) {
    //     this.recvAcctNo = recvAcctNo;
    // }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    // @PrePersist
    // private void checkAcctInfo(){
    //     if( acctNo == null ){
    //         throw new RuntimeException();
    //     }

    //     //int price = 0;
    //     //String productName = null;

    //     AcctInfoRepository acctInfoRepository = RequestApplication.applicationContext.getBean(AcctInfoRepository.class);
    //     List<AcctInfo> acctInfoList = acctInfoRepository.findByAcctNo(acctNo);
        
    //     if(acctInfoList.size() > 0 )    // 
    //     {
    //          throw new RuntimeException("[ISSUE] =============== already AcctINfo ");
    //     }
    //     // price = product.getPrice();
    //     // productName = product.getName();
    //     // if( product.getStock() < getQuantity()){
    //     //     throw new OrderException("Out of stock!");
    //     // }

    //     // this.setPrice(price);
    //     // this.setProductName(productName);
    //     // this.setProduct(product);
    // }
    @PrePersist
    public void onPrePersist() {

        if(this.getTranType().equals("register"))
        {
            AcctInfo acctInfo = new AcctInfo();
            acctInfo.setAcctNo(this.getAcctNo());
            // mappings goes here
            int val=RequestApplication.applicationContext
                .getBean(AcctInfoServiceClient.class)
                .registerCheck(acctInfo);
        
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ :"+val);
            
            if (val >= 1)  
            {
                System.out.println("??????????????? ?????? ???????????????.");
                throw new RuntimeException("[ISSUE] =============== ??????????????? ?????? ???????????????. ");
            }
        }else if(this.getTranType().equals("transfer"))
        {

            System.out.println("[onPrePersist]*************************************************** :"+this.getTranType());
        }else if(this.getTranType().equals("cancel"))  // ????????????????????? 
        {

            System.out.println("this.getTranType(cancel) ===== 1"+this.getTranType());

            RequestRepository requestRepository = RequestApplication.applicationContext.getBean(RequestRepository.class);
            List<Request> reqList = requestRepository.findByTranId(this.getTranId());
    
            System.out.println("this.getTranType(cancel) ===== 2"+ reqList.size());
            
            if(reqList.size() > 0 )    // ????????????
            {    
                System.out.println("====== ??????1 ");
                System.out.println("[onPostPersist] cancel "+ this.getTranId());

                System.out.println("====== ??????2 ");

                //?????????????????????
                Request reqEntity = reqList.get(0);

                ReqCancelled reqCancelled = new ReqCancelled(this);
                //reqCancelled.setAcctNo(this.getAcctNo());
                //reqCancelled.setId(this.getId());
                //reqCancelled.setRecvAcctNo(this.getRecvAcctNo());
                
                // ????????????????????? (Long ???)
                reqCancelled.setOriTranId(reqEntity.getId());

                reqCancelled.setTranId(this.getTranId());
                reqCancelled.setCusTelNo(this.getCusTelNo());
                reqCancelled.setCusName(this.getCusName());
                reqCancelled.publish();                
                
            
            }else{
                throw new RuntimeException("[ISSUE] =============== already Request ");
            }
        }else if(this.getTranType().equals("inquire"))
        {
            System.out.println("[onPrePersist]????????????]????????? TranType=================================="+this.getTranType());
        }
            
        //else
        // {        
        //     ReqRegistered reqRegistered = new ReqRegistered(this);
        //     reqRegistered.setAcctNo(this.getAcctNo());
        //     reqRegistered.setId(this.getId());
        //     reqRegistered.setTranId(this.getTranId());
        //     reqRegistered.setCusTelNo(this.getCusTelNo());
        //     reqRegistered.setRecvAcctNo(this.getRecvAcctNo());
        //     reqRegistered.setCusName(this.getCusName());
        //     reqRegistered.publishAfterCommit();
        // }

    }

    @PostPersist
    public void onPostPersist() {


        /* ?????? 1 ???????????? microservice ??? ?????? ?????? ????????? ?????????????????????????????? ??????..
        
        if( acctNo == null ){
            throw new RuntimeException("[ISSUE] =============== NO EXISTS AcctINfo  ");
        }
      
        System.out.println("FIRST ===== 1"+acctNo);

        AcctInfoRepository acctInfoRepository = RequestApplication.applicationContext.getBean(AcctInfoRepository.class);
        List<AcctInfo> acctInfoList = acctInfoRepository.findByAcctNo(acctNo);

        System.out.println("FIRST ===== 2"+acctInfoList.size());
        
        if(acctInfoList.size() > 0 )    // 
        {
             throw new RuntimeException("[ISSUE] =============== already AcctINfo ");
        }

        // ????????? ??????????????? ????????????.
        System.out.println("FIRST ===== 3"+acctInfoList.size());

        */
        
        /*  RestTemplate ????????? ??????????????? Count(*) ??? ???????????? ????????? ??????..??????;??????.
        RestTemplate restTemplate = RequestApplication.applicationContext.getBean(RestTemplate.class);
        Environment env = RequestApplication.applicationContext.getEnvironment();
        
        //String productUrl = env.getProperty("api.url.product") + "/products/" + productId;
        // ?????? ????????? ?????? ????????????.
        String accoInfoUrl = env.getProperty("api.url.product") + "/products/" + acctNo;  
        ResponseEntity<AcctInfo> productEntity = restTemplate.getForEntity(accoInfoUrl, AcctInfo.class);        
        AcctInfo acctInfo = productEntity.getBody();
        // int stock = acctInfo.getStock();
        */
        /* ?????? FeignClient ???????????? ??????..*/
   
        //AcctInfoService  AcctInfoServiceClient;
        //int val = AcctInfoServiceClient.acctInfoSize(acctNo);


        // ?????? ROOM??? Available??? ???????????? ??????
        // ??????????????? ??? ????????????????????? ????????????.
        // /acctInfo/{acctNO}  URL ?????? ????????????.
        //int intval = RequestApplication.applicationContext.getBean(jo.domain.AcctInfoServiceClient.class)
        //                .sizecnt(this.getAcctNo());
        // System.out.println("######## Check Result : " + intval);
     
        if(this.getTranType().equals("register"))
        {
            ReqRegistered reqRegistered = new ReqRegistered(this);
            reqRegistered.setAcctNo(this.getAcctNo());
            reqRegistered.setId(this.getId());
            reqRegistered.setTranId(this.getTranId());
            reqRegistered.setCusTelNo(this.getCusTelNo());
            reqRegistered.setRecvAcctNo(this.getRecvAcctNo());
            reqRegistered.setCusName(this.getCusName());
            reqRegistered.publishAfterCommit();
        }else if(this.getTranType().equals("transfer"))
        {

            System.out.println("[onPostPersist]*************************************************** :"+this.getTranType());

            ReqTransferred reqTransferred = new ReqTransferred(this);
            reqTransferred.setAcctNo(this.getAcctNo());
            reqTransferred.setId(this.getId());
            reqTransferred.setTranId(this.getTranId());
            reqTransferred.setCusTelNo(this.getCusTelNo());
            reqTransferred.setRecvAcctNo(this.getRecvAcctNo());
            reqTransferred.setCusName(this.getCusName());
            reqTransferred.publishAfterCommit();
        }else if(this.getTranType().equals("cancel"))
        {
                // System.out.println("====== ??????2 ");
                // ReqCancelled reqCancelled = new ReqCancelled(this);
                // //reqCancelled.setAcctNo(this.getAcctNo());
                // //reqCancelled.setId(this.getId());
                // //reqCancelled.setRecvAcctNo(this.getRecvAcctNo());
                // reqCancelled.setTranId(this.getTranId());
                // reqCancelled.setCusTelNo(this.getCusTelNo());
                // reqCancelled.setCusName(this.getCusName());
                // reqCancelled.publish();
               // reqCancelled.publishAfterCommit();    
        }else if(this.getTranType().equals("inquire"))
        {

        }
    }

    public static RequestRepository repository() {
        RequestRepository requestRepository = RequestApplication.applicationContext.getBean(
            RequestRepository.class
        );
        return requestRepository;
    }

    @PreRemove
    public void onPreRemove() {
     //   ReqCancelled reqCancelled = new ReqCancelled();
     //   BeanUtils.copyProperties(this, reqCancelled);;
     //   reqCancelled.publishAfterCommit();
    }

    public static void updateTranstatus(Transfered transfered) {
        /** Example 1:  new item 
        Request request = new Request();
        repository().save(request);

        */

        /** Example 2:  finding and process
        
        repository().findById(transfered.get???()).ifPresent(request->{
            
            request // do something
            repository().save(request);


         });
        */

        // ?????? gorapadd 
        // repository().findById(transfered.getId()).ifPresent(request->{
        //     transfered.setAcctNo();
        //     transfered.setTranId();
        //     transfered.setCusTelNo();
        //     transfered.setRecvAcctNo();
        //     transfered.setCusName()
        //     transfered.repository().save(request);


        //  });
        
    }
}
