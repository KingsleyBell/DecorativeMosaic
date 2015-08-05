package processingDrawings;

import processing.core.PApplet;
import processing.core.PGraphics;

public class processingDrawing2 extends PApplet {

	poin[] points;
	PGraphics g;

	public void setup() {
		colorMode(HSB, 100, 100, 100);
		points = new poin[0];
		size(640, 640);
		g = createGraphics(width, height, JAVA2D);
		g.beginDraw();
		g.noStroke();
		g.fill(0, 8);
		g.rect(0, 0, width, height);
		g.endDraw();
		noStroke();
		background(0);
		strokeWeight((float) 1.5);
		new poin(width / 2, height / 2);
	}

	public void draw() {
		image(g, 0, 0);
		for (int a = 0; a < points.length; a++) {
			points[a].dessine();
		}
		recentre();
	}

	class poin {
		float x, y, objx, objy, an, r, v, ac, acv;
		poin lautre;
		int c;

		poin(int _x, int _y) {
			ac = random(TWO_PI);
			acv = random((int)(-PI * 0.01),(int)(PI * 0.01));
			x = _x;
			y = _y;
			objx = x;
			objy = y;
			if (points.length > 0) {
				lautre = chercheLePlusProche(x, y);

				r = dist(lautre.x, lautre.y, x, y);
				an = atan2(y - lautre.y, x - lautre.x);
				v = random((int)(0.001), (int)(0.05));
				if (random(10) < 5) {
					v = -v;
				}
			}
			points = (poin[]) append(points, this);
		}

		void dessine() {
			stroke(sin(ac) * 50 + 50, 100, 100);
			ac += acv;
			float oldx = x, oldy = y;
			if (lautre != null) {
				x = lautre.x + cos(an) * r;
				y = lautre.y + sin(an) * r;
				an += v;
			}
			float d = dist(x, y, objx, objy);
			if (d > 2) {
				float nx = x + ((objx - x) / d) * 2;
				float ny = y + ((objy - y) / d) * 2;
				x = nx;
				y = ny;
				if (lautre != null) {
					line(x, y, lautre.x, lautre.y);
				}
			}
			oldx = x;
			oldy = y;
		}
	}

	void recentre() {
		float x = 0, y = 0, minx = width / 2, miny = height / 2, maxx = width / 2, maxy = height / 2;
		for (int a = 0; a < points.length; a++) {
			x += points[a].x;
			y += points[a].y;
		}
		x /= points.length;
		y /= points.length;
		float difx = width / 2 - x;
		float dify = height / 2 - y;
		for (int a = 0; a < points.length; a++) {
			points[a].objx = points[a].x + difx;
			points[a].objy = points[a].y + dify;
		}
	}

	public void mouseReleased() {
		new poin(mouseX, mouseY);
	}

	poin chercheLePlusProche(float x, float y) {
		poin lautre = lautre = points[floor(random(points.length))];
		float t = 100000;
		for (int a = 0; a < points.length; a++) {
			float d = dist(x, y, points[a].x, points[a].y);
			if (d < t) {
				t = d;
				lautre = points[a];
			}
		}
		return lautre;
	}

	public void keyReleased() {
		save("img" + millis() + ".png");
	}

}
