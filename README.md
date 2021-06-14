# Class Assignment 2 Report

Why, hello and welcome to the second installment of our weekly series, "Learning Gradle" or "How I learned that build tools are hard". This time we will work on using Gradle with a basic SpringBoot application. Let's jump right in.

## PART2
=================

### 0. Setup

- First let's get ourserlves a basic Gradle Spring project. Go to the following link:

https://start.spring.io/

Add dependencies for Rest Repositories, Thymeleaf, Spring Data JPA and H2 Database. Don't forget to select "Gradle Project" in the top left corner.

- Click Generate and unzip the downloaded file in your ca2/part2 folder. This should now be the project root.


### 1. Experimentation

- To check all the tasks available right away, in a command line use:

```
./gradlew tasks 
```

- Now let's delete the src folder. That's right, you guessed it, we're going to use the source code from the basic SpringBoot tutorial in ca1. Copy that bad boy and replace the deleted folder. Don't forget to copy webpack.config.js and package.json as well. Lastly, delete the folder src/main/resources/static/built/.

- You can try running the application using:

```
./gradlew bootRun
```

Notice that localhost:8080 is empty. This is because there is no frontend plugin in your project yet.


### 2. Add Frontend Plugin

- In your build.gradle file, copy into the plugin dependencies the following line:

```
id "org.siouan.frontend" version "1.4.1"
```

- You also need it's configuration, that specifies the node version and assemble script:

```
frontend {
   nodeVersion = "12.13.1"
   assembleScript = "run webpack"
}
```

- Now in your package.json file add the webpack to your scripts:

```
"scripts": {
   "watch": "webpack --watch -d",
   "webpack": "webpack"
}
```

Finally, we can build the application using *./gradlew build*. Make sure the application is not running! All the new dependencies and configurations you just specified should be installed now. Nice job!

**Oh no!** Did something go wrong? On my end I realized the copied src folder from the basic spring tutorial was using JDK 1.8, but the downloaded Gradle Spring project was using JDK 11. If you see this error, just go to your build.gradle file and change sourceCompatibility from 11 to 1.8

You can now find an index.html file that will forward your browser to a generated bundle.js file that consists of all javascript code in your project.

- Run your application with *./gradlew bootRun*. If you open localhost:8080 in your browser you should find your application running.


### 3. Add Copy Task for Jar File

The generated Jar file is located in your build/libs folder. We want to copy this file into a "dist" folder to be created in your project root.

- Write a task of type Copy. You have to specify the origin and destination folder. Don't forget to include .jar files to be copied:

```
task copyJar(type: Copy) {
    group: 'devOps'
    description 'Copies the jar file in build/libs to a dist folder in project root'

    from 'build/libs'
    include '*.jar'
    into 'dist'
}
```

- Build your project using *./gradlew build* and try using your newly created task with:

```
./gradlew copyJar
```

A new "dist" folder should be created with your .jar file inside.


### 4. Add Delete Task for Webpack Generated Files

Now we want to delete all generated files by webpack. These are usually located at src/main/resources/static/built/. You can learn about the delete task [here](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Delete.html). 

- Let's create a task:

```
task deleteWebpackGeneratedFiles(type: Delete) {
    delete 'src/main/resources/static/built/'
}
```

- Try this new task using:

```
./gradlew deleteWebpackGeneratedFiles
```

You can verify that your built folder should be deleted. Good job!

- But wait, there's more! We want to automate execution of this task before you execute the clean task. Try to add a dependsOn dependency to the clean task:

```
clean {
    dependsOn deleteWebpackGeneratedFiles
}
```

Now try running the clean task using *./gradlew clean*. The built folder should be deleted, as your deleteWebpackGeneratedFiles task is executed as well.


### 5. You made it!

Congratulations. Pat yourself on the back. But not before checking if everything is running properly. If it's not, google is your best friend.
