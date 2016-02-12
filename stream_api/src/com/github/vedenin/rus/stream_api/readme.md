## This is collection of java code examples, best practice and useful article and link for Java

### Java code examples and best practice

You can find following code examples:
- [I. Compare collections in Apache, Guava, Gs Collection and JDK analog](https://github.com/Vedenin/java_in_examples/tree/master/collections/src/com/github/vedenin/eng/collections)
    - [1. BiMap collection](https://github.com/Vedenin/java_in_examples/tree/master/collections/src/com/github/vedenin/eng/collections/bimap)
    - [2. MultiMap collection](https://github.com/Vedenin/java_in_examples/tree/master/collections/src/com/github/vedenin/eng/collections/multimap)
    - [3. Multiset collection](https://github.com/Vedenin/java_in_examples/tree/master/collections/src/com/github/vedenin/eng/collections/multiset)


- [II. Compare operations with collections in Apache, Guava, Gs Collection and JDK](https://github.com/Vedenin/java_in_examples/tree/master/collections/src/com/github/vedenin/eng/collections/utils)
    - [1. Operations for comparison collections](https://github.com/Vedenin/java_in_examples/blob/master/collections/src/com/github/vedenin/eng/collections/utils/CollectionCompareTests.java)  (containsAll, containsAny, intersect, difference, symmetric difference, union)
    - [2. Operations for searching in collections](https://github.com/Vedenin/java_in_examples/blob/master/collections/src/com/github/vedenin/eng/collections/utils/CollectionSearchTests.java) (count, getFirst, getLast, getSingle, getMax, getMin, binarySearch, search, getByIndex, select)
    - [3. Create collections](https://github.com/Vedenin/java_in_examples/blob/master/collections/src/com/github/vedenin/eng/collections/utils/CreateCollectionTest.java) (ArrayList, HashSet, HashMap)
    - [4. Transform collections](https://github.com/Vedenin/java_in_examples/blob/master/collections/src/com/github/vedenin/eng/collections/utils/JavaTransformTest.java) (sort, remove, retain, transform, changeAll)

- [III. Examples of Stream API](https://github.com/Vedenin/java_in_examples/tree/master/stream_api/src/com/github/vedenin/eng/stream_api)

- [IV. Examples of Dependency injections](https://github.com/Vedenin/java_in_examples/tree/master/dependency_injection) in Dagger, Spring and Guice

- [V. Examples of Natural Language Processing ](https://github.com/Vedenin/java_in_examples/tree/master/html_parser/src/github/vedenin/url_parser/eng) using OpenNLP, Stanford NLP amd so on

- [VI. Examples of integration and html parsing](https://github.com/Vedenin/java_in_examples/tree/master/html_parser)

- [VII. Examples of operation with String and Stream](https://github.com/Vedenin/java_in_examples/tree/master/other/src/main/java/com/github/vedenin/eng/string_and_stream)

- [VII. Examples of operation with Array](https://github.com/Vedenin/java_in_examples/tree/master/other/src/main/java/com/github/vedenin/eng/arrays)


### Java Collections and Stream Api in Article

- [I. Stream API](#i-stream-api)
- [1. All way to create Stream in Java 8](#1-all-way-to-create-stream-in-java-8)
  
#### I. Stream API

###### 1. All way to create Stream in Java 8

Way to create stream	 | 	Template	 | 	Example
-------------	 | 	-------------	 | 	-------------
1. Classic: Create stream from collection	 | 	collection.stream()	 | 	 Collection<String> collection = Arrays.asList("a1", "a2", "a3");  <br/>     Stream<String> streamFromCollection = collection.stream();
2. Create stream from values	 | 	Stream.of(value1,… ,valueN)	 | 	Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
3. Create stream from array	 | 	Arrays.stream(array)	 | 	String[] array = {"a1","a2","a3"};   <br/>         Stream<String> streamFromArrays = Arrays.stream(array);        
3. Create stream from part of array	 | 	Arrays.stream(array, start, end)	 | 	String[] array = {"a1","a2","a3"};<br/>              Stream<String> streamFromArrays = Arrays.stream(array, 1, 2);        
4. Create stream from file (every row from file become element of stream)	 | 	Files.lines(file_path)	 | 	Stream<String> streamFromFiles = Files.lines(Paths.get("file.txt"));
5. Create stream from stirng (every char become element of stream)	 | 	"string".chars()	 | 	IntStream streamFromString = "123".chars();
6. Using Stream.builder	 | 	Stream.builder().add(...)....build()	 | 	Stream.builder().add("a1").add("a2").add("a3").build();
7. Create parallel stream from collection	 | 	collection.parallelStream()	 | 	Stream<String> stream = collection.parallelStream();
8. Create infinive strean using Stream.iterate	 | 	Stream.iterate(init_value, generate_expression)	 | 	Stream<Integer> streamFromIterate = Stream.iterate(1, n -> n + 1);
9. Create infinive strean using Stream.generate	 | 	Stream.generate(generate_expression)	 | 	Stream<String> streamFromGenerate = Stream.generate(() -> "a1");
10. Create stream from path	 | 	Files.list(file_path)	 | 	Stream<Path> streamFromPath = Files.list(Paths.get(""));
11. Create stream from finding files	 | 	Files.find(file_path, max_depth, mathcher)	 | 	Stream<Path> streamFromFind = Files.find(Paths.get(""), 10, (p,a) -> true);
11. Create stream from files tree	 | 	Files.walk(file_path)	 |         Stream<Path> streamFromFileTree = Files.walk(Paths.get(""));
12. Create stream from all entities of jar file	 | 	new JarFile(jar_file).stream()	 | 	…
13. Create stream from all entities of zip file	 | 	new ZipFile(zip_file).stream()	 | 	…
14. Create stream from iterator	 | StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false) | 	...
15. Create stream from iterable	 | 	StreamSupport.stream(iterable.spliterator(), false) | 	…
16. Create infinive stream from iterator	 | 	Stream.generate(iterator::next) | 	…
17. Create empty stream  | 	Stream.empty() |  Stream<String> streamEmpty = Stream.empty();
18. Create stream from Pattern  | 	Pattern.compile(reg_exp).splitAsStream(string) |  Stream<String> streamFromPattern = Pattern.compile(":").splitAsStream("a1:a2:a3");
19. Create stream from BufferedReader  | 	bufferedReader.lines() |  Stream<String> streamFromBufferedReader = bufferedReader.lines();
20. Create stream from Enum  | 	EnumSet.allOf(MyEnum.class).stream() | Stream<MyEnum> streamFromEnum = EnumSet.allOf(MyEnum.class).stream();

More examples [this](https://github.com/Vedenin/java_in_examples/blob/master/src/com/github/vedenin/eng/stream_api/BuildTests.java)

