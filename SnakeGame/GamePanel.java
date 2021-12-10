package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;

//    Size of one grid is the unit size
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
//   DELAY will tell how fast the snake is running.
    static final int DELAY = 75;

//    As snake cannot be bigger than game units. x co-ordinates and y co-ordinates of snake's body.
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

//    We will begin with 6 body parts of snake.
    int bodyParts = 6 ;
    int applesEaten = 0;
    int appleX;
    int appleY;
//    X and Y coordinates of snake.

//    R for right L for left U for up and D for down.
    char direction = 'R';
    boolean running = false;
//    At the beginning it will not be running. That's why boolean running is set to the value as false.
    Timer timer;
    Random random;
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.pink);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
//        When we start the game, we will call newApple method to create a new apple on the screen for us.
        newApple();

//        As it is running now so boolean running is set to be true.
        running = true;

        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics g){
//        This will paint our game to the set background color.
        super.paintComponent(g);
//        Lines will be visible only if draw(g) is called.
        draw(g);
    }

    public void draw(Graphics g) {
//        To form grids we are using for loop.
        if (running) {
//            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
////            To partition x-axis in unit size length lines from one unit size to whole of the game panel.
////            From x1 to x2 and y1 to y2. We will get vertical lines dividing x-axis.
//                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
////            To divide y-axis into UNIT_SIZE length lines. We will get horizontal lines dividing y- axis.
//                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//            }
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
//                Color for the head of the snake.
                    g.setColor(Color.BLACK);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
//                Color for the body of the snake.
                    g.setColor(Color.DARK_GRAY);
//                    To get multi-colored snake body.
//                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("Chiller",Font.BOLD,50));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score :" + applesEaten,(SCREEN_WIDTH-metrics.stringWidth("Score :" + applesEaten))/2,g.getFont().getSize());
        }
        else{
            gameOver(g);
        }
    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move(){
        for(int i = bodyParts; i > 0 ; i--){
//            We shift the coordinates of x-axis by 1 spot and same for y-axis.
            x[i] = x [i-1];
            y[i] = y [i-1];
        }
//        To change the direction where our snake is headed, we need to create switch
        switch (direction){
            case 'U' :
                y[0] = y[0]- UNIT_SIZE;
                break;
            case 'D' :
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L' :
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R' :
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }
    public void checkApple(){
        if((x[0]== appleX) && (y[0]== appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisions(){
        for (int i = bodyParts; i >0; i--) {
//            x[0] head of our snake and x[i] is body of the snake and same for y.
//            We are checking here if snake's head collides with its body.
            if(x[0]== x[i] && y[0] == y[i]){
                running = false;
            }
        }
//        Check if head touches left border.
        if(x[0]<0){
            running = false;
        }
//        Check if head touches right border.
        if(x[0]>SCREEN_WIDTH){
            running = false;
        }
        //        Check if head touches top border.
        if(y[0]<0){
            running = false;
        }
//        Check if head touches bottom border.
        if(y[0]>SCREEN_HEIGHT){
            running = false;
        }
        if(! running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
//        Game over text setup
        g.setColor(Color.black);
        g.setFont(new Font("Chiller",Font.BOLD,150));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over",(SCREEN_WIDTH-metrics1.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
        g.setColor(Color.BLACK);

        g.setFont(new Font("Chiller",Font.BOLD,130));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score :" + applesEaten,(SCREEN_WIDTH-metrics2.stringWidth("Score :" + applesEaten))/2,g.getFont().getSize());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
