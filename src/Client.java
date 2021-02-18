
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Client
{
	private static final String SERVICE_URL = "service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi";
	private static final String JMX_NAME = "com.example:type=Example";

	public static void main(final String[] args) throws Exception
	{
		final JMXServiceURL url = new JMXServiceURL(SERVICE_URL);
		final JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
		final MBeanServerConnection connection = jmxc.getMBeanServerConnection();

		final IProxy proxy = MBeanServerInvocationHandler.newProxyInstance(connection,
				new ObjectName(JMX_NAME), IProxy.class, false);

		System.out.println(proxy.getHelloWorld());
		
	}
}
