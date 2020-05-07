package shapes;

import java.awt.*;
import java.util.ArrayList;

public interface IShapes
{
    void refactor(int posX, int posY, int width, int height);
    String accept(Visitor visitor);
}
