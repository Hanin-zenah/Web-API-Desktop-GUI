# Web API Consumer GUI
##### Java Application (Java Swing)

## APIs 
### Input API 
The-one API. URL: https://the-one-api.dev/ \
The application will retrieve information on user-specified characters from this API.

### Output API 
Pastebin API. URL: https://pastebin.com/ \
The application will send report data on a specified character from the-one API as a saved pastebin and will output its url.

This software has been built by following a Test-Driven Development approach. Only the main features implemented for the backend (model) are tested.

## Concurrency 
I have utilised Swing's threads (Event Dispatch Thread, and Worker threads) to create a non-freezing GUI such that multiple requests can be made independently at the same time.

## Quirks For Running My Application
Please note that due to my concurrency implementation, when you press a button, the frame will only\
show up once the data is ready to be displayed (i.e. required data has been fetched from the API and/or database). Meaning, the application will be doing the work
in the background, and the window will not immediately show up so please give it a second.

There is no particular reason as to why I chose to implement it like this and this can be easily changed if a client wants it to be different.

### Running the Application 
The application can be run online or offline. In the offline version, dummy implementation will be executed. 

To build the project, enter **gradle build** on your terminal in the directory of the build file. \
Then, to execute the program, enter **gradle run --args="<input_api_mode> <output_api_mode>"**, where api mode is either "online" or "offline". 

For example: \
*gradle run --args="online online"* \
and \
*gradle run -args="online offline"* \
are both valid.

## Configuration File 
In the "resources" folder of the application, there is a config.json file. 
This file contains the access tokens for both the-one input API and pastebin output API.
Please change those accordingly before running the program.