package com.mycat.catperson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;

public class Map {

    public static int count = 0;
    public static int name = 1;
    public static ArrayList<int[]> history = new ArrayList<>();
    public static int[][] map = {
            {5, 10, -10, 7},
            {-5, -10, -10, -7},
            {6, 9, -9, 8},
            {-6, 2, 3, -8},
            {1, 0, 0, 4}
    };

    public static String HistorytoString(){
        String data = "";
        for (int i = 0;i < history.size();i++){
            data = data + "%" + history.get(i)[0] + "%" + history.get(i)[1] + "%" + history.get(i)[2];
        }
        return data;
    }

    public static void setHistory(ArrayList<int[]> history) {
        Map.history = history;
    }


    private static MediaPlayer moveSoundPlayer;
    private static final String[] SOUND_FILES = {
            "src/main/resources/cat.mp3",
    };
    private static final Random RANDOM = new Random();

    public static String MaptoString(){
        String data = "";
        data = data + count;
        for (int i = 0; i < 5;i++){
            for (int j = 0; j < 4;j++){
                data = data + "%" + map[i][j];
            }
        }
        return data;
    }
    public static EntityType getEntityType(int num){
        if (num < 5 && num > 0) {
            return EntityType.OOBlock;
        } else if (num < 9 && num > 4) {
            return EntityType.OTBlock;
        } else if (num == 9) {
            return EntityType.TOBlock;
        } else if (num == 10) {
            return EntityType.TTBlock;
        } else {
            return EntityType.TTBlock;
        }
    }


    public static int getName() {
        return name;
    }

    public static void setName(int name) {
        Map.name = name;
    }

    public static ArrayList<int[]> getHistory() {
        return history;
    }

    public static void addhistory(int[] x) {
        history.add(x);
    }

    public static void clearhistory() {
        history.clear();
    }

    public static void remove() {
        history.remove(count - 1);
        count = count - 1;
    }

    public static int getCount() {
        return count;
    }

    public static int[][] getMap() {
        return map;
    }

    public static void setCount(int count) {
        Map.count = count;
    }

