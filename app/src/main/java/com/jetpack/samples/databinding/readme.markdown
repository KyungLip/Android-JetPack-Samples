@[TOC](目录)

# 前言

本篇文章主要介绍DataBinding的使用以及原理。

# 一 DataBinding是什么？

    DataBinding是Google推出的一种支持库，在API14上可以使用，主要功能有两种:
        1.减少findViewById样板代码。
        2.实现视图与数据的绑定(支持双向绑定)。

# 二 使用入门

## 1.配置开启

    在应用模块Module对应的build.gradle文件中添加dataBindging元素。

```
android {
    ......
    //开启数据绑定
    buildFeatures {
        dataBinding true
    }
}
```

## 2.简单使用

### 布局转换
组合键：Alt+Enter。
使用方式:光标移动到原有布局根元素上然后进行操作。
数据绑定布局文件原始布局文件略有不同，以根标记 layout 开头，后跟 data 元素和 view 根元素。此视图元素是非绑定布局文件中的根。

布局文件

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

     <data>
        <import type="com.jetpack.samples.databinding.User" />
        <variable
            name="user"
            type="User" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout

        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".databinding.DataBindActivity">

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{user.name}"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/tv_age"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{String.valueOf(user.age)}"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/tv_name" />
        <Button
            android:id="@+id/btn_change"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="改变"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"/>
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

### 在Activity中使用

```
class DataBindActivity : AppCompatActivity() {
    companion object{
        private const val TAG="kylp"
    }
    private lateinit var mDataBinding: ActivityDataBindBinding
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.创建绑定类
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind)
        //2.使用控件
        mDataBinding.btnChange.setOnClickListener {
            Log.d(TAG,"布局文件中对应的id为btn_change的Button被点击了！")
        }
        //3.绑定数据类
        mDataBinding.user=User("Hello World",18)
    }
}
```

是不是很简单，别急，这只是实现了和ViewBinding一样的功能。更强大的能力还在后面。

## 3.布局和绑定表达式

### 绑定表达式

DataBinding在布局中支持表达式。借助表达式可以实现与数据对象的单向/双向数据绑定。
在data标签中可以引入声明可使用的变量属性。
import:引入变量的完整类型。
variable:在词元素中可以声明变量的名称和类型。
name:变量名称。
type:变量类型。

```
  <data>
        <import type="com.jetpack.samples.databinding.User" />
        <variable
            name="user"
            type="User" />
    </data>
```
布局中的表达式使用@{}语法。
```
        <androidx.appcompat.widget.AppCompatTextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{user.name}"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

```
### 数据对象
    
在布局文件"android:text="@{user.name}""中使用的表达式@{user.name}，对应为Uer对象的name属性，如果改属性是private的但是提供了
对应的getName方法，那么也会被解析为name。
#### Kotlin
```
data class User(var name:String,var age:Int)

```   
#### Java 
```
public class User {
    private String name;
    private int age;

    public UserJava(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

# 总结


