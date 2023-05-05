# SpringFirebaseToDo
A simple Todo list that can perform CRUD in Firestore through Spring Boot

## How to run:
1. Download Java 17 or greater.
2. Clone the repository.
3. Setup the necessary configuration under the com.github.kylerequez.SpringFirebaseToDo.Configuration class.
4. Under the package com.github.kylerequez.SpringFirebaseToDo, run the main method of the SpringFirebaseToDoApplication.

## Note
Under the com.github.kylerequez.SpringFirebaseToDo.Configuration package, please enter the necessary fields in the class ConfigurationStrings:

| Variable Name | Description |
| ------------- | ----------- |
| FILE_STREAM | The file path of the private key file generated from the Project Settings section in your Firebase project |
| DB_URL | The URL of the database |

![image](https://user-images.githubusercontent.com/82488140/236388932-9ff84ef9-28c0-4966-a6fe-952adb832525.png)
