import java.lang.management.ManagementFactory;

import javax.management.*;

public class Server implements IProxy
{
	private static final String JMX_NAME = "com.example:type=Example";
	
	private final MBeanServer server;
	private final ObjectName name;

	public Server() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		server = ManagementFactory.getPlatformMBeanServer();
		name = new ObjectName(JMX_NAME);
		server.registerMBean(new StandardMBean(this, IProxy.class), name);
		System.out.println("Server Started.");
	}
			
	public static void main(String[] args) throws Exception
	{
		new Server();
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch(Exception e) {
			;
		}
	}
	
	public String getHelloWorld() {
		return "Hello, World!";
	}

}
