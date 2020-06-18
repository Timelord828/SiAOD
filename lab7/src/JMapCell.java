import javax.swing.*;
import java.awt.*;

public class JMapCell extends JComponent {
    private static final Dimension CELL_SIZE = new Dimension(12, 12);
    boolean endpoint = false;
    boolean passable = true;
    boolean path = false;
    boolean way = false;
    public JMapCell(boolean pass) {
        setPreferredSize(CELL_SIZE);
        setPassable(pass);
    }
    public JMapCell() {
        this(true);
    }
    public void setEndpoint(boolean end) {
        endpoint = end;
        updateAppearance();
    }
    public void setPassable(boolean pass) {
        passable = pass;
        updateAppearance();
    }
    public boolean isPassable() {
        return passable;
    }
    public void setWay(boolean way) {
        this.way = way;
        this.path = false;
        updateAppearance();
    }
    private void updateAppearance() {
        if (passable) {
            setBackground(Color.WHITE);
            if (endpoint)
                setBackground(Color.CYAN);
            else if (this.way)
                setBackground(Color.GREEN);
        }
        else {
            setBackground(Color.RED);
        }
    }
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}