package assertgin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static assertgin.StringUtil.capitalize;


public class Prop {

	String name;
	boolean accessible;
	Class type;
	Method setter;
	Method getter;

	public Prop(Field field, Object in) {

		name = field.getName();
		int modifiers = field.getModifiers();
		// can we access this field directly..is it not private and not static
		accessible = ! Modifier.isPrivate(modifiers) && ! Modifier.isStatic(modifiers);
		type = field.getType();
		getter = hasGetter(in, name);
		setter = hasSetter(in, name, type);

	}

	private static String cap(String str) {
		return capitalize(str);
	}

	private static String toSetter(String name){
		return "set" + cap(name);
	}


	/** returns setter Method or null */
	@SuppressWarnings("unchecked")
	private Method hasSetter(Object in, String name, Class type) {
		Method method = null;
		Class clazz = in.getClass();
		try {
			method = clazz.getDeclaredMethod(toSetter(name), type);
		} catch (NoSuchMethodException e) {
			return null;
		}
		int modifiers = method.getModifiers();
		// can we access this field directly..is it public or protected?
		boolean isAccessible = Modifier.isProtected(modifiers) || Modifier.isPublic(modifiers);

		boolean oneParam = method.getParameterTypes().length == 1;
		boolean returnTypeVoid = method.getReturnType() == Void.TYPE;
		return (isAccessible && oneParam && returnTypeVoid ? method : null);
	}

	/** returns getter method or null */
	@SuppressWarnings("unchecked")
	private Method hasGetter(Object in, String name) {
		Method method = null;
		Class clazz = in.getClass();
		boolean isType = false;
		boolean getType = false;

		try {
			method = clazz.getDeclaredMethod("get" + cap(name));
			getType = true;
		} catch (NoSuchMethodException e) {
			try {
				method = clazz.getDeclaredMethod("is" + cap(name));
				isType = true;
			} catch (NoSuchMethodException e1) {
					return null;
			}
		}
		boolean returnTypeBoolean = method.getReturnType() == Boolean.class || method.getReturnType() == Boolean.TYPE;
		boolean returnTypeVoid = method.getReturnType() == Void.class;
		boolean returnTypeOk = (isType && returnTypeBoolean) || (getType && !returnTypeVoid );
		int modifiers = method.getModifiers();
		boolean isAccessible = ! Modifier.isPrivate(modifiers);
		boolean noParams = method.getParameterTypes().length == 0;
		return (isAccessible && noParams && returnTypeOk ? method : null);
	}


	public static List<Prop> getProps(Object in) {

		if (in == null) {
			return null;
		}

		List<Prop> props = new ArrayList<>();

		Class<?> clazz = in.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			props.add(new Prop(field, in));
		}
		return props;
	}

	public static Map<String,Prop> getPropsAsMap(Object in){

		List<Prop> props = getProps(in);
		Map<String,Prop> map = new HashMap<>();

		for (Prop prop : props) {
			map.put(prop.name, prop);
		}
		return map;
	}

	public static List<String> getSetters(Object in){
		return getProps(in).stream()
				.filter(x -> x.setter != null)
				.map(x -> x.setter.getName())
				.collect(Collectors.toList());
	}

	public static List<String> getGetters(Object in){
		return getProps(in).stream()
				.filter(x -> x.getter != null)
				.map(x -> x.getter.getName())
				.collect(Collectors.toList());
	}

	public static List<String> getRawProperties(Object in){
		return getProps(in).stream()
				.filter(x -> x.accessible)
				.map(x -> x.name)
				.collect(Collectors.toList());
	}

}
