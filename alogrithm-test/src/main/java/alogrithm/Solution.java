package alogrithm;


import alogrithm.util.UtilTool;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Solution {
    final Random random = new Random();
    final int MOD = (int) 1e9 + 7;
    final int INF = 0x3f3f3f3f;

    public String getSqlResult(String sql, String sqlParam) {
        StringBuilder res = new StringBuilder();
        char[] chars1 = sqlParam.toCharArray();
        for (char c : chars1) {
            if (c != ' ') res.append(c);
        }
        sqlParam = res.toString();
        String[] split = sqlParam.split(",");
        Map<String, String> paramMap = new HashMap<>(split.length + 5,1);
        for (String s : split) {
            if(s == null || s.length() == 0) continue;
            String[] split1 = getSplit(s);
            paramMap.put(split1[0], split1[1]);
        }
        if (sql == null || sql.length() == 0) return null;
        char[] chars = sql.toCharArray();
        res.delete(0, res.length());
        int l = 0, r = 0;
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '$') {
                l = i;
                flag = true;
                continue;
            }
            if (flag && !(chars[i] <= '9' && chars[i] >= '0')) {
                r = i;
                flag = false;
                String key = sql.substring(l, r);
                res.append(paramMap.get(key));
            }
            if (!flag && chars[i] != '\n') {
                res.append(chars[i]);
            } else if (!flag && chars[i] == '\n') res.append(" ");
        }
        return res.toString();
    }

    private String[] getSplit(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '=') {
                l = i;
                break;
            }
        }
        return new String[]{new String(chars, 0, l), new String(chars, l + 1, chars.length - l - 1)};
    }

    public void run() throws IOException {
        long beforeN = System.nanoTime();
        String sql = UtilTool.getStr("test/text.txt");
        String sqlParam = UtilTool.getStr("test/text2.txt");
        System.out.println("读取耗时" + (System.nanoTime() - beforeN) + "ns");

        long before = System.currentTimeMillis();
        System.out.println(getSqlResult(sql, sqlParam));
        System.out.println("耗时" + (System.currentTimeMillis() - before) + "ms");
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.run();
    }

}
