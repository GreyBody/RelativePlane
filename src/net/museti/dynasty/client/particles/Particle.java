package net.museti.dynasty.client.particles;

import net.museti.dynasty.client.texture.Texture;

public class Particle {

	long uid;
	float x;
	float y;
	float z;
	float pitch;
	float yaw;

	public Texture texture;

	public Particle(float x, float y, float z, float pitch, float yaw) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public void changeX(float delta) {
		this.x += delta;
	}

	public void changeY(float delta) {
		this.y += delta;
	}

	public void changeZ(float delta) {
		this.z += delta;
	}

	public void changePitch(float delta) {
		this.pitch += delta;
	}

	public void changeYaw(float delta) {
		this.yaw += delta;
	}

	public void render() {
	}
}
