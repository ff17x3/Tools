package media;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Florian on 28.04.2016.
 */
public class GifFactory {

	private ImageOutputStream outputStream;
	private GifSequenceWriter writer;
	private static final int BUFIMG_TYPE = BufferedImage.TYPE_INT_ARGB;

	public GifFactory(File output, float frameRate, boolean infiniteLoop) throws Exception {

		outputStream = new FileImageOutputStream(output);
		writer = new GifSequenceWriter(outputStream, BUFIMG_TYPE, Math.round(1000 / frameRate), infiniteLoop);
	}

	/**
	 * Call this method every time you want to add a Frame to the GIF
	 *
	 * @param g
	 */
	public void addDrawedFrame(Graphics g) {
		Rectangle tempBounds = g.getClipBounds();
		BufferedImage currentImage = new BufferedImage(tempBounds.width, tempBounds.height, BUFIMG_TYPE);
		try {
			writer.writeToSequence(currentImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveGIF() {
		try {
			writer.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
