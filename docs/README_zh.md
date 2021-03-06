# Boat: SrcLab的核心基础Java/kotlin库

![Boat](../logo.svg)

## 变量

* author: Sun Qian
* boat-version: 0.1.0

## 修订

|Date|Revision|Author|Content|
|---|---|---|---|
|日期|修订版|作者|内容
|2020-03-26|0.0.0|{author}|旧项目
|2020-04-12|1.0.0|{author}|旧项目更新
|2020-12-10|0.0.0|{author}|新项目
|2020-12-10|{boat-version}|{author}|新项目重构

## 目录

* [简介](#introduction)
* [获取](#getting)
* [使用](#usage)
* [贡献和联系方式](#contact)
* [License](#license)

## <a id="introduction"/>简介

Boat是一组Java/Kotlin核心库集合（JDK 1.8+）, 主要由Kotlin编写, 广泛应用于SrcLab里的项目. 它提供了许多方便快捷的接口, 函数和工具.

Boat包括:

* *boat-annotations*: 核心注解, 如@Nullable, @NotNull, @DefaultNullable, @DefaultNotNull;
* *boat-core*: 核心和基础的接口, 功能, 和工具;
* *boat-serialize*: 序列化和反序列化的接口和工具 (支持json等);
* *boat-codec*: 编码接口和工具 (支持hex, base64, SHA, MD, HMAC, AES, RSA, SM2等);
* *boat-id*: 一个轻量级的ID生成框架;
* *boat-test*: 测试依赖管理, 用来在测试编译和运行范围下引入测试框架;
* *boat-bom*: Boat Bom.

如果你需要引入以上所有模块 (不包括boat-test和boat-bom), 只需要:

* *boat-all*: 引入所有Boat模块.

## <a id="getting"/>获取

### Gradle

```groovy
implementation("xyz.srclab.common:boat-all:{boat-version}")
```

### Maven

```xml

<dependency>
  <groupId>xyz.srclab.common</groupId>
  <artifactId>boat-all</artifactId>
  <version>{boat-version}</version>
</dependency>
```

### 源代码

https://github.com/srclab-projects/boat

## <a id="usage"/>使用

* [Boat Annotation](#usage-annotations)
* [Boat Core](#usage-core)
  * [Base](#usage-core-base)
  * [Bean](#usage-core-bean)
  * [Bus](#usage-core-bus)
  * [Cache](#usage-core-cache)
  * [Collect](#usage-core-collect)
  * [Convert](#usage-core-convert)
  * [Exception](#usage-core-exception)
  * [Invoke](#usage-core-invoke)
  * [Jvm](#usage-core-jvm)
  * [Proxy](#usage-core-proxy)
  * [Reflect](#usage-core-reflect)
  * [Run](#usage-core-run)
  * [State](#usage-core-state)
  * [Test](#usage-core-test)
* [Boat Serialize](#usage-serialize)
* [Boat Codec](#usage-codec)
* [Boat Id](#usage-id)

### <a id="usage-annotations"/>BoatAnnotations (boat-annotations)

Boat Annotations提供了许多可以让代码整洁又干净的注解:

* *DefaultNonNull*/*DefaultNullable*: 它说明注解范围内所有的变量, 属性, 参数和类型使用默认都是non-null/nullable的, 通常用在package-info.java中;
* *NotNull*/*Nullable*: 它说明被注解的变量, 属性, 参数和类型使用是non-null/nullable的;
* *JavaBean*: 它说明被注解的类型是一个javabean, 所有的属性默认都是nullable的;
* *Acceptable*/*Accepted*: 它说明参数只能接受指定的几个类型.
* *Rejectable*/*Rejected*: 它说明参数不接受指定的几个类型.
* *Written*: 它说明参数可能被进行写操作;
* *Immutable*: 它说明被注解的变量, 属性, 参数和类型使用是不可变和线程安全的;
* *ThreadSafe*: 它说明被注解的变量, 属性, 参数和类型使用是线程安全的;
* *ThreadSafeIf*: 它说明被注解的变量, 属性, 参数和类型使用在满足指定条件的情况下是线程安全的;

#### Java Examples

```java
public class AnnotationSample {

  @Test
  public void testAnnotations() {
    TestBean testBean = new TestBean();
    Assert.assertEquals(testBean.getP2().substring(1), "2");
    Assert.expectThrows(NullPointerException.class, () -> testBean.getP1().substring(1));

    StringBuilder buffer = new StringBuilder();
    writeBuffer(buffer, "123");
    Assert.assertEquals(buffer.toString(), "123");
  }

  private void writeBuffer(
      @Written StringBuilder buffer,
      @Accepted(String.class) @Accepted(StringBuffer.class) CharSequence readOnly
  ) {
    buffer.append(readOnly);
  }

  @JavaBean
  public static class TestBean {

    private String p1;
    @NonNull
    private String p2 = "p2";

    public String getP1() {
      return p1;
    }

    public void setP1(String p1) {
      this.p1 = p1;
    }

    @NonNull
    public String getP2() {
      return p2;
    }

    public void setP2(@NonNull String p2) {
      this.p2 = p2;
    }
  }
}
```

#### Kotlin Examples

```kotlin
class AnnotationSample {

  @Test
  fun testAnnotations() {
    val buffer = StringBuilder()
    buffer.writeBuffer("123")
    Assert.assertEquals(buffer.toString(), "123")
  }

  private fun @receiver:Written StringBuilder.writeBuffer(
    @Acceptable(
      Accepted(String::class),
      Accepted(StringBuffer::class),
    )
    readOnly: String
  ) {
    this.append(readOnly)
  }
}
class AnnotationSample {

  @Test
  fun testAnnotations() {
    val buffer = StringBuilder()
    buffer.writeBuffer("123")
    Assert.assertEquals(buffer.toString(), "123")
  }

  private fun @receiver:Written StringBuilder.writeBuffer(
    @Acceptable(
      Accepted(String::class),
      Accepted(StringBuffer::class),
    )
    readOnly: String
  ) {
    this.append(readOnly)
  }
}
```

### <a id="usage-core"/>Boat Core (boat-core)

#### <a id="usage-core-base"/>Base

Base包提供基本的核心基础接口, 功能和工具:

* 全局快捷对象: Current, Default, Environment;
* 语法增强(主要针对java): Let, Ref, Lazy;
* 字符串功能: CharsFormat, CharsTemplate, NamingCase;
* 核心基础接口: Accessor, Serial, SpecParser, CachingProductBuilder
* 常用工具: Anys, Bools, Chars, Nums, Dates, Randoms, Compares, Checks, Requires, Loaders;
* 其他工具: About, Counter, Shell.

##### Java Examples

```java
public class BaseSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testLet() {
    int sum = Let.of("1,2,3,4,5,6,7,8,9,10")
        .then(s -> s.split(","))
        .then(Arrays::asList)
        .then(l -> l.stream().mapToInt(Integer::parseInt))
        .then(IntStream::sum)
        .get();
    //55
    logger.log("sum: {}", sum);
  }

  @Test
  public void testRef() {
    Ref<String> ref = Ref.of("1");
    List<String> list = Arrays.asList("-1", "-2", "-3");

    //here <String> should be final without Ref
    list.forEach(i -> ref.set(ref.get() + i));
    //1-1-2-3
    logger.log("result: {}", ref.get());
  }

  @Test
  public void testCurrent() {
    Current.set("1", "2");
    //2
    logger.log(Current.get("1"));
    //System.currentTimeMillis();
    logger.log(Current.millis());
  }

  @Test
  public void testDefault() {
    //UTF-8
    logger.log(Default.charset());
    //Locale.getDefault();
    logger.log(Default.locale());
  }

  @Test
  public void testEnvironment() {
    logger.log(Environment.getProperty(Environment.KEY_OS_ARCH));
    logger.log(Environment.availableProcessors());
    logger.log(Environment.osVersion());
    logger.log(Environment.isOsWindows());
  }

  @Test
  public void testFormat() {
    String byFast = CharsFormat.fastFormat("1, 2, {}", 3);
    String byMessage = CharsFormat.messageFormat("1, 2, {0}", 3);
    String byPrintf = CharsFormat.printfFormat("1, 2, %d", 3);
    //1, 2, 3
    logger.log("byFast: {}", byFast);
    logger.log("byMessage: {}", byMessage);
    logger.log("byPrintf: {}", byPrintf);
  }

  @Test
  public void testTemplate() {
    Map<Object, Object> args = new HashMap<>();
    args.put("name", "Dog");
    args.put("name}", "DogX");
    args.put(1, "Cat");
    args.put(2, "Bird");
    CharsTemplate template1 = CharsTemplate.resolve(
        "This is a {name}, that is a {}", "{", "}");
    //This is a Dog, that is a Cat
    logger.log(template1.process(args));
    CharsTemplate template2 = CharsTemplate.resolve(
        "This is a } {name}, that is a {}}", "{", "}");
    //This is a } Dog, that is a Cat}
    logger.log(template2.process(args));
    CharsTemplate template3 = CharsTemplate.resolve(
        "This is a } \\{{name\\}} ({name}), that is a {}\\\\\\{\\", "{", "}", "\\");
    //This is a } {DogX (Dog), that is a Bird\\{\
    logger.log(template3.process(args));
  }

  @Test
  public void testNamingCase() {
    String upperCamel = "UpperCamel";
    String lowerCamel = NamingCase.UPPER_CAMEL.convertTo(upperCamel, NamingCase.LOWER_CAMEL);
    //upperCamel
    logger.log("lowerCamel: {}", lowerCamel);
  }

  @Test
  public void testCounter() {
    Counter counter = Counter.startsAt(100);
    counter.getAndIncrementInt();
    counter.reset();
    Counter atomicCounter = Counter.startsAt(100, true);
    atomicCounter.incrementAndGetInt();
    atomicCounter.reset();
  }

  @Test
  public void testLazy() {
    Lazy<String> lazy = Lazy.of(() -> UUID.randomUUID().toString());
    String value1 = lazy.get();
    String value2 = lazy.get();
    lazy.refresh();
    String value3 = lazy.get();
    //value1 == value2
    //value2 != value3
    logger.log("value1: {}", value1);
    logger.log("value2: {}", value2);
    logger.log("value3: {}", value3);
  }

  @Test
  public void testLoaders() {
    Class<String[][][]> cls = Loaders.loadClass("[[[Ljava.lang.String;");
    //class [[[Ljava.lang.String;
    logger.log("cls: {}", cls);
  }

  @Test
  public void testSpecParser() {
    String s = SpecParser.parseFirstClassNameToInstance("java.lang.String");
    //an empty String
    logger.log("s: {}", s);
  }

  @Test
  public void testUtils() {

    //Anys examples:
    List<String>[] lists = Anys.as(new List[]{});
    int hash = Anys.anyOrArrayHash(Arrays.asList("", 1));
    boolean equals = Anys.anyOrArrayEquals(Arrays.asList("", 1), Arrays.asList("", 1));

    //Chars examples:
    byte[] bytes = "message10086".getBytes();
    String toChars = Chars.toChars(bytes);
    byte[] toBytes = Chars.toBytes(toChars);
    //message10086
    logger.log("toChars: {}", toChars);
    //[109, 101, 115, 115, 97, 103, 101, 49, 48, 48, 56, 54]
    logger.log("toBytes: {}", toBytes);

    //Nums examples:
    BigDecimal n = Nums.toBigDecimal("110");
    int i = Nums.toInt(new BigDecimal("2333"));
    //110
    logger.log("n: {}", n);
    //2333
    logger.log("i: {}", i);

    //Bools examples:
    boolean b = Bools.toBoolean("true");
    //true
    logger.log("b: {}", b);

    //Dates examples:
    String timestamp = Dates.timestamp();
    LocalDateTime localDateTime = Dates.toLocalDateTime("2011-12-03T10:15:30");
    //20210207144816045
    logger.log("timestamp: {}", timestamp);
    //2011-12-03T10:15:30
    logger.log("localDateTime: {}", localDateTime);

    //Randoms examples:
    //[10, 20]
    for (int j = 0; j < 10; j++) {
      logger.log("random[10, 20]: {}", Randoms.between(10, 21));
    }

    //Compares example:
    //99
    logger.log("inBounds: {}", Compares.inBounds(100, 0, 99));

    //Checks examples:
    try {
      Checks.checkArgument(1 == 2, "1 != 2");
    } catch (IllegalArgumentException e) {
      //java.lang.IllegalArgumentException: 1 != 2
      logger.log("e: {}", e);
    }

    //Requires examples:
    try {
      Object notNull = Requires.notNull(null, "null");
    } catch (NullPointerException e) {
      //java.lang.NullPointerException: null
      logger.log("e: {}", e);
    }
  }

  @Test
  public void testCachingBuilder() {

    class CachingBuilderSample extends CachingProductBuilder<String> {

      private String value = "null";

      public void setValue(String value) {
        this.value = value;
        this.commitChange();
      }

      @NotNull
      @Override
      protected String buildNew() {
        return value + UUID.randomUUID().toString();
      }
    }

    CachingBuilderSample cachingBuilderSample = new CachingBuilderSample();
    cachingBuilderSample.setValue("1");
    String value1 = cachingBuilderSample.build();
    String value2 = cachingBuilderSample.build();
    cachingBuilderSample.setValue("2");
    String value3 = cachingBuilderSample.build();
    //10c66dae9-c056-464e-8117-4787914c3af8
    logger.log("value1: {}", value1);
    //10c66dae9-c056-464e-8117-4787914c3af8
    logger.log("value2: {}", value2);
    //2c7c2e230-50b0-4a0f-8530-151723297fb8
    logger.log("value3: {}", value3);
  }

  @Test
  public void testShell() {
    Shell shell = Shell.DEFAULT;
    shell.println("Hello", ",", "World", "!");
    shell.println(Arrays.asList("Hello", ",", "World", "!"));
    shell.println("123", EscapeChars.linefeed(), "456", EscapeChars.newline(), EscapeChars.reset());
    shell.println(
        SgrChars.foregroundRed("red"),
        SgrChars.backgroundCyan(" "),
        SgrChars.foregroundGreen("green")
    );
    shell.println(
        SgrChars.withParam("bright red", SgrParam.FOREGROUND_BRIGHT_RED),
        SgrChars.backgroundCyan(" "),
        SgrChars.withParam("bright green", SgrParam.FOREGROUND_BRIGHT_GREEN)
    );
    shell.println(
        SgrChars.withParam("color 8", SgrParam.foregroundColor(8)),
        SgrChars.backgroundCyan(" "),
        SgrChars.withParam("rgb(100, 100, 50)", SgrParam.foregroundColor(100, 100, 50))
    );
    shell.println(ControlChars.beep());
    shell.println("123", ControlChars.backspaces(), "456", ControlChars.beep());
  }

  @Test
  public void testAbout() {
    String verString = "1.2.3-beta.2.3+123";
    SemVer semVer = SemVer.parse(verString);
    About about = About.of(
        "name",
        semVer.normalString(),
        Collections.singletonList(Author.of("name", "author@mail.com", null)),
        "123@123.com",
        "url",
        Collections.singletonList("licence"),
        Collections.singletonList(About.of(
            "poweredBy",
            null,
            Collections.emptyList(),
            null,
            null,
            Collections.emptyList(),
            Collections.emptyList(),
            null
        )),
        "© 2021 SrcLab"
    );
    //name
    //Version: 1.2.3
    //Author: name(author@mail.com)
    //Mail: 123@123.com
    //Url: url
    //Licence: licence
    //Powered by: poweredBy
    //© 2021 SrcLab
    logger.log("About: {}", about);
  }
}
```

##### Kotlin Examples

```kotlin
class BaseSample {

  @Test
  fun testCurrent() {
    Current.set("1", "2")
    //2
    logger.log(Current.get<Any>("1"))
    //System.currentTimeMillis();
    logger.log(Current.millis)
  }

  @Test
  fun testDefault() {
    //UTF-8
    logger.log(Default.charset)
    //Locale.getDefault();
    logger.log(Default.locale)
  }

  @Test
  fun testEnvironment() {
    logger.log(Environment.getProperty(Environment.KEY_OS_ARCH))
    logger.log(Environment.availableProcessors)
    logger.log(Environment.osVersion)
    logger.log(Environment.isOsWindows)
  }

  @Test
  fun testFormat() {
    val byFast = "1, 2, {}".fastFormat(3)
    val byMessage = "1, 2, {0}".messageFormat(3)
    val byPrintf = "1, 2, %d".printfFormat(3)
    //1, 2, 3
    logger.log("byFast: {}", byFast)
    logger.log("byMessage: {}", byMessage)
    logger.log("byPrintf: {}", byPrintf)
  }

  @Test
  fun testTemplate() {
    val args: MutableMap<Any, Any?> = HashMap()
    args["name"] = "Dog"
    args["name}"] = "DogX"
    args[1] = "Cat"
    args[2] = "Bird"
    val template1 = "This is a {name}, that is a {}".resolveTemplate("{", "}")
    //This is a Dog, that is a Cat
    logger.log(template1.process(args))
    val template2 = "This is a } {name}, that is a {}}".resolveTemplate("{", "}")
    //This is a } Dog, that is a Cat}
    logger.log(template2.process(args))
    val template3 = "This is a } \\{{name\\}} ({name}), that is a {}\\\\\\{\\".resolveTemplate("{", "}", "\\")
    //This is a } {DogX (Dog), that is a Bird\\{\
    logger.log(template3.process(args))
  }

  @Test
  fun testNamingCase() {
    val upperCamel = "UpperCamel"
    val lowerCamel = NamingCase.UPPER_CAMEL.convertTo(upperCamel, NamingCase.LOWER_CAMEL)
    //upperCamel
    logger.log("lowerCamel: {}", lowerCamel)
  }

  @Test
  fun testCounter() {
    val counter = 100.counterStarts()
    counter.getAndIncrementInt()
    counter.reset()
    val atomicCounter = 100.counterStarts(true)
    atomicCounter.incrementAndGetInt()
    atomicCounter.reset()
  }

  @Test
  fun testLoaders() {
    val cls = "[[[Ljava.lang.String;".loadClass<Array<Array<Array<String>>>>()
    //class [[[Ljava.lang.String;
    logger.log("cls: {}", cls)
  }

  @Test
  fun testSpecParser() {
    val s = "java.lang.String".parseFirstClassNameToInstance<String>()
    //an empty String
    logger.log("s: {}", s)
  }

  @Test
  fun testUtils() {

    //Anys examples:
    val lists = arrayOf<List<*>>().asAny<Array<List<String>>>()
    val hash = Arrays.asList("", 1).anyOrArrayHash()
    val equals = Arrays.asList("", 1).anyOrArrayEquals(Arrays.asList("", 1))

    //Chars examples:
    val bytes = "message10086".toByteArray()
    val toChars = bytes.toChars()
    val toBytes = toChars.toBytes()
    //message10086
    logger.log("toChars: {}", toChars)
    //[109, 101, 115, 115, 97, 103, 101, 49, 48, 48, 56, 54]
    logger.log("toBytes: {}", toBytes)

    //Nums examples:
    val n = "110".toBigDecimal()
    val i = BigDecimal("2333").toInt()
    //110
    logger.log("n: {}", n)
    //2333
    logger.log("i: {}", i)

    //Bools examples:
    val b = "true".toBoolean()
    //true
    logger.log("b: {}", b)

    //Dates examples:
    val timestamp = timestamp()
    val localDateTime = "2011-12-03T10:15:30".toLocalDateTime()
    //20210207144816045
    logger.log("timestamp: {}", timestamp)
    //2011-12-03T10:15:30
    logger.log("localDateTime: {}", localDateTime)

    //Randoms examples:
    //[10, 20]
    for (j in 0..9) {
      logger.log("random[10, 20]: {}", randomBetween(10, 21))
    }

    //Compares example:
    //99
    logger.log("inBounds: {}", 100.inBounds(0, 99))

    //Checks examples:
    try {
      checkArgument(1 == 2, "1 != 2")
    } catch (e: IllegalArgumentException) {
      //java.lang.IllegalArgumentException: 1 != 2
      logger.log("e: {}", e)
    }

    //Requires examples:
    try {
      val notNull = null.notNull<Any>("null")
    } catch (e: NullPointerException) {
      //java.lang.NullPointerException: null
      logger.log("e: {}", e)
    }
  }

  @Test
  fun testCachingBuilder() {

    class CachingBuilderSample : CachingProductBuilder<String>() {
      private var value = "null"
      fun setValue(value: String) {
        this.value = value
        commitChange()
      }

      override fun buildNew(): String {
        return value + UUID.randomUUID().toString()
      }
    }

    val cachingBuilderSample = CachingBuilderSample()
    cachingBuilderSample.setValue("1")
    val value1 = cachingBuilderSample.build()
    val value2 = cachingBuilderSample.build()
    cachingBuilderSample.setValue("2")
    val value3 = cachingBuilderSample.build()
    //10c66dae9-c056-464e-8117-4787914c3af8
    logger.log("value1: {}", value1)
    //10c66dae9-c056-464e-8117-4787914c3af8
    logger.log("value2: {}", value2)
    //2c7c2e230-50b0-4a0f-8530-151723297fb8
    logger.log("value3: {}", value3)
  }

  @Test
  fun testShell() {
    val shell = Shell.DEFAULT
    shell.println("Hello", ",", "World", "!")
    shell.println(Arrays.asList("Hello", ",", "World", "!"))
    shell.println("123", ControlChars.linefeed, "456", EscapeChars.newline, EscapeChars.reset)
    shell.println(
      SgrChars.foregroundRed("red"),
      SgrChars.backgroundCyan(" "),
      SgrChars.foregroundGreen("green")
    )
    shell.println(
      SgrChars.withParam("bright red", SgrParam.FOREGROUND_BRIGHT_RED),
      SgrChars.backgroundCyan(" "),
      SgrChars.withParam("bright green", SgrParam.FOREGROUND_BRIGHT_GREEN)
    )
    shell.println(
      SgrChars.withParam("color 8", SgrParam.foregroundColor(8)),
      SgrChars.backgroundCyan(" "),
      SgrChars.withParam("rgb(100, 100, 50)", SgrParam.foregroundColor(100, 100, 50))
    )
    shell.println(ControlChars.beep)
    shell.println("123", ControlChars.backspaces, "456", ControlChars.beep)
  }

  @Test
  fun testAbout() {
    val verString = "1.2.3-beta.2.3+123"
    val semVer: SemVer = verString.parseSemVer()
    val about = About.of(
      "name",
      semVer.normalString,
      listOf(Author.of("name", "author@mail.com", null)),
      "123@123.com",
      "url",
      listOf("licence"),
      listOf(
        About.of(
          "poweredBy",
          null,
          emptyList(),
          null,
          null,
          emptyList(),
          emptyList(),
          null
        )
      ),
      "© 2021 SrcLab"
    )
    //name
    //Version: 1.2.3
    //Author: name(author@mail.com)
    //Mail: 123@123.com
    //Url: url
    //Licence: licence
    //Powered by: poweredBy
    //© 2021 SrcLab
    logger.log("About: {}", about)
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-bean"/>Bean

Bean包提供了强大的bean操作功能:

* Beans: 默认的bean工具;
* BeanResolver: Bean操作的核心接口, Beans使用它的默认实现;

> 在复制属性(copy-properties)功能上, 它比Apache的BeanUtils快十倍以上.

##### Java Examples

```java
public class BeanSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testBean() {
    A a = new A();
    a.setP1("1");
    a.setP2("2");
    B b = Beans.copyProperties(a, new B());
    int b1 = b.getP1();
    int b2 = b.getP2();
    //1
    logger.log("b1: {}", b1);
    //2
    logger.log("b1: {}", b2);
  }

  public static class A {
    private String p1;
    private String p2;

    public String getP1() {
      return p1;
    }

    public void setP1(String p1) {
      this.p1 = p1;
    }

    public String getP2() {
      return p2;
    }

    public void setP2(String p2) {
      this.p2 = p2;
    }
  }

  public static class B {
    private int p1;
    private int p2;

    public int getP1() {
      return p1;
    }

    public void setP1(int p1) {
      this.p1 = p1;
    }

    public int getP2() {
      return p2;
    }

    public void setP2(int p2) {
      this.p2 = p2;
    }
  }
}
```

##### Kotlin Examples

```kotlin
class BeanSample {

  @Test
  fun testBean() {
    val a = A()
    a.p1 = "1"
    a.p2 = "2"
    val b = a.copyProperties(B())
    val b1 = b.p1
    val b2 = b.p2
    //1
    logger.log("b1: {}", b1)
    //2
    logger.log("b1: {}", b2)
  }

  class A {
    var p1: String? = null
    var p2: String? = null
  }

  class B {
    var p1 = 0
    var p2 = 0
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-bus"/>Bus

Bus包提供EventBus.

##### Java Examples

```java
public class EventBusSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testEventBus() {
    EventBus eventBus = EventBus.newEventBus(Arrays.asList(
        new EventHandler<Object>() {
          @NotNull
          @Override
          public Object eventType() {
            return String.class;
          }

          @Override
          public void handle(@NotNull Object event) {
            logger.log(event);
          }
        },
        new EventHandler<Object>() {
          @NotNull
          @Override
          public Object eventType() {
            return Integer.class;
          }

          @Override
          public void handle(@NotNull Object event) {
            logger.log(event);
          }
        }
    ));
    //1
    eventBus.emit(1);
    //2
    eventBus.emit("2");
    //No output
    eventBus.emit(new Object());
    try {
      eventBus.emitOrThrow(new Object());
    } catch (EventHandlerNotFoundException e) {
      //xyz.srclab.common.bus.EventHandlerNotFoundException: class java.lang.Object
      logger.log(e);
    }
  }
}
```

##### Kotlin Examples

```kotlin
class EventBusSample {

  @Test
  fun testEventBus() {
    val eventBus = EventBus.newEventBus(
      listOf(
        object : EventHandler<Any> {

          override val eventType: Any
            get() {
              return String::class.java
            }

          override fun handle(event: Any) {
            logger.log(event)
          }
        },
        object : EventHandler<Any> {

          override val eventType: Any
            get() {
              return Int::class.java
            }

          override fun handle(event: Any) {
            logger.log(event)
          }
        }
      ))
    //1
    eventBus.emit(1)
    //2
    eventBus.emit("2")
    //No output
    eventBus.emit(Any())
    try {
      eventBus.emitOrThrow(Any())
    } catch (e: EventHandlerNotFoundException) {
      //xyz.srclab.common.bus.EventHandlerNotFoundException: class java.lang.Object
      logger.log(e)
    }
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-cache"/>Cache

Boat提供一个缓存接口和若干实现:

* Cache: 缓存核心接口;
* FastCache: 使用WeakHashMap和ThreadLocal的实现;
* CaffeineCache: 使用Caffeine的实现;
* GuavaCache: 使用Guava的实现;
* MapCache: 将Map作为缓存的实现;
* ThreadLocalCache: 将ThreadLocalMap作为缓存的实现.

##### Java Examples

```java
public class CacheSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testCache() {
    Cache<String, String> cache = Cache.newFastCache();
    cache.getOrLoad("1", k -> k);
    //1
    logger.log("1: {}", cache.get("1"));
    //null
    logger.log("2: {}", cache.getOrNull("2"));
  }
}
```

##### Kotlin Examples

```kotlin
class CacheSample {

  @Test
  fun testCache() {
    val cache = Cache.newFastCache<String, String>()
    cache.getOrLoad("1") { k: String -> k }
    //1
    logger.log("1: {}", cache.get("1"))
    //null
    logger.log("2: {}", cache.getOrNull("2"))
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-collect"/>Collect

Collect包提供集合和数组的接口, 工具和Ops操作:

* Collects: Collection工具;
* ArrayCollects: 数组工具;
* IterableOps, ListOps, SetOps, MapOps: Ops接口, 提供链式操作, 主要用于Java;
* SequenceOps: Sequence的Ops接口;
* IterableType, MapType: 泛型集合的元类型接口.

##### Java Examples

```java
public class CollectSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testArray() {
    String[] strings = ArrayCollects.newArray("1", "2", "3");
    ArrayCollects.asList(strings).set(0, "111");
    //111
    logger.log("string[0]: {}", strings[0]);
  }

  @Test
  public void testCollect() {
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    ListOps<String> listOps = ListOps.opsFor(list);
    int sum = listOps.addAll(ArrayCollects.newArray("4", "5", "6"))
        .removeFirst()
        .map(it -> it + "0")
        .map(Nums::toInt)
        .reduce(Integer::sum);
    //200
    logger.log("sum: {}", sum);
  }
}
```

#### <a id="usage-core-convert"/>Convert

Convert包提供类型转换功能:

* Converts: 转换工具;
* Converter: 类型转换的核心接口, Converts使用它的默认实现;
* FastConverter: 快速和功能窄化版本的Converter.

##### Java Examples

```java
public class ConvertSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testConvert() {
    String s = Converts.convert(123, String.class);
    //123
    logger.log("s: {}", s);

    A a = new A();
    a.setP1("1");
    a.setP2("2");
    B b = Converts.convert(a, B.class);
    //1
    logger.log("b1: {}", b.getP1());
    //2
    logger.log("b1: {}", b.getP2());

    FastConverter<String> fastConverter = FastConverter.newFastConverter(
        Arrays.asList(new ObjectConvertHandler(), new StringConvertHandler()));
    //123
    logger.log(fastConverter.convert(new StringBuilder("123")));
    //123123
    logger.log(fastConverter.convert("123"));
  }

  public static class A {
    private String p1;
    private String p2;

    public String getP1() {
      return p1;
    }

    public void setP1(String p1) {
      this.p1 = p1;
    }

    public String getP2() {
      return p2;
    }

    public void setP2(String p2) {
      this.p2 = p2;
    }
  }

  public static class B {
    private int p1;
    private int p2;

    public int getP1() {
      return p1;
    }

    public void setP1(int p1) {
      this.p1 = p1;
    }

    public int getP2() {
      return p2;
    }

    public void setP2(int p2) {
      this.p2 = p2;
    }
  }

  private static class ObjectConvertHandler implements FastConvertHandler<String> {

    @NotNull
    @Override
    public Class<?> supportedType() {
      return Object.class;
    }

    @Override
    public String convert(@NotNull Object from) {
      return from.toString();
    }
  }

  private static class StringConvertHandler implements FastConvertHandler<String> {

    @NotNull
    @Override
    public Class<?> supportedType() {
      return String.class;
    }

    @Override
    public String convert(@NotNull Object from) {
      return from.toString() + from.toString();
    }
  }
}
```

##### Kotlin Examples

```kotlin
class ConvertSample {

  @Test
  fun testConvert() {
    val s = 123.convert(String::class.java)
    //123
    logger.log("s: {}", s)
    val a = A()
    a.p1 = "1"
    a.p2 = "2"
    val b = a.convert(
      B::class.java
    )
    //1
    logger.log("b1: {}", b.p1)
    //2
    logger.log("b1: {}", b.p2)

    val fastConverter = newFastConverter(listOf(ObjectConvertHandler(), StringConvertHandler()))
    //123
    //123
    logger.log(fastConverter.convert(StringBuilder("123")))
    //123123
    //123123
    logger.log(fastConverter.convert("123"))
  }


  companion object {
    private val logger = TestLogger.DEFAULT
  }
}

class A {
  var p1: String? = null
  var p2: String? = null
}

class B {
  var p1 = 0
  var p2 = 0
}

private class ObjectConvertHandler : FastConvertHandler<String> {

  override val supportedType: Class<*> = Any::class.java

  override fun convert(from: Any): String {
    return from.toString()
  }
}

private class StringConvertHandler : FastConvertHandler<String> {

  override val supportedType: Class<*> = String::class.java

  override fun convert(from: Any): String {
    return from.toString() + from.toString()
  }
}
```

#### <a id="usage-core-exception"/>Exception

Exception包提供StatusException和ExceptionStatus, 继承自State (参考[State](#usage-core-state)), 还有一个ShouldNotException.

##### Java Examples

```java
public class ExceptionSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testStatusException() {
    SampleException sampleException = new SampleException();
    //000001-Unknown Error[for sample]
    logger.log("Status: {}", sampleException.withMoreDescription("for sample"));
  }

  public static class SampleException extends StatusException {

    public SampleException() {
      super(ExceptionStatus.UNKNOWN);
    }
  }
}
```

##### Kotlin Examples

```kotlin
class ExceptionSample {

  @Test
  fun testStatusException() {
    val sampleException = SampleException()
    //000001-Unknown Error[for sample]
    logger.log("Status: {}", sampleException.withMoreDescription("for sample"))
  }

  class SampleException : StatusException(ExceptionStatus.UNKNOWN)

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-invoke"/>Invoke

Invoke包提供Invoker接口去调用方法和函数.

##### Java Examples

```java
public class InvokeSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testInvoke() throws Exception {
    Invoker invoker = Invoker.forMethod(String.class.getMethod("getBytes"));
    byte[] bytes = invoker.invoke("10086");
    //[49, 48, 48, 56, 54]
    logger.log("bytes: {}", bytes);
  }
}
```

##### Kotlin Examples

```kotlin
class InvokeSample {

  @Test
  fun testInvoke() {
    val invoker: Invoker = Invoker.forMethod(String::class.java, "getBytes")
    val bytes = invoker.invoke<ByteArray>("10086")
    //[49, 48, 48, 56, 54]
    logger.log("bytes: {}", bytes)
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-jvm"/>Jvm

Jvm包提供Jvms工具类.

##### Java Examples

```java
public class JvmSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testJvms() {
    String jvmDescriptor = Jvms.jvmDescriptor(int.class);
    //I
    logger.log("jvmDescriptor: {}", jvmDescriptor);
  }
}
```

##### Kotlin Examples

```kotlin
class JvmSample {

  @Test
  fun testJvms() {
    val jvmDescriptor = Int::class.javaPrimitiveType!!.jvmDescriptor
    //I
    logger.log("jvmDescriptor: {}", jvmDescriptor)
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-proxy"/>Proxy

Proxy提供类代理功能, 底层使用spring-cglib, cglib或者JDK proxy.

##### Java Examples

```java
public class ProxySample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testProxy() {
    ProxyClass<Object> proxyClass = ProxyClass.newProxyClass(
        Object.class,
        Arrays.asList(
            new ProxyMethod<Object>() {
              @NotNull
              @Override
              public String name() {
                return "toString";
              }

              @NotNull
              @Override
              public Class<?>[] parameterTypes() {
                return new Class[0];
              }

              @Nullable
              @Override
              public Object invoke(
                  Object proxied,
                  @NotNull Method proxiedMethod,
                  @Nullable Object[] args, @NotNull SuperInvoker superInvoker
              ) {
                return "Proxy[super: " + superInvoker.invoke(args) + "]";
              }
            }
        )
    );
    String s = proxyClass.newInstance().toString();
    //Proxy[super: net.sf.cglib.empty.Object$$EnhancerByCGLIB$$4926690c@256f38d9]
    logger.log("s: {}", s);
  }
}
```

##### Kotlin Examples

```kotlin
class ProxySample {

  @Test
  fun testProxy() {
    val proxyClass = newProxyClass(
      Any::class.java,
      listOf(
        object : ProxyMethod<Any> {
          override val name: String
            get() {
              return "toString"
            }

          override val parameterTypes: Array<Class<*>>
            get() {
              return emptyArray()
            }

          override fun invoke(
            proxied: Any,
            proxiedMethod: Method,
            args: Array<out Any?>?, superInvoker: SuperInvoker
          ): Any? {
            return "Proxy[super: " + superInvoker.invoke(args) + "]"
          }
        }
      )
    )
    val s = proxyClass.newInstance().toString()
    //Proxy[super: net.sf.cglib.empty.Object$$EnhancerByCGLIB$$4926690c@256f38d9]
    logger.log("s: {}", s)
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-reflect"/>Reflect

Reflect包提供相关工具类:

* Reflects: 提供反射操作;
* Types: 用来创建泛型类型;
* TypeRef: 用来获取类型引用.

##### Java Examples

```java
public class ReflectSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testReflects() {
    Method method = Reflects.method(Object.class, "toString");
    String s = Reflects.invoke(method, new Object());
    //java.lang.Object@97c879e
    logger.log("s: {}", s);
  }

  @Test
  public void testTypes() {
    ParameterizedType type = Types.parameterizedType(List.class, String.class);
    GenericArrayType arrayType = Types.genericArrayType(type);
    //java.util.List<java.lang.String>[]
    logger.log("arrayType: {}", arrayType);
  }
}
```

##### Kotlin Examples

```kotlin
class ReflectSample {

  @Test
  fun testReflects() {
    val method = Any::class.java.method("toString")
    val s = method.invoke<String>(Any())
    //java.lang.Object@97c879e
    logger.log("s: {}", s)
  }

  @Test
  fun testTypes() {
    val type = parameterizedType(MutableList::class.java, String::class.java)
    val arrayType = type.genericArrayType()
    //java.util.List<java.lang.String>[]
    logger.log("arrayType: {}", arrayType)
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-run"/>Run

Run包提供Runner和Scheduler接口来运行一段代码, 可以在一个线程或者协程里.

##### Java Examples

```java
public class RunSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testRunner() {
    Runner runner = Runner.SYNC_RUNNER;
    IntRef intRef = IntRef.of(0);
    Running<?> running = runner.run(() -> {
      intRef.set(666);
      return null;
    });
    running.get();
    //666
    logger.log("int: {}", intRef.get());
  }

  @Test
  public void testScheduledRunner() {
    Scheduler scheduler = Scheduler.DEFAULT_THREAD_SCHEDULER;
    IntRef intRef = IntRef.of(0);
    Scheduling<?> scheduling = scheduler.scheduleFixedDelay(Duration.ZERO, Duration.ofMillis(1000), () -> {
      intRef.set(intRef.get() + 100);
      return null;
    });
    Current.sleep(2500);
    scheduling.cancel(false);
    //300
    logger.log("int: {}", intRef.get());
  }
}
```

##### Kotlin Examples

```kotlin
class RunSample {

  @Test
  fun testRunner() {
    val runner: Runner = Runner.SYNC_RUNNER
    val intRef = of(0)
    val running: Running<*> = runner.run<Any?> {
      intRef.set(666)
      null
    }
    running.get()
    //666
    logger.log("int: {}", intRef.get())
  }

  @Test
  fun testScheduledRunner() {
    val scheduler = Scheduler.DEFAULT_THREAD_SCHEDULER
    val intRef = of(0)
    val scheduling: Scheduling<*> = scheduler.scheduleFixedDelay<Any?>(Duration.ZERO, Duration.ofMillis(1000)) {
      intRef.set(intRef.get() + 100)
      null
    }
    sleep(2500)
    scheduling.cancel(false)
    //300
    logger.log("int: {}", intRef.get())
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-state"/>State

State包提供State接口来复制定制状态概念的类型.

##### Java Examples

```java
public class StateSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testState() {
    MyState myState = new MyState(1, "description");
    MyState newState = myState.withMoreDescription("cause");
    //description[cause]
    logger.log(newState.description());
  }

  public static class MyState implements State<Integer, String, MyState> {

    private final int code;
    private final String description;

    public MyState(int code, String description) {
      this.code = code;
      this.description = description;
    }

    @Override
    public Integer code() {
      return code;
    }

    @Nullable
    @Override
    public String description() {
      return description;
    }

    @NotNull
    @Override
    public MyState withNewDescription(@Nullable String newDescription) {
      return new MyState(code, newDescription);
    }

    @NotNull
    @Override
    public MyState withMoreDescription(@Nullable String moreDescription) {
      return new MyState(code, State.moreDescription(description, moreDescription));
    }
  }
}
```

##### Kotlin Examples

```kotlin
class StateSample {

  @Test
  fun testState() {
    val myState = MyState(1, "description")
    val newState = myState.withMoreDescription("cause")
    //description[cause]
    logger.log(newState.description)
  }

  class MyState(override val code: Int, override val description: String?) :
    State<Int, String, MyState> {

    override fun withNewDescription(newDescription: String?): MyState {
      return MyState(code, newDescription)
    }

    override fun withMoreDescription(moreDescription: String?): MyState {
      return MyState(code, description.stateMoreDescription(moreDescription))
    }
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

#### <a id="usage-core-test"/>Test

Test包提供简单的测试工具:

* Tester: 一个用来启动测试任务的接口;
* TestLogger: 简单的测试日志;
* TestTask and TestListener: 测试任务和监听器;
* Tests: 测试工具类.

##### Java Examples

```java
public class TestSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testTests() {
    Tests.testTasks(Arrays.asList(
        TestTask.newTask(() -> {
          logger.log("Run test task!");
        })
    ));
  }
}
```

##### Kotlin Examples

```kotlin
class TestSample {

  @Test
  fun testTests() {
    testTasks(
      listOf(
        TestTask.newTask { logger.log("Run test task!") }
      )
    )
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

### <a id="usage-serialize"/>Boat Serialize (boat-serialize)

Boat Serializer(需要导入boat-serialize)提供序列化核心接口:

* Serializer: 序列化核心接口;
* JsonSerials: JSON序列化工具;
* JsonSerial: JSON序列化核心接口, JsonSerials使用它的默认实现(Jackson);
* Json: JSON的核心接口, 代表一个JSON对象.

#### Java Examples

```java
public class SerializeSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testJsonSerialize() {
    Json json = JsonSerials.toJson("{\"p1\":\"p1 value\",\"p2\":\"p2 value\"}");
    Map<String, String> map = json.toObject(new TypeRef<Map<String, String>>() {
    });
    //{p1=p1 value, p2=p2 value}
    logger.log(map);
  }
}
```

#### Kotlin Examples

```kotlin
class SerializeSample {

  @Test
  fun testJsonSerialize() {
    val json = "{\"p1\":\"p1 value\",\"p2\":\"p2 value\"}".toJson()
    val map: Map<String, String> = json.toObject(object : TypeRef<Map<String, String>>() {})
    //{p1=p1 value, p2=p2 value}
    logger.log(map)
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

### <a id="usage-codec"/>Boat Codec (boat-codec)

Boat Codec (需要引入boat-codec)提供Codec, CodecKeys, AesKeys 以及 其他接口来实现编码功能, 支持hex, base64, AES, RSA, SM2以及更多算法:

* Codec: 编码功能的核心接口;
* CodecKeys: 编码秘钥工具;
* AesKeys: AES秘钥工具.

#### Java Examples

```java
public class CodecSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testCodec() {
    String password = "hei, xiongdi, womenhaojiubujiannizainali";
    String messageBase64 = "aGVpLCBwZW5neW91LCBydWd1b3poZW5kZXNoaW5pcWluZ2Rhemhhb2h1";
    SecretKey secretKey = AesKeys.newKey(password);

    //Use static
    String message = Codec.decodeBase64String(messageBase64);
    byte[] encrypt = Codec.aesCipher().encrypt(secretKey, message);
    String decrypt = Codec.aesCipher().decryptToString(secretKey, encrypt);
    //hei, pengyou, ruguozhendeshiniqingdazhaohu
    logger.log("decrypt: {}", decrypt);

    //Use chain
    encrypt = Codec.forData(messageBase64).decodeBase64().encryptAes(secretKey).doFinal();
    decrypt = Codec.forData(encrypt).decryptAes(secretKey).doFinalToString();
    //hei, pengyou, ruguozhendeshiniqingdazhaohu
    logger.log("decrypt: {}", decrypt);
  }
}
```

#### Kotlin Examples

```kotlin
class CodecSample {

  @Test
  fun testCodec() {
    val password = "hei, xiongdi, womenhaojiubujiannizainali"
    val messageBase64 = "aGVpLCBwZW5neW91LCBydWd1b3poZW5kZXNoaW5pcWluZ2Rhemhhb2h1"
    val secretKey = password.toAesKey()

    //Use static
    val message: String = messageBase64.decodeBase64String()
    var encrypt = Codec.aesCipher().encrypt(secretKey, message)
    var decrypt = Codec.aesCipher().decryptToString(secretKey, encrypt)
    //hei, pengyou, ruguozhendeshiniqingdazhaohu
    logger.log("decrypt: {}", decrypt)

    //Use chain
    encrypt = Codec.forData(messageBase64).decodeBase64().encryptAes(secretKey).doFinal()
    decrypt = Codec.forData(encrypt).decryptAes(secretKey).doFinalToString()
    //hei, pengyou, ruguozhendeshiniqingdazhaohu
    logger.log("decrypt: {}", decrypt)
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

### <a id="usage-id"/>Boat Id (boat-id)

Boat Id (需要引入boat-id)是一个轻量级id生成框架. 它提供IdFactory和一套接口来生成id:

* IdFactory: 核心接口, 用来生成新id;
* IdComponentFactory: 核心接口, 用来生成新id的一部分;
* AbstractIdFactory: IdFactory骨架实现, 用来辅助实现完整的IdFactory;
* StringIdFactory: IdFactory骨架实现, 用来辅助实现String类型id的IdFactory;

Boat ID同时提供StringIdSpec类, 一个String类型id的IdFactory, 可以从给定的字符串说明中生成新id, 请去看它的javadoc.

#### Java Examples

```java
public class IdSample {

  private static final TestLogger logger = TestLogger.DEFAULT;

  @Test
  public void testId() {
    String spec = "seq-{timeCount, yyyyMMddHHmmssSSS, 1023, %17s%04d}-tail";
    StringIdSpec stringIdSpec = new StringIdSpec(spec);
    //seq-202102071449568890000-tail
    for (int i = 0; i < 10; i++) {
      logger.log(stringIdSpec.create());
    }
  }
}
```

#### Kotlin Examples

```kotlin
class IdSample {

  @Test
  fun testId() {
    val spec = "seq-{timeCount, yyyyMMddHHmmssSSS, 1023, %17s%04d}-tail"
    val stringIdSpec = StringIdSpec(spec)
    //seq-202102071449568890000-tail
    for (i in 0..9) {
      logger.log(stringIdSpec.create())
    }
  }

  companion object {
    private val logger = TestLogger.DEFAULT
  }
}
```

## <a id="contact"/>贡献和联系方式

* fredsuvn@163.com
* https://github.com/srclab-projects/boat
* QQ群: 1037555759

## <a id="license"/>License

[Apache 2.0 license][license]

[license]: https://www.apache.org/licenses/LICENSE-2.0.html