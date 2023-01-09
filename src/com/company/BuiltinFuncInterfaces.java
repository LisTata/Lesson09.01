package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BuiltinFuncInterfaces {
    public static void main(String[] args) {
        //Integer->boolean
        Predicate<Integer> isPositive=i->i>0;

        Predicate<Integer> isZero = i->i==0;

        Predicate<Integer> isNegative=isPositive.or(isZero).negate();
        System.out.println(isNegative.test(-5));

        //Function: Person->String
        Function<Person, String> getName= Person::getName;
        System.out.println(getName.apply(new Person("Ivan","Ivanov")));

        //Supplier: ()->Person
        Supplier<Person> createPerson = Person::new;
        Person person=createPerson.get();
        System.out.println(person);

        //Consumer: Person-> ()
        Consumer<Person> hello=p-> System.out.printf("Hello,%s!%n",p.getName());
        hello.accept(new Person("Ivan","Ivanov"));

        //Comparator:(o1,o2)-> int
        Comparator<Person> comparator =(p1,p2)->p1.getName().compareTo(p2.getName());
        Person person1 = new Person("John", "Doe");
        Person person2 = new Person("Alice", "Krige");
        System.out.println(comparator.compare(person1,person2));

        //Optional - container
       Optional<String> empty=Optional.empty();
       if (!empty.isEmpty())
        System.out.println(empty.get());

       Optional<String> noEmpty = Optional.of("");
        System.out.println(noEmpty.isPresent());

        Optional<String> nullable =Optional.ofNullable(null);
        System.out.println(nullable.isPresent());



    }

    boolean isPositive(Integer i){
        return i>0;
    }
}
class Person{
    String name;
    String surname;
    int age;
    Group group;

    public Person(Group group, String name, String surname, int age) {

        this.group
                = group;
        group.add(this);

        this.name
                = name;
        this.surname = surname;
        this.age = age;
    }

    public Person(String name, String surname) {

        this.name
                = name;
        this.surname = surname;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", group=" + group +
                '}';
    }
}

class Group{
    String name;
    List<Person> persons = new ArrayList<>();

    public Group(String name) {

        this.name
                = name;
    }

    public void add(Person person){
        persons.add(person);
    }

    public void add(List<Person> persons){
        persons.addAll(persons);
    }

    public int getSize(){
        return persons.size();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                '}';
    }
}