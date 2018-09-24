import java.awt.Image;

import org.omg.PortableServer.THREAD_POLICY_ID;

import acm.graphics.GImage;

public class Karel extends GImage {
	
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	//private static final int KAREL_Y = 230;
	
	public Karel() {
		super("karel.png");
		this.setSize(KAREL_SIZE, KAREL_SIZE);
	}
	
	public int getImageSize() {
		return this.KAREL_SIZE;
	}
	public void setKarelDead() {
		this.setImage("karelFlipped.png");
		this.setSize(KAREL_SIZE, KAREL_SIZE);
	}
	
	
	
}
