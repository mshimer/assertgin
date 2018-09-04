package assertgin;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static assertgin.Prop.getProps;
import static org.apache.commons.lang3.StringUtils.uncapitalize;


/*


TODO context : bean properties vs getters

TODO templates (pluggable assertion leafTypes java or hamcrest or junit...)

TODO generics

TODO @AssertGinIgnore for fields


 */

public class AssertGin {

    private final LeafTypes leafTypes;


    public AssertGin(){
        this.leafTypes = new LeafTypes();
    }


    public AssertGin(List<LeafType> customTypes){
        this();
        this.leafTypes.addAll(customTypes);
    }


    public static String assertIt(Object in, String name) {

        AssertGin gin = new AssertGin();
        AssertGinContext context = new AssertGinContext(name);
        gin.assertObject(in, name, context);
        return context.getCode();
    }


    public static String assertIt(Object in, String name, AssertGinContext context) {

        AssertGin gin = new AssertGin();
        gin.assertObject(in, name, context);
        return context.getCode();
    }


    public static String assertIt(Object in, String name, List<LeafType> customTypes) {

        AssertGin gin = new AssertGin(customTypes);
        AssertGinContext context = new AssertGinContext(name);
        gin.assertObject(in, name, context);
        return context.getCode();
    }


    public static String assertIt(Object in, String name, AssertGinContext context, List<LeafType> customTypes) {

        AssertGin gin = new AssertGin(customTypes);
        gin.assertObject(in, name, context);
        return context.getCode();
    }


    private void assertObject(Object in, String name, AssertGinContext context) {

        if (isLeaf(in)) {
            assertLeaf(in, name, context);
        }
        else if (isArray(in)) {
            assertArray(in, name, context);
        }
        else if (isSet(in)) {
            assertSet(name, (Set) in, context);
        }
        else if (isList(in)) {
            assertList(name, (List) in, context);
        }
        else if (isMap(in)) {
            assertMap(name, (Map) in, context);
        }
        else {
            assertBean(in, name, context);
        }
    }


    private boolean isLeaf(Object in) {
        return this.leafTypes.isLeaf(in);
    }


    private void assertLeaf(Object in, String name, AssertGinContext context) {

        if (in == null && context.ignoreNulls()){
            return;
        }

        String observed = renderObserved(in, name);

        String expected = renderExpected(in);

        context.append("assertThat(" + observed + ", is(" + expected + "));\n");
    }


    /** used to generate : assertThat(renderObserved, is(renderExpected)) */
    private String renderObserved(Object in, String name){

        if (in == null){
            return name;
        }

        return this.leafTypes.renderObserved(in, name);
    }


    /** used to generate : assertThat(renderObserved, is(renderExpected)) */
    private String renderExpected(Object in) {

        if (in == null){
            return "nullValue()";
        }
        return this.leafTypes.renderExpected(in);
    }


    /** used to generate : map.get(renderedKey) */
    private String renderKey(Object in) {

        if (in == null){
            return "null";
        }
        return this.leafTypes.renderExpected(in);
    }


    private void assertSet(String name, Set set, AssertGinContext context) {

        if (set.isEmpty()) {
            return;
        }
        context.append("\n");

        // ideally we'd like a deterministic order to pu
        if (set.iterator().next() instanceof Comparable) {

            context.append("List " + name + "AsList = new ArrayList();\n");

            context.append(name + "AsList.addAll(" + name + ");\n" +
                    "Collections.sort(" + name + "AsList);\n" +
                    "assertThat(" + name + "AsList.size(), is(" + set.size() + "));\n");
            List list = new ArrayList();
            list.addAll(set);
            Collections.sort(list);
            assertListItems(list, name + "AsList", context);
        }
        else {

            Iterator it = set.iterator();
            context.append("// TODO : uses a non-deterministic iterator - you should make the elements in the Set Comparable.\n" +
                    "Iterator " + name + "It = " + name + ".iterator();\n");


            // uses a non determinate order.. you should implement Comparable.. caveot emptor
            int i = 0;
            while (it.hasNext()) {

                Object item = it.next();
                String itemType = coaxedType(item);
                String itemName = context.nextVar(uncapitalize(itemType));

                if (isLeaf(item)) {
                    assertLeaf(item, name + "It.next()", context);
                }
                else {
                    context.append(itemType + " " + itemName + " = (" + itemType + ") " + name + "It.next();\n");
                    assertObject(item, itemName, context);
                }
                i++;
            }
        }
    }


