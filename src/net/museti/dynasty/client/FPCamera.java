package net.museti.dynasty.client;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class FPCamera {

	private Vector3f position = null;
	private float yaw = 0.0f;
	private float pitch = 0.0f;

	public FPCamera(float x, float y, float z) {
		position = new Vector3f(x, y, z);
	}

	public void yaw(float amount) {
		yaw += amount;
	}

	public void yawinverse(float amount) {
		yaw -= amount;
	}

	public void pitch(float amount) {
		pitch += amount;
	}

	public void pitchinverse(float amount) {
		pitch -= amount;
	}

	public void walkForward(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw));
	}

	public void walkBackwards(float distance) {
		position.x += distance * (float) Math.sin(Math.toRadians(yaw));
		position.z -= distance * (float) Math.cos(Math.toRadians(yaw));
	}

	public void strafeLeft(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw - 90));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw - 90));
	}

	public void strafeRight(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw + 90));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw + 90));
	}

	public void flyUp(float distance) {
		position.y -= distance;
	}

	public void flyDown(float distance) {
		position.y += distance;
	}

	public void lookThrough() {
		GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(position.x, position.y, position.z);
	}

	public void setCam(float x, float y, float z, float yaw, float pitch) {
		GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(x, y, z);
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getZ() {
		return position.z;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}
}
