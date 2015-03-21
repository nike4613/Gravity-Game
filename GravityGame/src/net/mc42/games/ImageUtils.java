package net.mc42.games;

import java.io.File;
import java.io.FileOutputStream;

import net.mc42.global.ByteString;

import org.newdawn.slick.Image;

public class ImageUtils {
	public static Image getImageFromBytes(ByteString image) throws Exception{
		File f = new File(System.getProperty("user.dir") + "/tmp/decoder.png");
		File f1 = new File((System.getProperty("user.dir") + "/tmp"));
		if(!f.isFile() || !f1.isDirectory()){
			f1.mkdirs();
			f.createNewFile();
		}
		FileOutputStream fo = new FileOutputStream(f);
		fo.write(image.toByteArray());
		fo.close();
		return new Image(System.getProperty("user.dir") + "/tmp/decoder.png");
	}
}
