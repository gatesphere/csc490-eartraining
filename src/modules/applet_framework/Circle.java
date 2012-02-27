import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.jfugue.*;

public class Circle extends JPanel {
	
	int x, y;	//Circle's center
	int radius;	//Circle's radius
	int rim;	//Outer Circle's Width
	Color color;	//Circle's color
	String note; //Circle's note


	public Circle(int x, int y) {
		this.x = x;
		this.y = y;
		radius = 50;
		rim = 20;
		color = Color.BLACK;
		note = "C4";
	}

	public Circle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		rim = 20;
		color = Color.BLACK;
		note = "C4";
	}
	
	public Circle(int x, int y, String note) {
		this.x = x;
		this.y = y;
		radius = 50;
		rim = 20;
		color = Color.BLACK;
		this.note = note;
	}
	
	public Circle(int x, int y, int radius, int rim) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.rim = rim;
		color = Color.BLACK;
		note = "C4";
	}
	
	public Circle(int x, int y, int radius, int rim, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.rim = rim;
		this.color = color;
		note = "C4";
	}
	
	public Circle(int x, int y, int radius, int rim, String note) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.rim = rim;
		color = Color.BLACK;
		this.note = note;
	}
	
	public Circle(int x, int y, int radius, int rim, Color color, String note) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.rim = rim;
		this.color = color;
		this.note = note;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRim(int rim) {
		this.rim = rim;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int[] getPosition() {
		int[] xy = {x,y};
		return xy;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
		g.setColor(Color.WHITE);
		g.fillOval((int)(x - radius + rim), (int)(y - radius + rim), (int)(2 * (radius-rim)), (int)(2 * (radius-rim)));
	} 
}