    private String coaxedType(Object in) {

        if (in == null){
            return null;
        }
        else if (isSet(in)){
            return "Set";
        }
        else if (isList(in)){
            return "List";
        }
        else if (isMap(in)){
            return "Map";
        }
        else{
            return in.getClass().getSimpleName();
        }
    }


    private void assertList(String name, List list, AssertGinContext context) {

        if (!list.isEmpty()) {

            context.append("\nassertThat(" + name + ".size(), is(" + list.size() + "));\n");

            assertListItems(list, name, context);
        }
    }


    private void assertArray(Object in, String name, AssertGinContext context) {

        int length = Array.getLength(in);
        if (length > 0) {
            context.append("\nassertThat(" + name + ".length, is(" + length + "));\n");
            assertArrayItems(in, name, context);
        }
    }


    private void assertArrayItems(Object array, String name, AssertGinContext context) {

        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object item = Array.get(array, i);
            String itemType = coaxedType(item);
            String itemName = context.nextVar(uncapitalize(itemType));

            if (isLeaf(item)) {
                assertLeaf(item, name + "[" + i + "]", context);
            }
            else {
                context.append(itemType + " " + itemName + " = (" + itemType + ") " + name + "[" + i + "];\n");
                assertObject(item, itemName, context);
            }
        }
        context.append("\n");
    }


    private void assertListItems(List list, String name, AssertGinContext context) {

        for (int i = 0; i < list.size(); i++) {

            Object item = list.get(i);
            String itemType = coaxedType(item);
            String itemName = context.nextVar(uncapitalize(itemType));

            if (isLeaf(item)) {
                assertLeaf(item, name + ".get(" + i + ")", context);
            }
            else {
                context.append(itemType + " " + itemName + " = (" + itemType + ") " + name + ".get(" + i + ");\n");
                assertObject(item, itemName, context);
            }
        }
        context.append("\n");
    }


    private void assertMap(String name, Map map, AssertGinContext context) {

        context.append("\nassertThat(" + name + ".size(), is(" + map.size() + "));\n");

        assertMapItems(map, name, context);
    }


    private void assertMapItems(Map map, String name, AssertGinContext context) {

        for (Object key : map.keySet()) {

            Object item = map.get(key);

            String itemType = coaxedType(item);

            if (isLeaf(item)) {
                assertLeaf(item, name + ".get(" + renderKey(key) + ")", context);
            }
            else {
                String itemName = context.nextVar(key == null ? "item" : key.toString());

                String getAndCast = "(" + itemType + ") " + name + ".get(" + renderKey(key) + ")";

                context.append(itemType + " " + itemName + " = " + getAndCast + ";\n");

                assertObject(item, itemName, context);
            }
        }
        context.append("\n");
    }


    private void assertBean(Object in, String name, AssertGinContext context) {

        List<Prop> getters = getProps(in).stream().filter(x -> x.getter != null).collect(Collectors.toList());

        for (Prop getterProp : getters) {

            Object gottenVal;

            try {
                gottenVal = getterProp.getter.invoke(in);
            }
            catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            String methodName = getterProp.getter.getName();

            if (isLeaf(gottenVal)) {
                assertLeaf(gottenVal, name + "." + methodName + "()", context);
            }
            else {
                String type = getterProp.type.getSimpleName();
                String gottenName = context.nextVar(getterProp.name);
                context.append(type + " " + gottenName + " = " + name + "." + methodName + "();\n");
                assertObject(gottenVal, gottenName, context);
            }
        }
        context.append("\n");

    }

    private static boolean isSet(Object in) {
        return in instanceof Set;
    }

    private static boolean isList(Object in) {
        return in instanceof List;
    }

    private static boolean isMap(Object in) {
        return in instanceof Map;
    }

    private static boolean isArray(Object in) {
        return in != null && in.getClass().isArray();
    }

}
