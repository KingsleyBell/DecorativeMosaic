package processingFun.IgnoreThis;

import processing.core.PApplet;

public class ProcessingDrawing3 extends PApplet {

	float maxSize = random(20, 250);
	float minSize = (float)(Math.random()*(maxSize*0.6 - maxSize*0.3) + maxSize * 0.3);

	public void setup() {
		size(1024, 1024);
		background(255);
		colorMode(HSB);
		noStroke();
		smooth();
	}

	public void draw() {
		for (int a = 0; a < 5; a++) {
			cible();
		}
	}

	void cible() {
		float x = random(width), y = random(height);
		float r = random(maxSize, minSize);
		boolean bk = true;

		fill(0, 50);
		ellipse(x + 4, y + 4, r, r);

		if (random(100) < 50) {
			bk = false;
		}
		float c = random(255);
		while (r > 10) {
			if (bk) {
				fill(c, 255, 20);
			} else {
				fill(c, random(50, 200), 255);
			}
			ellipse(x, y, r, r);
			r *= Math.random()*(0.95-0.75) + 0.75;			
			bk = !bk;
		}
	}

	public void keyReleased() {
		save("img" + millis() + ".png");
	}

}
