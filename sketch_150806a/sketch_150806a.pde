void setup () {
  size(400, 400);
  background (255);
}

void draw () {
   if (mousePressed) {
      strokeWeight(3);
      fill(0);
      PVector v = new PVector(xVal(mouseY), yVal(mouseX));
      PVector centre = new PVector(width/2, height/2);
      v.sub(centre);
      line(mouseX, mouseY, v.x, v.y);
    } 
}

int xVal(int x) {
  return -x;
}

int yVal(int y) {
   return y; 
}
