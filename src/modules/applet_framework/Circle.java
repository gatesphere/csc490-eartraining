import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.jfugue.*;

public class Circle {
	
	double x, y;	//Circle's center
	double radius;	//Circle's radius
	double rim;	//Outer Circle's Width
	Color color;	//Circle's color
	Note note; //Circle's note

	public Circle(double x, double y) {
		this.x = x;
		this.y = y;
		radius = 25.0;
		rim = 5.0;
		color = Color.BLACK;
		note = new Note((byte)60);
	}

	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		rim = 5.0;
		color = Color.BLACK;
		note = new Note((byte)60);
	}
	
	public Circle(double x, double y, Note note) {
		this.x = x;
		this.y = y;
		radius = 25.0;
		rim = 5.0;
		color = Color.BLACK;
		this.note = note;
	}
	
	public Circle(double x, double y, double radius, double rim) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.rim = rim;
		color = Color.BLACK;
		note = new Note((byte)60);
	}
	
	public Circle(double x, double y, double radius, double rim, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.rim = rim;
		this.color = color;
		note = new Note((byte)60);
	}
	
	public Circle(double x, double y, double radius, double rim, Note note) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.rim = rim;
		color = Color.BLACK;
		this.note = note;
	}
	
	public Circle(double x, double y, double radius, double rim, Color color, Note note) {
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

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRim(double rim) {
		this.rim = rim;
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double[] getPosition() {
		double[] xy = {x,y};
		return xy;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Note getNote() {
		return note;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
		g.setColor(Color.WHITE);
		g.fillOval((int)(x - radius - rim), (int)(y - radius -rim), (int)(2 * (radius - rim)), (int)(2 * (radius - rim)));
	} 
}
