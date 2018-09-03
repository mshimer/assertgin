package assertgin;

// import org.joda.time.DateTime;
// import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Arrays.asList;

public class LeafTypes {
    
    List<LeafType> leafTypes = new ArrayList<>();
    
    public LeafTypes(){
        
        leafTypes.addAll(
                // initial set of types - you can always add more
                asList(
                        booleanType,
                        byteType,
                        charType, 
                        shortType, 
                        integerType,
                        floatType,
                        doubleType,
                        longType,
                        stringType,
                        dateType,
                        bigDecimalType
                        //,jodaTimeType
                )
        );
    }


    public void addAll(List<LeafType> customTypes){
        this.leafTypes.addAll(customTypes);
    }

    /** used to generate : assertThat(renderObserved, is(renderExpected)) */
    public String renderObserved(Object in, String name) {

        for (LeafType type : leafTypes) {
            if (type.is(in)){
                return type.renderObserved(name);
            }
        }
        return "LeafType.renderObserved CAN'T RENDER : "+in;
    }


    /** used to generate : assertThat(renderObserved, is(renderExpected)) */
    public String renderExpected(Object in){

        if (in == null){
            return "null";
        }

        for (LeafType type : leafTypes) {
            if (type.is(in)){
                return type.renderExpected(in);
            }
        }
        return "LeafType.renderExpected CAN'T RENDER : "+in;
    }


    public boolean isLeaf(Object in) {

        if (in == null){
            return true;
        }

        for (LeafType type : leafTypes) {
            if (type.is(in)){
                return true;
            }
        }
        return false;
    }

    //--------------------------------------------------------------------------------------------------

    LeafType booleanType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return ""+in;
        }
        @Override
        public boolean is(Object in) {
            return in instanceof Boolean;
        }
    };

    LeafType byteType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return "(byte) "+in;
        }
        @Override
        public boolean is(Object in) {
            return in instanceof Byte;
        }
    };
    
    LeafType charType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return "'"+in+"'";
        }
        @Override
        public boolean is(Object in) {
            return in instanceof Character;
        }
    };
    
    LeafType shortType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return "(short)"+in;
        }
        @Override
        public boolean is(Object in) {
            return in instanceof Short;
        }
    };
    
    LeafType integerType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return ""+in;
        }

        @Override
        public boolean is(Object in) {
            return in instanceof Integer;
        }
    };

    LeafType floatType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return ""+in+"F";
        }
        @Override
        public boolean is(Object in) {
            return in instanceof Float;
        }
    };

    LeafType doubleType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return ""+in+"D";
        }
        @Override
        public boolean is(Object in) {
            return in instanceof Double;
        }
    };
    
    LeafType longType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return ""+in+"L";
        }
        @Override
        public boolean is(Object in) {
            return in instanceof Long;
        }
    };
    
    LeafType stringType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return "\""+in+"\"";
        }

        @Override
        public boolean is(Object in) {
            return in instanceof String;
        }
    };

    LeafType dateType = new LeafType() {

        @Override
        public String renderObserved(String name) {
            return "new SimpleDateFormat(\"MM-dd-yyyy\").format(" + name + ")";
        }
        @Override
        public String renderExpected(Object in) {
            return "\"" + new SimpleDateFormat("MM-dd-yyyy").format(in) + "\"";
        }
        @Override
        public boolean is(Object in) {
            return in instanceof Date;
        }
    };


    LeafType bigDecimalType = new LeafType() {
        @Override
        public String renderExpected(Object in) {
            return "new BigDecimal(\"" + in + "\")";
        }
        @Override
        public boolean is(Object in) {
            return in instanceof BigDecimal;
        }
    };


    /*
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
    */



}
