package jo.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import jo.TransferApplication;
import jo.domain.Cancelled;
import jo.domain.Inquired;
import jo.domain.Transfered;
import lombok.Data;

@Entity
@Table(name = "Transfer_table")
@Data
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;

    private String acctNo;

    private Double tranAmt;

    private String cusTelNo;

    private String cusName;

    private String tranId;

    private String recvAcctNo;

    private String tranType;

    private Long oriTranId;

    public Long getOriTranId() {
        return oriTranId;
    }

    public void setOriTranId(Long oriTranId) {
        this.oriTranId = oriTranId;
    }

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

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getRecvAcctNo() {
        return recvAcctNo;
    }

    public void setRecvAcctNo(String recvAcctNo) {
        this.recvAcctNo = recvAcctNo;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    @PostPersist
    public void onPostPersist()
     {

        System.out.println("this.getTranType():"+this.getTranType());


        if(this.getTranType().equals("transfer"))
        {
            // 
            Transfered transfered = new Transfered(this);
            transfered.setAcctNo(this.getAcctNo());
            transfered.setCusTelNo(this.getCusTelNo());
            transfered.setCusName(this.getCusName());
            transfered.setTranType("tranfer");
            transfered.setTranId(this.getTranId());
            
            // 원거래고유번호 
            transfered.setOriTranId(this.getId());

            transfered.publishAfterCommit();

//            transfered.publishAfterCommit();
        }else if(this.getTranType().equals("cancel"))
        {
            Cancelled cancelled = new Cancelled(this);

            cancelled.setAcctNo(this.getAcctNo());
            cancelled.setCusTelNo(this.getCusTelNo());
            cancelled.setCusName(this.getCusName());
            cancelled.setTranType("cancel");
            cancelled.setTranId(this.getTranId());
            cancelled.publishAfterCommit();
        }else if(this.getTranType().equals("inquire"))
        {
            Inquired inquired = new Inquired(this);

            inquired.setAcctNo(this.getAcctNo());
            inquired.setCusTelNo(this.getCusTelNo());
            inquired.setCusName(this.getCusName());
            inquired.setTranType("inquire");
            inquired.setTranId(this.getTranId());
            inquired.publishAfterCommit();
        }

        // Inquired inquired = new Inquired(this);
        // inquired.publishAfterCommit();

        // Cancelled cancelled = new Cancelled(this);
        // cancelled.publishAfterCommit();
    }

    public static TransferRepository repository() {
        TransferRepository transferRepository = TransferApplication.applicationContext.getBean(
            TransferRepository.class
        );
        return transferRepository;
    }

    public static void inquire(ReqInquired reqInquired) {
        /** Example 1:  new item 
        Transfer transfer = new Transfer();
        repository().save(transfer);

        */

        /** Example 2:  finding and process
        
        repository().findById(reqInquired.get???()).ifPresent(transfer->{
            
            transfer // do something
            repository().save(transfer);


         });
        */

    }

    public static void cancel(ReqCancelled reqCancelled) {
        /** Example 1:  new item 
        Transfer transfer = new Transfer();
        repository().save(transfer);

        */

        /** Example 2:  finding and process
        
        repository().findById(reqCancelled.get???()).ifPresent(transfer->{
            
            transfer // do something
            repository().save(transfer);


         });
        */

    }

    public static void transfer(ReqTransferred reqTransferred) {
        /** Example 1:  new item 
        Transfer transfer = new Transfer();
        repository().save(transfer);

        */

        /** Example 2:  finding and process
        
        repository().findById(reqTransferred.get???()).ifPresent(transfer->{
            
            transfer // do something
            repository().save(transfer);


         });
        */

    }
}
