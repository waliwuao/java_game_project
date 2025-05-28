package com.mycat.catperson;

import java.util.*;

public class AiHelp {
    public static int[][] goal = {
            {5, 6, 7, 8},
            {-5, -6, -7, -8},
            {1, 3, 9, -9},
            {0, 10, -10, 4},
            {0, -10, -10, 2}
    };

    public static List<int[][]> goallist = new ArrayList<>();

    public static int[][][] goallist1 = {
            {
                    {5,10,-10,7},
                    {-5,-10,-10,-7},
                    {0,6,9,-9},
                    {1,-6,3,8},
                    {2,0,4,-8}
            },
            {
                    {5, 10, -10, 7},
                    {-5, -10, -10, -7},
                    {9, -9, 4, 3},
                    {1, 6, 0, 8},
                    {2, -6, 0, -8}
            },
            {
                    {5, 10, -10, 7},
                    {-5, -10, -10, -7},
                    {3, 6, 8, 0},
                    {4, -6, -8, 0},
                    {9, -9, 2, 1}
            },
            {
                    {3,5,10,-10},
                    {4,-5,-10,-10},
                    {0,6,8,7},
                    {0,-6,-8,-7},
                    {9,-9,2,1}
            },
            {
                    {3, 10, -10, 7},
                    {4, -10, -10, -7},
                    {0, 0, 2, 8},
                    {6, 5, 1, -8},
                    {-6, -5, 9, -9}
            },
            {
                    {2, 4, 3, 7},
                    {6, 10, -10, -7},
                    {-6, -10, -10, 8},
                    {0, 5, 1, -8},
                    {0, -5, 9, -9}
            },
            {
                    {2, 3, 7, 0},
                    {6, 4, -7, 0},
                    {-6, 10, -10, 8},
                    {5, -10, -10, -8},
                    {-5, 1, 9, -9}
            },
            {
                    {0, 6, 2, 7},
                    {0, -6, 3, -7},
                    {5, 10, -10, 8},
                    {-5, -10, -10, -8},
                    {1, 4, 9, -9}
            },
            {
                    {5, 6, 7, 8},
                    {-5, -6, -7, -8},
                    {2, 3, 9, -9},
                    {10, -10, 0, 4},
                    {-10, -10, 0, 1}
            }
    };


    public static String gettype(int num) {
        if (num < 5 && num > 0) {
            return "ooblock";
        } else if (num < 9 && num > 4) {
            return "otblock";
        } else if (num == 9) {
            return "toblock";
        } else if (num == 10) {
            return "ttblock";
        } else {
            return "";
        }
    }

    public enum EntityType {
        OOBlock,
        OTBlock,
        TOBlock,
        TTBlock
    }

