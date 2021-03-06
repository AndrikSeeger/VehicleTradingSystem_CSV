<!--Copyright Andrik Seeger 2022-->

# Vehicle Trading System (CSV)
This PoC-like project is the development of a new platform for POSIX-like systems to buy, sell and trade vehicles using ads solely written in Java using CSV-Files as a backup system with high security standards.
The functionality is very similar to popular car trading sites like cars.com or mobile.de. Since this is development focuses on the backend functionality there is no graphical user interface. 
The interface uses the terminal on POSIX-like operating systems for user input and output.

Currently there is a second, further developed version available using the Postgres-Database instead of CSV for storage.
You can find the repository <a href="https://github.com/AndrikSeeger/VehicleTradingSystem_POSTGRES" target="_blank">here</a>.

## Language
Since the usage of the project was initially planned as exclusive for the German car market the documentation is also in German.
For further information and explanations in English please contact me.

## Startup
On POSIX-like operating systems (macOS & most linux distributions) you can simply run the Execute.sh-Script in your preferred console.
There currently is no Windows-version with CSV-storage available since the <a href="https://github.com/AndrikSeeger/VehicleTradingSystem_POSTGRES" target="_blank">Postgres-version</a> is developed for Windows.

The initial menu looks like this:
<p align="center">
<img src="https://raw.githubusercontent.com/AndrikSeeger/VehicleTradingSystem_CSV/master/Ressources/Startup.png"/>
</p>

## Usage
The usage of the program is pretty straight forward using numbers as input for the menu. The program has complete error handling for unexpected inputs with detailed messages and explanations. Please consider that semicolons are not permitted in the input of names since they are the column-separator for the CSV-files. 

### Close input
To close the current input and to return to the last opened menu just press enter two times at any given point in the program.

### Setup
The search of vehicles ("Fahrzeugsuche") according to given filtering and sorting parameters is possible without being logged in. For other actions like advertising a new car you need to create a user account and login to the system. There are two types of accounts: personal and professional. Every kind of input like the e-mail-address are checked for the correct syntax described in each input request.

### Vehicle search
You can search for vehicles by filtering the technical parameters of each vehicle. The resulting vehicles can be sorted in different orders.

#### Sorting
You can sort by:
* Price increasing
* Price decreasing
* Mileage increasing
* Mileage decreasing
* First registration of the vehicle increasing
* First registration of the vehicle decreasing
* Publishing date of the ad increasing
* Publishing date of the ad decreasing

#### Filtering
First you have to choose if you're searching for cars or trucks.

You can filter cars by:
* Brand
* Model
* Price
* Power
* Mileage
* Weight
* First registration of the vehicle
* Type of car
* Sports package build in

You can filter trucks by:
* Brand
* Model
* Power
* Mileage
* Weight
* First registration of the vehicle
* Maximum load
* Towing capacity
* Hydraulic system build in

## Testing
There are unit tests available in the build. The lines of code **test coverage is at 96,2%**.

## Documentation
The full documentation was automatically created from the in-code documentation.
It's available <a href="Documentation" target="_blank">here</a> as linked HTML-Files, so it can be used like a website.

### Activity Diagram

The following image shows the **_vehicle search process_** as an **_activity diagram_**.

<p align="center">
<img src="https://raw.githubusercontent.com/AndrikSeeger/VehicleTradingSystem_CSV/master/Ressources/Activity_Diagram.png"/>
</p>

### Sequence Diagram

The following image shows the **_vehicle search process_** as a **_sequence diagram_**.

<p align="center">
<img src="https://raw.githubusercontent.com/AndrikSeeger/VehicleTradingSystem_CSV/master/Ressources/Sequence_Diagram.png"/>
</p>

### Use Cases

The following image shows a simplified version of the **_most important use cases_** as a **_use case diagram_**.

<p align="center">
<img src="https://raw.githubusercontent.com/AndrikSeeger/VehicleTradingSystem_CSV/master/Ressources/UseCases.png"/>
</p>

### UML

The following image shows basic program structure with the most important classes and interfaces.

<p align="center">
<img src="https://raw.githubusercontent.com/AndrikSeeger/VehicleTradingSystem_CSV/master/Ressources/UML.png"/>
</p>

## FAQ
* **Is the maximum amount of accounts/vehicles limited?**
 
    No, the number of accounts and vehicles is not artificially limited in code but the memory capacity on your system is limited, hence the system gets slower if you surpass your available physical memory capacity.
    
* **Can I use the code for my project?**
 
    Yes, you're free to use the project code for closed projects. If you plan on publishing your project please reference my development.
    
* **Are additional features available in the future?**
 
    There are some experimental features like remote access which currently aren't published. A new version of the <a href="https://github.com/AndrikSeeger/VehicleTradingSystem_POSTGRES" target="_blank">Postgres-version</a> featuring those functionalities is planned. Please contact me for further information.
