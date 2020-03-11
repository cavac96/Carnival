package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {

    public static int getRandomSelectIndex(int size){
        if(size == 1)
            return 0;
        else
            return generateRandomInt(1, size - 1);
    }

    private static int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static List<Integer> parseDayText(String duration) {
        String aux = duration.replaceAll("[^0-9]+", " ");
        List<Integer> durationLimits = new ArrayList<Integer>();
        for(String s : Arrays.asList(aux.trim().split(" "))) durationLimits.add(Integer.valueOf(s));
        return durationLimits;
    }
}
