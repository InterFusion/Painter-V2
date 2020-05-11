package shapes;

public interface Visitor
{
    String visitShapes(Shapes shapes);

    String visitCircle(Circle circle);

    String visitEllipse(Ellipse ellipse);

    String visitRectangle(Rectangle rectangle);

    String visitSquare(Square square);
}
