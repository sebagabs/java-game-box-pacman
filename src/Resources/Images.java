package Resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class Images {

	public static BufferedImage titleScreenBackground;
	public static BufferedImage pauseBackground;
	public static BufferedImage selectionBackground;
	public static BufferedImage galagaCopyright;
	public static BufferedImage galagaSelect;
	public static BufferedImage muteIcon;
	public static BufferedImage galagaPlayerLaser;
	public static BufferedImage[] startGameButton;
	public static BufferedImage[] galagaLogo;
	public static BufferedImage[] pauseResumeButton;
	public static BufferedImage[] pauseToTitleButton;
	public static BufferedImage[] pauseOptionsButton;
	public static BufferedImage[] galagaPlayer;
	public static BufferedImage[] galagaPlayerDeath;
	public static BufferedImage[] galagaEnemyDeath;
	public static BufferedImage[] galagaEnemyBee;

	public static BufferedImage[] map;
	public static BufferedImage ghost;
	public static BufferedImage[] pacmanBigDots;
	public static BufferedImage[] pacmanDots;
	public static BufferedImage[] pacmanRight;
	public static BufferedImage[] pacmanLeft;
	public static BufferedImage[] pacmanUp;
	public static BufferedImage[] pacmanDown;
	public static BufferedImage[] inkyRight;
	public static BufferedImage[] inkyLeft;
	public static BufferedImage[] inkyUp;
	public static BufferedImage[] inkyDown;
	public static BufferedImage[] pinkyRight;
	public static BufferedImage[] pinkyLeft;
	public static BufferedImage[] pinkyUp;
	public static BufferedImage[] pinkyDown;
	public static BufferedImage[] blinkyRight;
	public static BufferedImage[] blinkyLeft;
	public static BufferedImage[] blinkyUp;
	public static BufferedImage[] blinkyDown;
	public static BufferedImage[] clydeRight;
	public static BufferedImage[] clydeLeft;
	public static BufferedImage[] clydeUp;
	public static BufferedImage[] clydeDown;
	public static BufferedImage[] ghostPanic;
	public static BufferedImage[] bound;
	public static BufferedImage[] pacmanDeath;
	public static BufferedImage[] pacmanLogo;
	public static BufferedImage[] fruits;
	public static BufferedImage deadGhostRight;
	public static BufferedImage deadGhostLeft;
	public static BufferedImage deadGhostUp;
	public static BufferedImage deadGhostDown;
	public static BufferedImage teleporterSprite;
	public static BufferedImage fruitPoints;
	public static BufferedImage ghostPoints;
	public static BufferedImage intro;
	public static BufferedImage start;
	public static BufferedImage gameOverScreen;
	public static BufferedImage clearScreen;

	//Additional characters
	public static BufferedImage[] characters;

	//Maid
	public static BufferedImage[] maidRight;
	public static BufferedImage[] maidLeft;
	public static BufferedImage[] maidUp;
	public static BufferedImage[] maidDown;
	public static BufferedImage[] maidDeath;

	//Super Mario
	public static BufferedImage[] marioRight;
	public static BufferedImage[] marioLeft;
	public static BufferedImage[] marioUp;
	public static BufferedImage[] marioDown;
	public static BufferedImage[] marioDeath;

	//Zelda (haha)
	public static BufferedImage[] linkRight;
	public static BufferedImage[] linkLeft;
	public static BufferedImage[] linkUp;
	public static BufferedImage[] linkDown;
	public static BufferedImage[] linkDeath;

	//Pokemon Trainer Rosa
	public static BufferedImage[] rosaRight;
	public static BufferedImage[] rosaLeft;
	public static BufferedImage[] rosaUp;
	public static BufferedImage[] rosaDown;
	public static BufferedImage[] rosaDeath;




	public static BufferedImage galagaImageSheet;
	public SpriteSheet galagaSpriteSheet;

	public static BufferedImage pacmanImageSheet;
	public SpriteSheet pacmanSpriteSheet;

	public static BufferedImage characterImageSheet;
	public SpriteSheet characterSpriteSheet;

	public Images() {

		startGameButton = new BufferedImage[3];
		pauseResumeButton = new BufferedImage[2];
		pauseToTitleButton = new BufferedImage[2];
		pauseOptionsButton = new BufferedImage[2];
		galagaLogo = new BufferedImage[3];
		galagaPlayer = new BufferedImage[8];//not full yet, only has second to last image on sprite sheet
		galagaPlayerDeath = new BufferedImage[8];
		galagaEnemyDeath = new BufferedImage[5];
		galagaEnemyBee = new BufferedImage[8];

		pacmanLogo = new BufferedImage[3];
		map = new BufferedImage[5];
		pacmanBigDots = new BufferedImage[2];
		pacmanDots = new BufferedImage[3];
		pacmanRight = new BufferedImage[2];
		pacmanLeft = new BufferedImage[2];
		pacmanUp = new BufferedImage[2];
		pacmanDown = new BufferedImage[2];
		bound = new BufferedImage[16];
		pacmanDeath = new BufferedImage[12];
		inkyRight = new BufferedImage[2];
		inkyLeft = new BufferedImage[2];
		inkyUp = new BufferedImage[2];
		inkyDown = new BufferedImage[2];
		pinkyRight = new BufferedImage[2];
		pinkyLeft = new BufferedImage[2];
		pinkyUp = new BufferedImage[2];
		pinkyDown = new BufferedImage[2];
		blinkyRight = new BufferedImage[2];
		blinkyLeft = new BufferedImage[2];
		blinkyUp = new BufferedImage[2];
		blinkyDown = new BufferedImage[2];
		clydeRight = new BufferedImage[2];
		clydeLeft = new BufferedImage[2];
		clydeUp = new BufferedImage[2];
		clydeDown = new BufferedImage[2];
		ghostPanic = new BufferedImage[4];
		fruits = new BufferedImage[8];

		//Additional Characters
		characters = new BufferedImage[10];

		maidRight = new BufferedImage[4];
		maidLeft = new BufferedImage[4];
		maidUp = new BufferedImage[4];
		maidDown = new BufferedImage[4];
		maidDeath = new BufferedImage[4];

		marioRight = new BufferedImage[2];
		marioLeft = new BufferedImage[2];
		marioUp = new BufferedImage[4];
		marioDown = new BufferedImage[4];
		marioDeath = new BufferedImage[4];

		linkRight = new BufferedImage[4];
		linkLeft = new BufferedImage[4];
		linkUp = new BufferedImage[4];
		linkDown = new BufferedImage[4];
		linkDeath = new BufferedImage[2];

		rosaRight = new BufferedImage[4];
		rosaLeft = new BufferedImage[4];
		rosaUp = new BufferedImage[4];
		rosaDown = new BufferedImage[4];
		rosaDeath = new BufferedImage[4];



		try {

			startGameButton[0]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/NormalStartButton.png"));
			startGameButton[1]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/HoverStartButton.png"));
			startGameButton[2]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/ClickedStartButton.png"));

			titleScreenBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Title.png"));

			pauseBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Pause.png"));

			selectionBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Selection.png"));

			galagaCopyright = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/Copyright.png"));

			galagaSelect = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_select.png"));

			muteIcon = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/mute.png"));

			galagaLogo[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_logo.png"));
			galagaLogo[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/hover_galaga_logo.png"));
			galagaLogo[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pressed_galaga_logo.png"));

			pacmanLogo[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/pacman_logo.png"));
			pacmanLogo[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Pacman/hover_pacman_logo.png"));
			pacmanLogo[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Pacman/pressed_pacman_logo.png"));

			pauseResumeButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/NormalHoverResume.png"));
			pauseResumeButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/PressedResume.png"));

			pauseToTitleButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/NormalHoverToTitleButton.png"));
			pauseToTitleButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/PressedToTitleButton.png"));

			pauseOptionsButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/NormalHoverToOptionsButton.png"));
			pauseOptionsButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/PressedToOptionsButton.png"));

			galagaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/Galaga.png"));
			galagaSpriteSheet = new SpriteSheet(galagaImageSheet);

			galagaPlayer[0] = galagaSpriteSheet.crop(160,55,15,16);

			galagaPlayerDeath[0] = galagaSpriteSheet.crop(209,48,32,32);
			galagaPlayerDeath[1] = galagaSpriteSheet.crop(209,48,32,32);
			galagaPlayerDeath[2] = galagaSpriteSheet.crop(247,48,32,32);
			galagaPlayerDeath[3] = galagaSpriteSheet.crop(247,48,32,32);
			galagaPlayerDeath[4] = galagaSpriteSheet.crop(288,47,32,32);
			galagaPlayerDeath[5] = galagaSpriteSheet.crop(288,47,32,32);
			galagaPlayerDeath[6] = galagaSpriteSheet.crop(327,47,32,32);
			galagaPlayerDeath[7] = galagaSpriteSheet.crop(327,47,32,32);

			galagaEnemyDeath[0] = galagaSpriteSheet.crop(201,191,32,32);
			galagaEnemyDeath[1] = galagaSpriteSheet.crop(223,191,32,32);
			galagaEnemyDeath[2] = galagaSpriteSheet.crop(247,191,32,32);
			galagaEnemyDeath[3] = galagaSpriteSheet.crop(280,191,32,32);
			galagaEnemyDeath[4] = galagaSpriteSheet.crop(320,191,32,32);

			galagaEnemyBee[0] = galagaSpriteSheet.crop(188,178,9,10);
			galagaEnemyBee[1] = galagaSpriteSheet.crop(162,178,13,10);
			galagaEnemyBee[2] = galagaSpriteSheet.crop(139,177,11,12);
			galagaEnemyBee[3] = galagaSpriteSheet.crop(113,176,14,13);
			galagaEnemyBee[4] = galagaSpriteSheet.crop(90,177,13,13);
			galagaEnemyBee[5] = galagaSpriteSheet.crop(65,176,13,14);
			galagaEnemyBee[6] = galagaSpriteSheet.crop(42,178,12,11);
			galagaEnemyBee[7] = galagaSpriteSheet.crop(19,177,10,13);


			galagaPlayerLaser = galagaSpriteSheet.crop(365 ,219,3,8);

			pacmanImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Background.png"));
			pacmanSpriteSheet = new SpriteSheet(pacmanImageSheet);

			characterImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Sprites.png"));
			characterSpriteSheet = new SpriteSheet(characterImageSheet);

			map[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map0.png"));
			map[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map1.png"));
			map[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map2.png"));
			map[3] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map3.png"));
			map[4] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map4.png"));

			gameOverScreen = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/TryAgainScreen.png"));
			clearScreen = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/LevelClearScreen.png"));

			pacmanBigDots[0] = pacmanSpriteSheet.crop(643,18,16,16);
			pacmanBigDots[1] = pacmanSpriteSheet.crop(663, 18, 16, 16);

			pacmanDots[0] = pacmanSpriteSheet.crop(623,18,16,16);

			bound[0] = pacmanSpriteSheet.crop(603,18,16,16);//single
			bound[1] = pacmanSpriteSheet.crop(615,37,16,16);//right open
			bound[2] = pacmanSpriteSheet.crop(635,37,16,16);//down open
			bound[3] = pacmanSpriteSheet.crop(655,37,16,16);//left open
			bound[4] = pacmanSpriteSheet.crop(655,57,16,16);//up open
			bound[5] = pacmanSpriteSheet.crop(655,75,16,16);//up/down
			bound[6] = pacmanSpriteSheet.crop(656,116,16,16);//left/Right
			bound[7] = pacmanSpriteSheet.crop(656,136,16,16);//up/Right
			bound[8] = pacmanSpriteSheet.crop(655,174,16,16);//up/left
			bound[9] = pacmanSpriteSheet.crop(655,155,16,16);//down/Right
			bound[10] = pacmanSpriteSheet.crop(655,192,16,16);//down/left
			bound[11] = pacmanSpriteSheet.crop(664,232,16,16);//all
			bound[12] = pacmanSpriteSheet.crop(479,191,16,16);//left
			bound[13] = pacmanSpriteSheet.crop(494,191,16,16);//right
			bound[14] = pacmanSpriteSheet.crop(479,208,16,16);//top
			bound[15] = pacmanSpriteSheet.crop(479,223,16,16);//bottom

			pacmanRight[0] = pacmanSpriteSheet.crop(473,1,12,13);
			pacmanRight[1] = pacmanSpriteSheet.crop(489,1,13,13);

			pacmanLeft[0] = pacmanSpriteSheet.crop(474,17,12,13);
			pacmanLeft[1] = pacmanSpriteSheet.crop(489,1,13,13);

			pacmanUp[0] = pacmanSpriteSheet.crop(473,34,13,12);
			pacmanUp[1] = pacmanSpriteSheet.crop(489,1,13,13);

			pacmanDown[0] = pacmanSpriteSheet.crop(473,48,13,12);
			pacmanDown[1] = pacmanSpriteSheet.crop(489,1,13,13);

			inkyRight[0] = pacmanSpriteSheet.crop(457,97,14,14);
			inkyRight[1] = pacmanSpriteSheet.crop(473,97,14,14);

			inkyLeft[0] = pacmanSpriteSheet.crop(489,97,14,14);
			inkyLeft[1] = pacmanSpriteSheet.crop(505,97,14,14);

			inkyUp[0] = pacmanSpriteSheet.crop(521,97,14,14);
			inkyUp[1] = pacmanSpriteSheet.crop(537,97,14,14);

			inkyDown[0] = pacmanSpriteSheet.crop(553,97,14,14);
			inkyDown[1] = pacmanSpriteSheet.crop(569,97,14,14);

			pinkyRight[0] = pacmanSpriteSheet.crop(457,81,14,14);
			pinkyRight[1] = pacmanSpriteSheet.crop(473,81,14,14);

			pinkyLeft[0] = pacmanSpriteSheet.crop(489,81,14,14);
			pinkyLeft[1] = pacmanSpriteSheet.crop(505,81,14,14);

			pinkyUp[0] = pacmanSpriteSheet.crop(521,81,14,14);
			pinkyUp[1] = pacmanSpriteSheet.crop(537,81,14,14);

			pinkyDown[0] = pacmanSpriteSheet.crop(553,81,14,14);
			pinkyDown[1] = pacmanSpriteSheet.crop(569,81,14,14);

			blinkyRight[0] = pacmanSpriteSheet.crop(457,65,14,14);
			blinkyRight[1] = pacmanSpriteSheet.crop(473,65,14,14);

			blinkyLeft[0] = pacmanSpriteSheet.crop(489,65,14,14);
			blinkyLeft[1] = pacmanSpriteSheet.crop(505,65,14,14);

			blinkyUp[0] = pacmanSpriteSheet.crop(521,65,14,14);
			blinkyUp[1] = pacmanSpriteSheet.crop(537,65,14,14);

			blinkyDown[0] = pacmanSpriteSheet.crop(553,65,14,14);
			blinkyDown[1] = pacmanSpriteSheet.crop(569,65,14,14);

			clydeRight[0] = pacmanSpriteSheet.crop(457,113,14,14);
			clydeRight[1] = pacmanSpriteSheet.crop(473,113,14,14);

			clydeLeft[0] = pacmanSpriteSheet.crop(489,113,14,14);
			clydeLeft[1] = pacmanSpriteSheet.crop(505,113,14,14);

			clydeUp[0] = pacmanSpriteSheet.crop(521,113,14,14);
			clydeUp[1] = pacmanSpriteSheet.crop(537,113,14,14);

			clydeDown[0] = pacmanSpriteSheet.crop(553,113,14,14);
			clydeDown[1] = pacmanSpriteSheet.crop(569,113,14,14);

			ghostPanic[0] = pacmanSpriteSheet.crop(585, 65, 14, 14);
			ghostPanic[1] = pacmanSpriteSheet.crop(601, 65, 14, 14);
			ghostPanic[2] = pacmanSpriteSheet.crop(617, 65, 14, 14);
			ghostPanic[3] = pacmanSpriteSheet.crop(633, 65, 14, 14);

			deadGhostRight = pacmanSpriteSheet.crop(585, 81, 14, 14);
			deadGhostLeft = pacmanSpriteSheet.crop(601, 81, 14, 14);
			deadGhostUp = pacmanSpriteSheet.crop(617, 81, 14, 14);
			deadGhostDown = pacmanSpriteSheet.crop(633, 81, 14, 14);

			fruits[0] = pacmanSpriteSheet.crop(490, 50, 12, 12);
			fruits[1] = pacmanSpriteSheet.crop(506, 50, 11, 12);
			fruits[2] = pacmanSpriteSheet.crop(522, 50, 12, 12);
			fruits[3] = pacmanSpriteSheet.crop(538, 50, 12, 12);
			fruits[4] = pacmanSpriteSheet.crop(555, 49, 11, 14);
			fruits[5] = pacmanSpriteSheet.crop(570, 51, 11, 11);
			fruits[6] = pacmanSpriteSheet.crop(586, 50, 12, 13);
			fruits[7] = pacmanSpriteSheet.crop(600, 49, 15, 15);

			teleporterSprite = pacmanSpriteSheet.crop(569, 128, 14, 14);

			fruitPoints = pacmanSpriteSheet.crop(456, 133, 15, 7);
			ghostPoints = pacmanSpriteSheet.crop(488, 148, 15, 7);

			pacmanDeath[0] = pacmanSpriteSheet.crop(505, 1, 13, 12);
			pacmanDeath[1] = pacmanSpriteSheet.crop(521, 1, 13, 12);
			pacmanDeath[2] = pacmanSpriteSheet.crop(537, 1, 13, 12);
			pacmanDeath[3] = pacmanSpriteSheet.crop(553, 2, 13, 12);
			pacmanDeath[4] = pacmanSpriteSheet.crop(569, 2, 13, 12);
			pacmanDeath[5] = pacmanSpriteSheet.crop(585, 3, 13, 12);
			pacmanDeath[6] = pacmanSpriteSheet.crop(601, 3, 13, 12);
			pacmanDeath[7] = pacmanSpriteSheet.crop(617, 3, 13, 12);
			pacmanDeath[8] = pacmanSpriteSheet.crop(633, 3, 13, 12);
			pacmanDeath[9] = pacmanSpriteSheet.crop(649, 3, 13, 12);
			pacmanDeath[10] = pacmanSpriteSheet.crop(665, 4, 13, 12);

			//Additional characters//I've spent too much time on this
			maidRight[0] = characterSpriteSheet.crop(150, 6, 24, 24);
			maidRight[1] = characterSpriteSheet.crop(172, 6, 24, 24);
			maidRight[2] = characterSpriteSheet.crop(150, 6, 24, 24);
			maidRight[3] = characterSpriteSheet.crop(194, 6, 24, 24);

			maidLeft[0] = characterSpriteSheet.crop(80, 6, 24, 24);
			maidLeft[1] = characterSpriteSheet.crop(102, 6, 24, 24);
			maidLeft[2] = characterSpriteSheet.crop(80, 6, 24, 24);
			maidLeft[3] = characterSpriteSheet.crop(125, 6, 24, 24);

			maidUp[0] = characterSpriteSheet.crop(222, 6, 24, 24);
			maidUp[1] = characterSpriteSheet.crop(253, 6, 24, 24);
			maidUp[2] = characterSpriteSheet.crop(222, 6, 24, 24);
			maidUp[3] = characterSpriteSheet.crop(284, 6, 24, 24);

			maidDeath[0] = characterSpriteSheet.crop(318, 10, 20, 20);
			maidDeath[1] = characterSpriteSheet.crop(346, 9, 21, 21);
			maidDeath[2] = characterSpriteSheet.crop(318, 10, 20, 20);
			maidDeath[3] = characterSpriteSheet.crop(346, 9, 21, 21);

			maidDown[0] = characterSpriteSheet.crop(4, 6, 24, 24);
			maidDown[1] = characterSpriteSheet.crop(30, 6, 24, 24);
			maidDown[2] = characterSpriteSheet.crop(4, 6, 24, 24);
			maidDown[3] = characterSpriteSheet.crop(56, 6, 24, 24);

			marioRight[0] = characterSpriteSheet.crop(153, 36, 20, 20);
			marioRight[1] = characterSpriteSheet.crop(174, 36, 20, 20);

			marioLeft[0] = characterSpriteSheet.crop(83, 36, 20, 20);
			marioLeft[1] = characterSpriteSheet.crop(104, 36, 20, 20);

			marioUp[0] = characterSpriteSheet.crop(224, 36, 20, 20);
			marioUp[1] = characterSpriteSheet.crop(256, 36, 20, 20);
			marioUp[2] = characterSpriteSheet.crop(224, 36, 20, 20);
			marioUp[3] = characterSpriteSheet.crop(286, 36, 20, 20);

			marioDown[0] = characterSpriteSheet.crop(6, 36, 20, 20);
			marioDown[1] = characterSpriteSheet.crop(33, 36, 20, 20);
			marioDown[2] = characterSpriteSheet.crop(6, 36, 20, 20);
			marioDown[3] = characterSpriteSheet.crop(57, 36, 20, 20);

			marioDeath[0] = characterSpriteSheet.crop(316, 36, 24, 24);
			marioDeath[1] = characterSpriteSheet.crop(346, 36, 24, 24);
			marioDeath[2] = characterSpriteSheet.crop(316, 36, 24, 24);
			marioDeath[3] = characterSpriteSheet.crop(346, 36, 24, 24);

			linkRight[0] = characterSpriteSheet.crop(150, 61, 24, 24);
			linkRight[1] = characterSpriteSheet.crop(174, 61, 24, 24);
			linkRight[2] = characterSpriteSheet.crop(150, 61, 24, 24);
			linkRight[3] = characterSpriteSheet.crop(198, 61, 24, 24);

			linkLeft[0] = characterSpriteSheet.crop(80, 61, 24, 24);
			linkLeft[1] = characterSpriteSheet.crop(102, 61, 24, 24);
			linkLeft[2] = characterSpriteSheet.crop(80, 61, 24, 24);
			linkLeft[3] = characterSpriteSheet.crop(126, 61, 24, 24);

			linkUp[0] = characterSpriteSheet.crop(222, 61, 24, 24);
			linkUp[1] = characterSpriteSheet.crop(254, 61, 24, 24);
			linkUp[2] = characterSpriteSheet.crop(222, 61, 24, 24);
			linkUp[3] = characterSpriteSheet.crop(284, 61, 24, 24);

			linkDown[0] = characterSpriteSheet.crop(4, 61, 24, 24);
			linkDown[1] = characterSpriteSheet.crop(31, 61, 24, 24);
			linkDown[2] = characterSpriteSheet.crop(4, 61, 24, 24);
			linkDown[3] = characterSpriteSheet.crop(56, 61, 24, 24);

			linkDeath[0] = characterSpriteSheet.crop(316, 61, 24, 24);
			linkDeath[1] = characterSpriteSheet.crop(346, 61, 24, 24);

			rosaRight[0] = characterSpriteSheet.crop(380, 61, 18, 24);
			rosaRight[1] = characterSpriteSheet.crop(411, 60, 17, 25);
			rosaRight[2] = characterSpriteSheet.crop(440, 61, 18, 24);
			rosaRight[3] = characterSpriteSheet.crop(469, 60, 20, 24);

			rosaLeft[0] = characterSpriteSheet.crop(379, 33, 18, 24);
			rosaLeft[1] = characterSpriteSheet.crop(409, 32, 17, 25);
			rosaLeft[2] = characterSpriteSheet.crop(439, 33, 18, 24);
			rosaLeft[3] = characterSpriteSheet.crop(469, 32, 20, 24);

			rosaUp[0] = characterSpriteSheet.crop(377, 90, 23, 23);
			rosaUp[1] = characterSpriteSheet.crop(407, 90, 23, 23);
			rosaUp[2] = characterSpriteSheet.crop(437, 90, 23, 23);
			rosaUp[3] = characterSpriteSheet.crop(467, 90, 23, 23);

			rosaDown[0] = characterSpriteSheet.crop(377, 7, 23, 22);
			rosaDown[1] = characterSpriteSheet.crop(407, 6, 23, 23);
			rosaDown[2] = characterSpriteSheet.crop(437, 7, 23, 22);
			rosaDown[3] = characterSpriteSheet.crop(467, 6, 23, 23);

			rosaDeath[0] = pacmanSpriteSheet.crop(584, 176, 16, 16);
			rosaDeath[1] = pacmanSpriteSheet.crop(600, 176, 16, 16);
			rosaDeath[2] = pacmanSpriteSheet.crop(584, 192, 16, 16);
			rosaDeath[3] = pacmanSpriteSheet.crop(600, 192, 16, 16);

			characters[0] = pacmanRight[0];
			characters[1] = maidDown[0];
			characters[2] = marioDown[0];
			characters[3] = linkDown[0];
			characters[4] = rosaDown[0];

			intro = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/intro.png"));
			start = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/startScreen.png"));



		}catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Images.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
