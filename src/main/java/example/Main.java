package example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/");
        server.setHandler(ctx);

        ResourceConfig config = new ResourceConfig();
        config.packages("example");

        ServletContainer servlet = new ServletContainer(config);

        ctx.addServlet(new org.eclipse.jetty.servlet.ServletHolder(servlet), "/*");

        server.start();
        server.join();
    }
}