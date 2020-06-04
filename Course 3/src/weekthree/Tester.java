package weekthree;

import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows() {
        String st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);

        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows + " size: "+follows.size());
    }

    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);

        ArrayList<String> follows = markov.getFollows("he");
        System.out.println(follows + " size: "+follows.size());
    }
}
