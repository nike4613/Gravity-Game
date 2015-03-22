package net.mc42.games;

import java.io.File;
import java.io.FileOutputStream;

import net.mc42.global.ByteString;
import net.mc42.global.Pair;

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
	public static Pair<Integer,Integer> tileImageToApproxSize(Image i, int x, int y, int szx, int szy){
		int tilex = (szx/i.getWidth())+1;
		int tiley = (szy/i.getHeight())+1;
		for(int px = 0;px<tilex;px++){
			for(int py = 0;py<tiley;py++){
				i.draw(x+(px*i.getWidth()), y+(py*i.getHeight()));
			}
		}
		return new Pair<Integer,Integer>(tilex*i.getWidth(), tiley*i.getHeight());
	}
}
