We have used Stepwise refinement to divide the complete assignment into the following parts –  
1)	Algorithm Part (Strategy Pattern) 
2)		Action Menu (Chain of responsibility Pattern)  
3)			Shape option in Edit city properties (Decorator Pattern) 
4)				WorkSpace, WorkSpacePanel and Algorithm (Observer Pattern) 
			  
       Design decisions
       1) – Algorithm Part – We have used strategy pattern for the algorithm part, and we have the algorithm class which is like our controller where we use observer pattern to make this class receive the city list and update the route list. In the mainframe class, the user would click whichever algorithm they would like to run and that would set the algorithm running. We have the Algorithm interface to define our family of algorithms encapsulate each one and make them interchangeable.gn  Design decisions 
       2)  – Action Menu Part – We have implemented the chain of responsibility pattern for the different action options that are present in the Actions Menu bar. Each click will act an individual responsibility.  Design decisions
       3)   – EditCityProperties Part – We have implemented the decorator part for editing the shape of the city upon double click. We have used a decorator interface which has all the methods that need to be implemented in the child classes. The ConcreteDecorator class, Circle class, Square class, CirlceSquare class, CirlceMultipleRectangle class and MultipleRectangle class are used as part of this pattern to draw different shapes for the city when selected by the user.
