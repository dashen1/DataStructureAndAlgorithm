package com.example.datastructureandalgorithms.datastructure.stack;

public class Calculator {

    public static void main(String[] args) {

        //中缀表达式
        String expression = "30+2*6-2";
        //创建两个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operatorStack = new ArrayStack2(10);

        //定义相关变量
        int index = 0;
        int num1=0;
        int num2=0;
        int operator = 0;
        int result = 0;
        char ch= ' ';
        String keepNum = "";
        while (true){
            ch = expression.substring(index, index+1).charAt(0);
            //判断ch是什么
            if (operatorStack.isOperator(ch)) {
                if (!operatorStack.isEmpty()){
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        result = numStack.calculate(num1,num2,operator);
                        numStack.push(result);
                        operatorStack.push(ch);
                    }else {
                        operatorStack.push(ch);
                    }
                } else {
                        operatorStack.push(ch);
                }
            } else {
                //当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数 比如：70
                //numStack.push(ch-48);
                keepNum +=ch;
                //如果ch已经是最后一位，就直接入栈
                if (index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字
                    if (operatorStack.isOperator(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum="";
                    }
                }
            }
            //让index+1,并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length()){
                break;
            }
        }
        //扫描完毕，将数栈和操作数栈拿出来计算
        while (true){
            if (operatorStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            result = numStack.calculate(num1, num2, operator);
            numStack.push(result);
        }
        int finalResult = numStack.pop();
        System.out.printf("计算的结果是：%d\n",finalResult);
    }
}

class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top = -1;//栈顶

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull(){
        return top == maxSize -1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int value){
        if (isFull()){
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    //返回当前栈顶值，但不是真正的出栈

    public int peek(){
        return stack[top];
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("站空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }

        for (int i = top; i > -1 ; i--) {
            System.out.printf("栈的元素：%d\n",stack[i]);
        }
    }

    //返回运算符的优先级，优先级使用数字表示
    //数字越大，优先级越高
    public int priority(int operator){
        if (operator == '*' || operator == '/'){
            return 1;
        } else if (operator == '+' || operator == '-'){
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOperator(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }

    public int calculate(int num1,int num2, int operator){
        int result = 0;
        switch (operator){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num2-num1;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                result = num2/num1;
                break;
            default:
                break;
        }
        return result;
    }
}
