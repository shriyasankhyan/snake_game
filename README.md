# Snake Game
●	Snake game creating using Java. 
In this, you need to eat maximum apples without touching the boundaries or snake’s body itself.

I have this game divided into 3 java classes -> GameFrame.java , SnakeGame.java and GamePanel.java.

</hr>
</br>
**SnakeGame**
</br>
Snake game is used to run the function as it has main method.
In Snake game , GameFrame frame = new GameFrame(); , we have instantiated a new GameFrame object in our SnakeGame class and called it frame.

</hr>
</br>
**GameFrame**
</br>
In this class , we have imported javax.swing.* 

Java Swing -> It is a part of Java Foundation Classes and it is a lightweight Java graphical user interface (GUI) widget toolkit that includes a rich set of widgets.It is used to create window-based applications which makes it suitable for developing lightweight desktop applications. Java Swing is built on top of an abstract windowing toolkit API purely written in Java programming language.It provides interfaces that enable the development of input methods that can be used with any Java runtime environment. 

As we have imported javax.swing.* ,so we can use JFrame now. We have initialised the GameFrame constructor and in this , we have created a new instance of GamePanel and named it pannel.
GamePanel panel = new GamePanel();

By doing this.add(panel), we have added panel in GameFrame. Title has been set to "Snake" by this.setTitle("Snake"). For closing the game panel , we have used  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) as the default function of the JFrame is to hide the panel when you click the X button , so we need to add the exit function manually by adding this line.

this.setResizable(false); This ensures that a graphical interface looks the way you intend and it prevents the user from re-sizing it.

this.pack(); It sizes the frame so that all its contents are at or above their preferred sizes.

this.setVisible(true);
setVisible(true) is a blocking operation and blocks until dialog is closed. So one dialog pops up and app blocks on setVisible(true) when you close it, another setVisible(true) is invoked and so on.

this.setLocationRelativeTo(null); It centers the GUI on th screen.

All the above methods used are called inside the GameFrame constructor which is extending JFrame for creating a frame so that we do not need to do much and the Swing JFrame does it all for us.
