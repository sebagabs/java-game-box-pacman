package Game.PacMan.entities.Statics;

import Main.Handler;
import Resources.Animation;
import Resources.Images;


public class BigDot extends BaseStatic{
	
	public Animation bigDotAnim;
	
    public BigDot(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.pacmanBigDots[0]);
        bigDotAnim = new Animation(512,Images.pacmanBigDots);
    }
    @Override
    public void tick() {
    	bigDotAnim.tick();
    }
}
