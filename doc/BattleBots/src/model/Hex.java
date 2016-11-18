package model;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.LinkedList;



/**
 * 
 * @author mkp003
 */
public class Hex {
	
	public LinkedList<Robot> robotList;
	public boolean hexExists;
	
	private static int s = 0; //the length of a side for the hexagon
	private static int t = 0; //shortest side of the triangle outside of hexagon for angle
	private static int r = 0; //distance from the middle of the hexagon to a flat side or the longer side of the triangle
	private static int h = 0; //height of the whole hexagon
	private int pixels =15;
	public int posX;
	public int posY;
	public Polygon hexagon;
	
	public Hex(int x, int y, int height, Graphics2D graphics){
		robotList = new LinkedList<Robot>();
		hexExists = true;
		h = height;	
		r = h/2;
		s = (int) (h / 2 * Math.cos(30));	
		t = (int) (r*Math.tan(30) );
		int xx = x * (s+t);
		int yy = y * h + (x%2) * h/2;
		posX = xx + pixels;
		posY = yy + pixels;
		int xpoints[] = {posX+t, posX+s+t, posX+s+t+t, posX+s+t, posX+t, posX};
		int ypoints[] = {posY,posY,posY+r,posY+r+r,posY+r+r,posY+r};
		hexagon = new Polygon(xpoints,ypoints,6);
		graphics.setColor(Color.WHITE);
		graphics.fillPolygon(hexagon);
		graphics.setColor(Color.black);
		graphics.drawPolygon(hexagon);
	}
	
	
	/**
	 * This function will return a boolean value of true or false based 
	 * on whether or not the current Hex space is empty of any robots or not
	 * 
	 * @return True if empty, false otherwise
	 */
	public boolean isEmpty(){
		if(robotList == null){
			return true;
		}
		else{
			return false;
		}
	}
}
