package assertgin;

public abstract class LeafType {

	/** used to generate : assertThat(renderObserved, is(renderExpected)) */
	abstract String renderExpected(Object in);

	/** used to generate : assertThat(renderObserved, is(renderExpected)) */
	public String renderObserved(String name){
		return name;
	}

	abstract boolean is(Object in);

}
