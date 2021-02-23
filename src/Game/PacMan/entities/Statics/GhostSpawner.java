package Game.PacMan.entities.Statics;

import java.awt.event.KeyEvent;
import java.util.Random;

import Game.Galaga.Entities.BaseEntity;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Main.Handler;
import Resources.Images;

public class GhostSpawner extends BaseStatic {
	
	Random random;
	public int ghostType = 0;
	public int ghostCounter = 0;
	public boolean ghostVibeCheck[];
	
	public GhostSpawner(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, null);
    	ghostVibeCheck = new boolean[4];
	}
	
	public void ghostSpawn() {
		BaseDynamic ghost = new Ghost(this.x, this.y, Game.PacMan.World.MapBuilder.pixelMultiplier, Game.PacMan.World.MapBuilder.pixelMultiplier, ghostType, handler);
		handler.getMap().addEnemy(ghost);
		ghostVibeCheck[ghostType] = true;
		ghostCounter++;
	}
	public void tick() {
		random = new Random();
		if(random.nextInt(60*5) == 0 && ghostCounter < 4) {
			for(int i = 0; i < 4; i++) {
				if(ghostVibeCheck[i] == false) {
					ghostType = i;
					ghostSpawn();
					break;
				}
			}
		}
		
		if(handler.DEBUG) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) {
				ghostType = random.nextInt(4);
				ghostSpawn();
			}
		}
		
	}

}
