# Vehicle Trading System (CSV)
This PoC-like project is the development of a new platform for POSIX-like systems to buy, sell and trade vehicles using ads solely written in Java using CSV-Files as a backup system with high security standards.
The functionality is very similar to popular car trading sites like cars.com or mobile.de. Since this is development focuses on the backend functionality there is no graphical user interface. 
The interface uses the console on POSIX-like operating systems for user input and output.

Currently there is a second, further developed version available using the Postgres-Database instead of CSV for storage.
You can find the repository here.
//Link

## Language
Since the usage of the project was initially planned exclusively for the German car market the documentation is also in German.
For further information and explanations in English please contact me.

## Startup
On POSIX-like operating systems (macOS & most linux distributions) you can simply run the Execute.sh-Script in your preferred console.
There currently is no Windows-Version with CSV available since the Postgres-Version is developed for Windows.

The initial menu looks like this:
<p align="center">
<img src="https://raw.githubusercontent.com/AndrikSeeger/VehicleTradingSystem_CSV/master/Ressources/Startup.png"/>
</p>

## Usage
The usage of the program is pretty straight forward using numbers as input for the menu. The program has complete error handling for unexpected inputs with detailed messages and explanations. Please consider that semicolons are not permitted in the input of names since they are the column-seperator for the CSV-files. 

### Close input
To close the current input and to return to the last opened menu just press enter two times at any given point in the program.

### Setup
The search of vehicles ("Fahrzeugsuche") according to given filtering and sorting parameters is possible without being logged in. For other actions like advertising a new car you need to create a user account and login to the system. There are two types of accounts: personal and professional. Every kind of input like the e-mail-address are checked for the correct syntax described in each input request.

### Vehicle search
#### Sorting
#### Filtering

## Documentation

## FAQ
* **Is the maximum amount of accounts/vehicles limited?**
 
    No, the number of accounts and vehicles is not artifically limited in code but the memory capacity on your system is limited, hence the system gets slower if you surpass your available physical memory capacity.
    
* **Can I use the code for my project?**
 
    Yes, you're free to use the project code for closed projects. If you plan on puplicating your project please reference my development.
    
* **Are additional features available in the future?**
 
    There are some experimental features like remote access which currently aren't puplished. A new version of the Postgres-Version featuring those functionalities is planned. Please contact me for further information.
