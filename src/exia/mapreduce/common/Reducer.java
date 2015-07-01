package exia.mapreduce.common;

import java.util.List;

public interface Reducer<I, O> {

	public O reduce(List<I> data);
	
}
