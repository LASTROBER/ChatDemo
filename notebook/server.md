# 服务器功能
## *1.登入、登出、注册、修改信息*
### 对于数据库中没有的用户提示注册信息

1.注册信息需要指定用户名，密码，邮箱


注册流程：首先在数据库中搜索是否存在该用户【**这时候用户还没有创建，所以要放在登入同（后）级处理**】，如果不存在
获取邮箱和密码，密码二次验证，当正确输入发送的验证码后，
服务器在数据库的user表和user_p表插入相关信息，然后在服务器的文件夹（serverdir）
中创建以用户名为名字的文件夹（**注册时创建，登入后使用**）

如果用户需要开启文件传输功能，**需要设置本地接收文件的位置**

**邮箱验证码**这部分最后做

2.申请pop3协议，通过email给对应邮箱发送验证码。该验证码需要随机生成四位数字

以下为在maven中配置的依赖：

<!--javaMail-->
        <dependency>
            <groupId>javax.mail</groupId>
            <artifactId>javax.mail-api</artifactId>
            <version>1.5.6</version>
        </dependency>
        <dependency>
            <groupId>com.sun.mail</groupId>
            <artifactId>javax.mail</artifactId>
            <version>1.5.3</version>
        </dependency>

以下为参考代码：

      import javax.mail.internet.InternetAddress;
      import javax.mail.internet.MimeMessage;
      import java.util.Properties;
      
      /**
      * 发邮件工具类
        */
        public final class MailUtils {
        private static final String USER = "xxxxxxxxx@.com"; // 发件人称号，同邮箱地址※
        private static final String PASSWORD = "xxxxxxxxx"; // 授权码，开启SMTP时显示※
      
        /**
         *
         * @param to 收件人邮箱
         * @param text 邮件正文
           * @param title 标题
             */
             /* 发送验证信息的邮件 */
             public static boolean sendMail(String to, String text, String title){
             try {
             final Properties props = new Properties();
             props.put("mail.smtp.auth", "true");
             //            注意发送邮件的方法中，发送给谁的，发送给对应的app，※
             //            要改成对应的app。扣扣的改成qq的，网易的要改成网易的。※
             //            props.put("mail.smtp.host", "smtp.qq.com");
             props.put("mail.smtp.host", "smtp.163.com");
            package utils;
      
            import javax.mail.*;

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
     }catch (Exception e){
       e.printStackTrace();
       }
       return false;
       }

     public static void main(String[] args) throws Exception { // 做测试用
     MailUtils.sendMail("xxxxxx@qq.com","你好，这是一封测试邮件，无需回复。","测试邮件");//填写接收邮箱※
     System.out.println("发送成功");
     }
   
      }



### 对于数据库中存在的用户提示
    
1.密码错误（普通消息）
2.登入确认（确认消息）

### 登出用户向服务器发送登出消息，服务器接收，并通知好友该用户登出

通过设置数据库的Online参数。当用户登入时则向好友群发登入消息，登出同理
### 好友拥有查看用户基本信息的权限
## *2.处理用户之间通信*
用户发送普通消息给服务器，服务器处理报文（*发送方和接收方*）

根据报文内容：（**如何处理离线聊天记录？**）
1.
   先判断是否为好友关系
2. 判断对方是否在线，如果在线就直接发送，如果不在线就存储到数据库中，
等待对方上线后刷新在页面中

## *3.好友管理*
### 1.添加好友
1.通过UserId搜索

2.通过用户自行设置的昵称搜索

3.客户端向服务器发送添加好友请求，服务器向对方发送通知，对方返回确认或者拒绝消息，服务器接收到消息
在数据库中完成相应操作，然后将结果发送给客户端
### 2.删除好友
1.客户端向服务器发送删除好友请求，服务器在数据库中完成操作，将结果返回个客户端

2.服务器需要向被删除的人发送消息，并且修改被删除的人的数据库值（***因为删除是双向删除***）
### 3.查看好友信息
## 4.创建群组

## 5.文件传输

1.服务端需要为每个用户创建文件夹

2.文件传输流程：
   
   1.服务器接收客户端文件后，存储在服务器文件夹中用户对应目录

   2.当接收方同意接收文件
   
   3.服务器将文件从服务器文件夹存放在接收方文件夹

3.对文件进行判断：
   1.文件名字是否重复，如果重复则覆盖
   2，格式化文件大小，显示出GB、MB、KB，B等单位

## 拓展部分

1.[使用scoket在公网上通信](https://blog.csdn.net/u011630458/article/details/19832009)

2.[scoket实现文件传输](https://blog.csdn.net/huang930528/article/details/52401565)

3.[登入邮箱验证码](https://blog.csdn.net/GB__LaoWang/article/details/120289596)