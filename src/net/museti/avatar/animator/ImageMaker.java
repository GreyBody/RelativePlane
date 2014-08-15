package net.museti.avatar.animator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import net.museti.dynasty.client.Start;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ImageMaker {

	private Start s;
	private IntBuffer buffer;
	private int[] buf;

	public ImageMaker(Start s) {
		this.s = s;
	}

	public void createStillFrames() {
		if (s != null) {
			for (int i = 0; i < s.c.seconds.length; i++) {
				if (s.c.seconds[i] != null) {
					for (int j = 0; j < 25; j++) {
						s.fpc.setCam(s.c.seconds[i].camx[j], s.c.seconds[i].camy[j], s.c.seconds[i].camz[j], s.c.seconds[i].yaw[j], s.c.seconds[i].pitch[j]);
						createImage(Config.projHome, Config.proj, i, j, Config.videoWidth, Config.videoHeight);
					}
				}
			}
		}
	}

	public void createImage(String dir, String projName, int secid, int frameid, int w, int h) {
		int k = w * h;
		if (buffer == null || buffer.capacity() < k) {
			buffer = BufferUtils.createIntBuffer(k);
			buf = new int[k];
		}
		GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		buffer.clear();
		GL11.glReadPixels(0, 0, w, h, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, buffer);
		buffer.get(buf);
		func_74289_a(buf, w, h);
		BufferedImage bufferedimage = new BufferedImage(w, h, 1);
		bufferedimage.setRGB(0, 0, w, h, buf, 0, w);
		File f0 = new File(dir + projName + "/stills");
		if (!f0.exists())
			f0.mkdir();
		File file3 = new File(dir + projName + "/stills/still_" + secid + "_" + frameid + "_" + w + "_" + h + ".png");
		try {
			ImageIO.write(bufferedimage, "png", file3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void func_74289_a(int[] par0ArrayOfInteger, int par1, int par2) {
		int[] aint1 = new int[par1];
		int k = par2 / 2;

		for (int l = 0; l < k; ++l) {
			System.arraycopy(par0ArrayOfInteger, l * par1, aint1, 0, par1);
			System.arraycopy(par0ArrayOfInteger, (par2 - 1 - l) * par1, par0ArrayOfInteger, l * par1, par1);
			System.arraycopy(aint1, 0, par0ArrayOfInteger, (par2 - 1 - l) * par1, par1);
		}
	}
}
