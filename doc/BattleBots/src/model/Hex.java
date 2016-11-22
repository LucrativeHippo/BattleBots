package model;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
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
	private static int border =15; //distance from edge of screen
	public Polygon hexagon;
	
	public Hex(int i, int j, int height ){
		robotList = new LinkedList<Robot>();
		hexExists = true;
		h = height;	
		r = h/2;
		s = (int) (h / 1.73205);	//angle is 30 degrees and r is h /2, so to get s -> h / cos(30)*2
		t = (int) ( r / 1.73205);
		int x = i * (h)+ (j%2) * h/2;
		int y = j * (s+t);
		int posX = x + border;
		int posY = y + border*10;
		int xpoints[] = {posX+r, posX+h, posX+h, posX+r, posX, posX};
		int ypoints[] = {posY,posY-t, posY-t-s,posY-t-s-t,posY-t-s,posY-t};
		hexagon = new Polygon(xpoints,ypoints,6);

	}
	public void drawHex(Graphics2D graphics, Color color){
		graphics.setColor(Color.WHITE);
		graphics.fillPolygon(hexagon);
		graphics.setColor(Color.black);
		graphics.drawPolygon(hexagon);
	}
	
	public static Point PointAtHex(int mousex, int mousey) {
		Point p = new Point(-1,-1);
		mousex -= border;
		mousey -= (border*10);
		int row = (int) (mousey / (s+t)); 
		int column = (int) ((mousex - (row%2)*r)/h);
		int dy = mousey - row*(s+t);
		int dx = mousex - (column*h);
		System.out.println("dx=" + dx + " dy= " + dy + " row = " + row + " column = " + column + "\n");	
		if(row%2==0){
			if(dx > r){
				if(dy * r/t > dx - r){
					row--;
				}
			}
			if(dx < r){
				if((t-dy)*r/t < dx){
					row--;
					column--;
				}
			}
		}
		else{
			if(dx > h){
				if(dy * r/t > dx - h){
					row--;
					column++;
				}
			}
			if(dx < h){
				if((t-dy)*r/t < dx - r){
					row--;
				}
			}
		}
		
		p.x=column;
		p.y=row;
		return p;
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
