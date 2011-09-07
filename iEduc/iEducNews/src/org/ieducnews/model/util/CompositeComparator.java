package org.ieducnews.model.util;

import java.util.Comparator;

public class CompositeComparator {

	public <T> Comparator<T> createComparator(final Comparator<T> comparator1,
			final Comparator<T> comparator2) {
		return new Comparator<T>() {
			public int compare(T t1, T t2) {
				int result = comparator1.compare(t1, t2);
				if (result != 0) {
					return result;
				} else {
					return comparator2.compare(t1, t2);
				}
			}
		};
	}

}
