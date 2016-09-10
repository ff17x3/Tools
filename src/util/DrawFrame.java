package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class DrawFrame {

	private DrawPanel dp;
	private float scale;
	private ScaleChangeListener scaleChangeListener;
	private DimensionF mapSize;


	public DrawFrame(FrameInitInterface fii, DrawInferface d) {
		this(null, fii, d, null);
	}

	public DrawFrame(Dimension size, FrameInitInterface fii, DrawInferface d, DimensionF mapS) {
		JFrame f = new JFrame();
		this.mapSize = mapS;
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (size == null || size.width <= 0 || size.height <= 0)
			size = new Dimension(500, 500);
		if (mapS != null)
			f.addComponentListener(new ComponentListener() {
				public void componentResized(ComponentEvent e) {
					updateScale();
				}

				@Override
				public void componentHidden(ComponentEvent arg0) {

				}

				@Override
				public void componentMoved(ComponentEvent arg0) {

				}

				@Override
				public void componentShown(ComponentEvent arg0) {

				}
			});

		dp = new DrawPanel(d, size);
		f.add(dp);
		f.pack();
		if (fii != null)
			fii.initFrame(f, dp);

		f.setVisible(true);

	}

	private void updateScale() {
		scale = Math.min(dp.getWidth() / mapSize.width, dp.getHeight() / mapSize.height);
		if (scaleChangeListener != null)
			scaleChangeListener.onScaleChange(scale);
	}

	/**
	 * Calls onScaleChange instantly
	 * ATTENTION: only works when DimensionF mapS != null in DrawFrame Constructor
	 *
	 * @param scaleChangeListener
	 */
	public void addScaleChangeListener(ScaleChangeListener scaleChangeListener) {
		this.scaleChangeListener = scaleChangeListener;
		if (scaleChangeListener != null)
			scaleChangeListener.onScaleChange(scale);
	}

	public void setMapSize(DimensionF mapSize) {
		this.mapSize = mapSize;
		updateScale();
	}

	public float getScale() {
		return scale;
	}

	public void redraw() {
		dp.repaint();
	}

	public class DrawPanel extends JPanel {

		DrawInferface drawable;

		public DrawPanel(DrawInferface d, Dimension size) {
			super();
			setPreferredSize(size);
			drawable = d;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (drawable != null)
				drawable.draw(g, scale);
		}
	}

	public int getWidth() {
		if (dp == null) {
			return 0;
		}
		return dp.getWidth();
	}

	public int getHeigth() {
		if (dp == null) {
			return 0;
		}
		return dp.getHeight();
	}

	public static void fillRect(Graphics g, double x, double y, double w, double h) {


		int ix = (int) Math.round(x);
		int iy = (int) Math.round(y);
		int iw = (int) Math.round(w + x - ix);
		int ih = (int) Math.round(h + y - iy);

		g.fillRect(ix, iy, iw, ih);
	}

}
