# Rubik's Cube Solver

This program solves a Rubik's cube, and renders the solution in OpenGL (Java's graphics rendering library). 

The program takes in a video of a real-life scrambled Rubik's cube, converts this into a 3D render of the cube, and shows the steps required to solve the cube in a rendered simulation. 

I built both the Rubik's cube solver and simulation model from scratch! 

Video of Rubik's Cube -> 3D render of the cube -> 3D simulation solve of the cube

### Program in action 

**Video of real-life Rubik's cube:**

<img src="https://user-images.githubusercontent.com/65402135/130064639-f770fbc2-ef6a-4dfe-b405-38010981c01d.mp4" width=10% height=10%>

**Slow speed solve:**

<img src="https://user-images.githubusercontent.com/65402135/130063272-d113e056-9c51-4d48-9bce-4c7367998817.mp4" width=10% height=10%>

**High speed solve:** 

<img src="https://user-images.githubusercontent.com/65402135/130062170-b5231156-accd-46a9-9ebc-91fac7400e9b.mp4" width=10% height=10%>

### How does it work 

For all you Rubik's Cube nerds out there, I used a combination of the beginners method and CFOP method to solve the cube. 

These are the steps I used: 
* Cross
* First layer solve
* Second layer solve
* OLL
* PLL

I wanted to implement a straight CFOP solution using F2L, but I found this to be quite challenging and unfortunately ran out of time.  

In English... this solution solves the cube in layers, starting at the bottom layer and working its way up. The first three steps are solved using search algorithms with hand-crafted hueristics/tactics, and the final two steps are solved through algorithmic pattern matching.

For fun I also tried implementing neuro-evolutionary neural networks to see if they could make any progress... but unfortunately found the results to be too unpredicatable. 

Trying to derive algorithms to solve a Rubik's cube (without help from the internet) was a very fun challenge!
