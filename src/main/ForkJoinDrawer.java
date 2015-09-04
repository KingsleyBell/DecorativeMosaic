package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import processing.core.PApplet;
import processing.core.PMatrix;
import processing.core.PShape;

public class ForkJoinDrawer extends RecursiveAction {
	
	ArrayList<Frustum> frustums;
	PApplet p;
	
	public ForkJoinDrawer(ArrayList<Frustum> frustums, PApplet p) {
		this.frustums = frustums;	
		this.p = p;
//		System.out.println(frustums.size());
	}
		
	@Override
	protected void compute() {
		
		System.out.println("computing: " + frustums.size());
		if(frustums.size() > 100) {
			
			List<ForkJoinDrawer> subtasks = new ArrayList<ForkJoinDrawer>();
			
			subtasks.addAll(createSubtasks());
			
			for(RecursiveAction subtask : subtasks){
                subtask.fork();
            }
			
		}
		
		
		else {
			System.out.println("DOING IT");			
			for (Frustum f : frustums) {					
				PShape s = p.createShape();					
				s = f.makeFrustum(s);
				p.fill(f.getColour());							
				s.rotate(f.getOrientation());												
				System.out.println("frustum");
				p.shape(s, f.getX(), f.getY());				
			}
		}
		
		
	}
	
	private List<ForkJoinDrawer> createSubtasks() {
        List<ForkJoinDrawer> subtasks =
            new ArrayList<ForkJoinDrawer>();
        
        int halfway = (int)(frustums.size()/2);
        
        List<Frustum> sub1 = frustums.subList(0, halfway);
        List<Frustum> sub2 = frustums.subList(halfway, frustums.size());
        
        ArrayList<Frustum> s1 = new ArrayList<Frustum>(sub1);
        ArrayList<Frustum> s2 = new ArrayList<Frustum>(sub2);
        

        ForkJoinDrawer subtask1 = new ForkJoinDrawer(s1,p);
        ForkJoinDrawer subtask2 = new ForkJoinDrawer(s2,p);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
	
	public PMatrix getPApplet() {
		return p.getMatrix();
	}


	
	
}
