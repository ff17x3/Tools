package util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawFrame {

	DrawPanel dp;
	float scale;

	public DrawFrame(FrameInitInterface fii, DrawInferface d) {
		new DrawFrame(null, fii, d, null);
	}

	public DrawFrame(Dimension size, FrameInitInterface fii, DrawInferface d, DimensionF mapSize) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (size == null || size.width <= 0 || size.height <= 0)
			size = new Dimension(500, 500);
		if (mapSize != null)
			f.addComponentListener(new ComponentListener() {
				public void componentResized(ComponentEvent e) {
					scale = Math.min(dp.getWidth() / mapSize.width, dp.getHeight() / mapSize.height);
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
			drawable.draw(g,scale);
		}
	}

	public int getWidth() {
		return dp.getWidth();
	}

}
