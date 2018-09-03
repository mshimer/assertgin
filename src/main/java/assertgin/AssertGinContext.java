package assertgin;

import java.util.HashSet;
import java.util.Set;

public class AssertGinContext {

    private boolean ignoreNulls;
    private StringBuilder buffer = new StringBuilder();
    private Set vars = new HashSet();

    public AssertGinContext(String name){
        vars.add(name); // register the top leve object's name in our variable env
    }

    public void append(String in){
        buffer.append(in);
    }

    public String getCode(){
        return buffer.toString();
    }

    public String nextVar(String name) {

        name = (name == null ? "var" : name);

        // remove [] from simple type names
        name = name.replaceAll("[\\[\\]]", "");

        if (!vars.contains(name)){
            vars.add(name);
            return name;
        }

        // start adding numbers to the end to make unique
        int inc = 0;
        for ( ; vars.contains(name + inc) ; inc++) {
        }
        vars.add(name + inc);

        return name + inc;
    }

    public boolean ignoreNulls() {
        return ignoreNulls;
    }

    public void setIgnoreNulls(boolean ignoreNulls) {
        this.ignoreNulls = ignoreNulls;
    }

}
