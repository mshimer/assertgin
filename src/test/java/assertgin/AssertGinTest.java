package assertgin;

import assertgin.fixture.BigBean;
import assertgin.fixture.SmallBean;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static assertgin.AssertGin.assertIt;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class AssertGinTest {


	private String imports;

	private Map<String, Byte> byteMap = new LinkedHashMap<>();
	private Map<String, Short> shortMap = new LinkedHashMap<>();
	private Map<String, Integer> integerMap = new LinkedHashMap<>();
	private Map<String, Long> longMap = new LinkedHashMap<>();
	private Map<String, Float> floatMap = new LinkedHashMap<>();
	private Map<String, Double> doubleMap = new LinkedHashMap<>();
	private Map<String, Character> charMap = new LinkedHashMap<>();
	private Map<String, Boolean> booleanMap = new LinkedHashMap<>();
	private Map<String, BigBean> beanMap = new LinkedHashMap<>();

	private List<Byte> byteList;
	private List<Short> shortList;
	private List<Integer> integerList;
	private List<Long> longList;
	private List<Float> floatList;
	private List<Double> doubleList;
	private List<Character> characterList;
	private List<Boolean> booleanList;
	private List<BigBean> beanList;

	private Set<Byte> byteSet = new HashSet<>();
	private Set<Short> shortSet = new HashSet<>();
	private Set<Integer> integerSet = new HashSet<>();
	private Set<Long> longSet= new HashSet<>();
	private Set<Float> floatSet= new HashSet<>();
	private Set<Double> doubleSet= new HashSet<>();
	private Set<Character> characterSet = new HashSet<>();
	private Set<Boolean> booleanSet= new HashSet<>();
	private Set<BigBean> beanSet = new HashSet<>();

	private BigBean bean1, bean2, nested;

	private Compiler compiler;
	private final byte[] byteArray;
	private final short[] shortArray;
	private final int[] intArray;
	private final long[] longArray;
	private final float[] floatArray;
	private final double[] doubleArray;
	private final char[] charArray;
	private final boolean[] booleanArray;
	private final BigDecimal[] bigDecimals;
	private final Date[] dateArray;
	private final BigBean[] beanArray;
	private final Object[] objectArray;

	public AssertGinTest(){

		byteArray = new byte[3];
		byteArray[0] = (byte) 1;
		byteArray[1] = (byte) 2;
		byteArray[2] = (byte) 3;

		shortArray = new short[3];
		shortArray[0] = (short) 1;
		shortArray[1] = (short) 2;
		shortArray[2] = (short) 3;

		intArray = new int[3];
		intArray[0] = 1;
		intArray[1] = 2;
		intArray[2] = 3;

		longArray = new long[3];
		longArray[0] = 1L;
		longArray[1] = 2L;
		longArray[2] = (long) 3;

		floatArray = new float[3];
		floatArray[0] = 1.1F;
		floatArray[1] = (float) 2.2;
		floatArray[2] = 3.3F;

		doubleArray = new double[3];
		doubleArray[0] = (double) 1;
		doubleArray[1] = 2D;
		doubleArray[2] = 3D;

		charArray = new char[4];
		charArray[0] = 'c';
		charArray[1] = 'o';
		charArray[2] = 'o';
		charArray[3] = 'L';

		booleanArray = new boolean[3];
		booleanArray[0] = true;
		booleanArray[1] = false;
		booleanArray[2] = true;

		bigDecimals = new BigDecimal[3];
		bigDecimals[0] = new BigDecimal("1");
		bigDecimals[1] = new BigDecimal("2");
		bigDecimals[2] = new BigDecimal("3");

		dateArray = new Date[3];
		dateArray[0] = new Date();
		dateArray[1] = new Date();
		dateArray[2] = new Date();

		beanArray = new BigBean[3];
		beanArray[0] = bean1;
		beanArray[1] = bean2;
		beanArray[2] = null;

		objectArray = new Object[3];
		objectArray[0] = byteSet;
		objectArray[1] = longList;
		objectArray[2] = beanMap;

		byteList = asList( (byte) 1,  (byte) 2,  (byte) 3);
		shortList = asList( (short) 1,  (short) 2,  (short) 3);
		integerList = asList( 1,  2,  3);
		longList = asList( 1L,  2L,  3L);
		floatList = asList( 1F,  2F,  3F);
		doubleList = asList( 1D,  2D,  3D);
		characterList = asList( 'a',  'b',  'c');
		booleanList = asList( true,  false,  true);

		byteMap.put("first", (byte) 1);
		byteMap.put("second", (byte) 2);
		byteMap.put("third", (byte) 3);

		shortMap.put("first", (short) 1);
		shortMap.put("second", (short) 2);
		shortMap.put("third", (short) 3);

		integerMap.put("first",  1);
		integerMap.put("second",  2);
		integerMap.put("third",  3);

		longMap.put("first",  1L);
		longMap.put("second",  2L);
		longMap.put("third",  3L);

		floatMap.put("first",  1.1F);
		floatMap.put("second",  2.2F);
		floatMap.put("third",  3.2F);

		doubleMap.put("first",  1D);
		doubleMap.put("second",  2D);
		doubleMap.put("third",  3D);

		charMap.put("first",  'a');
		charMap.put("second",  'b');
		charMap.put("third",  'c');

		booleanMap.put("first",  true);
		booleanMap.put("second",  false);
		booleanMap.put("third",  true);

		byteSet.addAll(byteList);
		shortSet.addAll(shortList);
		integerSet.addAll(integerList);
		longSet.addAll(longList);
		floatSet.addAll(floatList);
		doubleSet.addAll(doubleList);
		characterSet.addAll(characterList);
		booleanSet.addAll(booleanList);

		bean1 = new BigBean((byte) 1, (short) 2, 3, 4, 5, 6, 'a', true, "hi");
		bean2 = new BigBean((byte) 2, (short) 3, 4, 5, 6, 6, 'b', true, "hi!");
		nested = new BigBean((byte) 4, (short) 5, 6, 7, 8, 9, 'c', true, "nested");
		nested.setBigBean(bean1);
		nested.setSmallBean(new SmallBean(3, "small"));

		beanSet.add(bean1);
		beanSet.add(bean2);

		beanList = asList(bean1, bean2);

		beanMap.put("b1", bean1);
		beanMap.put("b2", bean2);

		compiler = new Compiler();

		imports = "import assertgin.fixture.BigBean;\n" +
				"import assertgin.fixture.SmallBean;\n";

	}

	// SETS


	@Test
	public void testByteSet(){

		String code = assertIt(byteSet, "set");
		System.out.println(code);
		assertTrue(compiler.runsWithoutException(byteSet, "set", "Set", "", code));
	}


	@Test
	public void testShortSet(){

		String out = assertIt(shortSet, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(shortSet, "set", "Set", imports, out));
	}

	@Test
	public void testIntSet(){

		String out = assertIt(integerSet, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(integerSet, "set", "Set", imports, out));
	}

	@Test
	public void testLongSet(){

		String out = assertIt(longSet, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(longSet, "set", "Set", imports, out));
	}

	@Test
	public void testFloatSet(){

		String out = assertIt(floatSet, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(floatSet, "set", "Set", imports, out));
	}

	@Test
	public void testDoubleSet(){

		String out = assertIt(doubleSet, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(doubleSet, "set", "Set", imports, out));
	}

	@Test
	public void testCharacterSet(){

		String out = assertIt(characterSet, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(characterSet, "set", "Set", imports, out));
	}

	@Test
	public void testBooleanSet(){

		String out = assertIt(booleanSet, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(booleanSet, "set", "Set", imports, out));
	}

	// LISTS

	@Test
	public void testByteList(){

		String out = assertIt(byteList, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(byteList, "list", "List", imports, out));
	}

	@Test
	public void testShortList(){

		String out = assertIt(shortList, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(shortList, "list", "List", imports, out));
	}

	@Test
	public void testIntList(){

		String out = assertIt(integerList, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(integerList, "list", "List", imports, out));
	}

	@Test
	public void testLongList(){

		String out = assertIt(longList, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(longList, "list", "List", imports, out));
	}

	@Test
	public void testFloatList(){

		String out = assertIt(floatList, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(floatList, "list", "List", imports, out));
	}

	@Test
	public void testDoubleList(){

		String out = assertIt(doubleList, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(doubleList, "list", "List", imports, out));
	}

	@Test
	public void testCharacterList(){

		String out = assertIt(characterList, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(characterList, "list", "List", imports, out));
	}

	@Test
	public void testBooleanList(){

		String out = assertIt(booleanList, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(booleanList, "list", "List", imports, out));
	}

	// MAPS

	@Test
	public void testByteMap() {

		String out = assertIt(byteMap, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(byteMap, "map", "Map", imports, out));
	}

	@Test
	public void testShortMap() {

		String out = assertIt(shortMap, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(shortMap, "map", "Map", imports, out));
	}

	@Test
	public void testIntegerMap() {

		String out = assertIt(integerMap, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(integerMap, "map", "Map", imports, out));
	}

	@Test
	public void testLongMap() {

		String out = assertIt(longMap, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(longMap, "map", "Map", imports, out));
	}

	@Test
	public void testFloatMap() {

		String out = assertIt(floatMap, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(floatMap, "map", "Map", imports, out));
	}

	@Test
	public void testDoubleMap() {

		String out = assertIt(doubleMap, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(doubleMap, "map", "Map", imports, out));
	}

	@Test
	public void testCharMap() {

		String out = assertIt(charMap, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(charMap, "map", "Map", imports, out));
	}

	@Test
	public void testBooleanMap() {

		String out = assertIt(booleanMap, "booleanMap");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(booleanMap, "booleanMap", "Map", imports, out));
	}

	// BEANS

	@Test
	public void testLeafBean() {
		String out = assertIt(bean1, "bean1");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(bean1, "bean1", "BigBean", imports, out));
	}

	@Test
	public void testSetOfComparableBeans() {
		String out = assertIt(beanSet, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(beanSet, "set", "Set", imports, out));
	}

	@Test
	public void testSetOfNonComparableBeans() {

		Set<SmallBean> ncSet = new HashSet<>();
		ncSet.add(new SmallBean(5, "fiver"));
		ncSet.add(new SmallBean(10, "ten spot"));

		String out = assertIt(ncSet, "ncSet");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(ncSet, "ncSet", "Set", imports, out));
	}

	@Test
	public void testListOfBeans() {

		String out = assertIt(beanList, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(beanList, "list", "List", imports, out));
	}

	@Test
	public void testMapOfBeans() {

		String out = assertIt(beanMap, "beanMap");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(beanMap, "beanMap", "Map", imports, out));
	}

	// COMPLEX

	@Test
	public void testSetOfSets() {
		Set set = new HashSet<>();
		set.add(integerSet);
		set.add(booleanSet);
		String out = assertIt(set, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(set, "set", "Set", imports, out));
	}

	@Test
	public void testListOfLists() {
		List list = asList(integerList, booleanList);
		String out = assertIt(list, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(list, "list", "List", imports, out));

	}

	@Test
	public void testMapOfMaps() {

		Map map = new HashMap();
		map.put("a", integerMap);
		map.put("b", floatMap);
		String out = assertIt(map, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(map, "map", "Map", imports, out));
	}

	@Test
	public void testMapOfSets() {
		Map map = new HashMap();
		map.put("i", integerSet);
		map.put("f", floatSet);
		String out = assertIt(map, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(map, "map", "Map", imports, out));
	}

	@Test
	public void testMapOfLists() {
		Map map = new HashMap();
		map.put("i", integerList);
		map.put("f", floatList);
		String out = assertIt(map, "map");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(map, "map", "Map", imports, out));
	}

	@Test
	public void testSetOfMaps() {
		Set set = new HashSet<>();
		set.add(floatMap);
		set.add(byteMap);
		String out = assertIt(set, "set");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(set, "set", "Set", imports, out));
	}

	@Test
	public void testListOfMaps() {
		List list = asList(doubleMap, charMap);
		String out = assertIt(list, "list");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(list, "list", "List", imports, out));
	}

	@Test
	public void testBeanOfBeans() {
		String out = assertIt(nested, "nestedBean");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(nested, "nestedBean", "BigBean", imports, out));

	}

	@Test
	public void testComplexCombo() {
		Map map1 = new HashMap();
		map1.put("i", integerList);
		map1.put("f", floatSet);
		map1.put("c", charMap);
		map1.put("l", asList(nested));

		Map map2 = new HashMap();
		map2.put("map1", map1);
		map2.put("list2", longList);
		map2.put("set", characterSet);

		List all = asList(map1, map2, objectArray);

		String out = assertIt(all, "all");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(all, "all", "List", imports, out, false));
	}

	// NULLS

	@Test
	public void testSetNulls() {
		Set set = new HashSet<>();
		set.add(null);
		String out = assertIt(set, "set");
		System.out.println(out);
		assertThat(out.contains("nullValue()"), is(true));
		assertTrue(compiler.runsWithoutException(set, "set", "Set", imports, out));
	}

	@Test
	public void testSetNullsIgnored() {
		Set set = new HashSet<>();
		set.add(null);
		AssertGinContext context = new AssertGinContext("list");
		context.setIgnoreNulls(true);
		String out = assertIt(set, "set", context);
		System.out.println(out);
		assertThat(out.contains("nullValue()"), is(false));
		assertTrue(compiler.runsWithoutException(set, "set", "Set", imports, out));
	}

	@Test
	public void testListNulls() {
		List<Object> list = asList(null, null, null);
		String out = assertIt(list, "list");
		System.out.println(out);
		assertThat(out.contains("nullValue()"), is(true));
		assertTrue(compiler.runsWithoutException(list, "list", "List", imports, out));
	}

	@Test
	public void testListNullsIgnored() {
		List<Object> list = asList(null, null, null);
		AssertGinContext context = new AssertGinContext("list");
		context.setIgnoreNulls(true);
		String out = assertIt(list, "list", context);
		assertThat(out.contains("nullValue()"), is(false));
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(list, "list", "List", imports, out));
	}

	@Test
	public void testMapNullValue() {
		Map map = new HashMap();
		map.put("a", null);
		map.put("b", null);
		map.put("c", null);
		map.put(null, null);

		String out = assertIt(map, "map");
		assertThat(out.contains("nullValue()"), is(true));
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(map, "map", "Map", imports, out));
	}

	@Test
	public void testMapNullValueIgnored() {
		Map map = new HashMap();
		map.put("a", null);
		map.put("b", null);
		map.put("b", null);
		map.put(null, null);

		AssertGinContext context = new AssertGinContext("map");
		context.setIgnoreNulls(true);
		String out = assertIt(map, "map", context);
		assertThat(out.contains("nullValue()"), is(false));
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(map, "map", "Map", imports, out));

	}

	@Test
	public void testMapKeyTypes() {
		Map map = new HashMap();
		map.put("string", null);
		map.put(true, null);
		map.put(1, null);
		map.put(1L, null);
		map.put(1F, null);
		map.put(1D, null);
		map.put((byte)1, null);
		map.put((short)1, null);
		map.put('c', null);

		String out = assertIt(map, "map");
		System.out.println(out);
		assertThat(out.contains("nullValue()"), is(true));
		assertTrue(compiler.runsWithoutException(map, "map", "Map", imports, out));

	}

	@Test
	public void testNullBeanLeaves() {
		SmallBean bean = new SmallBean(1, null);
		String out = assertIt(bean, "bean");
		System.out.println(out);
		assertThat(out.contains("nullValue()"), is(true));
		assertTrue(compiler.runsWithoutException(bean, "bean", "SmallBean", imports, out));
	}

	@Test
	public void testNullBeanLeavesIgnored() {
		SmallBean bean = new SmallBean(1, null);
		AssertGinContext context = new AssertGinContext("bean");
		context.setIgnoreNulls(true);
		String out = assertIt(bean, "bean", context);
		assertThat(out.contains("nullValue()"), is(false));
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(bean, "bean", "SmallBean", imports, out));
	}

    // ARRAYS

	@Test
	public void testByteArray(){

		String out = assertIt(byteArray, "byteArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(byteArray, "byteArray", "byte[]", imports, out));
	}

	@Test
	public void testShortArray(){
		String out = assertIt(shortArray, "shortArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(shortArray, "shortArray", "short[]", imports, out));
	}


	@Test
	public void testIntegerArray(){
		String out = assertIt(intArray, "intArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(intArray, "intArray", "int[]", imports, out));
	}

	@Test
	public void testLongArray(){
		String out = assertIt(longArray, "longArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(longArray, "longArray", "long[]", imports, out));
	}

	@Test
	public void testFloatArray(){
		String out = assertIt(floatArray, "floatArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(floatArray, "floatArray", "float[]", imports, out));
	}

	@Test
	public void testDoubleArray(){
		String out = assertIt(doubleArray, "doubleArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(doubleArray, "doubleArray", "double[]", imports, out));
	}

	@Test
	public void testCharArray(){
		String out = assertIt(charArray, "charArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(charArray, "charArray", "char[]", imports, out));
	}

	@Test
	public void testBooleanArray(){
		String out = assertIt(booleanArray, "booleanArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(booleanArray, "booleanArray", "boolean[]", imports, out));
	}

	@Test
	public void testBigDecimalArray(){
		String out = assertIt(bigDecimals, "bigDecimals");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(bigDecimals, "bigDecimals", "BigDecimal[]", imports, out));
	}

	@Test
	public void testDateArray(){
		String out = assertIt(dateArray, "dateArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(dateArray, "dateArray", "Date[]", imports, out));
	}

	@Test
	public void testBeanArray(){
		String out = assertIt(beanArray, "beanArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(beanArray, "beanArray", "BigBean[]", imports, out));
	}

	@Test
	public void testObjectArray() {
		String out = assertIt(objectArray, "objectArray");
		System.out.println(out);
		assertTrue(compiler.runsWithoutException(objectArray, "objectArray", "Object[]", imports, out));
	}


}

