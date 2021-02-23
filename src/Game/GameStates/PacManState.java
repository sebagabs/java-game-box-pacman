package Game.GameStates;

import Display.UI.UIManager;
import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.Dot;
import Game.PacMan.entities.Statics.Fruit;
import Game.PacMan.entities.Statics.Teleporter;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PacManState extends State {

	private UIManager uiManager;
	public String Mode = "Intro";
	private int startCooldown = 60*5;//seven seconds for the music to finish
	private boolean newGame = true, startSpawn = true;
	private int fruitPointTimer = 0, ghostPointTimer = 0, imgX, imgY, level = 0, maxLevels = 5, initialLevel = 0, dotCounter = 0;
	private Handler handler;
	private int character = 0, maxChar = 5, selection = 0;

	public PacManState(Handler handler){
		super(handler);
		this.handler = handler;
		handler.setEffects(new ArrayList<>());
		handler.setMap(MapBuilder.createMap(Images.map[level], character, handler));
	}


	@Override
	public void tick() {
		if (Mode.equals("Stage")){
			if(newGame) {
				handler.setMap(MapBuilder.createMap(Images.map[level], character, handler));
				newGame = false;
			}
			if (startCooldown<=0) {
				if(startSpawn) {
					for(int i = 0; i < 4; i++) {
						handler.getGhostSpawner().ghostType = i;
						handler.getGhostSpawner().ghostSpawn();
					}
					startSpawn = false;

					if(level == 0) {
						handler.getMusicHandler().changeMusic("TropicalResort.wav");
					}
					else if(level == 1) {
						handler.getMusicHandler().changeMusic("PlanetWisp.wav");
					}
					else if(level == 2) {
						handler.getMusicHandler().changeMusic("AquariumPark.wav");
					}
					else if(level == 3) {
						handler.getMusicHandler().changeMusic("StarlightCarnival.wav");
					}
					else if(level == 4) {
						handler.getMusicHandler().changeMusic("TerminalVelocity.wav");
					}
				}
				for (BaseDynamic entity : handler.getMap().getEnemiesOnMap()) {
					entity.tick();
				}
				for(BaseStatic block : handler.getMap().getBlocksOnMap()) {
					block.tick();
				}
				ArrayList<BaseStatic> toRemoveBlock = new ArrayList<>();
				ArrayList<BaseDynamic> toRemoveEntity = new ArrayList<>();
				int blockIndex = 0;
				for (BaseStatic blocks: handler.getMap().getBlocksOnMap()){
					blockIndex++;
					if (blocks instanceof Dot){
						dotCounter++;
						if (blocks.getBounds().intersects(handler.getPacman().getBounds())){
							handler.getMusicHandler().playEffect("pacman_chomp.wav");
							toRemoveBlock.add(blocks);
							handler.getScoreManager().addPacmanCurrentScore(10);
							dotCounter--;
						}
					}
					else if (blocks instanceof Fruit){
						dotCounter++;
						if (blocks.getBounds().intersects(handler.getPacman().getBounds())){
							handler.getMusicHandler().playEffect("pacman_eatfruit.wav");
							fruitPointTimer = 60;
							imgX = blocks.getX();
							imgY = blocks.getY();
							toRemoveBlock.add(blocks);
							handler.getScoreManager().addPacmanCurrentScore(200);
							dotCounter--;
						}
					}else if (blocks instanceof BigDot){
						dotCounter++;
						if (blocks.getBounds().intersects(handler.getPacman().getBounds())){
							handler.getMusicHandler().playEffect("pacman_chomp.wav");
							toRemoveBlock.add(blocks);
							for (BaseDynamic entity : handler.getMap().getEnemiesOnMap()) {
								if(entity instanceof Ghost) {
									((Ghost) entity).edible = true;
									((Ghost) entity).edibleCounter = 60*7;
								}
							}
							handler.getMusicHandler().playEffect("pacman_power_pellet.wav");
							handler.getScoreManager().addPacmanCurrentScore(100);
							dotCounter--;
						}
					}
					else if (blocks instanceof Teleporter){
						if(handler.getPacman().facing.equals("Left")) {
							if (blocks.getMiddleBounds().intersects(handler.getPacman().getLeftBounds())){
								warp(blockIndex);
							}
						}
						else if(handler.getPacman().facing.equals("Right")) {
							if (blocks.getMiddleBounds().intersects(handler.getPacman().getRightBounds())){
								warp(blockIndex);
							}
						}
						else if(handler.getPacman().facing.equals("Up")) {
							if (blocks.getMiddleBounds().intersects(handler.getPacman().getTopBounds())){
								warp(blockIndex);
							}
						}
						else if(handler.getPacman().facing.equals("Down")) {
							if (blocks.getMiddleBounds().intersects(handler.getPacman().getBottomBounds())){
								warp(blockIndex);
							}
						}
					}
				}

				for(BaseDynamic enemies: handler.getMap().getEnemiesOnMap()){
					if (enemies instanceof Ghost){
						if (enemies.getBounds().intersects(handler.getPacman().getBounds()) && ((Ghost)enemies).edible){
							handler.getMusicHandler().playEffect("pacman_eatghost.wav");
							toRemoveEntity.add(enemies);
							handler.getGhostSpawner().ghostVibeCheck[((Ghost)enemies).realIdentity] = false;
							handler.getGhostSpawner().ghostCounter--;
							handler.getScoreManager().addPacmanCurrentScore(500);
							ghostPointTimer = 60;
							imgX = enemies.getX();
							imgY = enemies.getY();

						}
					}
				}

				for (BaseStatic removing: toRemoveBlock){
					handler.getMap().getBlocksOnMap().remove(removing);
				}
				for(BaseDynamic removing: toRemoveEntity) {
					handler.getMap().getEnemiesOnMap().remove(removing);
				}
				if(dotCounter == 0) {
					Mode = "Clear";
				}
				else {
					dotCounter = 0;
				}
			}else{
				startCooldown--;
			}

			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
				Mode = "Pause";
			}

			if(handler.getPacman().getHealth() <= 0 && handler.getPacman().deathAnim.end) {
				Mode = "GameOver";
				handler.getMusicHandler().stopMusic();
				handler.getMusicHandler().playEffect("EndGame.wav");
			}

		}else if (Mode.equals("Pause")){
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
				selection = 0;
			}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
				selection = 1;
			}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
				if(selection == 0) {
					Mode = "Stage";
				}
				else if(selection == 1) {
					Mode = "Menu";
					selection = 0;
					handler.getMusicHandler().stopAllEffects();
					handler.getMusicHandler().stopMusic();
				}
			}
		}else if (Mode.equals("Menu")){
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && character < maxChar - 1){
				character++;
			}
			else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && character > 0){
				character--;
			}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && initialLevel < maxLevels - 1){
				initialLevel++;
			}
			else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN) && initialLevel > 0){
				initialLevel--;
			}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
				restart();
			}
		}
		else if(Mode.equals("Clear")) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
				nextLevel();
			}
		}
		else if(Mode.equals("GameOver")) {
			if(handler.getScoreManager().getPacmanCurrentScore() > handler.getScoreManager().getPacmanHighScore()) {
				handler.getScoreManager().setPacmanHighScore(handler.getScoreManager().getPacmanCurrentScore());
				handler.getMap().reset();
			}
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
				restart();
			}
		}else{
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
				Mode = "Menu";
			}
		}

	}

	@Override
	public void render(Graphics g) {

		if (Mode.equals("Stage")){
			Graphics2D g2 = (Graphics2D) g.create();
			handler.getMap().drawMap(g2);
			if(fruitPointTimer > 0) {
				g.drawImage(Images.fruitPoints, imgX, imgY, 15, 7, null);
				fruitPointTimer--;
			}
			if(ghostPointTimer > 0) {
				g.drawImage(Images.ghostPoints, imgX, imgY, 15, 7, null);
				ghostPointTimer--;
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
			g.drawString("Score: " + handler.getScoreManager().getPacmanCurrentScore(),(handler.getWidth()/2) + handler.getWidth()/6, 25);
			g.drawString("High-Score: " + handler.getScoreManager().getPacmanHighScore(),(handler.getWidth()/2) + handler.getWidth()/6, 75);
			for(int i=0; i < handler.getPacman().getHealth(); i++) {
				g.drawImage(Images.characters[character], handler.getWidth() - handler.getWidth()/4 + handler.getPacman().width*i, handler.getHeight()/6, handler.getPacman().width, handler.getPacman().height, null);
			}
		}else if (Mode.equals("Menu")){
			g.drawImage(Images.start,0,0,handler.getWidth()/2,handler.getHeight(),null);
			g.drawImage(Images.characters[character], handler.getWidth()/7 - 20, handler.getHeight()/4, 64, 64, null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 36));
			g.drawString(String.valueOf(initialLevel + 1), handler.getWidth()/2 - handler.getWidth()/7 - 10, handler.getHeight()/4 + 20);
		}else if(Mode.equals("Clear")){
			g.drawImage(Images.clearScreen,0,0,handler.getWidth(),handler.getHeight(),null);
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 84));
			g.drawString("" + handler.getScoreManager().getPacmanCurrentScore(),handler.getWidth()/2 - handler.getWidth()/100, handler.getHeight() - (handler.getHeight()/3)+3);
			if( handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
				handler.getPacman().setHealth(3);
				handler.getMap().reset();
				Mode = ""; // Empty string so that the game goes to the screen before the "Menu" mode.
			}
		}else if(Mode.equals("GameOver")){
			g.drawImage(Images.gameOverScreen,0,0,handler.getWidth(),handler.getHeight(),null);
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 84));
			g.drawString("" + handler.getScoreManager().getPacmanCurrentScore(),handler.getWidth()/2 - handler.getWidth()/100, handler.getHeight() - (handler.getHeight()/3));
			g.drawString("" + handler.getScoreManager().getPacmanHighScore(), handler.getWidth()/2 - handler.getWidth()/100, handler.getHeight() - (handler.getHeight()/3)+68);
			if( handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
				handler.getPacman().setHealth(3);
				handler.getMap().reset();
				Mode = ""; // Empty string so that the game goes to the screen before the "Menu" mode.
			}
		}else if(Mode.equals("Pause")){
			Graphics2D g2 = (Graphics2D) g.create();
			handler.getMap().drawMap(g2);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 48));
			g.drawString("Resume", (handler.getWidth()/2) + handler.getWidth()/6, handler.getHeight()/2);
			g.drawString("Quit", (handler.getWidth()/2) + handler.getWidth()/6, handler.getHeight()/2 + handler. getHeight()/8);
			if(selection == 0) {
				g.setColor(Color.RED);
				g.drawString("Resume", (handler.getWidth()/2) + handler.getWidth()/6, handler.getHeight()/2);
			}else if(selection == 1) {
				g.setColor(Color.RED);
				g.drawString("Quit", (handler.getWidth()/2) + handler.getWidth()/6, handler.getHeight()/2 + handler. getHeight()/8);
			}
		}else{
			g.drawImage(Images.intro,0,0,handler.getWidth()/2,handler.getHeight(),null);

		}
	}

	public void warp(int blockIndex) {
		for(int j = blockIndex; j < handler.getMap().getBlocksOnMap().size(); j++) {
			if(handler.getMap().getBlocksOnMap().get(j) instanceof Teleporter) {
				handler.getPacman().setX(handler.getMap().getBlocksOnMap().get(j).getX());
				handler.getPacman().setY(handler.getMap().getBlocksOnMap().get(j).getY());
				break;
			}
			if(j == handler.getMap().getBlocksOnMap().size() - 1) {
				j = 0;
			}
		}
	}

	@Override
	public void refresh() {

	}

	public void  restart() {
		handler.getScoreManager().setPacmanCurrentScore(0);
		newGame = true;
		startSpawn = true;
		handler.getPacman().setHealth(3);
		level = initialLevel;
		startCooldown = 60*5;
		handler.getMusicHandler().stopMusic();
		handler.getMusicHandler().playEffect("pacman_beginning.wav");
		Mode = "Stage";
	}

	public void  nextLevel() {
		newGame = true;
		startSpawn = true;
		if(level < maxLevels-1) {
			level++;
		}
		else {
			level = 0;
		}
		startCooldown = 60*5;
		handler.getMusicHandler().stopMusic();
		handler.getMusicHandler().playEffect("pacman_beginning.wav");
		Mode = "Stage";
	}

}
