# junk dot pics

**Now featuring an ENTERPRISE READY codebase!**

This is the software that runs the website [junk dot pics](https://junk.pics).

## Development

This is a Java application using the Spring Framework, with using the
WebFlux/Reactive stack. You will need:

* A modern JDK (11+)
* Maven
* An Azure Storage account

Copy the example `application.yml` and fill it out:

```
$ cp src/main/resources/application.yml.example src/main/resources/application.yml
$ vim !$
```

Once edited, you can boot a local server:

```
$ mvn spring-boot:run
```

We have Spring Devtools enabled, so changes to application code will
automatically reload the server.
