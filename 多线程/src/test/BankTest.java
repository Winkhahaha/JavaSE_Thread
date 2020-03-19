package test;

import java.util.Scanner;

public class BankTest {
    public static void main(String[] args) {
        Banker2 bk = new Banker2();
        bk.menu();
    }
}

class Banker2 {
    Scanner scanner = new Scanner(System.in);
    int threadNum;          // 进程数
    int available[];        // 系统可用资源
    int max[][];            // 单个进程对各个资源最大需求
    int allocation[][];     // 单个进程当前所分配到的资源
    int need[][];           // 需求
    int request[];          // 存放请求到的资源


    int thread;         // 记录正在获取资源的进程号

    // 初始化数据
    public void initData() {
        System.out.println("请输入进程数:");
        this.threadNum = scanner.nextInt();
        System.out.println("请输入资源种类数:");
        int resources = scanner.nextInt();
        this.available = new int[resources];
        this.max = new int[threadNum][resources];
        this.allocation = new int[threadNum][resources];
        this.need = new int[threadNum][resources];
        this.request = new int[resources];
        System.out.println("请输入" + resources + "种资源的数量：");

        // 输入各类资源数量
        for (int i = 0; i < available.length; i++) {
            available[i] = scanner.nextInt();
        }
        // 输入每个进程对各类资源的最大需求
        for (int i = 0; i < threadNum; i++) {
            System.out.println("请输入进程" + i + "对" + resources + "种资源的最大需求");
            for (int j = 0; j < available.length; j++) {
                max[i][j] = scanner.nextInt();
            }
        }

        // 输入给每个进程分配的三类资源数
        for (int i = 0; i < threadNum; i++) {
            System.out.println("请输入进程" + i + "已分配的" + resources + "种资源数");
            for (int j = 0; j < available.length; j++) {
                allocation[i][j] = scanner.nextInt();
            }
        }

        // 计算每个进程还需要的三类资源数
        for (int i = 0; i < threadNum; i++) {
            for (int j = 0; j < available.length; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        // 重新计算available(第一次分配完后三类资源剩余数)
        for (int i = 0; i < available.length; i++) {
            for (int j = 0; j < threadNum; j++) {
                available[i] -= allocation[j][i];
            }
        }
    }

    public boolean check2() {//判断请求的资源是否超过系统剩余的资源
        for (int i = 0; i < available.length; i++) {
            if (request[i] > available[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean check1(int thread) {//判断请求的资源是否超过需要的资源
        for (int i = 0; i < available.length; i++) {
            if (request[i] > need[thread][i]) {
                return true;
            }
        }
        return false;
    }

    // 用户输入要申请资源的进程和申请的资源，并进行判断
    public void getThread() {
        System.out.println("请输入申请资源的线程");
        for (; ; ) {
            thread = scanner.nextInt();
            if (thread < 0 || thread > threadNum - 1) {
                System.out.println("该线程不存在,请重新输入");
            } else {
                break;
            }
        }
        System.out.println("请输入申请的资源(若某种资源不申请则填0)");
        for (; ; ) {
            for (int i = 0; i < available.length - 1; i++) {
                request[i] = scanner.nextInt();
            }
            if (check1(thread)) {
                System.out.println(thread + "线程申请的资源超出其需要的资源，请重新输入");
            } else if (check2()) {
                System.out.println(thread + "线程申请的资源大于系统资源，请重新输入");
            } else  {
                break;
            }
        }
        changeData(thread);
       // showData();
        if (!check()) {
            System.out.println("资源申请失败!");
            recoverData(thread);//系统不安全,还原已经分配的资源
            //showData();
        } else {
            System.out.println("资源申请成功!");
            checkNeedIs0(thread);
            //showData();
        }
    }

    private void checkNeedIs0(int nowThread) {
        nowThread = this.thread;
        int sum = 100;
        for (int j = 0; j < available.length; j++) {
            sum *= need[nowThread][j];
        }
        if (sum == 0) {
            for (int j = 0; j < available.length; j++) {
                available[j] += max[nowThread][j];
            }
        }
    }

    // thread线程请求响应后，试探性分配资源
    public void changeData(int thread) {
        for (int i = 0; i < available.length; i++) {
            // 重新调整系统资源数
            available[i] -= request[i];
            // 计算各个进程拥有资源
            allocation[thread][i] += request[i];
            // 重新计算需求
            need[thread][i] -= request[i];
        }
    }

    // 安全性检查未通过，分配失败时调用，恢复系统原状
    public void recoverData(int thread) {
        for (int i = 0; i < available.length; i++) {
            // 重新调整系统资源数
            available[i] += request[i];
            // 计算各个进程拥有资源
            allocation[thread][i] -= request[i];
            // 重新计算需求
            need[thread][i] += request[i];
        }
    }

    // 对线程thread安全性检查
    public boolean check() {
        boolean finish[] = new boolean[threadNum];
        int work[] = new int[available.length];
        int queue[] = new int[threadNum];// 用于存放安全队列
        int k = 0;// 安全队列下标
        int j = 0; // 要判断的进程
        int i;
        // 是否分配的标志
        for (i = 0; i < threadNum; i++) {
            finish[i] = false;
        }
        for (i = 0; i < available.length; i++) {
            work[i] = available[i];
        }
        while (j < threadNum) {
            for (i = 0; i < available.length; i++) {
                if (finish[j]) {
                    j++;
                    break;
                } else if (need[j][i] > work[i]) { //某一类资源需求量大于系统的数量
                    j++;
                    break;
                } else if (i == available.length - 1) {
                    for (int m = 0; m < available.length; m++) {
                        work[m] += allocation[j][m];
                    }
                    finish[j] = true;
                    queue[k] = j;
                    k++;
                    j = 0; // 从最小进程再开始判断
                }
            }
        }
        // 判断是否都属于安全状态
        for (int p = 0; p < threadNum; p++) {
            if (finish[p] == false) {
                System.out.println("系统不安全");
                return false;
            }
        }
        System.out.println("系统是安全的,安全队列为：");
        for (int q = 0; q < threadNum; q++) {
            System.out.print(queue[q] + " ");
        }
        System.out.println();
        return true;
    }

    // 打印表，需要时调用
    public void showData() {
        System.out.println("<---Available--->");
        for (int j = 0; j < available.length; j++) {
            System.out.print(available[j] + "     ");
        }
        System.out.println();

        System.out.println("<---Allocation--->");
        for (int i = 0; i < threadNum; i++) {
            for (int j = 0; j < available.length; j++) {
                System.out.print(allocation[i][j] + "     ");
            }
            System.out.println();
        }
        System.out.println("*****************");

        System.out.println("<-----Need----->");
        for (int i = 0; i < threadNum; i++) {
            for (int j = 0; j < available.length; j++) {
                System.out.print(need[i][j] + "     ");
            }
            System.out.println();
        }
        System.out.println("*****************");

        System.out.println("<-----Max----->");
        for (int i = 0; i < threadNum; i++) {
            for (int j = 0; j < available.length; j++) {
                System.out.print(max[i][j] + "     ");
            }
            System.out.println();
        }


    }

    public void menu() {
        String q = "";
        while (!q.equals("quit")) {
            System.out.println("按照提示输入数字,选择你想要查询的业务:");
            System.out.println("1----------------键盘录入初始化资源数目");
            System.out.println("2----------------查看当前资源分配矩阵");
            System.out.println("3----------------按需资源分配,分配完成后自动进行安全检测");
            System.out.println("4----------------回调资源(发生误操作时)");
            System.out.println("5----------------安全检测");
            System.out.println("0----------------结束");
            switch (scanner.nextInt()) {
                case 1:
                    initData();
                    System.out.println("**********************************");
                    break;
                case 2:
                    showData();
                    System.out.println("**********************************");
                    break;
                case 3:
                    getThread();
                    System.out.println("**********************************");
                    break;
                case 4:
                    for (int i = 0; i < threadNum; i++) {
                        recoverData(i);
                    }
                    System.out.println("**********************************");
                    break;
                case 0:
                    q = "quit";
                    System.out.println("******************程序结束*****************");
                    break;
                case 5:
                    check();
                    System.out.println("**********************************");
                    break;
            }

        }
    }

}


