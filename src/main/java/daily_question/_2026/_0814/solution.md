# 3090. 每个字符最多出现两次的最长子字符串

题目链接：https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences/description/

## 题目描述

给定一个字符串 `s`，返回满足「每个字符最多出现两次」的最长子字符串的长度。

## 思路：滑动窗口

维护窗口 `[left, right]`，用 `Map<Character, Integer>` 记录窗口内每个字符的出现次数：

1. `right` 不断右移，将新字符加入窗口，频次 +1；
2. 若该字符频次超过 2，说明窗口不合法，从 `left` 开始收缩：移除 `left` 处字符，频次 -1，`left++`，直到该字符频次 ≤ 2；
3. 此时窗口 `[left, right]` 一定合法，用 `right - left + 1` 更新答案。

因为 `left`、`right` 都只向右移动，时间复杂度 O(n)，空间复杂度 O(|Σ|)（HashMap 最多存 26 个键）。

## 示例推演

以 `main` 中的测试用例 `s = "bdbbabccad"` 为例：

| right | 字符 | 收缩后窗口 | occ（窗口内频次） | ans |
|-------|------|-----------|------------------|-----|
| 0 | b | [0,0]="b" | b:1 | 1 |
| 1 | d | [0,1]="bd" | b:1,d:1 | 2 |
| 2 | b | [0,2]="bdb" | b:2,d:1 | 3 |
| 3 | b | [1,3]="dbb"（b 超 2，移除 left=0 的 b） | b:2,d:1 | 3 |
| 4 | a | [1,4]="dbba" | b:2,d:1,a:1 | 4 |
| 5 | b | [3,5]="bab"（b 超 2，依次移除 d、b） | b:2,a:1 | 4 |
| 6 | c | [3,6]="babc" | b:2,a:1,c:1 | 4 |
| 7 | c | [3,7]="babcc" | b:2,a:1,c:2 | 5 |
| 8 | a | [3,8]="babcca" | b:2,a:2,c:2 | 6 |
| 9 | d | [3,9]="babccad" | b:2,a:2,c:2,d:1 | 7 |

返回 7。

## 出错信息（备注）

代码中注释记录了一个容易踩的坑：收缩窗口的 `while` 循环里错误地写了

```java
ch = s.charAt(left);
```

复用了外层变量 `ch`。问题在于：`ch` 是 `right` 处字符——正是它触发频次超限，`while` 条件 `occ.get(ch) > 2` 要靠它判断是否继续收缩。在循环内把它改成 `left` 处字符后，条件判断的对象变成了刚被移除的那个字符，而它的频次刚刚减 1，大概率已经 ≤ 2，于是循环**提前退出**，超限字符的频次根本没降下来，窗口仍然不合法。

反例：`s = "abbbc"`，正确答案是 3（如 "abb"、"bbc"）。

- right=3 时窗口 "abbb"，b 出现 3 次，进入收缩循环；
- 错误写法把 `ch` 改为 `s.charAt(0) = 'a'`，移除 a 后 `occ['a'] = 0`，条件 `0 > 2` 不成立，循环立刻退出；
- 此时窗口 "bbb" 中 b 仍出现 3 次，却按合法窗口参与计算，最终返回 4。

正确做法：用一个新变量接收 `left` 处字符，循环条件里的 `ch` 保持不动：

```java
while (occ.get(ch) > 2) {
    char lch = s.charAt(left);
    occ.put(lch, occ.getOrDefault(lch, 0) - 1);
    left++;
}
```

本质：`while` 条件的判断对象（`right` 处超限字符）和循环体的操作对象（`left` 处被移除字符）不是同一个字符，必须用两个变量区分。

## 代码

```java
public static int maximumLengthSubstring(String s) {
    int ans = 0, left = 0;
    Map<Character, Integer> occ = new HashMap<>();
    for (int right = 0; right < s.length(); right++) {
        char ch = s.charAt(right);
        occ.put(ch, occ.getOrDefault(ch, 0) + 1); // 元素加入窗口，频次 + 1
        while (occ.get(ch) > 2) {                 // 窗口不合法则收缩左边界
            char lch = s.charAt(left);
            occ.put(lch, occ.getOrDefault(lch, 0) - 1);
            left++;
        }
        ans = Math.max(ans, right - left + 1);    // 窗口 [left, right] 长度
    }
    return ans;
}
```