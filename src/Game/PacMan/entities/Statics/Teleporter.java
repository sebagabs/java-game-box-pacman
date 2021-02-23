package Game.PacMan.entities.Statics;

import Game.Galaga.Entities.BaseEntity;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Main.Handler;
import Resources.Images;

public class Teleporter extends BaseStatic{
	
	public Teleporter(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.teleporterSprite);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
