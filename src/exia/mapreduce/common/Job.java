package exia.mapreduce.common;

import java.util.Iterator;

public interface Job<InputType, MappedType, CombinedType, OutputType> {

	public void map(Iterator<InputType> input, MappedType output);
	
//	public void combine(MappedType input, CombinedType ouput);
	
	public void reduce(CombinedType input, OutputType ouput);
	
}
