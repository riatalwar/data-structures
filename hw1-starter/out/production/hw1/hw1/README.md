# Homework 1 Discussion

## Part I: Object-Oriented Programming Concepts

1. **Class**:
FlappyBox and Pipe are examples of classes, as they are templates that are used to instantiate objects in other segments of the program.

2. **Object**:
The flappyBox variable in the Game class is an example of an object, as it is an instance of the FlappyBox class.

3. **Encapsulation**:
Encapsulation is when processes are enclosed within a specific function so that they can be used repeatedly and improve code readability. For example, the moveGameObjects() function in the Game class encapsulates the process of moving the pipes and FlappyBox so that the runGameLoop() function can call it without having to write all the code within the function.

4. **Abstraction**:
Abstraction is when the details of a certain process are removed from focus so that it is not necessary to understand how they work to use them. For example, the StdDraw class and all its member function are abstracted away so that we can use its functionality without knowing the details of why it works.

5. **Data type**:
Data types are different types of information that can be stored in variables, and a variety are used in this project. For example, the Game class uses the Integer (int) and Boolean data types to store the score and game over status.

6. **Composite data type**:
A composite data type is comprised of other data types. For example, the Game class contains a list of Pipes, a FlappyBox, and an integer and a boolean as its member fields, making it a composite data type.

7. **Method**:
A method is a set of instructions grouped together under a specific name. For example, the handleCollisions() method in the Game class instructs the program to look at every pipe and determine if they intersect the flappyBox object.

8. **Constructor**:
A constructor is used to instantiate an object by setting initial values for the member fields of a class. The constructor for the Box class sets initial values for the x and y coordinates, along with the color and size of the box.

9. **Instance variable**:
An instance variable is an attribute of an object. For example, flappyPassedThisPipe is an instance variable of the Pipe class, and it is unique for each instance of the class. This means that it can be true for some instances while being false for others.

10. **Local variable**:
A local variable is a variable that can only be used within a specified scope. For example, the pipe variable in the handleCollisions() function within the Game class is a local variable that can only be used within the loop that is in the funtion. Attempting to access it elsewhere would raise errors, since it does not exist outside of that scope.

11. **Parameter**:
A parameter is an input variable to a function. In the FallingBox class, the setFallingSpeed(double fallingSpeed) function takes a double as a parameter to the function, which it uses to set the falling speed of the object. Some functions, like getFallingSpeed() in the same class, do not take any parameters.

12. **Return type**:
The return type of a function is the type of the value it outputs. For example, the FlappyBox class has a function getJumpVelocity with a double return type. However, it as another function setJumpVelocity with a void return type, meaning that the function does not return anything.

13. **Inheritance**:
Inheritance is where a class is defined as a subclass of another class so that it can inherit some of its attributes and functionality. One example of this is the FlappyBox class, which is a subclass of the FallingBox class. This means that FlappyBox can use the FallingBox constructors and functions such as move() that are defined in FallingBox.

14. **Type Hierarchy**:
A type hierarchy is when there is a chain of classes that extend each other. For example, we have a long type hierarchy where FlappyBox extends FallingBox extends Box extends Sprite extends Block.

15. **Apparent type**:
The apparent type of an object is given by the type of the variable where it is stored, and it helps define what an object is allowed to do. For example, in the BlockTest class a TestBlock object is assigned to a Block variable. Though a TestBlock object is initialized, the apparent type is Block because it is stored in a Block variable.

16. **Actual type**:
The actual type of an object is the type of an object behind the scenes in the program, and it determines the behavior of an object. The same example as #15 can be used to show the actual type of an object, as TestBlock is the actual type of the object, even though it outwardly shows as a Block.

17. **Is-a relationship**:
An is-a relationship occurs when one class is a subclass of another. For example, FlappyBox is a subclass of FallingBox, so it is a FallingBox.

18. **Has-a relationship**:
A has-a relationship is when one type is one of the member fields of another. For example, Game has a member FlappyBox, so Game has a FlappyBox.

19. **Method overloading**:
Method overloading is when a class contains multiple implementations of the same function. For example, the clear() function in the StdDraw class is overloaded, as there are two versions of the function, one with no inputs and one with a Color input. The functions can have the same, but the slightly different parameters require overloading so that calling clear() applies in a greater variety of situations.

20. **Method overriding**:
Method overriding occurs when a subclass creates its own implementation of a method that exits in its superclass. An example of this is shown in the move function in FallingBox, which extends Box, which extends Sprite. Sprite contains an implementation of move, but FallingBox overrides this so that it can have a unique implementation of the method.

21. **Static polymorphism**:
Static polymorphism is when one superclass has multiple subclasses. For example, both Pipe and FallingBox extend Box, so box has multiple different forms. This means that, for example, you could store either a Pipe or a FallingBox in a Box variable.

22. **Dynamic polymorphism**:
Dynamic polymorphism is when the behavior of an object is determined by its actual type on runtime. For example, the intersects function has different implementations for the FlappyBox and Pipe classes, as Pipe overrides the inherited function to implement a version that works for the Pipe object. The program determines which version of the function is called at runtime, determining the behavior of the object.

## Part II: Data Structures
The Game class uses a List data structure, allowing it to store a series of pipes that cross the screen. Using a List in this case allows the recyclePipes() function to easily add and remove pipes from the list. Since the indices automatically shift unlike an array, it is practical to use a List when the program is regularly adding and removing elements at different indices.
The Pipe class uses an Array instead of a list to store a series of Boxes that make up the pipe. In this case, an Array is a practical choice because the quantity and positions of the boxes do not change after initialization. This removes the more costly flexibility of list sizing while preserving easy access to each individual element.

## Part III: Algorithm Example
The updateScore() function in the Game class is an example of an algorithm important to the Game's design, as it allows the score of the player to be easily recorded. This function contains a series of instructions to check every pipe, determine if the pipe has been passed, and if the passing has been recorded. It utilizes several getter functions to determine the positions of the pipes and box along with the setFlappyPassedThisPipe() function. This allows the program to record the score when necessary, and continue on if the points have already been counted.