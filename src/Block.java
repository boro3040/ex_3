/*
Barak Davidovitch
211604350
OOP BIU 2024
 */
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class implements a block, that made of rectangle that is collidable
 * object.
 */
public class Block implements Collidable, Sprite {

    private final Rectangle rectangle;
    private final Color insideColor;
    private final Color outsideColor;

    /**
     * The constructor of the Block class.
     * @param rectangle the rectangle the block made of.
     * @param color ths color of the inside/outside block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = new Rectangle(rectangle);
        this.insideColor = color;
        this.outsideColor = color;
    }

    /**
     * constructor with rectangle and different colors to outside and inside.
     * @param rectangle the rectangle of the block.
     * @param insideColor the Color inside the block.
     * @param outsideColor the color outside the block.
     */
    public Block(Rectangle rectangle, Color insideColor, Color outsideColor) {
        this.rectangle = new Rectangle(rectangle);
        this.insideColor = insideColor;
        this.outsideColor = outsideColor;
    }

    /**
     * Constructor with the values of upper left point, width and height
     * and the color.
     * @param upperLeft the upper left point of the rectangle making the block
     * @param width the width of the block.
     * @param height the height og the block.
     * @param color the color of the inside/outside block.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.insideColor = color;
        this.outsideColor = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle);
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        // Check where the collision happened and update velocity accordingly
        if (Util.isEqual(collisionPoint.getX(),
            this.rectangle.getUpperLeft().getX()) || Util.isEqual(collisionPoint.getX(),
            this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth())) {
            newDx = -newDx;
        }
        if (Util.isEqual(collisionPoint.getY(),
            this.rectangle.getUpperLeft().getY()) || Util.isEqual(collisionPoint.getY(),
            this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())) {
            newDy = -newDy;
        }
        return new Velocity(newDx, newDy);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.insideColor);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                        (int) this.rectangle.getUpperLeft().getY(),
                        (int) this.rectangle.getWidth(),
                        (int) this.rectangle.getHeight());
        d.setColor(this.outsideColor);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                        (int) this.rectangle.getUpperLeft().getY(),
                        (int) this.rectangle.getWidth(),
                        (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        "".isEmpty();
    }

    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
