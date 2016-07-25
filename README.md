## BGLib
BGLib (BitzGames lib) is a library that allows you to make minigames with very minimal effort. This is done by creating and using "building blocks", which allow certain code to be run on events, etc.

Just a note, this requires [BitzAPI](https://github.com/Rayzr522/BitzAPI) to run. To use in a server, make sure you have [BitzAPI](https://github.com/Rayzr522/BitzAPI) installed in the server. To build the plugin, run the maven build on BitzAPI with the goals `clean package install` to install BitzAPI to your local maven repository, and then you can build this as you normally would with maven.

To build minigames using this you must first install it to your local maven directory. Run this with the goals of `clean package install` to install it to your local maven repository, and then reference it with:
```
<dependencies>
	<dependency>
		<groupId>com.rayzr522</groupId>
		<artifactId>bglib</artifactId>
		<version>0.1</version>
	</dependency>
</dependencies>
```
in your project's pom.xml
