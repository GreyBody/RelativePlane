package net.museti.dynasty.client.particles;

import org.lwjgl.opengl.GL11;

public class EarthSpikeFlat extends Particle {

	private float length;
	private float width0;
	private float width1;

	public EarthSpikeFlat(float x, float y, float z, float pitch, float yaw, float length, float width0, float width1) {
		super(x, y, z, pitch, yaw);
		this.length = length;
		this.width0 = width0;
		this.width1 = width1;
		this.y -= 1.0f;
		this.x -= 1.0f;
		this.z -= 1.0f;
	}

	public void render() {
		GL11.glPushMatrix();
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glRotatef(this.pitch, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(this.yaw, 0.0f, 1.0f, 0.0f);
		texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTranslatef(this.x, this.y, this.z);

		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(x, y, z);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(x + 1.0f * width0, y, z);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(x + 1.0f * width0, y + length, z);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(x, y + length, z);

		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(x, y, z);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(x, y, z + (1.0f * width1));
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(x, y + length, z + (1.0f * width1));
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(x, y + length, z);

		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(x + 1.0f * width0, y, z);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(x + 1.0f * width0, y, z + (1.0f * width1));
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(x + 1.0f * width0, y + length, z + (1.0f * width1));
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(x + 1.0f * width0, y + length, z);

		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(x, y, z + 1.0f * width1);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(x + 1.0f * width0, y, z + 1.0f * width1);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(x + 1.0f * width0, y + length, z + 1.0f * width1);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(x, y + length, z + 1.0f * width1);

		GL11.glTexCoord2f(0.0f, 0.0f);
		GL11.glVertex3f(x, y + length, z);
		GL11.glTexCoord2f(1.0f, 0.0f);
		GL11.glVertex3f(x + 1.0f * width0, y + length, z);
		GL11.glTexCoord2f(1.0f, 1.0f);
		GL11.glVertex3f(x + 1.0f * width0, y + length, z + 1.0f * width1);
		GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glVertex3f(x, y + length, z + 1.0f * width1);
		GL11.glEnd();
		GL11.glPopMatrix();
	}
}
