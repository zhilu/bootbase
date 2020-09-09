package shi.app.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class TomcatStarter {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        server.setPort(8080);
        Service service = tomcat.getService();
        service.setName("Tomcat-embbeded");
        Connector connector = tomcat.getConnector();
        connector.setPort(8888);
        Context context = tomcat.addContext("", "/");
        tomcat.addServlet("", "test", new MyServlet());
        context.addServletMappingDecoded("/*", "test");
        server.start();
        server.await();
    }
}