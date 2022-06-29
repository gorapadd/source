package jo.domain;


import javax.persistence.*;
//import jo.domain.Registered;
import lombok.Data;

@Data
public class AcctInfo {

    private Long id;


    private String cusName;
    private String acctNo;
    private String cusTelNo;
    private String tranType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }
  

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    
    public String getCusTelNo() {
        return cusTelNo;
    }

    public void setCusTelNo(String cusTelNo) {
        this.cusTelNo = cusTelNo;
    }



    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    
    // gorapadd 
    // public static AcctInfoRepository repository() {
    //     AcctInfoRepository acctInfoRepository = AcctInfoApplication.applicationContext.getBean(
    //         AcctInfoRepository.class
    //     );
    //     return acctInfoRepository;
    // }

    public static void register(ReqRegistered reqRegistered) {
        /** Example 1:  new item 
        AcctInfo acctInfo = new AcctInfo();
        repository().save(acctInfo);

        */

        /** Example 2:  finding and process
        
        repository().findById(reqRegistered.get???()).ifPresent(acctInfo->{
            
            acctInfo // do something
            repository().save(acctInfo);


         });
        */
    }
}
