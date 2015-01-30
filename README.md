# XFactory


An Embedded DSL (EDSL) into Xtend to build POJOs and persist entities.

* [Talk at EclipseCon](http://youtu.be/jCgPfxaY8XQ)

## Documentation

* Version 0.0.1
  * [XFactory Documentation](http://borisbrodski.github.io/xfactory/xfactory/doc-0.0.1/org/github/xfactory/docs/XFactoryDocumentationV001Suite.html)

## Download

Using Maven

```
<dependency>
	<groupId>com.github.borisbrodski</groupId>
	<artifactId>xfactory</artifactId>
	<version>0.0.1</version>
</dependency>
```

## Integration into your test environment

* Before each test: call `XFactory.initTest(...)` providing an instance of your infrastructure provider.
* After each test: call `XFactory.doneTest()`
* Add `XFactoryExtension` class to your Xtend extension imports with one of the following ways
  * Add `import static extension ...XFactoryExtension.*` to each of your Xtend test classes
  * Extend your [JUnit Rule](http://stackoverflow.com/questions/13489388/how-junit-rule-works) or abstract test class from the `XFactoryExtension` class.