    public static EntityType getEntityType(int num) {
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

    public static int[] Aimovecontroller(int num, int[][] map) {
        String type = gettype(num);
        int[] y = {0, 0, 0, 0}; // Initialize movement possibility array

        switch (type) {
            case "otblock":
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == num) {
                            if (j > 0 && map[i][j - 1] == 0 && map[i + 1][j - 1] == 0) {
                                y[0] = 1;
                            }
                            if (j < 3 && map[i][j + 1] == 0 && map[i + 1][j + 1] == 0) {
                                y[1] = 1;
                            }
                            if (i > 1 && map[i - 2][j] == 0 && map[i - 1][j] == 0) {
                                y[2] = 2;
                            }
                            if (i < 2 && map[i + 3][j] == 0 && map[i + 2][j] == 0) {
                                y[3] = 2;
                            }
                            // 简化：如果上下移动距离为2不可行，则尝试距离为1的移动
                            if (y[2] == 0 && i > 0 && map[i - 1][j] == 0) {
                                y[2] = 1;
                            }
                            if (y[3] == 0 && i < 3 && map[i + 2][j] == 0) {
                                y[3] = 1;
                            }
                            return y;
                        }
                    }
                }
                break;
            case "ooblock":
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == num) {
                            if (j > 1 && map[i][j - 2] == 0 && map[i][j - 1] == 0) {
                                y[0] = 2;
                            }
                            if (j < 2 && map[i][j + 2] == 0 && map[i][j + 1] == 0) {
                                y[1] = 2;
                            }
                            if (i > 1 && map[i - 2][j] == 0 && map[i - 1][j] == 0) {
                                y[2] = 2;
                            }
                            if (i < 3 && map[i + 2][j] == 0 && map[i + 1][j] == 0) {
                                y[3] = 2;
                            }
                            // 简化：如果左右移动距离为2不可行，则尝试距离为1的移动
                            if (y[0] == 0 && j > 0 && map[i][j - 1] == 0) {
                                y[0] = 1;
                            }
                            if (y[1] == 0 && j < 3 && map[i][j + 1] == 0) {
                                y[1] = 1;
                            }
                            if (y[2] == 0 && i > 0 && map[i - 1][j] == 0) {
                                y[2] = 1;
                            }
                            if (y[3] == 0 && i < 4 && map[i + 1][j] == 0) {
                                y[3] = 1;
                            }
                            return y;
                        }
                    }
                }
                break;
            case "toblock":
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == num) {
                            if (j > 1 && map[i][j - 1] == 0 && map[i][j - 2] == 0) {
                                y[0] = 2;
                            }
                            if (j < 1 && map[i][j + 2] == 0 && map[i][j + 3] == 0) {
                                y[1] = 2;
                            }
                            if (i > 0 && map[i - 1][j] == 0 && map[i - 1][j + 1] == 0) {
                                y[2] = 1;
                            }
                            if (i < 4 && map[i + 1][j] == 0 && map[i + 1][j + 1] == 0) {
                                y[3] = 1;
                            }
                            if (y[0] == 0 && j > 0 && map[i][j - 1] == 0) {
                                y[0] = 1;
                            }
                            if (y[1] == 0 && j < 2 && map[i][j + 2] == 0) {
                                y[1] = 1;
                            }
                            return y;
                        }
                    }
                }
                break;
            case "ttblock":
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == num) {
                            if (j > 0 && map[i][j - 1] == 0 && map[i + 1][j - 1] == 0) {
                                y[0] = 1;
                            }
                            if (j < 2 && map[i][j + 2] == 0 && map[i + 1][j + 2] == 0) {
                                y[1] = 1;
                            }
                            if (i > 0 && map[i - 1][j] == 0 && map[i - 1][j + 1] == 0) {
                                y[2] = 1;
                            }
                            if (i < 3 && map[i + 2][j] == 0 && map[i + 2][j + 1] == 0) {
                                y[3] = 1;
                            }
                            return y;
                        }
                    }
                }
                break;
        }
        return y;
    }

    public static int[][] Aimove(int num, int[][] map, int vector, int movable) {
        int[][] newMap = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new); // Deep copy
        String type = gettype(num);
        int found = 0;

        switch (type) {
            case "otblock":
                switch (vector) {
                    case 0:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i + 1][j] = 0;
                                    newMap[i][j - movable] = num;
                                    newMap[i + 1][j - movable] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i + 1][j] = 0;
                                    newMap[i][j + movable] = num;
                                    newMap[i + 1][j + movable] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i + 1][j] = 0;
                                    newMap[i - movable][j] = num;
                                    newMap[i - movable + 1][j] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i + 1][j] = 0;
                                    newMap[i + movable][j] = num;
                                    newMap[i + movable + 1][j] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                }
                break;
            case "ooblock":
                switch (vector) {
                    case 0:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j - movable] = num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j + movable] = num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i - movable][j] = num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i + movable][j] = num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                }
                break;
            case "toblock":
                switch (vector) {
                    case 0:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j + 1] = 0;
                                    newMap[i][j - movable] = num;
                                    newMap[i][j - movable + 1] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j + 1] = 0;
                                    newMap[i][j + movable] = num;
                                    newMap[i][j + movable + 1] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j + 1] = 0;
                                    newMap[i - movable][j] = num;
                                    newMap[i - movable][j + 1] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j + 1] = 0;
                                    newMap[i + movable][j] = num;
                                    newMap[i + movable][j + 1] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                }
                break;
            case "ttblock":
                switch (vector) {
                    case 0:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j + 1] = 0;
                                    newMap[i + 1][j] = 0;
                                    newMap[i + 1][j + 1] = 0;
                                    newMap[i][j - movable] = num;
                                    newMap[i][j - movable + 1] = -num;
                                    newMap[i + 1][j - movable] = -num;
                                    newMap[i + 1][j - movable + 1] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j + 1] = 0;
                                    newMap[i + 1][j] = 0;
                                    newMap[i + 1][j + 1] = 0;
                                    newMap[i][j + movable] = num;
                                    newMap[i][j + movable + 1] = -num;
                                    newMap[i + 1][j + movable] = -num;
                                    newMap[i + 1][j + movable + 1] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j + 1] = 0;
                                    newMap[i + 1][j] = 0;
                                    newMap[i + 1][j + 1] = 0;
                                    newMap[i - movable][j] = num;
                                    newMap[i - movable][j + 1] = -num;
                                    newMap[i - movable + 1][j] = -num;
                                    newMap[i - movable + 1][j + 1] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (newMap[i][j] == num && found == 0) {
                                    newMap[i][j] = 0;
                                    newMap[i][j + 1] = 0;
                                    newMap[i + 1][j] = 0;
                                    newMap[i + 1][j + 1] = 0;
                                    newMap[i + movable][j] = num;
                                    newMap[i + movable][j + 1] = -num;
                                    newMap[i + movable + 1][j] = -num;
                                    newMap[i + movable + 1][j + 1] = -num;
                                    found = 1;
                                }
                            }
                        }
                        break;
                }

        }

        return newMap;
    }

    public static int calculateManhattanDistanceSum(int[][] map1, int[][] map2) {
        int totalDistance = 0;

        for (int i = 1; i < 11; i++) {
            int[] coords1 = findCoordinates(map1, i);
            int[] coords2 = findCoordinates(map2, i);

            if (coords1 != null && coords2 != null) {
                totalDistance = totalDistance + manhattanDistance(coords1[0], coords1[1], coords2[0], coords2[1]);
            }
        }
        return totalDistance;
    }

    public static int calculateManhattanDistance(int[][] map1, int[][] map2, int transifor) {
        if (transifor == 1) {
            return calculateManhattanDistanceSum(map1, map2);
        } else {
            int totalDistance = 0;

            int[] coords1 = findCoordinates(map1, 10);
            int[] coords2 = findCoordinates(map2, 10);

            if (coords1 != null && coords2 != null) {
                totalDistance = manhattanDistance(coords1[0], coords1[1], coords2[0], coords2[1]);
            }
            return totalDistance;
        }
    }

    private static int[] findCoordinates(int[][] map, int number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == number) {
                    return new int[]{j, i};
                }
            }
        }
        return null;
    }

    private static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static int[] findshortmove(int[][] start, int[][] initialGoal) {
        List<int[][]> mapHistory = new ArrayList<>();
        List<State> statesearch = new ArrayList<>();
        List<State> statefinish = new ArrayList<>();
        goallist.clear();
        Collections.addAll(goallist, goallist1);

        int[][] currentGoal = initialGoal;
        int loopCount = 0;
        int countstop = 0;
        int transifor = 0;

        State startstate = new State(0, 0, 0, 0, 0, calculateManhattanDistance(start, currentGoal, transifor));
        mapHistory.add(start);
        statesearch.add(startstate);

        while (!statesearch.isEmpty()) {
            loopCount++;
            if (countstop == 0 && loopCount > 2000) {
                int[][] newGoal = findgoal(start);
                if (newGoal == null) {
                    System.out.println("No closer goal found.  Terminating search.");
                    return null;
                }
                if (Arrays.deepEquals(newGoal, currentGoal)) {
                    System.out.println("No closer goal found. Terminating search.");
                    return null;
                }

                currentGoal = newGoal;

                mapHistory.clear();
                statesearch.clear();
                statefinish.clear();
                startstate = new State(0, 0, 0, 0, 0, calculateManhattanDistance(start, currentGoal, transifor));
                mapHistory.add(start);
                statesearch.add(startstate);
                loopCount = 0;
                countstop = 1;
                transifor = 1;
            }

            State currentState = statesearch.stream()
                    .min(Comparator.comparingInt(State::getDistance))
                    .orElse(null);
            int currentMapIndex = currentState.getSelf();
            int[][] currentMap = mapHistory.get(currentMapIndex);

            statesearch.remove(currentState);
            statefinish.add(currentState);

            if (calculateManhattanDistance(currentMap, currentGoal, transifor) == 0) {

                State traceState = currentState;
                while (traceState.getFather() != 0) {
                    traceState = findStateByIndex(statefinish, traceState.getFather());
                    if (traceState == null) {
                        System.out.println("Error: Could not trace back to start state.");
                        return null;
                    }
                }

                return new int[]{traceState.getNum(), traceState.getVelocity(), traceState.getMovable()};
            }

            for (int num = 1; num <= 10; num++) {
                int[] moves = Aimovecontroller(num, currentMap);
                for (int i = 0; i < 4; i++) {
                    if (moves[i] > 0) {
                        int[][] nextMap = Aimove(num, currentMap, i, moves[i]);
                        boolean alreadyVisited = false;
                        for (int[][] visitedMap : mapHistory) {
                            if (Arrays.deepEquals(visitedMap, nextMap)) {
                                alreadyVisited = true;
                                break;
                            }
                        }

                        if (!alreadyVisited) {
                            mapHistory.add(nextMap);
                            int nextMapIndex = mapHistory.size() - 1;
                            int distanceToGoal = calculateManhattanDistance(nextMap, currentGoal, transifor);
                            State nextState = new State(currentMapIndex, nextMapIndex, num, i, moves[i], distanceToGoal);
                            statesearch.add(nextState);
                        }
                    }
                }
            }
        }
        System.out.println("No solution found.");
        return null;
    }

    private static State findStateByIndex(List<State> stateList, int index) {
        for (State state : stateList) {
            if (state.getSelf() == index) {
                return state;
            }
        }
        return null;
    }
    public static int bestgoal = 0;

    public static int[][] findgoal(int[][] start) {
        if (goallist == null || goallist.isEmpty()) {
            return null;
        }

        int closestIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < goallist.size(); i++) {
            int[][] potentialGoal = goallist.get(i);
            int distance = calculateManhattanDistanceSum(start, potentialGoal);
            if (distance < minDistance) {
                minDistance = distance;
                closestIndex = i;
            }
        }

        if (closestIndex > bestgoal ){
            bestgoal = closestIndex;
        }
        minDistance = calculateManhattanDistanceSum(start, goallist.get(bestgoal));
        if (minDistance == 0){
            bestgoal = bestgoal + 1;
        }
        if (closestIndex != -1 && closestIndex != goallist.size() - 1) {
            return goallist.get(bestgoal);
        }
        return null;
    }
}
