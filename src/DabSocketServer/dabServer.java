package DabSocketServer;

import java.io.IOException;
import java.net.ServerSocket;

public class dabServer {
	private ServerSocket socketserver;
	
	public dabServer(){
		
	}
	
	public boolean dabServer(){
		
		try {
			this.socketserver = new ServerSocket(2008);
			Thread t = new Thread(new dabClient(socketserver));
			t.start();
			
			return true;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
