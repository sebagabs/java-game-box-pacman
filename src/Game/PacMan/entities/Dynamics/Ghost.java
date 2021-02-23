package Game.PacMan.entities.Dynamics;

import Game.PacMan.entities.Statics.BaseStatic;
import java.util.Random;
import Game.PacMan.entities.Statics.BoundBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;

public class Ghost extends BaseDynamic{

	protected double velX,velY,speed = 1;
	public String facing = "Up";
	public boolean moving = true,turnFlag = true, edible = false, justSpawned = true;
	public Animation leftAnim, rightAnim, upAnim, downAnim;
	private int turnCooldown = 5, identity = 0, spawnX, spawnY;
	public int edibleCounter = 0, realIdentity = 0;
	Random random;

	public Ghost(int x, int y, int width, int height, int identity, Handler handler) {
		super(x, y, width, height, handler, null);
		changeIdentity(identity);
		this.realIdentity = identity;
		this.spawnX = x;
		this.spawnY = y;
	}

	@Override
	public void tick(){
		random = new Random();
		if(edibleCounter > 0) {
			if(identity != 4) {
				changeIdentity(4);
			}
			edibleCounter--;
		}
		else if(identity == 4){
			edible =  false;
			changeIdentity(realIdentity);
		}
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
			turnCooldown = 5;
		}
		if (turnFlag){
			turnCooldown--;
		}
			if (facing.equals("Right") || facing.equals("Left")){
				if(!checkPreHorizontalCollision(facing)) {
					if(checkPreVerticalCollision("Up") && random.nextBoolean()) {
						facing = "Up";
						turnFlag = true;
						justSpawned = false;
					}
					else if (checkPreVerticalCollision("Down")){
						facing = "Down";
						turnFlag = true;
						justSpawned = false;
					}
				}
				checkHorizontalCollision();
			}else{
				if(!checkPreVerticalCollision(facing)) {
					if(checkPreHorizontalCollision("Right") && random.nextBoolean()) {
						facing = "Right";
						turnFlag = true;
						justSpawned = false;
					}
					else if (checkPreHorizontalCollision("Left")) {
						facing = "Left";
						turnFlag = true;
						justSpawned = false;
					}
				}
				checkVerticalCollision();
			}
	}


	public void checkVerticalCollision() {
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();

		boolean ghostDies = false;
		boolean toUp = moving && facing.equals("Up");

		Rectangle ghostBounds = toUp ? ghost.getTopBounds() : ghost.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (ghostBounds.intersects(brickBounds)) {
					velY = 0;
					if (toUp)
						ghost.setY(brick.getY() + ghost.getDimension().height);
					else
						ghost.setY(brick.getY() - brick.getDimension().height);

				}
			}
		}

		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toUp ? enemy.getTopBounds() : enemy.getBottomBounds();
			if (ghostBounds.intersects(enemyBounds) && edible == true && enemy instanceof PacMan) {
				ghostDies = true;
				break;
			}
		}

		if(ghostDies) {
			handler.getMap().reset();
		}
	}


	public boolean checkPreVerticalCollision(String facing) {
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		boolean toUp = moving && facing.equals("Up");

		Rectangle ghostBounds = toUp ? ghost.getTopBounds() : ghost.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (ghostBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;

	}



	public void checkHorizontalCollision(){
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
		velX = speed;
		boolean ghostDies = false;
		boolean toRight = moving && facing.equals("Right");

		Rectangle ghostBounds = toRight ? ghost.getRightBounds() : ghost.getLeftBounds();

		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
			if (ghostBounds.intersects(enemyBounds) && edible == true && enemy instanceof PacMan) {
				ghostDies = true;
				break;
			}
		}

		if(ghostDies) {
			handler.getMap().reset();
		}else {

			for (BaseStatic brick : bricks) {
				if (brick instanceof BoundBlock) {
					Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
					if (ghostBounds.intersects(brickBounds)) {
						velX = 0;
						if (toRight)
							ghost.setX(brick.getX() - ghost.getDimension().width);
						else
							ghost.setX(brick.getX() + brick.getDimension().width);
					}
				}
			}
		}
	}


	public boolean checkPreHorizontalCollision(String facing){
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		velX = speed;
		boolean toRight = moving && facing.equals("Right");

		Rectangle ghostBounds = toRight ? ghost.getRightBounds() : ghost.getLeftBounds();

		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
				if (ghostBounds.intersects(brickBounds)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void changeIdentity(int identity) {
		switch (identity) {
		case 0:
			leftAnim = new Animation(128,Images.blinkyLeft);
			rightAnim = new Animation(128,Images.blinkyRight);
			upAnim = new Animation(128,Images.blinkyUp);
			downAnim = new Animation(128,Images.blinkyDown);
			speed = 4;
			break;
		case 1:
			leftAnim = new Animation(128,Images.pinkyLeft);
			rightAnim = new Animation(128,Images.pinkyRight);
			upAnim = new Animation(128,Images.pinkyUp);
			downAnim = new Animation(128,Images.pinkyDown);
			speed = 1;
			break;
		case 2:
			leftAnim = new Animation(128,Images.inkyLeft);
			rightAnim = new Animation(128,Images.inkyRight);
			upAnim = new Animation(128,Images.inkyUp);
			downAnim = new Animation(128,Images.inkyDown);
			speed = 2;
			break;
		case 3:
			leftAnim = new Animation(128,Images.clydeLeft);
			rightAnim = new Animation(128,Images.clydeRight);
			upAnim = new Animation(128,Images.clydeUp);
			downAnim = new Animation(128,Images.clydeDown);
			speed = 3;
			break;
		case 4:
			leftAnim = new Animation(128,Images.ghostPanic);
			rightAnim = new Animation(128,Images.ghostPanic);
			upAnim = new Animation(128,Images.ghostPanic);
			downAnim = new Animation(128,Images.ghostPanic);
			speed = 1;
		}
		this.identity = identity;
	}
	
	public void ghostReset() {
		this.x = spawnX;
		this.y = spawnY;
		this.facing = "Up";
	}
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}

}
