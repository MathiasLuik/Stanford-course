
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	private static final double startingPointWidth = 50;
	private static final double startingPointHeigth = 50;
	private static final double largeRedRadius = 72;
	private static final double mediumWhiteRadius = 72*0.65;
	private static final double smallRedRadius = 72*0.3;
	//the point of it to graph up target circle. Red white red
	public void run() {
		//triggering 3 circles
		makeLargeRedCircle();
		makeMediumWhiteCircle();
		makeSmallRedCircle();
	}
	private void makeLargeRedCircle() {
		//drawing Largest circle
		GOval largeRed = new GOval(getWidth()/2-0.5*largeRedRadius, getHeight()/2-0.5*largeRedRadius, largeRedRadius, largeRedRadius);
		largeRed.setFilled(true);
		largeRed.setColor(Color.RED);
		add(largeRed);
	}
	private void makeMediumWhiteCircle() {
		//drawing medium circle
		GOval mediumWhite = new GOval(getWidth()/2-0.5*mediumWhiteRadius, getHeight()/2-0.5*mediumWhiteRadius, mediumWhiteRadius, mediumWhiteRadius);
		mediumWhite.setFilled(true);
		mediumWhite.setColor(Color.WHITE);
		add(mediumWhite);
	}
	private void makeSmallRedCircle() {
		GOval smallRed = new GOval(getWidth()/2-0.5*smallRedRadius, getHeight()/2-0.5*smallRedRadius, smallRedRadius, smallRedRadius);
		smallRed.setFilled(true);
		smallRed.setColor(Color.RED);
		add(smallRed);
	}
}
