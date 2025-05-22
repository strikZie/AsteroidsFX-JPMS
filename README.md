This is a project for SE4-KOM

<h1>Project</h1>

Project is the Asteroids game, with focus on component based software.

the project is structured with the main game implemented on the main branch, the main game is considered as both the intro lab, the GameLab, the JPMS1 lab and the JPMS2 lab,
then subsequent excersizes are implemented on relevant branches with name of the excersize in question.
all of the excersizes have the main game as the base

e.g. the JavaLab is implemented on the branch JavaLab-ServiceLoader

<h1>Current Excersize : MicroservicesLab</h1>

The current branch is the implementation of the MicroservicesLab where we use the Spring framework for starting a scorinsystem web microservice,
that holds a score value, that can be changed with a call to http://localhost:8080/score/add?point=1
