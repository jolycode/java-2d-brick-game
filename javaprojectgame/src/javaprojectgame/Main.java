package javaprojectgame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections; 
public class Main   extends JPanel implements KeyListener, ActionListener,MouseMotionListener,MouseListener{		
		private boolean play = false ;
		private int score = 0; //score 0
		private int lives = 3; //lives of player
		private int level = 1; 
		private int  totalBricks = 26;//26 for end game exactly when no bricks left
		private Timer timer;
		private int delay =1 ;
		private int playerX = 310;
		private int ballposX = 320;
		private int ballposY = 450;
		private int ballX = -1 ;
		private int ballY = -2;
		private brick map;
		String name;
		String inputscore;
		String date;
		int playmuve=0;
		ArrayList<String> totalscore = new ArrayList<String>();
		ArrayList<String> totalname = new ArrayList<String>();
		ArrayList<String> totaldate = new ArrayList<String>();
		int scoremuve;
		public static enum STATE{  // menu
			MENU,
			GAME,
			OPTIONS,
			ABOUT,
			SCORES,
			HELP,
			EXIT,
			LEVEL1,LEVEL2,LEVEL3};//levels
public static STATE state= STATE.MENU;
		public Main() {
			map=new brick(3,9);//set number of bricks on the map
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			timer = new Timer(delay,this);
			timer.start();
			addKeyListener(this); //for paddle
			addMouseListener(this);// add mouse listener for menu
			}
		private void background(Graphics g) { //set background
			g.setColor(Color.pink);
			g.fillRect(1,1,800,781);
		}
		public Rectangle playButton = new Rectangle(320,80,100,50);
		public Rectangle optionButton = new Rectangle(320,160,100,50);
		public Rectangle helpButton = new Rectangle(320,240,100,50);
		public Rectangle scoreButton = new Rectangle(320,320,100,50);
		public Rectangle aboutButton = new Rectangle(320,400,100,50);
		public Rectangle quitButton = new Rectangle(320,480,100,50);
		
