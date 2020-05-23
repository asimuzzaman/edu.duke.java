package weektwo;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counter;

    public CharactersInPlay() {
        names = new ArrayList<String>();
        counter = new ArrayList<Integer>();
    }

    private void update(String person) {
        int index = names.indexOf(person);
        if(index == -1) {
            names.add(person);
            counter.add(1);
        } else {
            int value = counter.get(index);
            counter.set(index,value+1);
        }
    }

    private void findAllCharacters() {
        names.clear();
        counter.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()) {
            int period = line.indexOf('.');
            if(period != -1) {
                String person = line.substring(0,period);
                update(person.trim());
            }
        }
    }

    private void charactersWithNumParts(int num1, int num2) {
        for (int i=0; i<names.size(); i++) {
            int count = counter.get(i);
            if(count >= num1 && count <= num2)
                System.out.println(names.get(i) +" "+ counter.get(i));
        }
    }

    public void test() {
        findAllCharacters();

        int most = -1, mostDex = -1;
        for(int i=0; i<counter.size(); i++) {
            if(counter.get(i) > most) {
                most = counter.get(i);
                mostDex = i;
            }
        }
        System.out.println("Most: " + names.get(mostDex) + " "+ counter.get(mostDex));
        charactersWithNumParts(10,200);
    }
}
