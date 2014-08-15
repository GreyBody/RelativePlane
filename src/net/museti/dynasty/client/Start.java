package net.museti.dynasty.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.museti.avatar.animator.CamControl;
import net.museti.dynasty.client.model.Face;
import net.museti.dynasty.client.model.Model;
import net.museti.dynasty.client.model.ModelLoader;
import net.museti.dynasty.client.particles.EarthSpikeFlat;
import net.museti.dynasty.client.particles.Particle;
import net.museti.dynasty.client.particles.ParticleManager;
import net.museti.dynasty.client.particles.ParticleObject;
import net.museti.dynasty.client.texture.TextureLoader;
import net.museti.dynasty.client.world.World;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

public class Start {

	private World w;
	private static TextureLoader tl;
	public static ParticleManager pm;
	public CamControl c;

	public void Start() {
		try {
			net.museti.avatar.animator.Config.setHome();
			createWindow();
			w = new World();
			tl = new TextureLoader();
			tl.loadAllTextures();
			InitGL();
			pm = new ParticleManager();
			c = new CamControl();
			Run();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	DisplayMode displayMode;

	private void createWindow() throws Exception {
		Display.setFullscreen(false);
		DisplayMode d[] = Display.getAvailableDisplayModes();
		for (int i = 0; i < d.length; i++) {
			if (d[i].getWidth() == 640 && d[i].getHeight() == 480 && d[i].getBitsPerPixel() == 32) {
				displayMode = d[i];
				break;
			}
		}
		Display.setDisplayMode(displayMode);
		Display.setTitle(Config.gTitle);
		Display.create();
	}

	private void InitGL() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1.0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		GLU.gluPerspective(45.0f, (float) displayMode.getWidth() / (float) displayMode.getHeight(), 0.1f, 100.0f);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);

		int objDisplayList = GL11.glGenLists(1);
		GL11.glNewList(objDisplayList, GL11.GL_COMPILE);
		{
			Model m = null;
			try {
				m = ModelLoader.loadNOBJModel(new File(net.museti.avatar.animator.Config.projHome + net.museti.avatar.animator.Config.proj + "/models/wall0.obj"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Display.destroy();
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
				Display.destroy();
				System.exit(1);
			}
			GL11.glBegin(GL11.GL_TRIANGLES);
			for (Face f : m.faces) {
				GL11.glColor3f(f.color[0], f.color[1], f.color[2]);

				Vector3f n1 = m.normals.get((int) f.normal.x - 1);
				GL11.glNormal3f(n1.x, n1.y, n1.z);
				Vector3f v1 = m.vertices.get((int) f.vertex.x - 1);
				GL11.glVertex3f(v1.x, v1.y, v1.z);

				Vector3f n2 = m.normals.get((int) f.normal.y - 1);
				GL11.glNormal3f(n2.x, n2.y, n2.z);
				Vector3f v2 = m.vertices.get((int) f.vertex.y - 1);
				GL11.glVertex3f(v2.x, v2.y, v2.z);

				Vector3f n3 = m.normals.get((int) f.normal.z - 1);
				GL11.glNormal3f(n3.x, n3.y, n3.z);
				Vector3f v3 = m.vertices.get((int) f.vertex.z - 1);
				GL11.glVertex3f(v3.x, v3.y, v3.z);
			}
			GL11.glEnd();
		}
		GL11.glEndList();
	}

	public static FPCamera fpc = new FPCamera(0, 0, 0);

	private void Run() {
		float dx = 0.0f;
		float dy = 0.0f;
		float dt = 0.0f;
		float lastTime = 0.0f;
		float time = 0.0f;

		float mouseSensitivity = 0.05f;
		float movementSpeed = 10.0f;
		while (!Display.isCloseRequested()) {
			if (Config.freecam) {
				time = Sys.getTime();
				dt = (time - lastTime) / 1000.0f;
				lastTime = time;

				dx = Mouse.getDX();
				dy = Mouse.getDY();

				fpc.yaw(dx * mouseSensitivity);
				fpc.pitch(dy * mouseSensitivity);
				if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
					fpc.walkForward(movementSpeed * dt);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
					fpc.walkBackwards(movementSpeed * dt);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
					fpc.strafeLeft(movementSpeed * dt);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
					fpc.strafeRight(movementSpeed * dt);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
					fpc.flyUp(movementSpeed * dt);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
					fpc.flyDown(movementSpeed * dt);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
					fpc.yaw(movementSpeed * dt);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
					fpc.pitch(movementSpeed * dt);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
					fpc.yawinverse(movementSpeed * dt);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
					fpc.pitchinverse(movementSpeed * dt);
				}
			}
			try {
				//w.updateWorld(fpc.getX(), fpc.getZ());
				//System.out.println("X:" + fpc.getX() + " Y:" + fpc.getY() + " Z:" + fpc.getZ() + " YAW:" + fpc.getYaw() + " PITCH:" + fpc.getPitch());
				Render();
				Display.update();
				Display.sync(60);
			} catch (Exception e) {

			}
		}
		Display.destroy();
	}

	private void Render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		GL11.glLoadIdentity();
		fpc.lookThrough();

		pm.renderParticles();
	}

	public static void main(String[] args) throws LWJGLException {
		Start s = new Start();
		s.Start();
	}

	public static void initCommand(String[] args) throws IOException {
		Particle p = null;
		String[] dif = args[1].split(",");
		System.out.println("Command: " + args[0].replace(":", ""));
		if (args[0].startsWith("clearbuffer:")) {
			pm.clearBuffer();
		} else if (args[0].startsWith("earth-spike-flat:")) {
			float x = Float.parseFloat(dif[0]), y = Float.parseFloat(dif[1]), z = Float.parseFloat(dif[2]), pitch = Float.parseFloat(dif[3]), yaw = Float.parseFloat(dif[4]);
			float length = Float.parseFloat(dif[5]), w0 = Float.parseFloat(dif[6]), w1 = Float.parseFloat(dif[7]);
			p = new EarthSpikeFlat(x, y, z, pitch, yaw, length, w0, w1);
			p.texture = tl.getTexture("assets/earth0.png");
		} else if (args[0].startsWith("model:")) {
			int id = Integer.parseInt(dif[0]);
			float x = Float.parseFloat(dif[1]), y = Float.parseFloat(dif[2]), z = Float.parseFloat(dif[3]), pitch = Float.parseFloat(dif[4]), yaw = Float.parseFloat(dif[5]);
			p = new ParticleObject(x, y, z, pitch, yaw, id);
		}
		if (p != null)
			pm.addParticle(p);
	}
}