		public void paint(Graphics g) {//set menu and all pazzles of gameplay
			try {
				music.sound1.play(); //music is work but when i muve key left or right its stops 
		if(state==STATE.GAME) {
			music.sound1.play();
			play(g); 
			}
		else if(state==STATE.OPTIONS) {
			music.sound1.play();
			options(g);}
		else if(state==STATE.SCORES) {
			music.sound1.play();
			Scores(g);}
		else if(state==STATE.ABOUT) {
			music.sound1.play();
			about(g);}
		else if(state==STATE.HELP) {
			music.sound1.play();
			help(g);}
		else  if(state==STATE.MENU) { //main menu
			Graphics2D g2d=(Graphics2D)g;
			background(g);
			boarders(g);
			g.setColor(Color.black);
			g.setFont(new Font("serif",Font.BOLD,50));
			g.drawString("Menu Game",250,50);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("New Game", playButton.y+245, playButton.y+30);
			g2d.draw(playButton);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Options", optionButton.y+180, optionButton.y+30);
			g2d.draw(optionButton);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Scores", scoreButton.y+20, scoreButton.y+30);
			g2d.draw(scoreButton);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Help", helpButton.y+100, helpButton.y+30);
			g2d.draw(helpButton);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("About", aboutButton.y-60, aboutButton.y+30);
			g2d.draw(aboutButton);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Exit", quitButton.y-130, quitButton.y+30);
			g2d.draw(quitButton);
		}
		else if(state==STATE.LEVEL1) {
			music.sound2.play();
			play(g);}
		else if(state==STATE.LEVEL2) {
			music.sound2.play();
			play2(g);}
		else if(state==STATE.LEVEL3) {
			music.sound2.play();
			play3(g);}
		}
		catch (Exception error) {
			System.err.println("Error occured");
			error.printStackTrace();}
		}
		private void about(Graphics g) { //about
			background(g);
			boarders(g);
			g.setColor(Color.black);
			g.setFont(new Font("serif",Font.BOLD,40));
			g.drawString("About:",280,100);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Made by: Danylo Fedorov",250,250);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("School ID:20180702091",250,300);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Mail: danylo.fedorov@std.yeditepe.edu.tr",250,350);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Version 0.001",250,400);
		}
		private void Scores(Graphics g) { //score
			try {
			background(g);
			boarders(g);
			g.setColor(Color.black);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Your Best Scores bellow:",250,100);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Name:"+totalname+"Score:"+totalscore+"Date:"+totaldate,100,250);
			g.setFont(new Font("serif",Font.BOLD,20));}
			catch (Exception error) {
				System.err.println("Score table is empty");
				error.printStackTrace();}
		}
		public Rectangle level1 = new Rectangle(80,150,100,50); 
		public Rectangle level2 = new Rectangle(80,300,100,50);
		public Rectangle level3 = new Rectangle(80,450,100,50);
		private void options(Graphics g) { //options
			background(g);
			boarders(g);
			Graphics2D g2d=(Graphics2D)g;
			g.setColor(Color.black);
			g.setFont(new Font("serif",Font.BOLD,40));
			g.drawString("Options",300,50);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Choose your level game:",280,100);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("level 1", playButton.y+20, playButton.y+100);
			g2d.draw(level1);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("level 2", optionButton.y-60, optionButton.y+170);
			g2d.draw(level2);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("level 3", scoreButton.y-215, scoreButton.y+165);
			g2d.draw(level3);
		}
		private void help(Graphics g) { //help
			music.sound1.play();
			background(g);
			boarders(g);
			g.setColor(Color.BLACK);
			g.setFont(new Font("serif",Font.BOLD,35));
			g.drawString("HELP",300,50);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("To muve LEFT press this button '<'",80,150);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("To muve RIGHT press this button '>'",80,250);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("To  exit press 'CTRL+Q'",80,350);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("To return to the Menu press 'CTRL+X'",80,450);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("To muve PADDLE use KEYBOARD,to use MENU use MOUSE",80,550);
			g.setFont(new Font("serif",Font.BOLD,25));
		}
		public void play(Graphics g) { //play field,repaint all pazzles after win of lose
			background(g);
			boarders(g);
			drmp(g);
			paddle(g);
			score(g);
			lvl(g);
			live(g);
			gamelost(g);
			ball(g);	
			gamewon(g);
		}
		private void drmp(Graphics g) {//draw brick map
			map.draw((Graphics2D)g);	
		}
		public void play2(Graphics g) { ///level 2
			background(g);
			boarders(g);
			drmp(g);
			paddle(g);
			score(g);
			g.drawString("Level 2",340,20);
			live(g);
			gamelost(g);
			ball(g);	
			gamewon(g);
			repaint();	
		}
		public void play3(Graphics g) {///level 3
			background(g);
			boarders(g);
			drmp(g);
			paddle(g);
			score(g);
			g.drawString("Level 3",340,20);
			live(g);
			gamelost(g);
			ball(g);	
			gamewon(g);
		}
		private void lvl(Graphics g) { //level on screen lvl 1
			g.drawString("Level:"+level,340,20);
		}
		
		private void live(Graphics g) { //lives on screen
			g.drawString("Lives:"+lives,700,20);
		}
		private void boarders(Graphics g) { //boarders of window
			g.setColor(Color.black);
			g.fillRect(0,0,3,783);
			g.fillRect(0,0,800,3);
			g.fillRect(783,0,3,783);
		}
		private void paddle(Graphics g) { //paddle
			g.setColor(Color.black);
			g.fillRect(playerX,590,100,25);
		}
		private void ball(Graphics g) { //oval ball
			g.setColor(Color.white);
			g.fillOval(ballposX,ballposY,25,25);}
		  private void score(Graphics g) { //score
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Score:"+score,15,25);
		  }
		  private void gamelost(Graphics g) { //detect if game is lose
			  if(ballposY>600  ) { //if ball go out of range of game,-live
				  score=0;  //score set to 0 after each lose
				  music.sound3.play();
				 reset(g);} //reset
		  	else if(lives <1){ //if no lives left,back to menu and ask for data
		  		music.sound3.play(); ///little bag,need to muve buttons to back to menu
		  		play=false;
				g.setColor(Color.RED);
				g.setFont(new Font("serif",Font.BOLD,30));
				g.drawString("Game is lost!",250,340);
				g.setFont(new Font("serif",Font.BOLD,20));
				g.drawString("Press 'CTRL+X' to Return to menu",250,380);
				g.setFont(new Font("serif",Font.BOLD,20));
				name=JOptionPane.showInputDialog("Enter your name please: ");
				inputscore=JOptionPane.showInputDialog("Enter your score please: ");
				date=JOptionPane.showInputDialog("Enter todays date please: ");
				totalname.add(name);
				totalscore.add(inputscore);
				totaldate.add(date);
				resetevrything(g);
				Main.state=Main.STATE.MENU;
				paint(getGraphics());
				System.out.println("No lives left");
		  	}}
				//music.sound1.stop();}
		  private void reset(Graphics g) { //reset all pazzles to initial places
			  	play=false;
				ballX=0;
				ballY=0;
				lives-=1;
				ballposX = 320;
				ballposY = 450;
				ballX=-1;
				ballY=-2;
				playerX=310;
				totalBricks =27;
				map=new brick(3,9);
				repaint();		
		  } 
		  private void resetevrything(Graphics g) { //reset evrything pazzles to initial places
			  	play=false;
				ballX=0;
				ballY=0;
				lives=3;
				score=0;
				ballposX = 320;
				ballposY = 450;
				ballX=-1;
				ballY=-2;
				playerX=310;
				totalBricks =26;
				map=new brick(3,9);
				repaint();		
		  } 
		 private void gamewon(Graphics g) { //if no brick left game is won,ask for data and set to next level,if no level back to menu
			   if(totalBricks<0){	//little bag,need to muve keys after win to get to next level
				  music.sound2.play();
				  play = false;
				  g.setColor(Color.RED);
				  g.setFont(new Font("serif",Font.BOLD,30));
				  g.drawString("Game is won!",250,340);
				  g.setFont(new Font("serif",Font.BOLD,30));
				  g.setFont(new Font("serif",Font.BOLD,20));
				  g.drawString("Press 'CTRL+X' to Return to menu",250,380); 
				  name=JOptionPane.showInputDialog("Enter your name please: ");
				  inputscore=JOptionPane.showInputDialog("Enter your score please: ");
				  date=JOptionPane.showInputDialog("Enter todays date please: ");
				  totalname.add(name);
				  totalscore.add(inputscore);
				  totaldate.add(date);
				  repaint();
				  playmuve+=1;
				  resetevrything(g);
				  if(playmuve==1) {
					  Main.state=Main.STATE.LEVEL2;
					System.out.println("LEVEL2");
					  music.sound2.play();
					paint(getGraphics());}
				  else if(playmuve==2) {
					  Main.state=Main.STATE.LEVEL3;
					  music.sound3.play();
					  System.out.println("LEVEL3");
					  paint(getGraphics());}
				  else {
					  resetevrything(g);
					  Main.state=Main.STATE.MENU;
					  paint(getGraphics());
					  System.out.println("No levels left");}}	 
		  }
		@Override
		public void actionPerformed(ActionEvent e) { //detect if ball intersects with paddle and bricks
			try {
			timer.start();
			if(play) {
				if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,535,85,10))) {
					ballY=-ballY;}//intersection with paddle
		      for(int i=0;i<map.map.length;i++) {
					for(int j=0;j<map.map[0].length;j++) {
						if(map.map[i][j]>0) {
							int bX=j*map.Width+90;
							int bY=i*map.Height+100;
							int Wigth = map.Width;
							int Height = map.Height;
							Rectangle rect = new Rectangle(bX,bY,Wigth,Height);
							Rectangle ballr= new Rectangle(ballposX,ballposY,20,20);
							if(ballr.intersects(rect)) {//if intersect then ...
								map.brickmap(0, i, j);
								totalBricks--; //at evry hit total bricks dicrease,score insreases by 100
								score +=100;
								if(ballposX+20<=rect.x || ballposX+1>=rect.x+rect.width) {//left and right intrsct of ball
									ballX=-ballX;} //velocity of ball after hit 
								else {
									ballY = -ballY;}
								break;}}}}
				ballposX += ballX;
				ballposY += ballY;
				if(ballposX<0) {//left boarder
					ballX=-ballX;}//opposite velocity of ball after hit
				if(ballposY<0) {//top boarder
					ballY=-ballY;}
				if(ballposX>670) {//right boarder
					ballX=-ballX;}
			repaint();}}
			catch (Exception error) {
				System.err.println("Error occured");
				error.printStackTrace();}
			}
		@Override
		public void keyReleased(KeyEvent e) {
		}
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override	
		public void keyPressed (KeyEvent e ){
			try {
		
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) { //muve right
				if(playerX>=700) 
					playerX=700;
				else {
					play=true;
					playerX+=25;}
			}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) { //muve left
			if(playerX<10) 
				playerX=10;
			else {
				play=true;
				playerX-=25;}
		}
		if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q) { //quit of game
			System.exit(1);
            }
		if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_X) { 
			Main.state=Main.STATE.MENU;
			paint(getGraphics());
			resetevrything(getGraphics());
		}}	
		catch (Exception error) {
			error.printStackTrace();}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			System.out.println("Dragged");	//just checking if user Dragged  mouse
		}
		@Override
		public void mouseMoved(MouseEvent e) {//just checking if user Moved  mouse
			System.out.println("Moved");	
		}
		@Override
		public void mouseClicked(MouseEvent e) {//just click if user Moved  mouse
			System.out.println(e.getX()+", "+e.getY()+" clicked");	
		}
		@Override
		public void mousePressed(MouseEvent e) { //detect mouse press 
			try {
			if(state==STATE.ABOUT||state==STATE.HELP||state==STATE.MENU||state==STATE.OPTIONS||state==STATE.SCORES||state==STATE.GAME) {
			int mx= e.getX();
			int my= e.getY();
			if(mx>=320 && mx <= 420) {
			if(my>=80 && my<=140) {
				Main.state=Main.STATE.GAME;
				paint(getGraphics());
				System.out.println("NEW GAME");}
				if(my>=160 && my<=200) {
					Main.state=Main.STATE.OPTIONS;
					paint(getGraphics());}
			if(mx>=320 && mx <= 420) {
				if(my>=240 && my<=300) {
					Main.state=Main.STATE.HELP;
					System.out.println("HELP");
					paint(getGraphics());}
				if(my>=320 && my<=380) {
					Main.state=Main.STATE.SCORES;
					System.out.println("SCORES");
					paint(getGraphics());}	
				if(my>=400 && my<=470) {
					Main.state=Main.STATE.ABOUT;
					System.out.println("ABOUT");
					paint(getGraphics());}
				if(my>=480 && my<=530) {
					System.exit(1);
					System.out.println("EXIT");}
		}}
		if(mx>=80 && mx <= 180) {
			if(my>=150 && my<=250) {
				Main.state=Main.STATE.LEVEL1;
				System.out.println("LEVEL1");
				paint(getGraphics());}
			if(my>=300 && my<=420) {
				Main.state=Main.STATE.LEVEL2;
				System.out.println("LEVEL2");
				paint(getGraphics());}
			if(my>=450 && my<=520) {
				Main.state=Main.STATE.LEVEL3;
				System.out.println("LEVEL3");
				paint(getGraphics());}}}}
			  catch (Exception error) {
					System.err.println("Error occured");
					error.printStackTrace();}
		System.out.println(e.getX()+", "+e.getY()+" Pressed");	
		}

		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
		public static void main(String[] args) { //main body,we add gameplay to jframe,we set bounds and set it visible
		 try {
			 JFrame frame = new JFrame();
		     Main g = new Main();
		     frame.setResizable(false);
		     frame.setBounds(0,0,800,640);
		     frame.setTitle("Arkanoid game");
		     frame.add(g);
		     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		     frame.setVisible(true);}
		  catch (Exception e) {
			System.err.println("Error occured");
            e.printStackTrace();}
		}
		
}