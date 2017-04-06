package exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;
	
	/**
	 * Construct a GameClient
	 * @param serverName Server name
	 * @param portNumber port number
	 */
	public GameClient(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
					palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 * Client will quit from either server telling it to, or the client telling it to
	 */
	public void communicate()  {
		System.out.println("Starting...");
		String response = "";
			try {
				response = socketIn.readLine();
				GFrame gui = new GFrame(response, stdIn, socketOut);
				gui.setVisible(true);
				GUIThread gt = new GUIThread(response, gui, socketIn, gui.messages);
				gt.start();
				gt.join();
			} catch (IOException e) {
				System.out.println("Disconected: " + e.getMessage());
			} catch (NullPointerException n){
				System.out.println("Disconected: Connection Reset");
			} catch (InterruptedException e) {
				System.out.println("Disconected: Connection Reset");
			}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}
		System.out.println("Closing...");
	}

	public static void main(String[] args) throws IOException  {
		GameClient a = new GameClient("localhost", 9090);
		a.communicate();
	}
}
