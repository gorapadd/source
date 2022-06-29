package jo.domain;

import jo.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "transfers", path = "transfers")
public interface TransferRepository
    extends PagingAndSortingRepository<Transfer, Long> {}
