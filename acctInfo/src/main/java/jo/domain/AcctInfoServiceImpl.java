package jo.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AcctInfoServiceImpl implements AcctInfoService {

    @Autowired
    AcctInfoRepository AcctInfoRepository;

    public int acctInfoSize(String  acctNo) {

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX===="+acctNo);
        int returnVal=0;
        List<AcctInfo> acctInfoList = AcctInfoRepository.findByAcctNo(acctNo);
        returnVal =  acctInfoList.size();

        return returnVal;

    }
}
