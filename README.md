cellsociety Project Plan
===========

Davis, Wenjun, Will


Introduction



Overview

Our primary goal is to make a super class which is extended by some specific subclasses.

Our main contains the following:

the creation of a grid, then the main timer/ update process , and the object calculations

A 2-D array is created to hold each node of the grid.






User Interface

-> use buttons for Settings
  
  Speed of Simulation
  Reset
  Start
  Step
  GridSize
  Stop/Pause
  Percentage of Fish vs Sharks
  Empty = white space
  Segragation Specific
    Similarity = Satisfaction
  Forest Specific
    Probability of catching fire
  Predator Specific
    Fish
      Breeding
    Shark 
      Breeding
      Feeding


  



Design Details 

Abstract Cell

--Variables: 

  currentState, futureState
  
  (uses enumeration: public enum state ON_FIRE, EMPTY, TREE)
  
  Point as location
  
  Methods
  
  prepareToUpdate()
  
  performUpdate()

  Constructor(point P)
  
  -> Segregation cell
  
  figuring out how to handle instantaneous updates
  
  (set a flag to indicate whether the block needs to be moved)
  
  -> Forest cell

  Update color based on surroundings
  
  -> Predator Cell
  
  implement an interface of eat,breed and move
  
  Shark Cell
  
    ->Breeding time
    
    ->Starving time
    
    ->movementlocations
    
  Fish Cell
  
    ->Breeding time
    
    ->movement locations
  
  Own Designed Cell
  
  
  
Grid Controller

  Contains 2D Array with cell objects
  
  Constructor 
  
  getCellAtPoint()
  
  getCellAroundPoint()
  
  getEmptyCellAroundPoint()
  
    the "around" isdefined differently for the three cases
  
  Grid updates all cells
  
    main controller handles periodic grid updates
    
    
    
    
  
  
  
  
  
Design Considerations 



Team Responsibilities

