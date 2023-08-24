# kotlin-hotel
Kotlin Hotel project for Spring Boot

## Debugging Setup for Kotlin Spring Boot Application
This guide provides step-by-step instructions to ensure a smooth debugging experience on <b>VSCode</b>.

### Setting up Debugging
1. Create a launch.json File
   - In VSCode, navigate to the "Debug" tab (Ctrl+Shift+D).
   - Click on the gear icon to configure a new launch configuration.
   - Select "Kotlin Attach" as the configuration type.
2. Add Configuration details
   - Paste the following code into the launch.json file:
``` json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "kotlin",
            "request": "attach",
            "name": "Kotlin Attach",
            "hostName": "localhost",
            "port": 5005,
            "timeout": 30000,
            "projectRoot": "${workspaceFolder}"
        }
    ]
}
```
3. Start Debugging
   - Execute the following command in your terminal:
``` bash
./gradlew bootRun -Pdebug
```
This command will initiate the Spring Boot application with a debug listener on port 5005.

4. Start debugging in VSCode
   - Launch the debugger in VSCode using the configuration you created in the launch.json file.


### Gradle/debugger details (optional)
If you're curious about how the debug listener is enabled through the build.gradle file, here's an optional explanation.

1. Enabling Debug Listener
   - If you wish to start the application with the debug listener using Gradle, add the following code to your build.gradle file:

``` kotlin
if (project.hasProperty('debug')) {
    bootRun {
        jvmArgs '-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=*:5005'
    }
}
```
This allows you to start the application with debugging capabilities by running ./gradlew bootRun -Pdebug in the terminal.
