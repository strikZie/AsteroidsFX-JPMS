This is a project for SE4-KOM

<h1>Project</h1>

Project is the Asteroids game, with focus on component based software.

the project is structured with the main game implemented on the main branch, the main game is considered as both the intro lab, the GameLab, the JPMS1 lab and the JPMS2 lab,
then subsequent excersizes are implemented on relevant branches with name of the excersize in question.
all of the excersizes have the main game as the base

e.g. the JavaLab is implemented on the branch JavaLab-ServiceLoader

<h1>Current Excersize : SpringLab</h1>

The current branch is the implementation of the SpringLab where we use the Spring framework for dependency injection,

On this branch this is done on the GameEngine module

Wasn't sure if i was supposed to use autowiring for IEntityProcessors and others, so i first did it with serviceloader, 
then on the commit e16f3c5  : - serviceloader + autowiring, i change it to use autowiring, and services are annotated as @Component
