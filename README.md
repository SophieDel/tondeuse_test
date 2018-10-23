Tondeuse
=====

### Lauch
Go to the project root and put

```
sbt run
```


### Configuration

The program mowes lawn according to a configuration file. 

There is a default configuration file.

If you want to have your own configuration, you can give a .txt as argument. The file must have the following format : 

```
- first line : lawn upper left corner String String String
- second line : mower initial coordinates and direction String String String
- third line : mower moves sequences String.

You can repeat the second and the lines as many times as the number of mowers.
```