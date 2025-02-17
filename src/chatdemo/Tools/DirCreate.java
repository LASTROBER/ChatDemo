package chatdemo.Tools;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DirCreate {

    private String path;
    public String createServerDir() {
        Scanner scanner = new Scanner(System.in);

            System.out.println("请输入创建路径");
            path = scanner.nextLine();
            scanner.nextLine();
            File file = new File(path, "serverdir");

            if (file.exists()) {
                System.out.println("文件夹已存在");
            return null;
            } else if (file.mkdir()) {
                System.out.println("文件夹 serverdir 在目录 "+path+" 下创建成功");
                try {
                    return file.getCanonicalPath();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("创建失败");
                return null;
        }
    }

    public void createClientDir(String clientName) {

        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("请输入创建路径");
            path = scanner.nextLine();
            scanner.nextLine();

            File file = new File(path, clientName);
            if (file.exists()) {
                System.out.println("文件夹已存在");
            } else if (file.mkdir()) {
                System.out.println(String.format("文件夹 %s 在目录 %s 下创建成功", clientName, path));
                break;
            } else {
                System.out.println("创建失败");
                break;
            }
        }

    }

}
