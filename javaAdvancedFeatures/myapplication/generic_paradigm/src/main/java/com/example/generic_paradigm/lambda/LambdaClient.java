package com.example.generic_paradigm.lambda;

public class LambdaClient {

    public static void main(String[] args) {

        // 函数式接口
        LambdaInterface lambdaInterface = s -> System.out.println(s);
        lambdaInterface.runTest("hello");
        /**
         *
         * Annotation
         * @Retention
         * @RetentionPolicy.SOURCE
         * @RetentionPolicy.CLASS
         * @RetentionPolicy.RUNTIME
         *
         * @TARGET
         * ElementType.ANNOTATION
         * ElementType.FIELD
         * ElementType.METHOD
         * ElementType.LOCAL
         * ElementType.PACKAGE
         * ElementType.CONSTRUCTOR
         * ElementType.PARAMETER
         *
         * @Inherited
         *
         * @Document
         *
         * @Repeatable
         */
    }
}
