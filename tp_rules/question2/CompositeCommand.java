package question2;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class CompositeCommand<E,R> implements CommandI<E,R>{
    protected List<CommandI<E,R>> commands;

    public CompositeCommand(){
        this.commands = new ArrayList<>();
    }

    public CompositeCommand<E,R> add(CommandI<E,R> command){
        commands.add(command);
        return this;
    }

    public void setCommands(CommandI<E,R>[] commands){
        this.commands.addAll(Arrays.asList(commands));
    }

    public abstract R execute(E e, R r) throws Exception;

    public String toString(){
        String name = getClass().getSimpleName();
        StringBuffer sb = new StringBuffer(name+"(");
        Iterator<CommandI<E,R>> it = commands.iterator();
        while(it.hasNext()){
            sb.append(it.next().toString());
            if(it.hasNext())sb.append("/");
        }
        sb.append(")");
        return sb.toString();
    }
}
