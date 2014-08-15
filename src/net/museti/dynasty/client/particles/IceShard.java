package net.museti.dynasty.client.particles;

import org.lwjgl.opengl.GL11;

public class IceShard extends Particle {

	public IceShard(float x, float y, float z, float pitch, float yaw) {
		super(x, y, z, pitch, yaw);
	}

	public void render() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glRotatef(this.pitch, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(this.yaw, 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(this.x, this.y, this.z);
		GL11.glEnd();
	}
}
