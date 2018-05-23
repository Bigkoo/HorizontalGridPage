# PageGridView

## 介绍

一款采用RecyclerView的横向翻页网格布局控件，支持自定义行和列。自带翻页指示器。重要的是数据横向排列。
![效果图](https://res.bigkoo.com/PageGridView/PageGridView.gif)


此控件的数据排列顺序做了调整。
下图为Android默认的数据排列方式
![原始排列](https://res.bigkoo.com/PageGridView/1.png)

下图为优化后的数据排列方式，更符合我们日常。
![转换排列](https://res.bigkoo.com/PageGridView/2.png)



## 文件说明

* PageGridRecyclerView   网格页面
* PageIndicatorView   翻页指示器
* PageGridAdapter   页面适配器
* PageBuilder   参数构建器
* CallBack   回调处理



## 选项配置

* 自定义行和列
* 自定义页面边距
* 自定义数据排列方向
* 自定义指示器大小
* 自定义指示器间隔
* 自定义指示器图片
* 自定义翻页有效距离



## 用法

**引入**

Gradle方式：
```java
implementation 'com.bigkoo:pagegridrecyclerview:1.1'
```

或者

Maven方式：
```java
<dependency>
  <groupId>com.bigkoo</groupId>
  <artifactId>pagegridrecyclerview</artifactId>
  <version>1.1</version>
  <type>pom</type>
</dependency>
```

布局文件
```java
    <com.bigkoo.pagegridview.PageGridRecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="match_parent"
        android:layout_height="300dp" />

    <com.bigkoo.pagegridview.PageIndicatorView
        android:id="@+id/indicator"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp" />
```

```java
// 构建参数
PageBuilder pageBuilder = new PageBuilder.Builder()
	.setGrid(3, 4)//设置网格
	.setPageMargin(5)//页面边距
	.setIndicatiorMargins(5)//设置指示器间隔
	.setIndicatorSize(15)//设置指示器大小
	.setIndicatorBackground(android.R.drawable.presence_online,
			android.R.drawable.presence_invisible)//设置指示器图片资源
	.setDataOrientation(PageGridAdapter.Orientation.HORIZONTAL)//设置数据排列方向
	.setSwipePageDistance(50)//设置翻页滑动距离百分比（1-100）
	.build();
```

```java
        //从XML加载网格视图
        PageGridRecyclerView pageGridRecyclerView = findViewById(R.id.recyclerview);
        //网格页面初始化配置
        pageGridRecyclerView.init(pageBuilder);
        //从XML加载指示器
        PageIndicatorView pageIndicatorView = findViewById(R.id.indicator);
        //指示器初始化配置
        pageIndicatorView.init(pageBuilder);
        //网格页面加入指示器
        pageGridRecyclerView.setIndicator(pageIndicatorView);
```


## 注意事项

1、通过position获取数据时，如果设置数据排列方向为横向，data对象需要从adapter.getData().get(position)来获取；如果数据排列方向为纵向，可以直接data.get(position)获取。

## 版本

当前版本：v1.2

更新说明(2018-5-20)：
* 支持设置翻页距离

[版本历史](https://github.com/Bigkoo/PageGridRecyclerView/wiki/版本更新历史)
