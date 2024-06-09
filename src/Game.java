/*
Barak Davidovitch
211604350
OOP BIU 2024
 */
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * This class will hold the sprites and the collidables, and will be in charge
 * of the animation.
 */
public class Game {

    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private GUI gui;

    /**
     * The constructor that initialize empty sprites and environment.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * add a collidable to collidables list in GameEnvironment.
     *
     * @param c The collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a sprite to sprites list in SpriteCollection.
     *
     * @param s The sprite we want to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        int guiWidth = 800;
        int guiHeight = 600;
        this.gui = new GUI("title", guiWidth, guiHeight);
        // add ball to the game.
        Point ballStart = new Point(100, 100);
        Ball ball = new Ball(ballStart, 5, Color.WHITE, this.environment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(55, 2));
        ball.addToGame(this);
        int rectsAmount = 7;

        // add all the inside blocks.
        for (int i = 0; i < rectsAmount; i++) {
            for (int j = 0; j < rectsAmount; j++) {
                Block block = new Block(new Rectangle(new Point(50 + i * 80,
                                        50 + j * 60), 20, 20),
                                        Color.BLUE, Color.BLACK);
                block.addToGame(this);
            }
        }

        // add all 4 frame blocks.
        int frameSize = 20;
        // right block
        Block block = new Block(new Rectangle(new Point(guiWidth - frameSize,
                            frameSize), frameSize, guiHeight - frameSize),
                            Color.GRAY, Color.BLACK);
        block.addToGame(this);
        // left block
        block = new Block(new Rectangle(new Point(0, frameSize), frameSize,
                            guiHeight - frameSize), Color.GRAY, Color.BLACK);
        block.addToGame(this);
        // upper block
        block = new Block(new Rectangle(new Point(0, 0), guiWidth,
                            frameSize), Color.GRAY, Color.BLACK);
        block.addToGame(this);
        // lower block
        block = new Block(new Rectangle(new Point(frameSize, guiHeight - frameSize),
                            guiWidth - 2 * frameSize, frameSize),
                            Color.GRAY, Color.BLACK);
        block.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            Sleeper sleeper = new Sleeper();
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * the maim method that creating the game, initialize it, and running it.
     * @param args command line arguments (not in use).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
