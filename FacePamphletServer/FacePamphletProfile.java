/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import java.util.*;

import javax.management.loading.PrivateClassLoader;

public class FacePamphletProfile {
	public ArrayList<String> listOfPeopleWithProfile = new ArrayList<String>();
	// your code here
	private String name;
	private String fileName;
	private String status;
	private String friend;
	private String listOfPeople;

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile.
	 */
	public FacePamphletProfile(String name) {
		this.name=name;
		
		//listOfPeopleWithProfile.add(name);
		//System.out.println("list of people with profile" +listOfPeopleWithProfile);
		
	}
	/*
	public FacePamphletProfile(String name,String fileName, String status, String friend, String listOfPeople) {
		this.name=name;
		this.fileName=fileName;
		this.status=status;
		this.friend=friend;
		this.listOfPeople=listOfPeople;
		listOfPeopleWithProfile.add(name);
		System.out.println("list of people with profile" +listOfPeopleWithProfile);
		
	}
	*/
	/** This method returns the name associated with the profile. */ 
	public String getName() {
		// You fill this in.  Currently always returns the empty string.
		return this.name;
	}


	public String getImageFileName() {
		// You fill this in.  Currently always returns the empty string.
		return this.fileName;
	}

	/** This method sets the image associated with the profile. */ 
	public void setImageFileName(String fileName) {
		this.fileName=fileName;
		// You fill this in.
	}

	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 */ 
	public String getStatus() {
		// You fill this in.  Currently always returns the empty string.
		return this.status;
	}

	/** This method sets the status associated with the profile. */ 
	public void setStatus(String status) {
		this.status=status; 
	}

	/** 
	 * This method adds the named friend to this profile's list of 
	 * friends.  It returns true if the friend's name was not already
	 * in the list of friends for this profile (and the name is added 
	 * to the list).  The method returns false if the given friend name
	 * was already in the list of friends for this profile (in which 
	 * case, the given friend name is not added to the list of friends 
	 * a second time.)
	 */
	public boolean addFriend(String friend) {
		// You fill this in.  Currently always returns false.
		return false;
	}

	/** 
	 * This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed from
	 * the list).  The method returns false if the given friend name 
	 * was not in the list of friends for this profile (in which case,
	 * the given friend name could not be removed.)
	 */
	public boolean removeFriend(String friend) {
		// You fill this in.  Currently always returns false.
		return false;
	}

	/** 
	 * This method returns an iterator over the list of friends 
	 * associated with the profile.
	 */ 
	public List<String> getFriends() {
		// You fill this in.  Currently always returns null.
		return null;
	}

}
