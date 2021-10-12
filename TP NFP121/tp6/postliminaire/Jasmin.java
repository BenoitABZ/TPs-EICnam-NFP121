package postliminaire;

import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Method;

import question1.Contexte;

import jasmin.Main;

public class Jasmin{

    public static void assemble(String code, String nomDeClasse) throws Exception{
        //new File(nomDeClasse + ".j").delete();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(nomDeClasse + ".j")));
        out.write(code,0,code.length());
        out.flush();
        out.close();
        System.out.println(code.toString());

        jasmin.Main.assemble(nomDeClasse + ".j");
    }

    public static void exec(String nomDeClasse) throws Exception{
        Class<?> classe = Class.forName(nomDeClasse, true, new MyClassLoader());
        //Class<?> classe = Class.forName(nomDeClasse);
        Method methode = classe.getMethod("main", new Class[] {String[].class });
        System.out.println( methode + " called"); 
        methode.invoke(null, new Object[]{new String[]{}}); 
    }

  
    private static class MyClassLoader extends ClassLoader{ 
        private Map<String,Class<?>> classes;

        public MyClassLoader(){
            super(MyClassLoader.class.getClassLoader());
            classes = new HashMap<String,Class<?>>();
        }

        protected synchronized Class<?> loadClass(String name,boolean resolve) throws ClassNotFoundException{ 
            Class classe = classes.get(name); //  ou bien sans classes, Class classe = findLoadedClass(name);
            if (classe == null) {  
                byte[] classBytes = loadClassBytes(name);
                if (classBytes == null){
                    return findSystemClass(name);
                }

                classe = defineClass(name, classBytes, 0, classBytes.length);
                if (classe == null)
                    throw new ClassNotFoundException(name);
                classes.put(name, classe); 
            }
            if (resolve) resolveClass(classe);

            return classe;
        }

        private byte[] loadClassBytes(String name){  
            String cname = name.replace('.', '/') + ".class";
            FileInputStream in = null;
            try{  
                in = new FileInputStream(cname);
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int ch;
                while ((ch = in.read()) != -1){
                    byte b = (byte)(ch);
                    buffer.write(b);
                }
                in.close();
                return buffer.toByteArray();
            }catch (IOException e){
                if (in != null){  
                    try {in.close(); } catch (IOException e2) { }
                }
                return null;
            }
        }
    }

}
