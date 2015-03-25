package framework;
import framework.RpcFramework;

public class Server {
	public static void main(String []args) throws Exception {
		HelloService service = new HelloServiceImpl();
		RpcFramework.export(service, 1234); 
	}
}