    public static void setMap(int[][] map) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                Map.map[i][j] = map[i][j];
            }
        }
    }

    public static int[] getposition(int name) {
        int[] y = {0, 0};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == name) {
                    y[0] = j;
                    y[1] = i;
                    return y;
                }
            }
        }
        return y;
    }

    public static String gettype(int i, int j) {
        if (map[i][j] < 5 && map[i][j] > 0) {
            return "ooblock";
        } else if (map[i][j] < 9 && map[i][j] > 4) {
            return "otblock";
        } else if (map[i][j] == 9) {
            return "toblock";
        } else if (map[i][j] == 10) {
            return "ttblock";
        } else {
            return "";
        }
    }

    public static int getnum(int i, int j) {
        return map[i][j];
    }

    public static int[] movecontroller(EntityType x, int name) {
        switch (x) {
            case OTBlock:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == name) {
                            int[] y = {0, 0, 0, 0};
                            if (j > 0 && map[i][j - 1] == 0 && map[i + 1][j - 1] == 0) {
                                y[0] = 1;
                            } else if (j < 3 && map[i][j + 1] == 0 && map[i + 1][j + 1] == 0) {
                                y[1] = 1;
                            } else if (i > 1 && map[i - 2][j] == 0 && map[i - 1][j] == 0) {
                                y[2] = 2;
                            } else if (i < 2 && map[i + 3][j] == 0 && map[i + 2][j] == 0) {
                                y[3] = 2;
                            } else {
                                if (i > 0 && map[i - 1][j] == 0) {
                                    y[2] = 1;
                                }
                                if (i < 3 && map[i + 2][j] == 0) {
                                    y[3] = 1;
                                }
                            }
                            return y;
                        }
                    }
                }
                break;
            case OOBlock:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == name) {
                            int[] y = {0, 0, 0, 0};
                            if (j > 1 && map[i][j - 2] == 0 && map[i][j - 1] == 0) {
                                y[0] = 2;
                            } else if (j < 2 && map[i][j + 2] == 0 && map[i][j + 1] == 0) {
                                y[1] = 2;
                            } else if (i > 1 && map[i - 2][j] == 0 && map[i - 1][j] == 0) {
                                y[2] = 2;
                            } else if (i < 3 && map[i + 2][j] == 0 && map[i + 1][j] == 0) {
                                y[3] = 2;
                            } else {
                                if (j > 0 && map[i][j - 1] == 0) {
                                    y[0] = 1;
                                }
                                if (j < 3 && map[i][j + 1] == 0) {
                                    y[1] = 1;
                                }
                                if (i > 0 && map[i - 1][j] == 0) {
                                    y[2] = 1;
                                }
                                if (i < 4 && map[i + 1][j] == 0) {
                                    y[3] = 1;
                                }
                            }
                            return y;
                        }
                    }
                }
                break;
            case TOBlock:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == name) {
                            int[] y = {0, 0, 0, 0};
                            if (j > 1 && map[i][j - 1] == 0 && map[i][j - 2] == 0) {
                                y[0] = 2;
                            } else if (j < 1 && map[i][j + 2] == 0 && map[i][j + 3] == 0) {
                                y[1] = 2;
                            } else if (i > 0 && map[i - 1][j] == 0 && map[i - 1][j + 1] == 0) {
                                y[2] = 1;
                            } else if (i < 4 && map[i + 1][j] == 0 && map[i + 1][j + 1] == 0) {
                                y[3] = 1;
                            } else {
                                if (j > 0 && map[i][j - 1] == 0) {
                                    y[0] = 1;
                                }
                                if (j < 2 && map[i][j + 2] == 0) {
                                    y[1] = 1;
                                }
                            }
                            return y;
                        }
                    }
                }
                break;
            case TTBlock:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == name) {
                            int[] y = {0, 0, 0, 0};
                            if (j > 0 && map[i][j - 1] == 0 && map[i + 1][j - 1] == 0) {
                                y[0] = 1;
                            } else if (j < 2 && map[i][j + 2] == 0 && map[i + 1][j + 2] == 0) {
                                y[1] = 1;
                            } else if (i > 0 && map[i - 1][j] == 0 && map[i - 1][j + 1] == 0) {
                                y[2] = 1;
                            } else if (i < 3 && map[i + 2][j] == 0 && map[i + 2][j + 1] == 0) {
                                y[3] = 1;
                            }
                            return y;
                        }
                    }
                }
                break;
        }
        return new int[0];
    }

    private static void playRandomMoveSound() {
        if (SOUND_FILES == null || SOUND_FILES.length == 0) {
            return; // No sound files to play
        }

        int randomIndex = RANDOM.nextInt(SOUND_FILES.length);
        String soundFile = SOUND_FILES[randomIndex];

        try {
            Media sound = new Media(Paths.get(soundFile).toUri().toString());
            if (moveSoundPlayer != null) {
                moveSoundPlayer.stop();
                moveSoundPlayer.dispose(); // Release resources of the previous playe    // Layout Coordinates (Adjust these to fine-tune the layout)r
            }
            moveSoundPlayer = new MediaPlayer(sound);
            moveSoundPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading and playing sound: " + soundFile + " - " + e.getMessage());
            e.printStackTrace();
            moveSoundPlayer = null; // Ensure it's null to prevent further errors
        }
    }


    public static void movecat(EntityType entityType, int vector, int movable, int name) {
        int found = 0;
        switch (entityType) {
            case OTBlock:
                switch (vector) {
                    case 0:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i + 1][j] = 0;
                                    map[i][j - movable] = name;
                                    map[i + 1][j - movable] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i + 1][j] = 0;
                                    map[i][j + movable] = name;
                                    map[i + 1][j + movable] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i + 1][j] = 0;
                                    map[i - movable][j] = name;
                                    map[i - movable + 1][j] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i + 1][j] = 0;
                                    map[i + movable][j] = name;
                                    map[i + movable + 1][j] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                }
                break;
            case OOBlock:
                switch (vector) {
                    case 0:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j - movable] = name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j + movable] = name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i - movable][j] = name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i + movable][j] = name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                }
                break;
            case TOBlock:
                switch (vector) {
                    case 0:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j + 1] = 0;
                                    map[i][j - movable] = name;
                                    map[i][j - movable + 1] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j + 1] = 0;
                                    map[i][j + movable] = name;
                                    map[i][j + movable + 1] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j + 1] = 0;
                                    map[i - movable][j] = name;
                                    map[i - movable][j + 1] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j + 1] = 0;
                                    map[i + movable][j] = name;
                                    map[i + movable][j + 1] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                }
                break;
            case TTBlock:
                switch (vector) {
                    case 0:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j + 1] = 0;
                                    map[i + 1][j] = 0;
                                    map[i + 1][j + 1] = 0;
                                    map[i][j - movable] = name;
                                    map[i][j - movable + 1] = -name;
                                    map[i + 1][j - movable] = -name;
                                    map[i + 1][j - movable + 1] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j + 1] = 0;
                                    map[i + 1][j] = 0;
                                    map[i + 1][j + 1] = 0;
                                    map[i][j + movable] = name;
                                    map[i][j + movable + 1] = -name;
                                    map[i + 1][j + movable] = -name;
                                    map[i + 1][j + movable + 1] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j + 1] = 0;
                                    map[i + 1][j] = 0;
                                    map[i + 1][j + 1] = 0;
                                    map[i - movable][j] = name;
                                    map[i - movable][j + 1] = -name;
                                    map[i - movable + 1][j] = -name;
                                    map[i - movable + 1][j + 1] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (map[i][j] == name && found == 0) {
                                    map[i][j] = 0;
                                    map[i][j + 1] = 0;
                                    map[i + 1][j] = 0;
                                    map[i + 1][j + 1] = 0;
                                    map[i + movable][j] = name;
                                    map[i + movable][j + 1] = -name;
                                    map[i + movable + 1][j] = -name;
                                    map[i + movable + 1][j + 1] = -name;
                                    found = 1;
                                }
                            }
                        }
                        break;
                }
        }
        CatPerson.playSound(2);
        CatPerson.reinitcreat();
        CatPerson.setFish();
    }
}
