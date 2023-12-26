package alogrithm.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author chenjunhong
 * 创建日期: 2023-12-23  13:33
 */
public class CalculatePoint {


    /**
     * scanX = {0,2,1,-7,-15,-18,-11,-8,-5,-6,0,-3,-2,-5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
     * scanY = {10,20,25,32,27,22,20,9,14,5,7,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
     */
    private static Integer[] CURRENT_MOVE_X = new Integer[]{0,2,1,-7,-15,-18,-11,-8,-5,-6,0,-3,-2,-5};
    private static Integer[] CURRENT_MOVE_Y = new Integer[]{10,20,25,32,27,22,20,9,14,5,7,4,4};


    /**
     * 计算平均速度
     */
    private BigDecimal avgSpeed(BigDecimal distance, BigDecimal time) {
        return distance.divide(time, 5, RoundingMode.HALF_UP);
    }

    /**
     * 计算加速度
     */
    private BigDecimal acceleration(BigDecimal speedBegin, BigDecimal speedEnd, BigDecimal time) {
        return speedEnd.subtract(speedBegin).divide(time, 5, RoundingMode.HALF_UP);
    }

    /**
     * 根据时间初速度 加速度 获取间隔距离
     *
     * @return
     */
    private BigDecimal getDistance(BigDecimal speedBegin, BigDecimal acceleration, BigDecimal time) {
        return speedBegin.multiply(time).add(acceleration.multiply(time.pow(2)).divide(new BigDecimal(2), 3, RoundingMode.HALF_UP));
    }

    /**
     * 输入 时间、路程、初速度 求加速度
     */
    private BigDecimal getAcceleration(BigDecimal distance, BigDecimal speedBegin, BigDecimal time) {
        return distance.subtract(speedBegin.multiply(time)).multiply(new BigDecimal(2)).divide(time.pow(2), 10, RoundingMode.HALF_UP);
    }

    /**
     * 输入初速度、子弹间隔时间、子弹间隔距离、计算子弹间隔时间内每1/10的运动距离
     */
    private void solution() {
        int gapTime = 120;
        int gapCount = 5;
        BigDecimal lm = new BigDecimal("1");
        StringBuilder sb = new StringBuilder();
        BigDecimal avgSpeed = BigDecimal.ZERO;
        for (Integer currentMoveX : CURRENT_MOVE_X) {
            BigDecimal bigCurrentMoveX = new BigDecimal(currentMoveX).multiply(lm);
            // 初速度取上一轮的平均速度
            BigDecimal speedBegin = avgSpeed;
            // 平均速度
            avgSpeed = avgSpeed(bigCurrentMoveX, new BigDecimal(gapTime));
            // 加速度
            BigDecimal acceleration = getAcceleration(bigCurrentMoveX, speedBegin, new BigDecimal(gapTime));

            BigDecimal currentDistance = BigDecimal.ONE;

            BigDecimal lastDistance = BigDecimal.ZERO;

            for (int i = 0; i < gapCount; i++) {
                BigDecimal currentTime = new BigDecimal(gapTime / gapCount);
                BigDecimal distance = getDistance(speedBegin, acceleration, currentTime).add(lastDistance);
                speedBegin = speedBegin.add(acceleration.multiply(currentTime));
                //如果本轮不足 1 则保留小数到下次计算的时候使用
                // 如果本轮大于1 则取整数部分
                if (distance.abs().compareTo(BigDecimal.ONE) > 0) {
                    BigDecimal integer = distance.setScale(0, RoundingMode.DOWN);
                    lastDistance = distance.subtract(integer);
                } else {
                    lastDistance = distance;
                    distance = BigDecimal.ZERO;
                }

                currentDistance = currentDistance.add(distance.setScale(0, RoundingMode.DOWN));
                BigDecimal currentRealMove;
                if (i == gapCount - 1) {
                    currentRealMove = distance.setScale(0, RoundingMode.CEILING);
                } else {
                    currentRealMove = distance.setScale(0, RoundingMode.DOWN);
                }
                sb.append(currentRealMove).append(",");
            }
            lastDistance = BigDecimal.ZERO;
            sb.append("\t")
                    .append("total:").append(currentDistance).append("\t")
                    .append("origin:").append(bigCurrentMoveX).append("\t")
                    .append("avgSpeed:").append(avgSpeed).append("\t")
                    .append("acceleration:").append(acceleration).append("\t")
                    .append("speedBegin:").append(speedBegin);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        CalculatePoint calculatePoint = new CalculatePoint();
        calculatePoint.solution();
    }

}
