package ornaments;

import shapes.Shapes;

public class TopDecorator extends ShapeDecorator
{
    public TopDecorator(Shapes decoratedShape)
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
