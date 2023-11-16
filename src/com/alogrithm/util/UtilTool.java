package com.alogrithm.util;


import java.io.*;
import java.util.*;

public class UtilTool {
    public static void bfsPrintTree(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (node != null) {
            queue.offer(node);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                final TreeNode poll = queue.poll();
                assert poll != null;
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                System.out.printf("%d\t", poll.val);
            }
            System.out.println();
        }
    }

    /**
     * 序列化二叉树
     */
    public static String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    /**
     * 反序列化二叉树
     */
    public static TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }

    static int index;

    public static TreeNode deserialize(Integer[] data) {
        index = 0;
        return rdeserialize(data);
    }

    private static TreeNode rdeserialize(Integer[] data) {
        if (data[index] == null) {
            index++;
            return null;
        }
        TreeNode root = new TreeNode(data[index++]);
        root.left = rdeserialize(data);
        root.right = rdeserialize(data);
        return root;
    }

    private static String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += root.val + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    private static TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("None")) {
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);
        return root;
    }

    public static String parseArray(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[') {
                chars[i] = '{';
            } else if (chars[i] == ']') {
                chars[i] = '}';
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(parseArray("[[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]"));
    }

    public static int[] readFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            boolean exists = file.mkdirs();
            if (!exists) throw new IOException("创建文件夹失败");
        }
        File newFile = new File(path + "/text.txt");
        if (!newFile.exists()) newFile.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(newFile));
        String line = br.readLine();
        br.close();
        if (line == null || line.length() == 0) return new int[]{};
        return strConvert2Arr(line);
    }

    public static int[] strConvert2Arr(String arr) {
        String newLine = arr.substring(1, arr.length() - 1);
        String[] str = newLine.split(",");
        int[] res = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            res[i] = Integer.parseInt(str[i]);
        }
        return res;
    }

    public static int[][] readDoubleFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            boolean exists = file.mkdirs();
            if (!exists) throw new IOException("创建文件夹失败");
        }
        File newFile = new File(path + "/text.txt");
        if (!newFile.exists()) newFile.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(newFile));
        String line = br.readLine();
        br.close();
        if (line == null || line.length() == 0) return new int[][]{};
        String newLine = line.substring(1, line.length() - 1);
        List<Integer> splitIndex = new ArrayList<>();
        for (int i = 0; i < newLine.length(); i++) {
            if (i > 0 && newLine.charAt(i - 1) == ']' && newLine.charAt(i) == ',' && newLine.charAt(i + 1) == '[') {
                splitIndex.add(i);
            }
        }
        int[][] newArr = new int[splitIndex.size() + 1][];
        if (splitIndex.size() == 0) {
            newArr[0] = strConvert2Arr(newLine);
            return newArr;
        }
        int before = 0;
        for (int i = 0; i < splitIndex.size(); i++) {
            int[] ints = strConvert2Arr(newLine.substring(before, splitIndex.get(i)));
            newArr[i] = ints;
            before = splitIndex.get(i) + 1;
        }
        newArr[splitIndex.size()] = strConvert2Arr(newLine.substring(before));
        return newArr;
    }

    public static int[] arrOne() {
        try {
            return readFile("./test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[]{};
    }

    public static int[][] arrTwo() {
        try {
            return readDoubleFile("./test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[][]{};
    }

    public static String getStr(String textPath) throws IOException {
        File newFile = new File(textPath);
        if (!newFile.exists()) {
            try {
                boolean newFile1 = newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedReader br = new BufferedReader(new FileReader(newFile));
        String tempStr = null;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            tempStr = br.readLine();
            if (tempStr != null)
                stringBuilder.append(tempStr).append('\n');
        } while (tempStr != null);
        return stringBuilder.toString();
    }
}
