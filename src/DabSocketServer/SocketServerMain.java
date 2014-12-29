package DabSocketServer;


public class SocketServerMain {
	
	public static void main(String[] args) {
		
		dabServer server = new dabServer();
	
		if(server.dabServer()){
			System.out.println("Le socket est en ecoute sur le port 2008");
		}
	}
}
