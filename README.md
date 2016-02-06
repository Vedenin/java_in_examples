## This is collection of java code examples, best practice and useful article and link for Java

You can find following code examples:
- I. Compare collections in Apache, Guava and Gs Collection:
-   1. BiMap collection in Apache, Guava and JDK analog
-   2. MultiMap collection in Apache, Guava, Gs Collection and JDK analog
-   3. Multiset collection in Apache, Guava, Gs Collection and JDK analog
- II. Compare operations with collections in Apache, Guava, Gs Collection and JDK:
- 1. Operations for comparison collections (containsAll, containsAny, intersect, difference, symmetric difference, union)
- 2. Operations for searching in collections (count, getFirst, getLast, getSingle, getMax, getMin, binarySearch, search, getByIndex, select)
- 3. Create collections (ArrayList, HashSet, HashMap)
- 4. Transform collections (sort, remove, retain, transform, changeAll)

### java Collections and Stream Api in Examples

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

