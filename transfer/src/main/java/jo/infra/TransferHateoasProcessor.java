package jo.infra;

import jo.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class TransferHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Transfer>> {

    @Override
    public EntityModel<Transfer> process(EntityModel<Transfer> model) {
        return model;
    }
}
