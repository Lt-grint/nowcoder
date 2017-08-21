package litao.worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by tao on 2017/8/21.
 */

/*
### 题目1 洗牌
#### 题目描述

洗牌在生活中十分常见，现在需要写一个程序模拟洗牌的过程。 现在需要洗2n张牌，从上到下依次是第1张，第2张，第3张一直到第2n张。首先，我们把这2n张牌分成两堆，左手拿着第1张到第n张（上半堆），右手拿着第n+1张到第2n张（下半堆）。接着就开始洗牌的过程，先放下右手的最后一张牌，再放下左手的最后一张牌，接着放下右手的倒数第二张牌，再放下左手的倒数第二张牌，直到最后放下左手的第一张牌。接着把牌合并起来就可以了。 例如有6张牌，最开始牌的序列是1,2,3,4,5,6。首先分成两组，左手拿着1,2,3；右手拿着4,5,6。在洗牌过程中按顺序放下了6,3,5,2,4,1。把这六张牌再次合成一组牌之后，我们按照从上往下的顺序看这组牌，就变成了序列1,4,2,5,3,6。 现在给出一个原始牌组，请输出这副牌洗牌k次之后从上往下的序列。

#### 输入描述:

第一行一个数T(T ≤ 100)，表示数据组数。对于每组数据，第一行两个数n,k(1 ≤ n,k ≤ 100)，接下来一行有2n个数a1,a2,...,a2n(1 ≤ ai ≤ 1000000000)。表示原始牌组从上到下的序列。

#### 输出描述:

对于每组数据，输出一行，最终的序列。数字之间用空格隔开，不要在行末输出多余的空格。

### 示例1
#### 输入
```
3
3 1
1 2 3 4 5 6
3 2
1 2 3 4 5 6
2 2
1 1 1 1
```
#### 输出
```
1 4 2 5 3 6
1 5 4 3 2 6
1 1 1 1
```
 */
public class Topic001 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        ArrayList results[] = new ArrayList[T];
        for (int i = 0; i < T; i++) {
            //一只手拿的牌数
            int n = in.nextInt();
            //洗牌次数
            int k = in.nextInt();
            //存放结果的数组

            ArrayList<String> list = new ArrayList<String>(2*n);
            ArrayList<String> other = new ArrayList<String>();
            for(int j = 0; j <2 * n; j++) {
                String oneBrand = in.next();
                list.add(oneBrand);
            }
            while (k > 0) {
                other = shuffle(list);
                list = other;
                k--;
            }
            results[i] = other;
        }
        for (ArrayList<String> list:results) {
            for (int i = 0; i < list.size()-1; i++) {
                System.out.print(list.get(i)+" ");
            }
            System.out.println(list.get(list.size()-1));
        }
    }
    public static Stack listToStack(List<String> list) {
        Stack stack = new Stack();
        for (String s:list) {
            stack.push(s);
        }
        return stack;
    }
    public static ArrayList reverse(ArrayList list) {
        ArrayList other = new ArrayList();
        for (int j = list.size()-1; j >= 0; j--) {
            other.add(list.get(j));
        }
        return other;
    }
    public static ArrayList shuffle(ArrayList list) {
        int n = list.size() / 2;
        List<String> leftList = list.subList(0,n);
        List<String> rightList = list.subList(n,2*n);
        Stack left = listToStack(leftList);
        Stack right = listToStack(rightList);
        ArrayList result = new ArrayList();
        list.clear();
        while (!right.isEmpty()) {
            list.add(right.pop());
            list.add(left.pop());
        }
        result = reverse(list);
        list = result;
        return result;
    }
}
