# XFactory


An Embedded DSL (EDSL) into Xtend to build POJOs and persist entities.

* [Talk at EclipseCon](http://youtu.be/jCgPfxaY8XQ)

## Documentation

* Version 0.0.2
  * [XFactory Documentation](http://borisbrodski.github.io/xfactory/doc-0.0.2/org/github/xfactory/docs/XFactoryDocumentationV002Suite.html)
  * [JavaDoc](http://borisbrodski.github.io/xfactory/doc\-0.0.2/javadoc/index.html)

* Version 0.0.1
  * [XFactory Documentation](http://borisbrodski.github.io/xfactory/doc-0.0.1/org/github/xfactory/docs/XFactoryDocumentationV001Suite.html)

## Download

* Using Maven
```
<dependency>
	<groupId>com.github.borisbrodski</groupId>
	<artifactId>xfactory</artifactId>
	<version>0.0.1</version>
</dependency>
```

* Direct download
  * [xfactory-0.0.1.jar](http://central.maven.org/maven2/com/github/borisbrodski/xfactory/0.0.1/xfactory-0.0.1.jar)
  * [xfactory-0.0.1-javadoc.jar](http://central.maven.org/maven2/com/github/borisbrodski/xfactory/0.0.1/xfactory-0.0.1-javadoc.jar)
  * [xfactory-0.0.1-sources.jar](http://central.maven.org/maven2/com/github/borisbrodski/xfactory/0.0.1/xfactory-0.0.1-sources.jar)

## Integration into your test environment

* Before each test: call `XFactory.initTest(...)` providing an instance of your infrastructure provider.
* After each test: call `XFactory.doneTest()`
* Add `XFactoryExtension` class to your Xtend extension imports with one of the following ways
  * Add `import static extension ...XFactoryExtension.*` to each of your Xtend test classes
  * Extend your [JUnit Rule](http://stackoverflow.com/questions/13489388/how-junit-rule-works) or abstract test class from the `XFactoryExtension` class.
