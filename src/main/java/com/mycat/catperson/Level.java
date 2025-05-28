package com.mycat.catperson;

public class Level {
    public static int levelnum = 1;

    public static int getLevelnum() {
        return levelnum;
    }

    public static void setLevelnum(int levelnum) {
        Level.levelnum = levelnum;
    }

    public static void setLevel2(int[][] map){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                level2[i][j] = map[i][j];
            }
        }
    }

    public static int[][] getlevel(){
        switch (levelnum){
            case 1:
                return level1;
            case 2:
                return level2;
        }
        return level1;
    }

    public static int[][] level1 = {
            {5,10,-10,7},
            {-5,-10,-10,-7},
            {6,9,-9,8},
            {-6,2,3,-8},
            {1,0,0,4}
    };

    public static int[][] level2 = {
            {5,9,-9,7},
            {-5,2,3,-7},
            {6,10,-10,8},
            {-6,-10,-10,-8},
            {1,0,0,4}
    };
}
