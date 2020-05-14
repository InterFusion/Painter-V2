package ornaments;

import shapes.Shapes;

public class RightDecorator extends ShapeDecorator
{
    public RightDecorator(Shapes decoratedShape)
    {
        super(decoratedShape);
    }

    @Override
    public void setOrnament(String position, String text)
    {
        decoratedShape.setOrnament(position, text);
        setTopDecorator();
    }

    public void setTopDecorator(){
    }
}
