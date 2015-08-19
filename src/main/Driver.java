package main;

import processing.core.PApplet;

public class Driver 
{
  


public static void main(String[] args) 
{
	PApplet sketch = new processingDrawing2();
	sketch.init();
	drawGUI frame = new drawGUI(sketch);
	frame.setVisible(true);
}

}
