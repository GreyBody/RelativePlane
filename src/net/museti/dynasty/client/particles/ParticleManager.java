package net.museti.dynasty.client.particles;

import java.util.ArrayList;
import java.util.List;

public class ParticleManager {

	public List<Particle> particles = new ArrayList<Particle>();

	public ParticleManager() {
	}

	public void renderParticles() {
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render();
		}
	}

	public void addParticle(Particle p) {
		particles.add(p);
	}

	public void clearBuffer() {
		particles.clear();
	}
}
