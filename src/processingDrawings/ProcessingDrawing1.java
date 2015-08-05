package processingDrawings;

import java.io.File;

import processing.core.PApplet;


public class ProcessingDrawing1 extends PApplet{
	
	int ecart=(int) (random(4,10));
	float angle1=(float) (PI*0.2);
	float sat=random(200,240);
	int hauteur=(int)(random(30,50));

	public void setup() {

	  size(640, 640);

	  if(random(2)<1){
	  background(255);
	  } else {
	    background(0);
	  }
	  stroke(255);
	  colorMode(HSB);
	  strokeWeight(2);
	}

	public void draw() {
	  for (int a=50; a<1000;a+=(int)(random(200,220))){
	    dessineCloture(a);
	  }
	  save("/img" + File.separator + "im"+millis()+".png");
	    noLoop();
	}

	void dessineCloture(int h) {

	  float x=0; 
	  float y=h; 
	  int cas=0; 
	  float t=100; 
	  while (x<640) {
	    float angle=0; 
	    switch(cas) {
	      case 0 : 
	      angle=angle1; 
	      cas=1; 
	      t=random(60, 120); 
	      break; 
	      case 1 : 
	      angle=-angle1; 
	      cas=2; 
	      t=random(60, 120); 
	      break; 
	      case 2 : 
	      angle=PI+angle1; 
	      t=random(80, 150); 
	      cas=3; break; 
	      case 3 : 
	      angle=-angle1; 
	      cas=0; 
	      t=random(60, 120); 
	      break;
	    } 
	    float  newx=x+cos(angle)*t; 
	    float  newy=y+sin(angle)*t;  
	    for (int a=0; a<hauteur; a++) {
	      stroke(map(a,0,hauteur,0,255),sat,255,200);
	      line(x, y+a*ecart, newx, newy+a*ecart);
	    }
	    y=newy; 
	    x=newx;
	  }
	}
}
