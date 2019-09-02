
# Workout Buddy [![Heroku CI Status](https://workout-buddy-backend.herokuapp.com/last.svg)](https://dashboard.heroku.com/pipelines/e1f7a3c0-0785-4724-a0ee-f70cc044c9e8/tests)
Diet and training tracker backend application.

## Description
Workout Buddy project is digitial version of gym assistant, it helps you to keeps track of your progreess, both in diet and training, and motivates you to pursue your fitness goals. 

## Architecture
Code is following clean architecture principles. It takes advantage of Vavr's Either to control program flow, it uses immutable data structures and at its core, its divided into 3 layers: <br>
* Infrastructure - this is where all the I/O is implemented, so thats the place for HTTP endpoints, repositories and other ways to communicate with outer world.
* Domain - all business logic lays here, services that implements that logic are hidden inside packages that represents application domains. Entry point of each of that modules is facade, communication with external services is done via port interfaces. Also, this layer has no dependencies on framework, so its easy to swap Spring for something else. 
* Configuration - glue of previous layers, here is where domain facade beans are created, thats the module that establishes connection with database and manages security of application.

## Technologies
* Spring (Boot, Web, Security, Data)<br>
* MongoDB<br>
* Vavr<br>
* JUnit<br>

