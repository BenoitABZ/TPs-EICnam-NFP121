package question2;

public class MacroCommand<E, R> extends CompositeCommand<E, R> {

	public R execute(E e, R r) throws Exception {

		for (CommandI<E, R> command : super.commands) {

			r = command.execute(e, r);

		}

		return r;
	}

}
