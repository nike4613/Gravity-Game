package net.mc42.games;

import java.io.FileOutputStream;

import org.newdawn.slick.Image;

public class ImageUtils {
	public static Image getImageFromBytes(byte[] data) throws Exception{
		FileOutputStream f = new FileOutputStream(System.getProperty("user.dir") + "/tmp/decoder");
		f.write(data);
		f.close();
		return new Image(System.getProperty("user.dir") + "/tmp/decoder");
	}
}
