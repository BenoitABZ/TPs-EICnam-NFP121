package question1;

import java.lang.reflect.*;

public class ReflectSpecification<E> implements SpecificationI<E> {
	protected SpecificationI<E> instanceSpecification = null;

	public SpecificationI<E> getSpecification() {
		return instanceSpecification;
	}

	public void setSpecification(String specification) {
		try {

			Class<?> classSpecification = Class.forName(specification);

			try {

				instanceSpecification = (SpecificationI<E>) classSpecification.newInstance();

			} catch (InstantiationException e) {

				e.printStackTrace();

			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public boolean isSatisfiedBy(E e) {
		Method mSpecification = null;

		boolean bool = false;
		
		Class<?> eClass = null;
		
		try {
					
			try {
				
				eClass = e.getClass();
				
			}catch(NullPointerException npe) {
				
				eClass = Object.class;
				
			}
			
			mSpecification = instanceSpecification.getClass().getDeclaredMethod("isSatisfiedBy", eClass);

			bool = (boolean) mSpecification.invoke(instanceSpecification, e);

			return bool;

		} catch (Exception exc) {
			exc.printStackTrace();
			throw new RuntimeException(exc);
		} finally {
			
		}
	}
}
