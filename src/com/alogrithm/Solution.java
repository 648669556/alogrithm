package com.alogrithm;


import com.alogrithm.util.UtilTool;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Solution {
    final Random random = new Random();
    final int MOD = (int) 1e9 + 7;
    final int INF = 0x3f3f3f3f;

    public String getSqlResult(String sql, String sqlParam) {
        sqlParam = sqlParam.replaceAll("\\s+", "");
        String[] split = sqlParam.split(",");
        Map<String, String> paramMap = new HashMap<>();
        for (String s : split) {
            String[] split1 = s.split("=");
            paramMap.put(split1[0], split1[1]);
        }
        if (sql == null || sql.length() == 0) return null;
        StringBuilder res = new StringBuilder();
        char[] chars = sql.toCharArray();
        int l= 0,r = 0;
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '$'){
                l = i;
                flag = true;
                continue;
            }
            if(flag && !(chars[i] <= '9' && chars[i] >= '0')){
                r = i;
                flag = false;
                String key = sql.substring(l,r);
                res.append(paramMap.get(key));
            }
            if(!flag) res.append(chars[i]);
        }

        return res.toString();
    }

    public void run() throws IOException {
        long before = System.currentTimeMillis();
        String sql = UtilTool.getStr("./test/text.txt");
        String sqlParam = UtilTool.getStr("./test/text2.txt");

        System.out.println(sql);
        System.out.println(sqlParam);

        System.out.println(getSqlResult(sql, sqlParam));
        System.out.println("耗时" + (System.currentTimeMillis() - before) + "ms");
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.run();
    }

}
