package net.museti.dynasty.client.particles;

import org.lwjgl.opengl.GL11;

public class ParticleObject extends Particle {

	public int id;

	public ParticleObject(float x, float y, float z, float pitch, float yaw, int id) {
		super(x, y, z, pitch, yaw);
		this.id = id;
	}

	public void render() {
		GL11.glPushMatrix();
		GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(this.x, this.y, this.z);
		GL11.glCallList(id);
		GL11.glPopMatrix();
	}
}
