package InvertedIndex;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InvertedIndex_Reducer extends Reducer<Text,Text,Text,Text>{
    
    private Text result = new Text();

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        
        for(Text id: values){
        	if(!sb.toString().contains(id.toString())) {
        	if(first){
                first= false;
            }
            else {
                sb.append(", ");
            }
             
            sb.append(id.toString());
            }
        }
            result.set(sb.toString());
            context.write(key, result);
    }   
}
