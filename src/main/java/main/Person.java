package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Person implements Serializable {
    private int id;
    private String name;
    private List<Person> friends = new ArrayList<Person>();
    private transient int luckNumber;

    public void addFriend(Person person) {
        friends.add(person);
    }
    public Iterator<Person> getFriends() {
        return friends.iterator();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLuckNumber() {
        return luckNumber;
    }

    public void setLuckNumber(int luckNumber) {
        this.luckNumber = luckNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", friends=[");
        for (Person friend : friends) {
            sb.append(friend.id);
            sb.append(" ");
        }
        sb.append("], luckNumber=").append(luckNumber);
        sb.append('}');
        return sb.toString();
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
