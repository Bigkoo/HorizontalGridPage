## 一、介绍

一款采用RecyclerView的横向翻页网格布局控件，支持自定义行和列。自带翻页指示器。重要的是数据横向排列。
![3x4效果图](https://res.bigkoo.com/horizontalgridpage3x4Demo.gif)
![1x1效果图](https://res.bigkoo.com/horizontalgridpage3x4Demo.gif)



## 二、数据排列方式

![转换排列](https://res.bigkoo.com/PageGridView/2.png)



## 三、文件说明

* HorizontalGridPage---横向网格页面
* PageGridView---------网格视图
* PageIndicatorView----翻页指示器
* PageGridAdapter------页面适配器
* PageBuilder----------参数构建器
* PageCallBack---------回调处理



## 四、选项配置

* 自定义网格（行数、列数）
* 自定义页面边距
* 是否显示指示器
* 自定义指示器大小
* 自定义指示器间隔
* 自定义指示器图片
* 自定义指示器位置
* 自定义滑动翻页百分比



## 五、用法

**引入**

Gradle方式：
```java
implementation 'com.bigkoo:horizontalgridpage:1.0'
```

或者

Maven方式：
```java
<dependency>
  <groupId>com.bigkoo</groupId>
  <artifactId>horizontalgridpage</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```

布局文件
```java
<com.bigkoo.horizontalgridpage.HorizontalGridPage
    android:id="@+id/pageView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

```java
// 构建参数
PageBuilder pageBuilder = new PageBuilder.Builder()
            .setGrid(1, 1)//设置网格
            .setPageMargin(0)//页面边距
            .setIndicatorMargins(5, 5, 5, 5)//设置指示器间隔
            .setIndicatorSize(10)//设置指示器大小
            .setIndicatorRes(android.R.drawable.presence_invisible,
                    android.R.drawable.presence_online)//设置指示器图片资源
            .setIndicatorGravity(Gravity.CENTER)//设置指示器位置
            .setSwipePercent(50)//设置翻页滑动距离百分比（1-100）
            .setShowIndicator(true)//设置显示指示器
            .build();
```

```java
HorizontalGridPage pageView = findViewById(R.id.pageView);
pageView.init(pageBuilder);

// Activity implements PageCallBack
PageGridAdapter adapter = new PageGridAdapter<>(this);
adapter.init(pageBuilder);
adapter.setData(data);
pageView.setAdapter(adapter);
```


## 六、注意事项

1、通过position获取数据时，data对象需要从adapter.getData().get(position)来获取。
2、整个控件都可以不指定高度自动计算。


## 七、版本

当前版本：v1.0

更新说明：
* 发布版本v1.0

[版本更新说明](https://github.com/Bigkoo/HorizontalGridPage/wiki/版本更新说明)
