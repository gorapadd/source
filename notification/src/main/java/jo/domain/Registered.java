package jo.domain;

import java.util.Date;
import jo.domain.*;
import jo.infra.AbstractEvent;
import lombok.Data;

@Data
public class Registered extends AbstractEvent {

    private Long id;


    private String cusName;
    private String acctNo;
    private String cusTelNo;
    private String tranType;
    // keep
    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }



    public String getCusTelNo() {
        return cusTelNo;
    }

    public void setCusTelNo(String cusTelNo) {
        this.cusTelNo = cusTelNo;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Registered() {
        super();
    }

}
