package com.example.generic_paradigm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.generic_paradigm.serialize.Course;
import com.example.generic_paradigm.serialize.Student;

public class MainActivity extends AppCompatActivity {

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //泛型 把异常提前到编译期 不需要强转
        //使用泛型 代码更健壮 在运行期不会出现ClassNotFound异常 增加代码复用性
        // 1.查看是否有类型限制 2.强转 3.为了维护多态
        // ? extends T 上界 ？super T 下界
        //类加载器+class对象
        initStudent();
    }

    private void initStudent() {
        student = new Student("King", "男", 21);
        for (int i = 0; i < 20; i++) {
            student.addCourse(new Course("英语", 67.9f));
        }
    }
}