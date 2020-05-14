package ornaments;
import shapes.Shapes;

public abstract class ShapeDecorator implements IShapeDecorator
{
    protected Shapes decoratedShape;

    public ShapeDecorator(Shapes decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void setOrnament(String position, String text)
    {
        decoratedShape.setOrnament(position, text);
    }
}
