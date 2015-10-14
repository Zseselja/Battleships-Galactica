package view;

import java.awt.Color;
import java.awt.Rectangle;

public class BoardPiece extends Rectangle {
	private Color color;
	
	public BoardPiece(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
