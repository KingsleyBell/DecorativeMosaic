void setup () {
  size(400, 400);
  background (255);
}

void draw () {
//   if (mousePressed) {
      strokeWeight(3);
      fill(0);
//      for(int i = 0; i < 100; i++) {
//        float rX = random(500);
//        float rY = random(500);
//        PVector R = new PVector(rX, rY);
//        PVector centre = new PVector(width/2, height/2);
//        PVector r = PVector.sub(R, centre);
//        PVector v = new PVector(-r.y, r.x);
//        v.setMag(20);
//        translate(width/2,height/2);
//        v.setMag(20);
//  //      print(v.x);
//  //      line(mouseX, mouseY, mouseX + v.x, mouseY + v.y);
//        line(r.x, r.y, r.x +v.x, r.y + v.y);
//      }
      if(mousePressed) {
        PVector R = new PVector(mouseX, mouseY);
        PVector centre = new PVector(width/2, height/2);
        PVector r = PVector.sub(R, centre);
        PVector v = new PVector(-r.y, r.x);
        v.setMag(20);
        translate(width/2,height/2);
        v.setMag(20);
  //      print(v.x);
  //      line(mouseX, mouseY, mouseX + v.x, mouseY + v.y);
        line(r.x, r.y, r.x +v.x, r.y + v.y);
    } 
}

