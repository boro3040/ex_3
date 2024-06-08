/*
Barak Davidovitch
211604350
OOP BIU 2024
 */

/**
 * This class will hold the sprites and the collidables, and will be in charge
 * of the animation.
 */
public class Game {

    private SpriteCollection sprites;
    private GameEnvironment environment;

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

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize();

    // Run the game -- start the animation loop.
    public void run();

    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
