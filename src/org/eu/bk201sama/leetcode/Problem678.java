package org.eu.bk201sama.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。
 *
 * 有效 字符串符合如下规则：
 *
 * 任何左括号 '(' 必须有相应的右括号 ')'。
 * 任何右括号 ')' 必须有相应的左括号 '(' 。
 * 左括号 '(' 必须在对应的右括号之前 ')'。
 * '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "(*)"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(*))"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * s[i] 为 '('、')' 或 '*'
 */
public class Problem678 {
    public static void main(String[] args) {
        List<String> testCases = new ArrayList<>();
        testCases.add("()");
        testCases.add("(*)");
        testCases.add("(*))");
        testCases.add("(*))");
//        testCases.add(")(");

        for (String testCase : testCases) {
            if(!Solution2.checkValidString(testCase)){
                System.out.println("not pass");
                return;
            }
        }
        System.out.println("pass");
    }

    /**
     * 贪心算法
     *   时间复杂度：O(n)，其中 n 是字符串 s 的长度。需要遍历字符串一次。
     *
     *      空间复杂度：O(1)。
     *
     *
     *
     *
     *
     *
     *
     */
     class Solution{
        public static boolean checkValidString(String s) {
            int max =0,min=0;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i) == '('){
                    max++;
                    min++;
                }
                if(s.charAt(i) == ')'){
                    max--;
                    min--;
                }
                if(s.charAt(i) == '*'){
                    max++;
                    min--;
                }
                //匹配中左括号最多剩余，如果小于0，说明已经不够了
                if(max<0){
                    return false;
                }
                //匹配中左括号最少剩余，如果小于0，并不能代表不够了，可以由*来补充，剩余置0继续
                if(min<0){
                    min=0;
                }
            }
            //表示匹配结束后，左括号最少剩余
            return min==0;
        }
    }

    /**
     * 堆栈
     *
     * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。需要遍历字符串一次，遍历过程中每个字符的操作时间都是 O(1)，遍历结束之后对左括号栈和星号栈弹出元素的操作次数不会超过 n。
     *
     * 空间复杂度：O(n)，其中 n 是字符串 s 的长度。空间复杂度主要取决于左括号栈和星号栈，两个栈的元素总数不会超过 n。
     *
     *
     *
     */

    class Solution2{
        public static boolean checkValidString(String s) {
            Stack<Integer> stack = new Stack<>();
            Stack<Integer> startStack = new Stack<>();

            for(int i=0;i<s.length();i++){
                if(s.charAt(i) == '('){
                    stack.push(i);
                }
                else if(s.charAt(i) == ')'){
                    if(!stack.isEmpty()){
                        stack.pop();
                    }
                    else {
                        if(!startStack.isEmpty()){
                            startStack.pop();
                        }
                        else {
                            return false;
                        }
                    }

                }else if(s.charAt(i) == '*'){
                    startStack.push(i);
                }
            }
            while(!startStack.isEmpty()&&!stack.isEmpty()){
                    if(startStack.peek()<stack.peek()){
                        stack.pop();
                        startStack.pop();
                    }else {
                        return false;
                    }

            }
            return stack.isEmpty();
        }
    }


}