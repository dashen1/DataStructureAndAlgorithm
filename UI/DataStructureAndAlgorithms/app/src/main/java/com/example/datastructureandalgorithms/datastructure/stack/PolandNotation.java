package com.example.datastructureandalgorithms.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        //将中缀表达式转换为后缀表达式
        // 1 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -

        String expression = "1+((2+3)*4)-5";

        List<String> infixExpressionList = toInFixExpressionList(expression);


        //定义一个逆波兰表达式
        // (3+4)*5-6 3 4 + 5 * 6 -
        String suffixExpression = "30 4 + 5 * 6 -";

        List<String> parseSuffixExpression = parseToSuffixExpressionList(infixExpressionList);
        //思路：
        //1.现将“3 4 + 5 x 6 -”=》放到ArrayList
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈 完成计算

        List<String> list = getListString(suffixExpression);
        System.out.println("rpnString: " + list);

        System.out.println("后缀表达式："+parseSuffixExpression);
        int result = calculate(parseSuffixExpression);
        System.out.println("后缀表达式的结果是：" + result);
    }

    public static List<String> parseToSuffixExpressionList(List<String> list) {
        Stack<String> s1 = new Stack<>();

        ArrayList<String> s2 = new ArrayList<>();

        for (String item : list) {
            //如果是一个数，直接入栈s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2,知道遇到左括号为止，此时将这一对括号丢弃
                //后缀表达式注意：
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                //当item的优先级小于等于栈顶运算符，将s1栈顶的的运算符弹出并加入到s2中
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符以此弹出并加入到s2中
        while (s1.size() !=0){
            s2.add(s1.pop());
        }
        return s2;
    }


    // [1,+,(,(,2,+,3,),*,4,),-,5] => [1, 2, 3, +, 4, *, +, 5, -]
    public static List<String> toInFixExpressionList(String s) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        do {
            //如果c是一个非数字，就加入到list
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;
            } else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        //创建栈 3 4 + 5 * 6 - 8 2 /
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {//匹配的是多位数
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算有误！");
                }
                stack.push("" + res);
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}
