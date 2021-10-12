package exemple_kindergarten;

import container.ApplicationContext;
import java.util.*;

import exemple_kindergarten.entity.*;
import exemple_kindergarten.specification.*;

import question1.SpecificationI;
import question1.And;
/**
 * Kindergarten mais avec l'usage de femtoContainer.
 * 
 */
public class KindergartenAppWithFemtoContainer{

    public  static void main(String[] args) throws Exception{
        ApplicationContext ctx = null;
        ctx = container.Factory.createApplicationContext("./exemple_kindergarten/README.TXT");

        // Child johny  = ctx.getBean("johny");
        // Child johny2 = ctx.getBean("johny2");
        // Child max    = ctx.getBean("max");
        // Child jenny  = ctx.getBean("jenny");

        // Set<Child> children = ctx.getBean("children");
        // children.add(johny);
        // children.add(johny2);
        // children.add(max);
        // children.add(jenny);
        
        Set<Child> children = ctx.getBean("children_setof");
        
        // * Let's find all children named Johny
        SpecificationI<Child> spec_johny = ctx.getBean("spec_johny");
        System.out.println("\nChildren whose name is Johny");
        printSpecified(children, spec_johny);

        // * Let's find all children who like red toys
        SpecificationI<Child> spec_red_toy = ctx.getBean("spec_red_toy");
        System.out.println("\nChildren who likes some red toys");
        printSpecified(children, spec_red_toy);

        // * Let's find all children who likes a toy that's not red
        SpecificationI<Child> spec_not_red_toy = ctx.getBean("spec_not_red_toy");
        System.out.println("\nChildren who likes a toy that's not red");
        printSpecified(children, spec_not_red_toy);

        SpecificationI<Child> spec_johny_likes_red_toys = ctx.getBean("spec_johny_likes_red_toys");
        System.out.println("\nJohnys who like red toys");
        printSpecified(children, spec_johny_likes_red_toys);

        // * Let's find all children, who are going to school (in Poland - from 6 years old to 19 y.o.)
        SpecificationI<Child> spec_schoolchildren = ctx.getBean("spec_schoolchildren");
        System.out.println("\nSchool Children");
        printSpecified(children, spec_schoolchildren);
        
        // Set<Toy> toys = ctx.getBean("toys");
        // toys.add(ctx.getBean("ferrari"));
        // toys.add(ctx.getBean("laFireTruck"));
        // toys.add(ctx.getBean("policeCar"));
        // toys.add(ctx.getBean("timmy"));
        
        Set<Toy> toys = ctx.getBean("toys_setof");
 
        // * Let's find all toys, 0 > weight < 10
        SpecificationI<Toy> spec_weight_toy = ctx.getBean("spec_weight_toy");
        System.out.println("\nWeight Toy >0 and <10");
        printSpecified(toys, spec_weight_toy);
        
        ToyWeightBetweenSpecification spec_weight_toy2 = ctx.getBean("spec_weight_toy");
        spec_weight_toy2.setMin(1);
        spec_weight_toy2.setMax(5);
        System.out.println("\nWeight Toy Toy >1 and <5");
        printSpecified(toys, spec_weight_toy);
        
        ToyColorSpecification toyColorRed = ctx.getBean("toyColorRed");
        SpecificationI<Toy> spec_weight_color_toy = new And<Toy>(toyColorRed, spec_weight_toy);
        System.out.println("\nRed toy and Weight Toy >0 and <10");
        printSpecified(toys, spec_weight_color_toy);

    }

    private static <T> void printSpecified(Set<T> set, SpecificationI<T> spec) {
        for(T t : set) {
            if( spec.isSatisfiedBy(t) ) {
                System.out.println(t);
            }
        }
    }
    // ToyType fireTruckType = new ToyType("Fire truck");
    // ToyType racingCarType = new ToyType("Racing car");
    // ToyType policeCarType = new ToyType("Police car");
    // ToyType teddyBear     = new ToyType("Teddy Bear"); 

    // Toy ferrari = new Toy("Ferrari","Red",racingCarType,0.4);
    // Toy laFireTruck = new Toy("Los Angeles Fire Truck","Red",fireTruckType,3.0);
    // Toy policeCar = new Toy("New York Police Department car","White",policeCarType,0.9);
    // Toy timmy = new Toy("Timmy The Bear","Brown",teddyBear,0.35);

    // Child johny = new Child("Johny",7);
    // johny.getFavouriteToys().add(ferrari);
    // johny.getFavouriteToys().add(laFireTruck);

    // Child johny2 = new Child("Johny",10);
    // johny2.getFavouriteToys().add(policeCar);

    // Child max = new Child("Max",8);
    // max.getFavouriteToys().add(policeCar);
    // max.getFavouriteToys().add(laFireTruck);

    // Child jenny = new Child("Jenny",5);
    // jenny.getFavouriteToys().add(timmy);

    // Set<Child> children = new HashSet<>();
    // children.add(johny);
    // children.add(johny2);
    // children.add(max);
    // children.add(jenny);

    // /**
    // * Let's find all children named Johny
    // */

    // ChildNameSpecification spec_johny = new ChildNameSpecification("Johny");
    // System.out.println("\nChildren whose name is Johny");
    // printSpecified(children, spec_johny);

    // /**
    // * Let's find all children who like red toys
    // */

    // SpecificationI<Child> spec_red_toy = new ChildLikesSpecifiedToySpecification(new ToyColorSpecification("Red"));
    // System.out.println("\nChildren who likes some red toys");
    // printSpecified(children, spec_red_toy);

    // /**
    // * Let's find all children who likes a toy that's not red
    // */
    // SpecificationI<Child> spec_not_red_toy = new Not<Child>(new ChildLikesSpecifiedToySpecification(new ToyColorSpecification("Red")));
    // System.out.println("\nChildren who likes a toy that's not red");
    // printSpecified(children, spec_not_red_toy);

    // /**
    // * Let's find all Johnys who like red toys
    // */

    // SpecificationI<Child> spec_johny_likes_red_toys = new And<Child>(spec_johny,spec_red_toy);
    // System.out.println("\nJohnys who like red toys");
    // printSpecified(children, spec_johny_likes_red_toys);

    // /**
    // * Let's find all children, who are going to school (in Poland - from 6 years old to 19 y.o.)
    // */
    // SpecificationI<Child> spec_schoolchildren = new SchoolChildrenSpecification(); 
    // System.out.println("\nSchool Children");
    // printSpecified(children, spec_schoolchildren);
    // }

}
