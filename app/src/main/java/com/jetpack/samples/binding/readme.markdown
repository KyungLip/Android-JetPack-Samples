# 设置是否生成绑定类

    tools:viewBindingIgnore="true"
    添加到布局文件的根视图中，用于设置是否生成绑定类。

# 设置生成的类型

## tools:viewBindingType="android.widget.TextView"

    使用条件：
    1.必须继承android.view.View。
    2.必须是放置它的标签类型的超类。

```
    <EditText
    android:id="@+id/tv_title"
    tools:viewBindingType="android.widget.TextView"
    .../>
    <TextView android:id="@+id/tv_title"
    tools:viewBindingType="android.widget.TextView"
    .../>
```

这样在绑定类中生成的tv_title就是TextView类型，而不是EditText类型。

# 绑定类位置

    /build/generated/data_binding_base_class_source_out