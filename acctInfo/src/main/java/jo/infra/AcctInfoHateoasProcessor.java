package jo.infra;

import jo.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AcctInfoHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<AcctInfo>> {

    @Override
    public EntityModel<AcctInfo> process(EntityModel<AcctInfo> model) {
        //model.add(Link.of(model.getRequiredLink("self").getHref() + "/sizecnt").withRel("sizecnt"));
        return model;
    }
}
