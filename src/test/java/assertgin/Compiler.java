package assertgin;

import org.mdkt.compiler.InMemoryJavaCompiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static java.util.Arrays.asList;

public class Compiler {


	public boolean runsWithoutException(Object in,
										String name,
										String type,
										String imports,
										String assertionCode){

		return runsWithoutException(in, name, type, imports, assertionCode, false);
	}


	public boolean runsWithoutException(Object in,
										String name, String type,
										String imports,
										String assertionCode,
										boolean printOut){

		boolean result = false;

		String code = "" +
				"package shimer;\n" +
				"import java.util.*;\n" +
				imports +
				"import static org.hamcrest.MatcherAssert.assertThat;\n" +
				"import static org.hamcrest.core.IsNull.nullValue;\n" +
				"import static org.hamcrest.core.Is.is;\n" +
				"import java.text.SimpleDateFormat;\n" +
				"import java.math.BigDecimal;\n" +
				"\n" +
				"public class Inception{\n" +
				"\n" +
				"    public boolean runsWithoutException(Object in) {\n" +
				"        "+ type +" "+ name +" = ("+type+") in;\n" +
				"        try {\n" +
				"             " + assertionCode + "\n" +
				"        } catch (Exception e){\n" +
				"             e.printStackTrace();\n" +
				"             return false;\n" +
				"        }\n" +
				"        return true;\n" +
				"    }\n" +
				"" +
				"}";

		if (printOut){
			System.out.println("code = " + code);
		}

		try {

			Class<?> clazz = InMemoryJavaCompiler
					.newInstance()
					.ignoreWarnings()
					.compile("shimer.Inception", code);

			Method runsWithoutException = clazz.getDeclaredMethod("runsWithoutException", Object.class);

			Object inception = clazz.newInstance();

			result = (boolean) runsWithoutException.invoke(inception, in);
		}
		catch (InvocationTargetException e) {

			if (e.getCause() instanceof AssertionError) {
				System.out.println("\nException running code snippet : \n");
				e.printStackTrace();
			}
			else {
				System.out.println("Exception : ");
				e.printStackTrace();
			}
		}
		catch (Exception e){
			System.out.println("Exception : ");
			e.printStackTrace();
		}

		System.out.println("\nranWithoutException = " + result);

		return result;
	}

	// test method
	public static void main(String[] args) {

		Compiler compiler = new Compiler();

		List<Byte> in = asList((byte) 1, (byte) 2, (byte) 3);

		String code = "assertThat(list.size(), is(3));\n" +
				"assertThat(list.get(0), is((byte) 1));\n" +
				"assertThat(list.get(1), is((byte) 2));\n" +
				"assertThat(list.get(2), is((byte) 3));";

		compiler.runsWithoutException(
				in,
				"list",
				"List",
				"import static java.util.Arrays.asList;\n",
				code,
				true
		);

	}

}
