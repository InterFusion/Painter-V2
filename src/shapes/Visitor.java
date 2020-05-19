package shapes;

public interface Visitor
{
    String visitShapes(Shapes shapes);

    StringBuilder getOrnament(Shapes shapes, StringBuilder whiteSpace);

    String visitCircle(Circle circle);

    String visitEllipse(Ellipse ellipse);

    String visitRectangle(Rectangle rectangle);

    String visitSquare(Square square);
}
