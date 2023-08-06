package cn.itcast.gateway.leetcode;

import java.util.*;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author 18451
 * @Date 2023/7/22 9:59
 * @Version 1.0
 **/
public class Solution {

    public boolean lemonadeChange(int[] bills) {
        int fiveNums = 0, tenNums = 0, twentyNums = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                fiveNums++;
            } else if (bills[i] == 10) {
                if (fiveNums == 0) return false;
                else {
                    fiveNums--;
                    tenNums++;
                }
            } else if (bills[i] == 20) {
                if (tenNums > 0 && fiveNums > 0) {
                    tenNums = tenNums - 1;
                    fiveNums = fiveNums - 1;
                    twentyNums++;
                } else if (fiveNums > 2) {
                    fiveNums = fiveNums - 3;
                    twentyNums++;
                } else return false;
            }
        }
        return true;
    }

    public int numJewelsInStones(String jewels, String stones) {
        int sum = 0;
        char[] precious_stone = jewels.toCharArray();
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < precious_stone.length; i++) {
            dict.put(precious_stone[i], 1);
        }
        for (int i = 0; i < stones.length(); i++) {
            if (dict.containsKey(stones.charAt(i))) {
                sum = sum + 1;
            } else {
                continue;
            }
        }
        return sum;
    }

    /*
    首先计算出数组和,即最少要减少多少,尽量减半最大的值.
    大根堆：PriorityQueue
     */
    public int halveArray(int[] nums) {
        PriorityQueue<Double> pq = new PriorityQueue<Double>((a, b) -> b.compareTo(a));
        for (int num : nums) {
            pq.offer((double) num);
        }
        int res = 0;
        double sum = 0;
        for (int num : nums) {
            sum += num;
        }
        double sum2 = 0.0;
        while (sum2 < sum / 2) {
            double x = pq.poll();
            sum2 += x / 2;
            pq.offer(x / 2);
            res++;
        }
        return res;
    }

    public int deleteGreatestValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            Arrays.sort(grid[i]);
        }
        for (int i = 0; i < n; i++) {
            int temp = Integer.MIN_VALUE;
            for (int j = 0; j < m; j++) {
                temp = Math.max(temp, grid[j][i]);
            }
            sum = sum + temp;
        }
        return sum;
    }

    public int minimumTime(int n, int[][] relations, int[] time) {
        int mx = 0;
        List<Integer>[] prev = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            prev[i] = new ArrayList<Integer>();
        }
        for (int[] relation : relations) {
            int x = relation[0], y = relation[1];
            prev[y].add(x);
        }
        Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
        for (int i = 1; i <= n; i++) {
            mx = Math.max(mx, dp(i, time, prev, memo));
        }
        return mx;
    }

    public int dp(int i, int[] time, List<Integer>[] prev, Map<Integer, Integer> memo) {
        if (!memo.containsKey(i)) {
            int cur = 0;
            for (int p : prev[i]) {
                cur = Math.max(cur, dp(p, time, prev, memo));
            }
            cur += time[i - 1];
            memo.put(i, cur);
        }
        return memo.get(i);
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    /**
     * 时间大于目标
     *
     * @param hours
     * @param target
     * @return
     */
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int sum = 0;
        for (int hour : hours) {
            if (hour >= target) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    /**
     * 不同元素的数目等于整个数组不同元素的数目
     *
     * @param nums
     * @return
     */
    public int countCompleteSubArrays(int[] nums) {
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> dict = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                dict.add(nums[j]);
                if (dict.size() == set.size()) {
                    count += nums.length - j;
                    break;
                }
            }
        }
        return count;
    }

    /**
     * 合并两个升序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode temp = new ListNode(-1);
        ListNode prev = temp;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                temp.next = list2;
                list2 = list2.next;
            } else {
                temp.next = list1;
                list1 = list1.next;
            }
            temp = temp.next;
        }
        temp.next = list1 == null ? list2 : list1;
        return prev.next;
    }

    /**
     * 两两交换节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.deleteGreatestValue(new int[][]{{1, 2, 4}, {3, 3, 1}});
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
