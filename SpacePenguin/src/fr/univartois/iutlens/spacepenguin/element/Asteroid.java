package fr.univartois.iutlens.spacepenguin.element;

public class Asteroid {
	
	
	
	public static final int MIN_DIST = 0;
	public static final int MAX_DIST = 200;
	private static final float MIN_SIZE = 0.05f;
	private static final float MAX_SIZE = 0.85f;
	private static final float Pingouin_size = 0.12f;
	
	//possibilité de modifier la taille des astéroides
	private float[] coord;
	private float size;
	private float angleX;
	private float angleY;
	private float speedX;
	private float speedY;
	private float speedZ;
	
	public float[] getCoord() {
		return coord;
	}
	
	public float getSize() {
		return size;
	}

	public Asteroid(boolean far){
		coord = new float[3];
		
		coord[0] = 5*((float) Math.random()*2f-1f); //tire un nombre aléatoire entre -5 et 5
		coord[1] = 5*((float) Math.random()*2f-1f);
		coord[2] = (far ? MAX_DIST : ((float)(MIN_DIST+(MAX_DIST-MIN_DIST)*Math.random())));
		// y = y + Z * dy
		angleX = 0.1f*((float) Math.random()*2f-1f); 
		angleY = 0.1f*((float) Math.random()*2f-1f);
		
		coord[0] = coord[0]+angleX*coord[2];
		coord[1] = coord[1]+angleY*coord[2];
		
		size = (float)(MIN_SIZE+(MAX_SIZE-MIN_SIZE)*Math.random());
	}
	
	public boolean move(float d){
		speedX=d;
		speedY=angleY*d;
		speedZ=angleX*d;
		coord[2] -= speedX;
		coord[1] -= speedY;
		coord[0] -= speedZ;
		
		//coord[2] -= d;
		//coord[1] -= angleY*d;
		//coord[0] -= angleX*d;
		
		if (coord[2]< MIN_DIST){
			coord[0] += (MAX_DIST-MIN_DIST)*angleX;
			coord[1] += (MAX_DIST-MIN_DIST)*angleY;
			coord[2] += MAX_DIST-MIN_DIST;
			return true;
		}
		return false;
	}

	//size seul=taille d'un astéroide
	public boolean collision(float x, float y) {
		
		//return (coord[2]<size) && dist2(coord[0]-x,coord[1]-y) < (size+Pingouin_size)*(size+Pingouin_size);
		
		//coord[2] = (far ? MAX_DIST : ((float)(MIN_DIST+(MAX_DIST-MIN_DIST)*Math.random())));
		//coord[2] = (far ? MAX_DIST : ((float)(0+(200-0)*Math.random())));
		
		                        //size = (float)(MIN_SIZE+(MAX_SIZE-MIN_SIZE)*Math.random());
						    	//size = (float)(0.05f+(0.85f-0.05f)*Math.random());
								//size = (float)(0.05f+(0.80f)*Math.random()); = taille de l'astéroide
		
		//test1= si distance parcourue en Z en 1 unité de temps est inférieure à taille d'un astéroide alors retourne vrai
		//pour dist2 voir la fonction en dessous
		
		return (coord[2]<speedZ+size) && dist2(coord[0]-x,coord[1]-y) < (size+Pingouin_size)*(size+Pingouin_size);
		
	}

	private float dist2(float dx, float dy) {
		return dx*dx+dy*dy;
	}
	//en y on se décale de Z*dY 
}
