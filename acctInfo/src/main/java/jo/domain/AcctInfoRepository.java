package jo.domain;

import jo.domain.*;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "acctInfos", path = "acctInfos")
public interface AcctInfoRepository
    extends PagingAndSortingRepository<AcctInfo, Long> {
        
        List<AcctInfo> findByAcctNo(@Param("acctNo") String acctNo);
    }
