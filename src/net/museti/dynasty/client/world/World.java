package net.museti.dynasty.client.world;

import java.util.ArrayList;
import java.util.List;

import net.museti.dynasty.client.Region;

public class World {

	private List<Region> world = new ArrayList<Region>();
	float xx;
	float zz;

	boolean config = false;
	boolean confi = true;

	public World() {
		loadRegion(0, 0);
	}

	public void updateWorld(float x, float z) {
		if (x == xx && z == zz)
			return;
		xx = x;
		zz = z;
		int[] xarr = calcX(-x, (int) -(x / 64.0f));
		int[] zarr = calcZ(-z, (int) -(z / 64.0f));
		if (xarr[0] < 0 || zarr[0] < 0)
			return;
		System.out.println("" + xarr[1] + " " + zarr[1] + " " + xarr[0] + " " + zarr[0]);
		if (xarr[0] >= 0 && zarr[0] < 0) {
			loadRegion(xarr[0], zarr[0]);
			unloadRegion(xarr[1], zarr[1]);
		}
		if (xarr[0] < 0 && zarr[0] >= 0) {
			loadRegion(xarr[0], zarr[0]);
			unloadRegion(xarr[1], zarr[1]);
		}
		if (xarr[0] >= 0 && zarr[0] >= 0) {
			loadRegion(xarr[0], zarr[0]);
			unloadRegion(xarr[1], zarr[1]);
		}
	}

	private int[] calcX(float x, int cx) {
		int[] d = { -1, -1 };
		int[] e = { -1, -1 };
		if (x < 2.0f * 32.0f * cx + (2.0f * 5.0f)) {
			d[0] = cx - 1;
			d[1] = cx + 1;
		}
		if (x > 2.0f * 32.0f * (cx + 1) - (2.0f * 5.0f)) {
			e[0] = cx + 1;
			e[1] = cx - 1;
		}
		if (d[0] >= 0)
			return d;
		if (e[0] >= 0)
			return e;
		return new int[] { 0, 0 };
	}

	private int[] calcZ(float z, int cz) {
		int[] d = { -1, -1 };
		int[] e = { -1, -1 };
		if (z < 2.0f * 32.0f * cz + (2.0f * 5.0f)) {
			d[0] = cz - 1;
			d[1] = cz + 1;
		}
		if (z > 2.0f * 32.0f * (cz + 1) - (2.0f * 5.0f)) {
			e[0] = cz + 1;
			e[1] = cz - 1;
		}
		if (d[0] >= 0)
			return d;
		if (e[0] >= 0)
			return e;
		return new int[] { 0, 0 };
	}

	public void loadRegion(int x, int z) {
		Region r = new Region(x, z);
		world.add(r);
	}

	public void unloadRegion(int x, int z) {
		for (int i = 0; i < world.size(); i++) {
			if (x == world.get(i).x && z == world.get(i).z)
				world.remove(i);
		}
	}

	public void renderWorld() {
		for (int i = 0; i < world.size(); i++) {
			if (world.get(i).realChunk) {
				world.get(i).renderChunks();
			}
		}
	}
}
