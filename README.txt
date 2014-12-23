      _      _      _      USB       _      _      _
   __(.)< __(.)> __(.)=   Rubber   >(.)__ <(.)__ =(.)__
   \___)  \___)  \___)    Ducky!    (___/  (___/  (___/ 

The USB Rubber Ducky is a Human Interface Device programmable with a simple scripting language allowing penetration testers to quickly and easily craft and deploy security auditing payloads that mimic human keyboard input. The source is written in C and requires the AVR Studio 5 IDE from atmel.com/avrstudio. Hardware is commercially available at hakshop.com. Tools and payloads can be found at usbrubberducky.com. Quack!

To run this version you need to do the following while in the directory that contains the pom.xml file:

- `mvn clean package`
- `java -jar target/Encoder-1.0-SNAPSHOT-jar-with-dependencies.jar`

After `target/Encoder-1.0-SNAPSHOT-jar-with-dependencies.jar` just add in the options that it asks for and you're set!
