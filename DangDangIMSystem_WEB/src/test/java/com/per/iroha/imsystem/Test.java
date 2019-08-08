package com.per.iroha.imsystem;

import com.per.iroha.mapper.UserMapperImpl;
import com.per.iroha.model.Advice;

import java.sql.SQLException;
import java.util.*;

public class Test {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    @org.junit.Test
    public void test() throws SQLException {
        UserMapperImpl userMapper = new UserMapperImpl();
        HashMap map = new HashMap();
        Advice advice = new Advice();
        advice.setDate("123123");
        advice.setFromUsername("123123");
        advice.setAdvice("123123");

        userMapper.saveAdvice(advice);
    }

    public int StrToInt(String str) {
        if(str.equals("") || str.length() == 0){
            return 0;
        }
        int flag = 0;
        int sum = 0;
        char[] a = str.toCharArray();
        if(a[0] == '-'){
            flag = 1;
        }
        for(int i = flag; i < a.length; i++){
            if(a[i] == '+'){
                continue;
            }
            if(a[i] > '9' || a[i] < '0'){
                return 0;
            }
            sum = sum * 10 + a[i] - '0';
        }
        return flag == 0 ? sum : sum * (-1);
    }

    public boolean duplicate(int numbers[],int length,int [] duplication) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < length; i++){
            if(hashSet.contains(numbers[i])){
                duplication[0] = numbers[i];
                return false;
            }else{
                hashSet.add(numbers[i]);
            }
        }
        return true;
    }

    public int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        if(length != 0){
            B[0] = 1;
            for(int i = 1; i < length; i++){
                B[i] = B[i - 1] * A[i - 1];
            }
            int temp = 1;
            for(int j = length - 2; j >= 0; j--){
                temp *= A[j + 1];
                B[j] *= temp;
            }
        }
        return B;
    }

    public boolean match(char[] str, char[] pattern)
    {
        if(str.length == 0 || pattern.length == 0){
            return false;
        }
        int strIndex = 0, parIndex = 0;
        return matchString(str,pattern,strIndex,parIndex);
    }

    public boolean matchString(char[] str, char[] pattern,int strIndex,int parIndex){
        if(strIndex == str.length && parIndex == pattern.length){
            return true;
        }
        if(strIndex != str.length && parIndex == pattern.length){
            return false;
        }
        if(parIndex + 1 < pattern.length && pattern[parIndex + 1] == '*'){
            if((strIndex != str.length && pattern[parIndex] == str[strIndex]) || (strIndex != str.length && pattern[parIndex] == '.')){
                return matchString(str,pattern,strIndex + 1,parIndex) || matchString(str,pattern,strIndex,parIndex + 2) || matchString(str,pattern,strIndex + 1,parIndex + 2);
            }else{
                return matchString(str,pattern,strIndex,parIndex + 2);
            }
        }
        if ((strIndex != str.length && pattern[parIndex] == str[strIndex]) || (strIndex != str.length && pattern[parIndex] == '.')){
            return matchString(str,pattern,strIndex + 1,parIndex + 1);
        }
        return false;
    }

    private int index = 0;
    public boolean isNumeric(char[] str) {
        if(str.length == 0){
            return false;
        }
        boolean flag = scanInteger(str);

        if(index < str.length && str[index] == '.'){
            index++;
            flag = scanInteger(str) || flag;
        }
        if(index < str.length && (str[index] == 'E' || str[index] == 'e')){
            index++;
            flag = scanUnsignInteger(str) && flag;
        }
        return flag && index == str.length;
    }

    private boolean scanInteger(char[] str){
        if(index < str.length && (str[index] == '-' || str[index] == '+')){
            index++;
        }
        return scanUnsignInteger(str);
    }
    private boolean scanUnsignInteger(char[] str){
        int start = index;
        while(index < str.length && (str[index] >= '0' && str[index] <= '9')){
            index++;
        }
        return index - start > 0;
    }

    private LinkedHashMap<Character,Integer> linkedHashMap = new LinkedHashMap<>();
    public void Insert(char ch)
    {
        if(linkedHashMap.containsKey(ch)){
            linkedHashMap.put(ch,linkedHashMap.get(ch) + 1);
        }else{
            linkedHashMap.put(ch,1);
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        for(Map.Entry<Character,Integer> entry : linkedHashMap.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return '#';
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead == null || pHead.next == null){
            return null;
        }
        ListNode fast = pHead.next;
        ListNode slow = pHead;
        while(fast != slow){
            if(fast.next != null && fast.next.next != null){
                fast = fast.next;
                slow = slow.next;
            }else{
                return null;
            }
        }

        slow = pHead;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode deleteDuplication(ListNode pHead)
    {
        ListNode p = new ListNode(-1);
        p.next = pHead;
        ListNode slow = p;
        ListNode fast = pHead;

        while(fast != null && fast.next != null){
            if(fast.val == fast.next.val){
                int val = fast.val;
                while(fast != null && fast.val == val){
                    fast = fast.next;
                }
                slow.next = fast;
            }else{
                slow = fast;
                fast = fast.next;
            }
        }
        return p.next;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode == null){
            return null;
        }
        if(pNode.right != null){
            pNode = pNode.right;
            while(pNode.left != null){
                pNode = pNode.left;
            }
            return pNode;
        }
        while(pNode.next != null){
            if(pNode.next.left == pNode){
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }

    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot == null){
            return true;
        }
        return compare(pRoot.left, pRoot.right);
    }

    private boolean compare(TreeNode left,TreeNode right){
        if(left == null){
            return right == null;
        }
        if(right == null){
            return false;
        }
        if(left.val != right.val){
            return false;
        }
        return compare(left.left,left.right) && compare(right.left,right.right);
    }

    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(pRoot == null){
            return result;
        }
        boolean flag = true;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(pRoot);
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            if(flag){
                ArrayList<Integer> arrayList = new ArrayList<>();
                while(!stack1.isEmpty()){
                    TreeNode node = stack1.pop();
                    arrayList.add(node.val);
                    if(node.left != null){
                        stack2.push(node.left);
                    }
                    if(node.right != null){
                        stack2.push(node.right);
                    }
                }
                result.add(arrayList);
                flag = !flag;
            }else{
                ArrayList<Integer> arrayList = new ArrayList<>();
                while(!stack2.isEmpty()){
                    TreeNode node = stack2.pop();
                    arrayList.add(node.val);
                    if(node.right != null){
                        stack1.push(node.right);
                    }
                    if(node.left != null){
                        stack1.push(node.left);
                    }
                }
                result.add(arrayList);
                flag = !flag;
            }
        }
        return result;
    }

    ArrayList<ArrayList<Integer> > Printl(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(pRoot == null){
            return res;
        }
        boolean flag = true;
        LinkedList<TreeNode> linkedList1 = new LinkedList<>();
        LinkedList<TreeNode> linkedList2 = new LinkedList<>();
        linkedList1.add(pRoot);
        while(!linkedList1.isEmpty() || !linkedList2.isEmpty()){
            if(flag){
                ArrayList<Integer> arrayList = new ArrayList<>();
                while(!linkedList1.isEmpty()){
                    TreeNode node = linkedList1.poll();
                    arrayList.add(node.val);
                    if(node.left != null){
                        linkedList2.add(node.left);
                    }
                    if(node.right != null){
                        linkedList2.add(node.right);
                    }
                }
                res.add(arrayList);
                flag = !flag;
            }else{
                ArrayList<Integer> arrayList = new ArrayList<>();
                while(!linkedList2.isEmpty()){
                    TreeNode node = linkedList2.poll();
                    arrayList.add(node.val);
                    if(node.left != null){
                        linkedList1.add(node.left);
                    }
                    if(node.right != null){
                        linkedList1.add(node.right);
                    }
                }
                res.add(arrayList);
                flag = !flag;
            }
        }
        return res;
    }

    TreeNode KthNode(TreeNode pRoot, int k)
    {
        int i = 0;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.empty() || pRoot != null){
            while(pRoot != null){
                stack.push(pRoot);
                pRoot = pRoot.left;
            }
            pRoot = stack.pop();
            i++;
            if(i == 7){
                return pRoot;
            }else{
                pRoot = pRoot.right;
            }
        }
        return null;
    }

    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private int count = 0;

    //读入字符，放到合适位置
    public void Insert(Integer num) {
        if (count %2 == 0) {
            maxHeap.offer(num);
            int filteredMaxNum = maxHeap.poll();
            minHeap.offer(filteredMaxNum);
        } else {
            minHeap.offer(num);
            int filteredMinNum = minHeap.poll();
            maxHeap.offer(filteredMinNum);
        }
        count++;
    }

    //求中位数
    public Double GetMedian() {
        if (count %2 == 0) {
            return new Double((minHeap.peek() + maxHeap.peek())) / 2;
        } else {
            return new Double(minHeap.peek());
        }
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        if(num.length == 0 || size <= 0 || num.length < size){
            return arrayList;
        }
        for(int i = 0; i < num.length; i++){
            while(!linkedList.isEmpty() && num[linkedList.peekLast()] < num[i]){
                linkedList.pollLast();
            }
            linkedList.addLast(i);
            if(linkedList.peekFirst() == i - size){
                linkedList.pollFirst();
            }
            if(i - size + 1 >= 0){
                arrayList.add(num[linkedList.peekFirst()]);
            }
        }
        return arrayList;
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        boolean[] check = new boolean[matrix.length];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(searchFromHere(matrix,rows,cols,i,j,str,0,check)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchFromHere(char[] matrix, int rows, int cols, int r, int c, char[] str,int index, boolean[] check){
        if(r < 0 || r >= rows || c < 0 || c >= cols || matrix[r * cols + c] != str[index] || check[r * cols + c]){
            return false;
        }
        if(index == str.length -1){
            return true;
        }
        check[r * cols + c] = true;
        if(searchFromHere(matrix,rows,cols,r - 1,c,str,index + 1,check) ||
                searchFromHere(matrix,rows,cols,r + 1,c,str,index + 1,check)||
                searchFromHere(matrix,rows,cols,r,c - 1,str,index + 1,check)||
                searchFromHere(matrix,rows,cols,r,c + 1,str,index + 1,check)){
            return true;
        }
        check[r * cols + c] = false;
        return false;
    }

    public int movingCount(int threshold, int rows, int cols)
    {
        boolean[][] check = new boolean[rows][cols];
        return search(threshold,rows,cols,0,0,check);
    }

    private int search(int threshold, int rows, int cols, int r, int c, boolean[][] check){
        if(r < 0 || r >= rows || c < 0 || c >= cols || bitCount(r) + bitCount(c) > threshold || check[r][c]){
            return 0;
        }
        check[r][c] = true;
        return search(threshold,rows,cols,r - 1,c,check) +
                search(threshold,rows,cols,r + 1,c,check) +
                search(threshold,rows,cols,r,c - 1,check) +
                search(threshold,rows,cols,r,c + 1,check) + 1;

    }

    private int bitCount(int num){
        int count = 0;
        while(num != 0){
            count += num % 10;
            num /= 10;
        }
        return count;
    }
}
