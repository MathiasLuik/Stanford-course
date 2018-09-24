
import acm.graphics.*;
import acm.program.*;

public class SevenPetalRosette extends GraphicsProgram{

	// Each circle has this radius
	private static final int RADIUS = 100;
	
	// There should be this many circles around the center circle
	private static final int N_OUTER_CIRCLES = 19;
	// Draw the 
	public void run() {
		setSize(1500,1500);
		double angle = 0;
		for(int i = 0; i < N_OUTER_CIRCLES; i++) {
			println(i);
			double delta = (360.0 / N_OUTER_CIRCLES);
			angle += delta;
			println(angle);
			drawOuterCircle(angle);
		}
		drawCenteredCircle();
	}
	private void drawOuterCircle(double outerAngle) {
		GOval outerCircle = makeCenteredOval();
		add(outerCircle);
		outerCircle.movePolar(RADIUS, outerAngle);
	}
	private void drawCenteredCircle() {
		GOval centeredCircle = makeCenteredOval();
		add(centeredCircle);
	}
	private GOval makeCenteredOval() {
		double x = getWidth()/2 - RADIUS;
		double y = getHeight()/2 - RADIUS;
		double width = RADIUS * 2;
		double height = width;
		GOval circle = new GOval(x, y, width, height);
		circle.setLineWidth(5);
		return circle;
	}
}
