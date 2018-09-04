# Assert Gin : A Java Assertion Generator

Generates code to assert an object's current runtime state

## Writing assertion code is boring...

## Wouldn't it be cool if you could just generate them?

## YES !

AssertGin allows Java developers to generate assertion code for runtime 
objects by adding one line of code to their test.

The generated assertions will be written to the IDE console or stdout.

Then you just paste them into your code..  Boom!

# Example - list of bytes
Example : 

**Let's say I'm writing a test..**

    @Test
	public void testByteList(){
	
	    Our result is a List of bytes = ( 1, 2, 3 )

	    .. Now I need to write some assertions ..boring.. 
    } 

 
 
**Now you can generate them with one line of code :** 
 
    // import static assertgin.AssertGin.assertIt;
    
	@Test
	public void testByteList(){
	    List<Byte> byteList = asList( (byte) 1,  (byte) 2,  (byte) 3);
	    		
	    System.out.println( assertIt(byteList, "list") );
	}
	
	// Will output this to the stdout, or IDE console :
	
    assertThat(list.size(), is(3));
    assertThat(list.get(0), is((byte) 1));
    assertThat(list.get(1), is((byte) 2));
    assertThat(list.get(2), is((byte) 3));
    
    // Then we paste these into the test :
    
	@Test
	public void testByteList(){
	
	    // test does some stuff that ends up with a byte list
	    
	    // List<Byte> byteList = asList( (byte) 1,  (byte) 2,  (byte) 3);

        // with our pasted in assertions.. 
        assertThat(list.size(), is(3));
        assertThat(list.get(0), is((byte) 1));
        assertThat(list.get(1), is((byte) 2));
        assertThat(list.get(2), is((byte) 3));
	}    


## Workflow

1. Write your test
1. Add this line for the object you want to generate assertions for:
    1. `System.out.println( assertIt(myVar, "myVar") );`
        1. `myVar` is the object to create assertions for
        1. `"myVar"` is the name of the object in the generated output
1. Run the test
1. Paste the generated assertion code from the console back into your test


**That's it.. run the test with the new assertions.**

Of course it's your job to make sure the runtime state of your object is
correct per your application.  AssertGin cannot figure that out..


## Collections
        
**That's great but what if I use an Array, Set, or Map ?**

    @Test
	public void testByteArray(){
        byte[] byteArray = new byte[3];
	    byteArray[0] = (byte) 1;
	    byteArray[1] = (byte) 2;
	    byteArray[2] = (byte) 3;
		
	    System.out.println( assertIt(byteArray, "byteArray") );
	}
	    
	// Arrays output like this :
	    
	assertThat(byteArray.length, is(3));
    assertThat(byteArray[0], is((byte) 1));
    assertThat(byteArray[1], is((byte) 2));
    assertThat(byteArray[2], is((byte) 3));
		

	@Test
	public void testByteSet(){
	    Set byteSet = new HashSet();
	    set.add((byte) 1);
	    set.add((byte) 2);
	    set.add((byte) 3);
	    
	    System.out.println( assertIt(byteSet, "byteSet") );
    }
    
    // Sets output like this : 
    
    List setAsList = new ArrayList();
    setAsList.addAll(set);
    Collections.sort(setAsList);
    assertThat(setAsList.size(), is(3));
    assertThat(setAsList.get(0), is((byte) 1));
    assertThat(setAsList.get(1), is((byte) 2));
    assertThat(setAsList.get(2), is((byte) 3));

    
    @Test
    public void testLongMap(){ 
        longMap.put("first",  1L);
		longMap.put("second",  2L);
		longMap.put("third",  3L);
		
		System.out.println( assertIt(longMap, "longMap") );
	}
	
	// map output looks like this : 
	
	assertThat(map.size(), is(3));
    assertThat(map.get("first"), is(1L));
    assertThat(map.get("second"), is(2L));
    assertThat(map.get("third"), is(3L));
  

**So this works for all java primitive and wrapped types, and others?**
   - B/boolean
   - B/byte
   - Character/char
   - S/short
   - Integer/int
   - F/float
   - D/double
   - L/long
   - String
   - Date
   - BigDecimal
   - <you easily can add your own custom type .. (scroll down)

