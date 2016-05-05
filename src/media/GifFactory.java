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
	private BufferedImage currentImage;
	private Rectangle size;
	private PaintGif paintInterface;

	private static final int BUFIMG_TYPE = BufferedImage.TYPE_INT_ARGB;

	public GifFactory(Rectangle size, File output, float frameRate, boolean infiniteLoop, PaintGif paintInterface) throws Exception {
		this.size = size;
		this.paintInterface = paintInterface;
		outputStream = new FileImageOutputStream(output);
		writer = new GifSequenceWriter(outputStream, BUFIMG_TYPE, Math.round(1000 / frameRate), infiniteLoop);
	}

	/**
	 * Call this method every time you want to add a frame to the GIF.
	 * This method calls <code>paintGif(Graphics g)</code> in order to get the frame.
	 *
	 */
	public void addDrawedFrame() {
		currentImage = new BufferedImage(size.width, size.height, BUFIMG_TYPE);
		paintInterface.paintGif(currentImage.createGraphics());
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
