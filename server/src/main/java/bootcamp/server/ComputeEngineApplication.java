package bootcamp.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ComputeEngineApplication extends Application {

        @Override
        public synchronized Restlet createInboundRoot() {
            Router router = new Router(getContext());
            router.attach(ComputeEngineResource.RESOURCE_URI,
                    ComputeEngineResource.class);

            return router;
        }
}

