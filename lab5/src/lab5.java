import java.awt.*;
import java.awt.event.*;
public class lab5 extends Canvas {
    private static final long serialVersionUID = 546343032002044481L;
    // таймер
    public static double timer = 0.0;
    public static void time() {
        if (timer == 0)
            timer = System.nanoTime();
        else {
            double val = (double)
                    (System.nanoTime() - timer);
            System.out.println(val/1000000);
            timer = 0.0;
        }
    }
    // формируем цвета и масштаб
    public void paint(Graphics graph) {
        setBackground(Color.white);
        graph.setColor(Color.blue);
        Point a = new Point(0, 10);
        Point b = new Point(this.getWidth(), 10);
        // отмечаем время отрисовки кривой
        time();
        // задаем глубину
        drawKochLine(graph, a, b, 0, 2);
        time();
    }
    public void drawKochLine(Graphics graph, Point a, Point b, double angle, int depth) {
        if (depth <= 0) {
            // рисуем прямую, если достигнута необходимая глубина рекурсии
            graph.drawLine(a.x, a.y, b.x, b.y);
        } else {
            // находим длину отрезка (a; b)
            double length = Math.pow(Math.pow(b.y - a.y, 2) + Math.pow(b.x - a.x, 2), 0.5);
            // находим длину 1/3 отрезка (a; b)
            double length13 = length / 3;
            // находим точку, делящую отрезок как 1:3
            Point a1 = new Point(a.x + (int) Math.round((length13 * Math.cos(angle))),
                    a.y + (int) Math.round((length13 * Math.sin(angle))));
            // находим точку, делящую отрезок как 2:3
            Point b1 = new Point(a1.x + (int) Math.round((length13 * Math.cos(angle))),
                    a1.y + (int) Math.round((length13 * Math.sin(angle))));
            // находим точку, которая будет вершиной равностороннего треугольника
            Point c = new Point(a1.x + (int) Math.round((length13 * Math.cos(angle + Math.PI/3))),
                    a1.y + (int) Math.round((length13 * Math.sin(angle + Math.PI/3))));
            depth--;
            drawKochLine(graph, a1, c, angle + Math.PI/3, depth);
            drawKochLine(graph, c, b1, angle - Math.PI/3, depth);
            depth--;
            drawKochLine(graph, a, a1, angle, depth);
            drawKochLine(graph, b1, b, angle, depth);
        }
    }
    public static int width = 800, height = 300;
    public static void main(String[] args) {
        final Frame frame = new Frame("Кривая Коха");
        frame.setSize(width, height);
        frame.add(new lab5());
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }
}