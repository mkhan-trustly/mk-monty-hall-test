# crm-monty-hall-test

Ett förberett projekt baserat på create-react-app och spring initializr.

#### Systemkrav

[Maven](https://maven.apache.org/)<br>
[Java11](https://openjdk.java.net/projects/jdk/11/)<br>
[NodeJS](https://nodejs.org)<br>
<br>

#### Starta frontend
```
cd frontend
npm install
npm start
```

#### Starta backend
```
cd backend
mvn install
java -jar target/monty-hall-0.0.1-SNAPSHOT.jar   
```

#### Verifikation
React appen har en komponent som pollar spring boot appens health endpoint och skriver ut svaret. Svaret ska vara "UP" om allt funkar som det ska.

#### Backend tjänst
```
http://localhost:8080/api/v1/game/simulate?iterations=10&playerStrategy=SWITCH_DOOR
http://localhost:8080/api/v1/game/simulate 
(obs: default iteration=1 och playerStrategy=STICK_TO_INITIAL_DOOR)
```
Tjänsten levererar en simulationsrapport:
```
{
    host: "Monty Hall",
    nbrOfSimulations: 1,
    selectedStrategy: "STICK_TO_INITIAL_DOOR",
    won: 0,
    lost: 1,
    winsInPercent: 0,
    lostInPercent: 100
}
```

#### Test
```
GameSessionTest.java
AppliationExceptionHandler.java
```





