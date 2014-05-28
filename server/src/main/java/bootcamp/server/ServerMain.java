package bootcamp.server;

import org.restlet.Component;
import org.restlet.data.Protocol;


public class ServerMain {

    public static void main(String[] args) {
        Component component = new Component();

        component.getServers().add(Protocol.HTTP, 8182);
        component.getDefaultHost()
                .attach(new ComputeEngineApplication());

        try {
            component.start();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
