package question3;

import java.util.*;
import java.lang.reflect.*;

public class TestsStructureAvecDecorateurs extends junit.framework.TestCase{
  protected StructureI<Integer> s1,s2;
  
  @Override
  protected void setUp(){
    s1 = new Structure<Integer>();
    s2 = new Structure<Integer>();
  }
 
  public void testDecorateurAf(){
      s1 = new StructureAf<>(s1);
      //...
  }
  
  public void testDecorateurAfRepOk(){
      s1 = new StructureAf<>(new StructureRepOk<>(s1));
      //...
  } 
  
  public void testDecorateurAfRepOkPrePost(){
      s1 = new StructureAf<>(new StructureRepOk<>( new StructurePrePost<>(s1)));
      // ...
  }
  
  public void testDecorateurRepOkPrePost(){
      // etc
  }
}

