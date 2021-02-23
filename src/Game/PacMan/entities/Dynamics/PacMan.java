package Game.PacMan.entities.Dynamics;

import Game.PacMan.entities.Statics.BaseStatic;

import Game.PacMan.entities.Statics.BoundBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PacMan extends BaseDynamic{

	protected double velX,velY,speed = 2;
	public String facing = "Left";
	public boolean moving = true,turnFlag = false;
	public Animation leftAnim,rightAnim,upAnim,downAnim,deathAnim;
	int turnCooldown = 5;
	private int health = 3, spawnX, spawnY, character = 0;


	public PacMan(int x, int y, int width, int height, int character, Handler handler) {
		super(x, y, width, height, handler, Images.pacmanRight[0]);
		characterSelect(character);
		spawnX = x; spawnY = y;
	}


	@Override
	public void tick(){
		if(this.ded) {
			if(this.dedCounter <= 0){
				this.dedCounter = 60*5;
				deathAnim.reset();
			}
			else {
				deathAnim.tick();
				this.dedCounter--;
				if(dedCounter <= 0) {
					deathAnim.reset();
					ded = false;
				}
			}
		}
		else {
			switch (facing){
			case "Right":
				x+=velX;
				rightAnim.tick();
				break;
			case "Left":
				x-=velX;
				leftAnim.tick();
				break;
			case "Up":
				y-=velY;
				upAnim.tick();
				break;
			case "Down":
				y+=velY;
				downAnim.tick();
				break;
			}
			if (turnCooldown<=0){
				turnFlag= false;
			}
			if (turnFlag){
				turnCooldown--;
			}

			if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)  || handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) && !turnFlag && checkPreHorizontalCollision("Right")){
				facing = "Right";
				turnFlag = true;
				turnCooldown = 5;
			}else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) && !turnFlag&& checkPreHorizontalCollision("Left")){
				facing = "Left";
				turnFlag = true;
				turnCooldown = 5;
			}else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)  ||handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) && !turnFlag&& checkPreVerticalCollision("Up")){
				facing = "Up";
				turnFlag = true;
				turnCooldown = 5;
			}else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)  || handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) && !turnFlag&& checkPreVerticalCollision("Down")){
				facing = "Down";
				turnFlag = true;
				turnCooldown = 5;
			}

			if (facing.equals("Right") || facing.equals("Left")){
				checkHorizontalCollision();
			}else{
				checkVerticalCollision();
			}

			if(handler.DEBUG) {
				if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_N) && getHealth() < 3){
					setHealth(getHealth()+1);
					handler.getMusicHandler().playEffect("pacman_extrapac.wav");
				}
				if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P) && getHealth() > 0) {
					pacmanDeath();
				}
			}
		}

	}

	public void pacmanDeath() {
		this.ded = true;
		handler.getMusicHandler().playEffect("pacman_death.wav");
		setHealth(getHealth()-1);
	}

	public void checkVerticalCollision() {
		PacMan pacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();

		boolean pacmanDies = false;
		boolean toUp = moving && facing.equals("Up");

		Rectangle pacmanBounds = toUp ? pacman.getTopBounds() : pacman.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (pacmanBounds.intersects(brickBounds)) {
					velY = 0;
					if (toUp)
						pacman.setY(brick.getY() + pacman.getDimension().height);
					else
						pacman.setY(brick.getY() - brick.getDimension().height);
				}
			}
		}
		pacmanBounds = pacman.getBounds();
		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = enemy.getBounds();
			if(enemy instanceof Ghost) {
				if (pacmanBounds.intersects(enemyBounds) && ((Ghost)enemy).edible == false) {
					pacmanDies = true;
					break;
				}
			}
		}

		if(pacmanDies) {
			pacmanDeath();
			handler.getMap().reset();
		}
	}


	public boolean checkPreVerticalCollision(String facing) {
		PacMan pacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();

		boolean pacmanDies = false;
		boolean toUp = moving && facing.equals("Up");

		Rectangle pacmanBounds = toUp ? pacman.getTopBounds() : pacman.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (pacmanBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;

	}



	public void checkHorizontalCollision(){
		PacMan pacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
		velX = speed;
		boolean pacmanDies = false;
		boolean toRight = moving && facing.equals("Right");

		Rectangle pacmanBounds = pacman.getBounds();

		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = enemy.getBounds();
			if(enemy instanceof Ghost) {
				if (pacmanBounds.intersects(enemyBounds) && ((Ghost)enemy).edible == false) {
					pacmanDies = true;
					break;
				}
			}
		}

		if(pacmanDies) {
			pacmanDeath();
			handler.getMap().reset();
		}else {
			pacmanBounds = toRight ? pacman.getRightBounds() : pacman.getLeftBounds();
			for (BaseStatic brick : bricks) {
				if (brick instanceof BoundBlock) {
					Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
					if (pacmanBounds.intersects(brickBounds)) {
						velX = 0;
						if (toRight)
							pacman.setX(brick.getX() - pacman.getDimension().width);
						else
							pacman.setX(brick.getX() + brick.getDimension().width);
					}
				}
			}
		}
	}


	public boolean checkPreHorizontalCollision(String facing){
		PacMan pacman = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		velX = speed;
		boolean toRight = moving && facing.equals("Right");

		Rectangle pacmanBounds = toRight ? pacman.getRightBounds() : pacman.getLeftBounds();

		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
				if (pacmanBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;
	}

	public void characterSelect(int i) {
		if(i == 0) {
			leftAnim = new Animation(128,Images.pacmanLeft);
			rightAnim = new Animation(128,Images.pacmanRight);
			upAnim = new Animation(128,Images.pacmanUp);
			downAnim = new Animation(128,Images.pacmanDown);
			deathAnim = new Animation(128,Images.pacmanDeath);
		}
		else if(i == 1) {
			leftAnim = new Animation(128,Images.maidLeft);
			rightAnim = new Animation(128,Images.maidRight);
			upAnim = new Animation(128,Images.maidUp);
			downAnim = new Animation(128,Images.maidDown);
			deathAnim = new Animation(256,Images.maidDeath);
		}
		else if(i == 2) {
			leftAnim = new Animation(128,Images.marioLeft);
			rightAnim = new Animation(128,Images.marioRight);
			upAnim = new Animation(128,Images.marioUp);
			downAnim = new Animation(128,Images.marioDown);
			deathAnim = new Animation(256,Images.marioDeath);
		}
		else if(i == 3) {
			leftAnim = new Animation(128,Images.linkLeft);
			rightAnim = new Animation(128,Images.linkRight);
			upAnim = new Animation(128,Images.linkUp);
			downAnim = new Animation(128,Images.linkDown);
			deathAnim = new Animation(256,Images.linkDeath);
		}
		else if(i == 4) {
			leftAnim = new Animation(128,Images.rosaLeft);
			rightAnim = new Animation(128,Images.rosaRight);
			upAnim = new Animation(128,Images.rosaUp);
			downAnim = new Animation(128,Images.rosaDown);
			deathAnim = new Animation(256,Images.rosaDeath);
		}
	}

	public void pacmanReset() {
		this.x = spawnX;
		this.y = spawnY;
		this.facing = "Left";
	}
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setCharacter(int character) {
		this.character = character;
	}

}
