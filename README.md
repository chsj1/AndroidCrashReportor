# Android异常捕获工具

##编译说明

1. 该项目基于AndroidStudio+JDK1.7开发。
2. 调试开关：

    默认情况下，项目仅打印较少的日志信息，在命令行中输入以下命令

        adb shell setprop log.tag.crashreportor VERBOSE

    可以开启详细的日志信息（你可能需要重启app）。

##项目依赖

该项目依赖两个兼容包：

1. com.android.support:appcompat-v7:23.1.1
2. com.android.support:support-v4:23.1.1

##项目Module

1. `library`：项目实现的核心库，异常捕获模块化处理；
2. `app_demo`：依赖于library，可直接运行。类似的，

##TODO

1. 支持`native`层异常捕获；
2. 支持文件上传到服务器；
3. 日志文件记录优化：单个文件记录日志信息、文件读写性能优化；
4. 日志文件信息加密处理。

