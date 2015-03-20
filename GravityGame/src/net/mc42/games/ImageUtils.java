package net.mc42.games;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.newdawn.slick.Image;

public class ImageUtils {
	public static Image getImageFromBytes(ArrayList<Byte> image) throws Exception{
		File f = new File(System.getProperty("user.dir") + "/tmp/decoder.png");
		File f1 = new File((System.getProperty("user.dir") + "/tmp"));
		if(!f.isFile() || !f1.isDirectory()){
			f1.mkdirs();
			f.createNewFile();
		}
		FileOutputStream fo = new FileOutputStream(f);
		for(byte b:image)
			fo.write(b);
		fo.close();
		return new Image(System.getProperty("user.dir") + "/tmp/decoder.png");
	}
}
