package assertgin;


import org.junit.Test;

import java.util.List;
import java.util.Map;

import static assertgin.Prop.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class PropTest {


	public static class Nuttin {
		// empty
	}


	public static class PC {
		String yes;
		private String no;
		protected String protectedYes;
		static String alsoNoAlso;
	}

	public static class TC{

		byte	abyte;
		short	ashort;
		int	    aint;
		long	along;
		float	afloat;
		double	adouble;
		char	achar;
		boolean	aboolean;

		Byte	wbyte;
		Short	wshort;
		Integer	wint;
		Long	wlong;
		Float	wfloat;
		Double	  wdouble;
		Character	wchar;
		Boolean	wboolean;

		static Object nope;
		private int pri; // private property

		String valS;
		String valG;
		String valSG;
		String valSP;
		String valGP;
		String valSGP;

		boolean b2;

		public TC(){}

		public void setValS(String valS) {
			this.valS = valS;
		}

		public String getValG() {
			return valG;
		}

		public String getValSG() {
			return valSG;
		}

		public void setValSG(String valSG) {
			this.valSG = valSG;
		}

		// private setters and getters.. error cases
		private void setValSP(String valSP) {
			this.valSP = valSP;
		}

		private String getValGP() {
			return valGP;
		}

		private String getValSGP() {
			return valSGP;
		}

		private void setValSGP(String valSGP) {
			this.valSGP = valSGP;
		}

		public boolean isB2() {
			return b2;
		}
	}


	@Test
	public void testNull() {
		assertThat(getProps(null), is(nullValue()));
	}

	@Test
	public void testNoFields() {
		assertThat(getProps(new Nuttin()).isEmpty(), is(equalTo(true)));
	}

	@Test
	public void testPropsBIG() {

		Map<String, Prop> propsMap = getPropsAsMap(new TC());

		Prop prop = propsMap.get("ashort");
		assertThat(prop.type, is(equalTo(Short.TYPE)));
		assertThat(prop.name, is(equalTo("ashort")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop = propsMap.get("adouble");
		assertThat(prop.type, is(equalTo(Double.TYPE)));
		assertThat(prop.name, is(equalTo("adouble")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop = propsMap.get("achar");
		assertThat(prop.type, is(equalTo(Character.TYPE)));
		assertThat(prop.name, is(equalTo("achar")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop = propsMap.get("aint");
		assertThat(prop.type, is(equalTo(Integer.TYPE)));
		assertThat(prop.name, is(equalTo("aint")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop = propsMap.get("along");
		assertThat(prop.type, is(equalTo(Long.TYPE)));
		assertThat(prop.name, is(equalTo("along")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop = propsMap.get("aboolean");
		assertThat(prop.type, is(equalTo(Boolean.TYPE)));
		assertThat(prop.name, is(equalTo("aboolean")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop = propsMap.get("abyte");
		assertThat(prop.type, is(equalTo(Byte.TYPE)));
		assertThat(prop.name, is(equalTo("abyte")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop = propsMap.get("afloat");
		assertThat(prop.type, is(equalTo(Float.TYPE)));
		assertThat(prop.name, is(equalTo("afloat")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));
		
		prop = propsMap.get("b2");
		assertThat(prop.type, is(equalTo(Boolean.TYPE)));
		assertThat(prop.name, is(equalTo("b2")));
		assertThat(prop.getter.getName(), is(equalTo("isB2")));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop = propsMap.get("valSP");
		assertThat(prop.type, is(equalTo(String.class)));
		assertThat(prop.name, is(equalTo("valSP")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("wboolean");
		assertThat(prop.type, is(equalTo(Boolean.class)));
		assertThat(prop.name, is(equalTo("wboolean")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("valSG");
		assertThat(prop.type, is(equalTo(String.class)));
		assertThat(prop.name, is(equalTo("valSG")));
		assertThat(prop.getter.getName(), is(equalTo("getValSG")));
		assertThat(prop.setter.getName(), is(equalTo("setValSG")));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("valSGP");
		assertThat(prop.type, is(equalTo(String.class)));
		assertThat(prop.name, is(equalTo("valSGP")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("wfloat");
		assertThat(prop.type, is(equalTo(Float.class)));
		assertThat(prop.name, is(equalTo("wfloat")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("valS");
		assertThat(prop.type, is(equalTo(String.class)));
		assertThat(prop.name, is(equalTo("valS")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter.getName(), is(equalTo("setValS")));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("pri");
		assertThat(prop.type, is(equalTo(Integer.TYPE)));
		assertThat(prop.name, is(equalTo("pri")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(false)));

		prop =  propsMap.get("valGP");
		assertThat(prop.type, is(equalTo(String.class)));
		assertThat(prop.name, is(equalTo("valGP")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("wint");
		assertThat(prop.type, is(equalTo(Integer.class)));
		assertThat(prop.name, is(equalTo("wint")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("valG");
		assertThat(prop.type, is(equalTo(String.class)));
		assertThat(prop.name, is(equalTo("valG")));
		assertThat(prop.getter.getName(), is(equalTo("getValG")));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("wshort");
		assertThat(prop.type, is(equalTo(Short.class)));
		assertThat(prop.name, is(equalTo("wshort")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("wdouble");
		assertThat(prop.type, is(equalTo(Double.class)));
		assertThat(prop.name, is(equalTo("wdouble")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

		prop =  propsMap.get("wchar");
		assertThat(prop.type, is(equalTo(Character.class)));
		assertThat(prop.name, is(equalTo("wchar")));
		assertThat(prop.getter, is(equalTo(null)));
		assertThat(prop.setter, is(equalTo(null)));
		assertThat(prop.accessible, is(equalTo(true)));

	}

	@Test
	public void testGetSetters() {
		List<String> setters = getSetters(new TC());
		assertThat(setters.size(), is(equalTo(2)));
		assertThat(setters.get(0), is(equalTo("setValS")));
		assertThat(setters.get(1), is(equalTo("setValSG")));
	}

	@Test
	public void testGetGetters() {
		List<String> getters = getGetters(new TC());
		assertThat(getters.size(), is(equalTo(3)));
		assertThat(getters.get(0), is(equalTo("getValG")));
		assertThat(getters.get(1), is(equalTo("getValSG")));
		assertThat(getters.get(2), is(equalTo("isB2")));
	}

	@Test
	public void testGetWritableProperties() {
		List<String> props = getRawProperties(new PC());
		assertThat(props.size(), is(equalTo(2)));
		assertThat(props.get(0), is(equalTo("yes")));
		assertThat(props.get(1), is(equalTo("protectedYes")));


	}

}
