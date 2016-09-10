package util;

import java.awt.*;

/**
 * Created by Florian on 12.07.2016.
 */
public class Obstacle implements DrawInferface, ScaleChangeListener {

	private PointF[] points;
	private Color color;
	private int[] xPoints, yPoints;
	private float scale = 1;

	public Obstacle(PointF[] points) {
		new Obstacle(points, new Color(0xff000000));
	}

	public Obstacle(PointF[] points, Color color) {
		this.points = points;
		this.color = color;
	}

	@Override
	public void draw(Graphics g, float scale) {
		g.setColor(color);
		g.fillPolygon(xPoints, yPoints, xPoints.length);
	}

	private void updatePoints() {
		for (int i = 0; i < points.length; i++) {
			xPoints[i] = Math.round(points[i].x * scale);
			yPoints[i] = Math.round(points[i].y * scale);
		}
	}

	@Override
	public void onScaleChange(float scale) {
		this.scale = scale;
		updatePoints();
	}
}
