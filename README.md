# Rubik's Cube Solver
 
This is a program that is able to import the shuffled state of a real life Rubik's cube, render it on the screen and solve it. 

It does this with the following steps 
* Records a video of a mixed up Rubik's Cube with the camera's webcam 
* Converts this video into a 2D map of the cube's surface
* Translates this 2D map into a 3D cube that can be rendered on a computer screen with OpenGL
* Determines the steps needed to solve the cube from this scrambled state
* Returns the list of steps as well as carrying out these steps on the rendered Rubik's cube  
 
## Program in action 

Solving the cube at fast speed
https://user-images.githubusercontent.com/65402135/130063272-d113e056-9c51-4d48-9bce-4c7367998817.mp4


Solving the cube at maximum speed: 
https://user-images.githubusercontent.com/65402135/130062170-b5231156-accd-46a9-9ebc-91fac7400e9b.mp4



## How it was made 

This project was made using Java along with OpenGL for the rendering.

I also used Java's OpenCV library for the webcam video recording.

## How it solves the cube 

For all you Rubik's Cube nerds out there, I used a combination of the beginners method and CFOP method to solve the cube. 

These are the steps I used: 
* Cross
* First layer solve
* Second layer solve
* OLL
* PLL

I wanted to implement a straight CFOP solution using F2L but found this to be quite challenging and I unfortunately ran out of time.  

In English, my solution solves the cube in layers, first by solving the bottom layer, then the middle then the top layer. The bottom layer is solved by making a cross shape with the edge pieces and then filling in the rest of the corner pieces in the bottom. The middle layer is solved by filling in the remaining gaps with the edge pieces. Finally, the top layer is solved by orientating all the remaining pieces and then moving them around into their correct positions. 

For fun I also tried implementing neuro-evolutionary neural networks to see if they could make any progress but found the results to be too unpredicatable and so stuck to manually implementation.

## Why I made it and What I learnt

I made this project in the Christmas holidays after my OOP and Java university course, I wanted to try and implement some of the OOP concepts we had learnt in the course. The Graphics university course inspired me to create a program that included 3D object renders.

I have always loved solving Rubik's cubes and creating a program to solve them has always been an itch I've wanted to scratch, during this holiday I finally felt like I'd learnt all the skills I needed to take on this challenge. 

This is the largest project I had worked on at the time which allowed me to learn how to manage and structure large projects. The code also became complicated very quickly so learning how to effectively document my code along the way was another useful skill I learnt. 

I greatly enjoyed this project and would love to come back to it in the future to optimise and add more features to it. 
