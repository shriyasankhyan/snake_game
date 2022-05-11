# Snake Game
●	Snake game creating using Java. 
In this, you need to eat maximum apples without touching the boundaries or snake’s body itself.

I have this game divided into 3 java classes -> GameFrame.java , SnakeGame.java and GamePanel.java.

## SnakeGame
#### Class
Snake game is used to run the function as it has main method.
In Snake game , GameFrame frame = new GameFrame(); , we have instantiated a new GameFrame object in our SnakeGame class and called it frame.

## GameFrame
#### Imports
In this class , we have imported javax.swing.* 

Java Swing -> It is a part of Java Foundation Classes and it is a lightweight Java graphical user interface (GUI) widget toolkit that includes a rich set of widgets.It is used to create window-based applications which makes it suitable for developing lightweight desktop applications. Java Swing is built on top of an abstract windowing toolkit API purely written in Java programming language.It provides interfaces that enable the development of input methods that can be used with any Java runtime environment. 

#### Constructor
As we have imported javax.swing.* ,so we can use JFrame now. We have initialised the GameFrame constructor and in this , we have created a new instance of GamePanel and named it pannel.
GamePanel panel = new GamePanel();

The this keyword refers to the current object in a method or constructor. The most common use of the this keyword is to eliminate the confusion between class attributes and parameters with the same name.

By doing this.add(panel), we have added panel in GameFrame. Title has been set to "Snake" by this.setTitle("Snake"). For closing the game panel , we have used  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) as the default function of the JFrame is to hide the panel when you click the X button , so we need to add the exit function manually by adding this line.

this.setResizable(false); This ensures that a graphical interface looks the way you intend and it prevents the user from re-sizing it.

this.pack(); It sizes the frame so that all its contents are at or above their preferred sizes.

this.setVisible(true);
setVisible(true) is a blocking operation and blocks until dialog is closed. So one dialog pops up and app blocks on setVisible(true) when you close it, another setVisible(true) is invoked and so on.

this.setLocationRelativeTo(null); It centers the GUI on th screen.

All the above methods used are called inside the GameFrame constructor which is extending JFrame for creating a frame so that we do not need to do much and the Swing JFrame does it all for us.

## GamePanel
#### Imports
In this class too, we have imported javax.swing.* same as GameFrame.
import java.awt.* -> It contains all of the classes for creating user interfaces and for painting graphics and images.
import java.awt.event.* -> It provides interfaces and classes for dealing with different types of events fired by AWT components and it defines a contract between user-interface components and an assistive technology that provides access to those components.
import java.util.Random -:> By this, we can make instances of the Random class.This class provides several methods to generate random numbers of type integer, double, long, float etc. Random number generation algorithm works on the seed value.A seed value specifies a particular stream from a set of possible random number streams.


#### Class
This class extends JPanel class and implements Action Listener interface.
JPanel -> The JPanel is a simplest container class. It provides space in which an application can attach any other component. It inherits the JComponents class.
Action Listener -> The listener interface is for receiving action events. The class that is interested in processing an action event implements this interface, and the object created with that class is registered with a component, using the component's addActionListener method.

We have set the screen height and screen width.
static final int SCREEN_WIDTH = 600;
static final int SCREEN_HEIGHT = 600;
We use static in java to define a method accessible in whole of the program and final keyword is used so that the value of screen width and screen height is not changed afterwards by anybody.

Size of one grid is the unit size
static final int UNIT_SIZE = 25;

Total number of game units = surface area of the screen multiplied by a single unit size.
static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;

DELAY will tell how fast the snake is running. If we increase the DELAY , the snake will move slow. 
static final int DELAY = 75;

This defines the array of snake's body co-ordinates. Here, x[] stands for the array of x-coordinates of snake's body and y[] stands for the array of y-coordinates of snake's body. As snake cannot be bigger than game units. x co-ordinates and y co-ordinates of snake's body.
final int x[] = new int[GAME_UNITS];
final int y[] = new int[GAME_UNITS];

We will begin with 6 body parts of snake. We can specify any number of body parts here. It will specify the intial number of units snake's body occupies when we begin the game.
int bodyParts = 6 ;

Initially , the score is zero and snake has not eaten any apple. So applesEaten variable is set to zero.
int applesEaten = 0;

X and Y coordinates of the apple generated.
int appleX;
int appleY;

