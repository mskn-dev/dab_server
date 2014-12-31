package DabSocketServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class dabClient implements Runnable {
	
	private ServerSocket socketServer;
	private Socket socket;
	private int nbrClient = 1;
	private String adresse;
	private int port;
	private List<String> listeClients = new ArrayList<String>();
	private List<dabClient> listeSockets = new ArrayList<dabClient>();
	
	private ObjectOutputStream  out;
	
	public dabClient(ServerSocket s){
		this.socketServer = s;
	}
	
	public void run() {

        try {
        	while(true){
    			socket = this.socketServer.accept(); 
    			System.out.println("Le client numéro "+this.nbrClient+ " est connecté !");
    			this.nbrClient++;
    			this.adresse = this.socket.getLocalAddress().getAddress().toString();
    			this.port = this.socket.getLocalPort();
    			this.listeClients.add(this.adresse);
    			
    			this.out = new ObjectOutputStream (socket.getOutputStream());
    			this.listeSockets.add(this);
    			
    			this.sendIdClient();
    			this.sendListeClientsToAll(listeClients);

        	}
        
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendIdClient() throws IOException{
		this.out.writeObject(this.adresse);
	}
	
	public void sendListeClientsToAll(List<String> listeClients){
		try {
			for(dabClient s : this.listeSockets){
		       s.out.writeObject(listeClients);
		       s.out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
