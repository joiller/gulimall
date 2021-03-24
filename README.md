# 关于nacos
由于我是 macOS Big Sur 

他自带一个java，我自己也下载了一个java。
默认的是自带的java，通过`/usr/libexec/java_home -V`可以查看所有的java版本和位置，和默认的java。
结果是： 
```
(base) jianghuilai@jianghuilaideMacBook-Pro bin % /usr/libexec/java_home -V
Matching Java Virtual Machines (2):
    1.8.271.09 (x86_64) "Oracle Corporation" - "Java" /Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home
    1.8.0_241 (x86_64) "Oracle Corporation" - "Java SE 8" /Library/Java/JavaVirtualMachines/jdk1.8.0_241.jdk/Contents/Home
/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home
```
> 当然不带参数就是直接返回默认java。

这里显示有两个java。一个是1.8.271.09，这个是系统自带的（还时不时的提醒我更新，原来我更新的不是我平时用的）
另一个是1.8.0_241，这个是我下载的。默认的在最后一行，他的路径为`/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home`。

这个路径里面有空格，nacos识别出问题，而且也不是我平时用的版本，所以干脆直接改一下默认的java：
`export JAVA_HOME=\`/usr/libexec/java_home -v 1.8.0_241\``

