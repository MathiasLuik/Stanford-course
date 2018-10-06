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
	public List<String> people = new ArrayList<String>();
	/* The server object. All you need to do is start it */
	private SimpleServer server = new SimpleServer(this, PORT);
	private String profileName;
	//private FacePamphletProfile name;
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
	private boolean containingProfile(String cmd) {
		if (cmd.equals("containsProfile")){
			return true;
		}
		return false;
	}
	private void addPeople(String profileName) {
		if (!people.contains(profileName)) {
			people.add(profileName);
		}
	}
	private boolean containsProfile(String profileName) {
		if (people.contains(profileName)) {
			return true;
		}
		return false;
	}
	public String requestMade(Request request) {		
		//List<FacePamphletProfile> people = new ArrayList<FacePamphletProfile>();		
		String cmd = request.getCommand();
		System.out.println(request.getCommand());
		
		//System.out.println(request.getParam(cmd));
		//System.out.println(cmd);
		String value=request.toGetRequest(); //addProfile?name=Chris
		//System.out.println(value);
		//System.out.println(value.indexOf("="));
		//System.out.println(value.substring(value.indexOf("?")+1, value.indexOf("=")));
		 // i need to get location of "?" AND "=", then can make substring with a value of Name. Otherwise it would return error
		String getParamKey=value.substring(value.indexOf("?")+1, value.indexOf("="));
		//System.out.println(request.getRaw(cmd));
		
		cmd.toString();
		//if(request.getParam("name")!=null) {
		if(getParamKey=="name") {
			FacePamphletProfile name = new FacePamphletProfile(request.getParam("name"));
			profileName = name.getName();
		}
		
		if(cmd.equals("ping")) {
			System.out.println("Ping Test is passed");
			
			return request.getParam(cmd);
		}
		else if(cmd.equals("addProfile")) {
			//System.out.println(cmd); //addProfile
			//System.out.println(request.toGetRequest());  //addProfile?name=Chris			
			//FacePamphletProfile name = new FacePamphletProfile(request.getParam("name"));
			//String profileName = name.getName();
		
			//System.out.println("request.getParam(\"name\") = " + name.getName());
			//System.out.println("peoples array = " + people);
			
			if (!containsProfile(profileName)){
				people.add(profileName);			
				return "success";				
			} 
		}
		else if(cmd.equals("containsProfile")) {
			//FacePamphletProfile name = new FacePamphletProfile(request.getParam("name"));
			//String profileName = name.getName();	
			if (containsProfile(profileName)){				
				return "true";				
			}
			else {
				return "false";
			}
		}
		else if(cmd.equals("deleteProfile")) {
			//FacePamphletProfile name = new FacePamphletProfile(request.getParam("name"));
			//String profileName = name.getName();	
			if (containsProfile(profileName)){				
				people.remove(profileName);
				return "success";				
			}
		}
		return "Error: Unknown command " + cmd + ".";
	}

}
