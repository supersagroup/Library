

模块名称：Login (GUI)

输入：用户账户和密码

处理：

```java
JSONObject response=new TcpClient("192.168.43.90",8080).action(new LoginRequest(account,password));//用JSON将帐号密码发送给服务器
int confirm=response.getInteger("confirm");//判断是否登录成功
int identity=response.getInteger("identity");//根据返回的identity（管理员/学生）来显示相对应的界面
```



算法描述

输出

```java
if(confirm==0) ;//显示登录失败界面
else{
	if(identity==0);//显示学生界面，传入帐号，在StudentUi中进行初始化
    else if(identity==1) ;//显示管理员界面，传入帐号，在ManagerUi中进行初始化
}
```



---

模块名称：ManagerUi.BorrowFrame

输入：学生ID、书ID

处理：通过`Attandent.borrow(String stuID, String bkID)`向服务器发送借书请求

算法描述

输出

```java
if (Attandent.borrow(stuID, bookID))
    ;//显示借阅成功
else
    ;//显示借阅失败
```

---

模块名称：ManagerUi.ReturnRemoveFrame

输入：书ID

处理：通过`Attandent.borrow(String bkID)`/`Attandent.remove(String bkID)`向服务器发送还书/删书请求

算法描述

输出

```java
if(PATTERN==ReturnRemoveFrame.RETURN)
	if(Attandent.borrow(bkID))
    	;//显示还书成功
	else
    	;//显示还书失败
if(PATTERN==ReturnRemoveFrame.REMOVE)
    if(Attandent.remove(bkID))
        ;//显示删除成功
	else
        ;//显示删书失败
```



---

模块名称：ManagerUi.AddFrame

输入:书名，作者，发行商，bkID，位置，（借阅）持续时间

处理：通过`Attandent.add_book(bkname, wr, pub, ID, loc, lasttime)`向服务器发送添书请求

算法描述

输出

```java
if(添书成功)
    ;//显示添书成功
else
    ;//显示添书失败
```



---

模块名称：ManagerUi.PayFineFrame

输入：学生ID

处理：通过`Attandent.pay_fine(String stuID)`向服务器发送还罚款请求

算法描述

输出

```java
if(Attandent.pay_fine(stuID))
    ;//显示还款成功
else
    ;//显示还款失败
```



---

模块名称：ManagerUi.PrintFrame

输入：书籍ID，作家（可为空），发行商（可为空）

处理：通过`Attandent.print_log(bkname,wr,pub)`向服务器发送请求，得到符合条件的书籍

算法描述

输出

```java
ArrayList<Book> books=Attandent.print_log(...);
;//根据books显示书籍（PrintUi）
```



---

模块名称：StudentUi.ShowInfoFrame

输入：null，在初始化学生类时直接获得学生的所有信息

处理

算法描述

输出：学生的所有信息

---

模块名称：StudentUi.PrintFrame

输入：书名，wr，pub

处理：通过`Student.search(bkname,wr,pub)`向服务器发送搜索请求，活动符合条件的书籍

算法描述

输出：显示所有书籍