package exemple_instruction.instructions;

import java.util.*;

public class Memory<E>{
    private Map<String,E> map;
    public Memory(){
        this.map = new HashMap<>();
    }

    public void set(String name, E e){
        map.put(name,e);
    }

    public E get(String name){
        return map.get(name);
    }

    public String toString(){
        return map.toString();
    }
}