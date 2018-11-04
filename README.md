## Sample customer search application

A sample CLI based application written in Java, that takes a source file with list 
of customers, and other search parameters, and shows the output on standard output.

## Usage

### Build

To build and run tests :

``./gradlew clean build``

Build a fatJar with all dependencies :

``./gradlew fatJar``

### Run
To run application with default CLI args (Finding customers within 100kms from Intercom Dublin location):

``./gradlew run``

To run application with custom arguments :

``java -jar <fatJar> --help``

### Arguments

Arguments supported by the CLI :

~~~
java -jar <fatJar> --help


Usage: <main class> [options]
  Options:
    --help

  * --inputFilePath, -i
      Absolute path to input file with customer details
    --max-distance-kms
      Maximum distance within which we search.
      Default: 100.0
    --sort-field
      Name of the field to sort.
      Default: USER_ID
      Possible Values: [USER_ID, NAME]
    --sort-order
      Order of search results.
      Default: ASC
      Possible Values: [ASC, DESC]
    --source-latitude
      Latitude of the geo location from where distance is to be calculated.
      Default corresponds to Dublin.
      Default: 53.339428
    --source-longitude
      Longitude of the geo location from where distance is to be calculated.
      Default corresponds to Dublin.
      Default: -6.257664
~~~

As shown, apart from the input file, every other CLI arg has default values. Default values can be overridden in CLI arguments.

## Tools and libraries used

* Java 8
* JCommander
* Lombok
* Mockito
* Jackson
* Gradle

## Possible improvements

* A Dependency Injection library could be used.
* `FilterParams` could be made as an interface, and can be removed from the constructor
of `DistanceFilter` and passed as a parameter to `filter` method.
 That way, if this application is to be deployed on a server, we can have a singleton instance of `DistanceFilter` and not
 instantiate it for every incoming request.
* Validation of CLI args could be done manually and additional validation wrapper classes could be removed
from the main class.
* Logging could be configured to log into a file, which now is being written to stdout.

## Why not a Server?

Could be written as a server as well, inside Spring Boot or Dropwizard for e.g. However, migrating this to a REST server should be easy enough as may be the
only change to be done is in the main CLI class, which could be converted to a controller. Also, CLI args can be taken as 
query parameters or request body.  
  
 