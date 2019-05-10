package me.ryota;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSimpleArgParser {

    private List<String> ignored = new ArrayList<String>();

    private Map<String,String> validOptions = new HashMap<String, String>();
    
    public JSimpleArgParser(){
    }

    public JSimpleArgParser(String[] args){
        this.parse(args);
    }


    public boolean isValid(String arg){
        return validOptions.containsKey(arg);
    }

    public Map<String,String> getValidOptions(){
        return validOptions;
    }

    public List<String> getIgnoredOptions(){
        return ignored;
    }

    public String getValue(String arg,String defaultValue){
        String result = validOptions.get(arg);
        if(result == null){
            result = defaultValue;
        }
        return result;
    }

    public void parse(String[] args){

        for(int i = 0;i < args.length;i++){
            String arg = args[i];
            try{
                if(arg.startsWith("-")){
                    if(arg.startsWith("---")){
                        ignored.add(arg);
                        continue;
                    }
                    String key = arg.replace("-","");
                    String value = args[++i];
                    validOptions.put(key,value);
                }else{
                    ignored.add(arg);
                }
            }catch (IndexOutOfBoundsException e){
                ignored.add(arg);
            }
        }
    }
}