**With collection types :**
   - Array
   - List
   - Set
   - Map
   
   
## Java Beans   
**What about java beans?**

Let's say we have a bean : 

    public class SmallBean {
        int aint;
        String aString;

        public SmallBean(int aint, String aString) {
            this.aint = aint;
            this.aString = aString;
        }

        public int getAint() {
            return aint;
        }

        public String getAString() {
            return aString;
        }
    }  


And it's runtime state is

    SmallBean bean = new SmallBean(1, "awesome);
    
And we add the following line to our test case 

    System.out.println( assertIt(smallBean, "smallBean") );

And re run our test and look at the console :

    assertThat(smallBean.getAint(), is(1));
    assertThat(smallBean.getAString(), is("awesome")); 
    
**Then we simple paste the code into the test to add the assertions!**
       
       
## Complex Types       

**what if the runtime object I want to generate assertions for is YUGE?**

What if I have a List of Map, of Arrays, Sets and Beans, all in a tree
where some values might be null?

This is when AssertGin really shines.  Big complex, real life objects.

Can you imagine trying to figure out and write all these assertions?

It could take an hour..

Not it takes a single test run to generate the output 
and another to run it with the pasted assertion code 

Sample output for a complex test case : 

    assertThat(all.size(), is(3));
    Map map = (Map) all.get(0);
    
    assertThat(map.size(), is(4));
    Map c = (Map) map.get("c");
    
    assertThat(c.size(), is(3));
    assertThat(c.get("first"), is('a'));
    assertThat(c.get("second"), is('b'));
    assertThat(c.get("third"), is('c'));
    
    Set f = (Set) map.get("f");
    
    List fAsList = new ArrayList();
    fAsList.addAll(f);
    Collections.sort(fAsList);
    assertThat(fAsList.size(), is(3));
    assertThat(fAsList.get(0), is(1.0F));
    assertThat(fAsList.get(1), is(2.0F));
    assertThat(fAsList.get(2), is(3.0F));
    
    List i = (List) map.get("i");
    
    assertThat(i.size(), is(3));
    assertThat(i.get(0), is(1));
    assertThat(i.get(1), is(2));
    assertThat(i.get(2), is(3));
    
    List l = (List) map.get("l");
    
    assertThat(l.size(), is(1));
   
    BigBean bigBean = (BigBean) l.get(0);
    assertThat(bigBean.getAbyte(), is((byte) 4));
    assertThat(bigBean.getAshort(), is((short)5));
    assertThat(bigBean.getAint(), is(6));
    assertThat(bigBean.getAlong(), is(7L));
    assertThat(bigBean.getAfloat(), is(8.0F));
    assertThat(bigBean.getAdouble(), is(9.0D));
    assertThat(bigBean.getAchar(), is('c'));
    assertThat(bigBean.isAboolean(), is(true));
    assertThat(bigBean.getAstring(), is("nested"));
    
    BigBean bigBean0 = bigBean.getBigBean();
    assertThat(bigBean0.getAbyte(), is((byte) 1));
    assertThat(bigBean0.getAshort(), is((short)2));
    assertThat(bigBean0.getAint(), is(3));
    assertThat(bigBean0.getAlong(), is(4L));
    assertThat(bigBean0.getAfloat(), is(5.0F));
    assertThat(bigBean0.getAdouble(), is(6.0D));
    assertThat(bigBean0.getAchar(), is('a'));
    assertThat(bigBean0.isAboolean(), is(true));
    assertThat(bigBean0.getAstring(), is("hi"));
    assertThat(bigBean0.getBigBean(), is(nullValue()));
    assertThat(bigBean0.getSmallBean(), is(nullValue()));
    assertThat(new SimpleDateFormat("MM-dd-yyyy").format(bigBean0.getAdate()), is("09-03-2018"));
    assertThat(bigBean0.getBigDecimal(), is(new BigDecimal("1.33333333333333333")));
    
    SmallBean smallBean = bigBean.getSmallBean();
    assertThat(smallBean.getAint(), is(3));
    assertThat(smallBean.getAString(), is("small"));
    
    assertThat(new SimpleDateFormat("MM-dd-yyyy").format(bigBean.getAdate()), is("09-03-2018"));
    assertThat(bigBean.getBigDecimal(), is(new BigDecimal("1.33333333333333333")));
   
    Map map0 = (Map) all.get(1);
    
    assertThat(map0.size(), is(3));
    Set set = (Set) map0.get("set");
    
    List setAsList = new ArrayList();
    setAsList.addAll(set);
    Collections.sort(setAsList);
    assertThat(setAsList.size(), is(3));
    assertThat(setAsList.get(0), is('a'));
    assertThat(setAsList.get(1), is('b'));
    assertThat(setAsList.get(2), is('c'));
    
    Map map1 = (Map) map0.get("map1");
    
    assertThat(map1.size(), is(4));
    Map c0 = (Map) map1.get("c");
    
    assertThat(c0.size(), is(3));
    assertThat(c0.get("first"), is('a'));
    assertThat(c0.get("second"), is('b'));
    assertThat(c0.get("third"), is('c'));
    
    Set f0 = (Set) map1.get("f");
    
    List f0AsList = new ArrayList();
    f0AsList.addAll(f0);
    Collections.sort(f0AsList);
    assertThat(f0AsList.size(), is(3));
    assertThat(f0AsList.get(0), is(1.0F));
    assertThat(f0AsList.get(1), is(2.0F));
    assertThat(f0AsList.get(2), is(3.0F));
    
    List i0 = (List) map1.get("i");
    
    assertThat(i0.size(), is(3));
    assertThat(i0.get(0), is(1));
    assertThat(i0.get(1), is(2));
    assertThat(i0.get(2), is(3));
    
    List l0 = (List) map1.get("l");
    
    assertThat(l0.size(), is(1));
    BigBean bigBean1 = (BigBean) l0.get(0);
    assertThat(bigBean1.getAbyte(), is((byte) 4));
    assertThat(bigBean1.getAshort(), is((short)5));
    assertThat(bigBean1.getAint(), is(6));
    assertThat(bigBean1.getAlong(), is(7L));
    assertThat(bigBean1.getAfloat(), is(8.0F));
    assertThat(bigBean1.getAdouble(), is(9.0D));
    assertThat(bigBean1.getAchar(), is('c'));
    assertThat(bigBean1.isAboolean(), is(true));
    assertThat(bigBean1.getAstring(), is("nested"));
  
    BigBean bigBean2 = bigBean1.getBigBean();
    assertThat(bigBean2.getAbyte(), is((byte) 1));
    assertThat(bigBean2.getAshort(), is((short)2));
    assertThat(bigBean2.getAint(), is(3));
    assertThat(bigBean2.getAlong(), is(4L));
    assertThat(bigBean2.getAfloat(), is(5.0F));
    assertThat(bigBean2.getAdouble(), is(6.0D));
    assertThat(bigBean2.getAchar(), is('a'));
    assertThat(bigBean2.isAboolean(), is(true));
    assertThat(bigBean2.getAstring(), is("hi"));
    assertThat(bigBean2.getBigBean(), is(nullValue()));
    assertThat(bigBean2.getSmallBean(), is(nullValue()));
    assertThat(new SimpleDateFormat("MM-dd-yyyy").format(bigBean2.getAdate()), is("09-03-2018"));
    assertThat(bigBean2.getBigDecimal(), is(new BigDecimal("1.33333333333333333")));
    
    SmallBean smallBean0 = bigBean1.getSmallBean();
    assertThat(smallBean0.getAint(), is(3));
    assertThat(smallBean0.getAString(), is("small"));
    
    assertThat(new SimpleDateFormat("MM-dd-yyyy").format(bigBean1.getAdate()), is("09-03-2018"));
    assertThat(bigBean1.getBigDecimal(), is(new BigDecimal("1.33333333333333333")));
    
    List list2 = (List) map0.get("list2");
    
    assertThat(list2.size(), is(3));
    assertThat(list2.get(0), is(1L));
    assertThat(list2.get(1), is(2L));
    assertThat(list2.get(2), is(3L));
    
    Object[] object = (Object[]) all.get(2);
    
    assertThat(object.length, is(3));
    Set set0 = (Set) object[0];
    
    List set0AsList = new ArrayList();
    set0AsList.addAll(set0);
    Collections.sort(set0AsList);
    assertThat(set0AsList.size(), is(3));
    assertThat(set0AsList.get(0), is((byte) 1));
    assertThat(set0AsList.get(1), is((byte) 2));
    assertThat(set0AsList.get(2), is((byte) 3));
    
    assertThat(object[1], is(nullValue()));
    Map map2 = (Map) object[2];
    
    assertThat(map2.size(), is(2));
    BigBean b1 = (BigBean) map2.get("b1");
    assertThat(b1.getAbyte(), is((byte) 1));
    assertThat(b1.getAshort(), is((short)2));
    assertThat(b1.getAint(), is(3));
    assertThat(b1.getAlong(), is(4L));
    assertThat(b1.getAfloat(), is(5.0F));
    assertThat(b1.getAdouble(), is(6.0D));
    assertThat(b1.getAchar(), is('a'));
    assertThat(b1.isAboolean(), is(true));
    assertThat(b1.getAstring(), is("hi"));
    assertThat(b1.getBigBean(), is(nullValue()));
    assertThat(b1.getSmallBean(), is(nullValue()));
    assertThat(new SimpleDateFormat("MM-dd-yyyy").format(b1.getAdate()), is("09-03-2018"));
    assertThat(b1.getBigDecimal(), is(new BigDecimal("1.33333333333333333")));
    
    BigBean b2 = (BigBean) map2.get("b2");
    assertThat(b2.getAbyte(), is((byte) 2));
    assertThat(b2.getAshort(), is((short)3));
    assertThat(b2.getAint(), is(4));
    assertThat(b2.getAlong(), is(5L));
    assertThat(b2.getAfloat(), is(6.0F));
    assertThat(b2.getAdouble(), is(6.0D));
    assertThat(b2.getAchar(), is('b'));
    assertThat(b2.isAboolean(), is(true));
    assertThat(b2.getAstring(), is("hi!"));
    assertThat(b2.getBigBean(), is(nullValue()));
    assertThat(b2.getSmallBean(), is(nullValue()));
    assertThat(new SimpleDateFormat("MM-dd-yyyy").format(b2.getAdate()), is("09-03-2018"));
    assertThat(b2.getBigDecimal(), is(new BigDecimal("1.33333333333333333")));

      
## Custom Types

What if my company has a type that's not included in your code.

How easy is it to add custom types ? 

Like Joda Time DateTime for example.  Testing date types can be a pain.

Here we format the date and compare the formatted result with what we expect it to be.

And add types in the overridden constructor like so : 


    LeafType jodaTimeType = new LeafType() {

        @Override
        public String renderObserved(String name) {
            return name+".toString(DateTimeFormat.forPattern(\"MM-dd-yyyy\"))";
        }
        @Override
        public String renderExpected(Object in) {
            return "\"" + ((DateTime)in).toString(DateTimeFormat.forPattern("MM-dd-yyyy"))+ "\"";
        }
        @Override
        public boolean is(Object in) {
            return in instanceof DateTime;
        }
    };
    
     
The generate assertion for your custom type will follow the pattern : 

```assertThat(/* result from renderExpected */ exp, is( /* result from renderObserved */ obs) )```
	
The dateTimeType will generate assertions that look like : 	
	
```assertThat(dateTime.toString(DateTimeFormat.forPattern("MM-dd-yyyy")), is("06-09-2018")));```

To add your custom types : 


    List<LeafType> companyTypes = asList(jodaTimeType));
    ...
    System.out.println( assertIt(in, name, companyTypes) );



## Misc

- [add Code overview]


- Tests : Runs the generated code to make sure it runs without exception


- uses https://github.com/trung/InMemoryJavaCompiler to compile the generated code
in my tests cases for this project.



Someone could IntelliJ Debugger plug in using this:

Right click for context menu on a variable

Click 'Generate AssertGin Assertions' 


[eof]


