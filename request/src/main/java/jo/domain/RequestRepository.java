package jo.domain;

import jo.domain.*;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "requests", path = "requests")
public interface RequestRepository
    extends PagingAndSortingRepository<Request, Long> {

        List<Request> findByTranId(@Param("tranId") String tranId);
        
    }
