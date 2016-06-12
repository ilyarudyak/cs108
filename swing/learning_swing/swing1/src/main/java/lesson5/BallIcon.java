package lesson5;
import java.awt.*;
import javax.swing.*;

public class BallIcon implements Icon {

	private int size;
	private Color[] colors = new Color[2];
	float fractions[] = { 0, 1 };
	
	public BallIcon(int size, Color color) {
		this.size = size;
		colors[0] = color;
		colors[1] = color.darker().darker();
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		if (g instanceof Graphics2D) {
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int radius = size/2;
			RadialGradientPaint rgrad = new RadialGradientPaint(x+radius, y+radius, radius*3/2, fractions, colors);
			((Graphics2D)g).setPaint(rgrad);
		}
		else {
			g.setColor(colors[0]);
		}
		g.fillOval(x, y, size-1, size-1);
		g.setColor(Color.BLACK);
	}

	@Override
	public int getIconWidth() {
		return size;
	}

	@Override
	public int getIconHeight() {
		return size;
	}

}
