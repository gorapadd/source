package jo.domain;

import java.util.Date;
import jo.domain.*;
import jo.infra.AbstractEvent;
import lombok.Data;

@Data
public class ReqRegistered extends AbstractEvent {

    private Long id;
    private String acctNo;
    private String tranId;
    private String tranType;
    private Double tranAmt;
    private String cusTelNo;
    private String cusName;
    private String recvAcctNo;

    public ReqRegistered(Request aggregate) {
        super(aggregate);
    }

    public ReqRegistered() {
        super();
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

    public String getRecvAcctNo() {
        return recvAcctNo;
    }

    public void setRecvAcctNo(String recvAcctNo) {
        this.recvAcctNo = recvAcctNo;
    }

    

    // keep

}
