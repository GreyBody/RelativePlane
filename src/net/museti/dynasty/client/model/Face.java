package net.museti.dynasty.client.model;

import org.lwjgl.util.vector.Vector3f;

public class Face {

	public Vector3f vertex = new Vector3f();
	public Vector3f normal = new Vector3f();
	public float[] color = new float[3];

	public Face(Vector3f vertex, Vector3f normal) {
		this.vertex = vertex;
		this.normal = normal;
	}
}
