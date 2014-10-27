# XFactory


An Embedded DSL (EDSL) into Xtend to build POJOs and persist entities.

## Documentation

* [Using XFactories](http://borisbrodski.github.io/xfactory/xfactory/doc-gen/org/github/xfactory/testset1/UsingXFactoriesToCreateAndPersistEntitiesSpec.html)
* [Writing XFactories](http://borisbrodski.github.io/xfactory/xfactory/doc-gen/org/github/xfactory/testset1/WritingXFactoriesSpec.html)
 

## Download

Using Maven

```
  comming soon
```

## Integration into your test environment

* Before each test: call `XFactory.initTest(...)` providing an instance of your infrastructure provider.
* After each test: call `XFactory.doneTest()`
* Add `XFactoryExtension` class to your Xtend extension imports with one of the following ways
  * Add `import static extension ...XFactoryExtension.*` to each of your Xtend test classes
  * Extend your [JUnit Rule](http://stackoverflow.com/questions/13489388/how-junit-rule-works) or abstract test class from the `XFactoryExtension` class.