Initially , the snake starts moving in the right direction. In this , we have specified directions as - 
R -> right, L -> left, U -> up and D -> down.
char direction = 'R';

In the beginning, game will not be running. That's why boolean running is set to the value as false.
boolean running = false;

An object of timer and random is made.
Timer timer;
Random random;

#### Constructor
A constructor of GamePanel is made in which following methods are called.
A new Random instance of Random class in initiated.
random = new Random();

Size is set to the dimensions we specified as SCREEN_WIDTH as width and SCREEN_HEIGHT as height.
this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));

Background color is set to pink.
this.setBackground(Color.pink);

Based on the Java API, setFocusable method is located in the Component class, the super class of JPanel. It sets the focusable state of this Component to the specified value. This value overrides the Component's default focusability.
this.setFocusable(true);

By using addKeyListener, the Java KeyListener is notified whenever you change the state of key. In this, we have added a new instance of MyKeyAdapter class, which is the implementation of callbacks for the key events. It reports all regular key presses, the four arrow keys, and some other special keys (backspace, delete, escape, page up, page down, home, end, tab, fkeys). It ignores other key pressed events, key released events, etc. All methods of this class are empty.
this.addKeyListener(new MyKeyAdapter());

startGame method is called.
startGame();

#### Methods
##### startGame
When we start the game, we are calling the newApple method.
newApple();

running = true; Running is set to true.

New instance of timer object is created and started.
Timer is passed 2 arguments DELAY to specify the delay and this to specify the task to be performed.
timer = new Timer(DELAY,this);
timer.start();

##### paintComponent
This method takes an instance (We have named it 'g') of the Graphics object as an argument.A Graphics object encapsulates state information needed for the basic rendering operations that Java supports.This will paint our game to the set background color.

The super keyword refers to parent class objects. It is used to call superclass methods, and to access the superclass constructor. The invocation of super.paintComponent(g) passes the graphics context off to the component's UI delegate, which paints the panel's background.
super.paintComponent(g);

draw method is called in which g is passed as an argument.
draw(g);

##### draw
If the game is running , 
g.setColor(Color.red); -> Color is set to red
g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE) -> Fill an oval at appleX and appleY coordinates of UNIT_SIZE width and height

Then a for loop is started for coloring the body parts of the snake. When i = 0 , we can set the head of the snake. In this, we have set the head color to black and filled the whole grid by using the method fillRect to fill the rectangle at coordinates x[0] and y[0] with UNIT_SIZE as width and height.
Else , when i is other than 0 , we set the color of the snake's body to dark gray and again filled a grid box at x[i] and y[i] with UNIT_SIZE as width and  height.

**Setting the scoreboard**
We set the color of font to black , font type to Chiller , text is bold and font size is 50.
The FontMetrics class is used to return the specific parameters for a particular Font object. An object of this class is created using the getFontMetrics() method.g.getFont is passed in this method.
Then a string is drawn by using g.drawString to show the score.

Then else statement is used which indicates if the game is not running, method known as gameOver which has g as an argument is called.

##### newApple
In this method , we have initiated random numbers for the x and y coordinates of the apple.
For x coordinate , we have parsed SCREEN_WIDTH/UNIT_SIZE into int and generated a random number which is then multiplied by UNIT_SIZE to give us the number of units available for the x coordinate of apple.
For y coordinate , we have parsed SCREEN_HEIGHT/UNIT_SIZE into int and generated a random number which is then multiplied by UNIT_SIZE to give us the number of units available for the y coordinate of apple.

##### move
In this , we make the snake move from one position to another by changing it's bodyParts indices. If U is pressed, all of the snake's body moves in the up direction , D for down , R for right and L for left.

##### checkApple
If the head of the snake moves to the coordinates of the apple, snake's bodyParts increases by 1 and newApple method is called which will change the coordinates of the apple to be generated.

##### checkCollisions
In this method, we check whether the snake collides with its own body or the borders of the game panel. If it does, then the timer is stopped and game is over

##### gameOver
When the game is over, scoreboard is displayed in the specified font color and size.

##### actionPerformed
If the game is running , move , checkApple and checkCollisions method is called, otherwise the game screen is repainted.

#### MyKeyAdapter
This class extends KeyAdapter and it specifies the keys for which we are using KeyAdapter. When the specified keys are performed, functions are performed.


