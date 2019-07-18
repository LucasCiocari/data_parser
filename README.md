Run the program, then whenever a new file pops in the folder data/in, it will be parsed. 

To run as a docker container: 

`docker run -d lucasciocari/parser:4.0`

to run while using a local folder

`docker run -d -v C:/Users/{Change to your user}/data/in:/home/data/in/ lucasciocari/parser:4.0`

Known bug:

If there is a file that ends with '.dat', but it isn't a valid file, the program will crash. 
