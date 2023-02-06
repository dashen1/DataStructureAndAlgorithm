package com.example.datastructureandalgorithms.theleftgod.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {



    public static void main(String[] args) {

    }

    //以会议最早结束时间排序
    public static int bestArrange(Program[] programs,int currentTime){
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if(currentTime <= programs[i].start){
                result++;
                currentTime = programs[i].end;
            }
        }
        return result;
    }

}

class Program{
    public int start;
    public int end;

    public Program(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class ProgramComparator implements Comparator<Program>{
    @Override
    public int compare(Program o1, Program o2) {
        return o1.end - o2.end;
    }
}



