/*
 * File: FacePamphletServer.java
 * ------------------------------
 * This program runs a server which hosts the data for a 
 * FacePamphlet internet application. The server stores all 
 * of the data and contains the logic for creating, deleting 
 * profiles and getting and setting profile properties. When 
 * the server receives a requests (which often come from the 
 * client), it updates its internal data, and sends back a string. 
 */

import java.util.*;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import acm.program.*;

public class FacePamphletServer extends ConsoleProgram 
					implements SimpleServerListener {
	
	/* The internet port to listen to requests on */
	private static final int PORT = 8000;
	
	/* The server object. All you need to do is start it */
	private SimpleServer server = new SimpleServer(this, PORT);
	
	/**
	 * Starts the server running so that when a program sends
	 * a request to this computer, the method requestMade is
	 * called.
	 */
	
	
	public void run() {
		println("Starting server on port " + PORT);
		server.start();
	}

	/**
	 * When a request is sent to this computer, this method is
	 * called. It must return a String.
	 */
	private boolean isPing(String cmd) {
		if (cmd.equals("ping")){
			return true;
		}
		return false;
	}
	private boolean addprofile(String cmd) {
		if (cmd.equals("addProfile")){
			return true;
		}
		return false;
	}
	
	
	public String requestMade(Request request) {
		FacePamphletProfile name;
		List<FacePamphletProfile> people = new ArrayList<FacePamphletProfile>();
		String cmd = request.getCommand();
		cmd.toString();
		if(isPing(cmd)) {
			System.out.println("Ping Test is passed");
			String raw = request.getRaw("ping");
			//println("hello, internet");
			//println(request.getParam(cmd));
			//return "hello, internet";
			return request.getParam(cmd);
		}
		else if(addprofile(cmd)) {
			System.out.println("addProfile Test is passed");
			System.out.println(cmd); //addProfile
			System.out.println(request.toGetRequest());  //addProfile?name=Chris
			name = new FacePamphletProfile(request.getParam("name"));
			System.out.println("---" + name.getName());
			System.out.println(request.getParam("name")); //Chris
			
			//people.add(request.getParam("name"));
			//System.out.println(request.getRaw(cmd)); //null
			//System.out.println(request.getParam(cmd));
			
			//println(request.getParam(cmd));
			return "success";
			//return request.getParam(cmd);
		}
		
		
		
		
		
		//System.out.println(commandKey);
		//String ping2= request.toString();
		//println(ping2);
		//if (ping2=="ping") {
		//	System.out.println("ohho");
		//}
		
		return "Error: Unknown command " + cmd + ".";
	}

}
