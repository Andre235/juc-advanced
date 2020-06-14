package com.geek.demo10;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author : 赵静超
 * @date : 2020/6/14
 * @description : 四大常用函数式接口
 */
public class StreamDemo {
    public static void main(String[] args) {
        functionTest();
        predicateTest();
        consumerTest();
        supplierTest();

    }

    //供给型接口
    private static void supplierTest() {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "没有参数，只有返回值";
            }
        };
        System.out.println(supplier.get());

        Supplier<String> supplier1 = () -> "没有参数，只有返回值";
        System.out.println(supplier1.get());
    }

    //消费型接口
    private static void consumerTest() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String string) {
                System.out.println("消费型接口，消费的参数为 " + string);
            }
        };
        consumer.accept("hello");

        Consumer<String> consumer1 = string -> System.out.println("消费型接口，消费的参数为 " + string);
        consumer1.accept("word");
    }

    //断言型接口
    private static void predicateTest() {
        //创建匿名内部类
        Predicate<Long> predicate = new Predicate<Long>() {
            @Override
            public boolean test(Long num) {
                return num != null && num != 0L;
            }
        };
        System.out.println(predicate.test(1L));

        //用lambda表达式实现
        Predicate<Long> predicate1 = num -> num != null && num != 0L ;
        System.out.println(predicate1.test(null));
    }

    //Function函数型接口，有一个输入值有一个输出值
    private static void functionTest() {
        //创建匿名内部类
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };
        System.out.println(function.apply("hello"));

        //lambda表达式简化函数式接口
        Function<String,String> function1 = string -> string;
        System.out.println(function1.apply("word"));
    }
}
