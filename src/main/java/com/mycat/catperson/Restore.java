package com.mycat.catperson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Restore {

    private static final String FILENAME = "src/main/resources/restore.txt"; // 定义文件名常量

    public static void Checkfile() {
        File restore = new File(FILENAME);
        if (!restore.exists()) {
            try {
                restore.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getLine(int n) {
        File restore = new File(FILENAME);
        try (BufferedReader br = new BufferedReader(new FileReader(restore))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber == n) {
                    return line;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void replaceLine(int n, String newString) {
        File restore = new File(FILENAME);
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(restore))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (n > 0 && n <= lines.size()) {
            lines.set(n - 1, newString);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(restore))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSubstringOfLine(int n, int x, int y) {
        String line = getLine(n);
        if (line != null && x >= 0 && y < line.length() && x <= y) {
            return line.substring(x, y + 1);
        } else {
            return null;
        }
    }

    public static void appendLine(String newString) {
        File restore = new File(FILENAME);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(restore, true))) {
            bw.write(newString);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSignature(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeEmptyLines() {
        File restore = new File(FILENAME);
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(restore))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { // 检查行是否为空
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(restore))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cleanAndValidateLines() {
        removeEmptyLines(); // 移除空行

        File restore = new File(FILENAME);
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(restore))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> filteredLines = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.startsWith("@")) {
                String[] parts = line.split("@");
                if (parts.length == 5) { // 确保有四个部分
                    String hash1 = parts[1];
                    String userData = parts[2];
                    String hash2 = parts[3];
                    String Mapdata = parts[4];

                    String[] userParts = userData.split("%");
                    if (userParts.length >= 2) {
                        String username = userParts[0];
                        String password = userParts[1];

                        String expectedHash1 = getSignature(username + "%" + password); // 计算期望的哈希值1

                        if (expectedHash1.equals(hash1)) { // 检查哈希值1是否匹配
                            String expectedHash2 = getSignature(Mapdata); // 计算期望的哈希值2

                            if (expectedHash2.equals(hash2)) { // 检查哈希值2是否匹配
                                filteredLines.add(line); // 如果两个哈希值都匹配，则保留该行
                            } else {
                                String resetData = "@" + Restore.getSignature(username + "%" + password) + "@" +
                                        username + "%" + password + "@" + Restore.getSignature(Map.MaptoString() + Map.HistorytoString()) + "@" +
                                        Map.MaptoString() + Map.HistorytoString();
                                filteredLines.add(resetData);
                            }
                        } else {
                            System.out.println("Invalid user data found. Removing line: " + line);
                        }
                    }
                } else {
                    System.out.println("Invalid line format. Removing line: " + line);
                }
            } else {
                System.out.println("Removing non-user data line: " + line);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(restore))) {
            for (String line : filteredLines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPasswordByUsername(String username) {
        File restore = new File(FILENAME);
        try (BufferedReader br = new BufferedReader(new FileReader(restore))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("@")) {
                    String[] parts = line.split("@");
                    if (parts.length == 5) {
                        String userData = parts[2];
                        String[] userParts = userData.split("%");
                        if (userParts.length >= 2 && userParts[0].equals(username)) {
                            return userParts[1]; // 返回密码哈希值
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getCountAndMapByUsername(String username) {
        File restore = new File(FILENAME);
        try (BufferedReader br = new BufferedReader(new FileReader(restore))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("@")) {
                    String[] parts = line.split("@");
                    if (parts.length == 5) {
                        String userData = parts[2];
                        String mapData = parts[4];
                        String[] userParts = userData.split("%");
                        if (userParts.length >= 2 && userParts[0].equals(username)) {
                            String[] dataParts = mapData.split("%");

                            int count = Integer.parseInt(dataParts[0]);
                            Map.setCount(count);

                            int[][] mapDataArray = new int[5][4];
                            for (int i = 1; i <= 20; i++) {
                                mapDataArray[(i - 1) / 4][(i - 1) % 4] = Integer.parseInt(dataParts[i]);
                            }

                            if (mapDataArray[3][1] != 10){
                                Level.setLevel2(mapDataArray);
                            }else {
                                Map.setCount(0);
                            }

                            ArrayList<int[]> historyData = new ArrayList<>();
                            for (int i = 21; i < dataParts.length; i += 3) {
                                if (i + 2 < dataParts.length) {
                                    try {
                                        int[] move = {Integer.parseInt(dataParts[i]), Integer.parseInt(dataParts[i + 1]), Integer.parseInt(dataParts[i + 2])};
                                        historyData.add(move);
                                    } catch (NumberFormatException e) {
                                        System.err.println("Invalid history data format. Skipping.");
                                        break;
                                    }
                                } else {
                                    System.err.println("Incomplete history data. Skipping.");
                                    break;
                                }
                            }
                            Map.setHistory(historyData);

                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getuserData() {
        return "@" + Restore.getSignature(CatPerson.username + "%" + CatPerson.password) + "@" +
                CatPerson.username + "%" + CatPerson.password + "@" + Restore.getSignature(Map.MaptoString() + Map.HistorytoString()) + "@" +
                Map.MaptoString() + Map.HistorytoString();
    }

    public static void updateUserRecord(String username) {
        File restore = new File(FILENAME);
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(restore))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        boolean found = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("@")) {
                String[] parts = line.split("@");
                if (parts.length == 5) {
                    String userData = parts[2];
                    String[] userParts = userData.split("%");
                    if (userParts.length >= 2 && userParts[0].equals(username)) {
                        lines.set(i, getuserData());
                        found = true;
                        break;
                    }
                }
            }
        }

        if (!found) {
            lines.add(getuserData());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(restore))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
