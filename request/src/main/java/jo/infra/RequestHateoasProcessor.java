package jo.infra;

import jo.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class RequestHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Request>> {

    @Override
    public EntityModel<Request> process(EntityModel<Request> model) {
        return model;
    }
}
