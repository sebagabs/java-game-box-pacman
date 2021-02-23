package Game.PacMan.entities.Statics;

import java.util.Random;
import Main.Handler;
import Resources.Images;

public class Fruit extends BaseStatic{
	
	private Random random;
	
    public Fruit(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, null);
    	random = new Random();
    	this.sprite = Images.fruits[random.nextInt(8)];
    }
}