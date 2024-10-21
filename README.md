# oaelelevator

To execute, build project with maven and execute jar passing in arguments from command line. There is a sample shell script in the /bin folder.

I can't think of any assumptions that I made beyond selection of technology stack. I used Maven with Java 11 and wrote unit tests using Junit 5. I didn't add any dependencies beyond unit testing tools to keep it simple. I leveraged the Stream API's as much as possible to keep code succinct and easier to read. I favored arrays over List's to "try" to keep it simple as possible. There was one place I had to convert an int[] into an Integer[] because IntStream does weird things and couldn't get my custom Stream Collector class to work right. 

Instructions asked for unimplemented features. I am not sure how to answer that. I implemented the instructions as written, no more no less. I guess that I could have gold-plated the exercise and implemented a CLI or a floor to floor calculator with intermittent requests for floor changes. 

Buon Apetito!

For example:

java -jar {project root}/target/oaelevator-1.0-SNAPSHOT.jar start=12 floor=2,9,1,32
