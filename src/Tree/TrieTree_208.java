package Tree;

public class TrieTree_208 {
    /*208. 实现 Trie (前缀树)*/
    /*
    前缀树的节点底层是一个节点数组包含当前节点所有子节点的信息;
    以及一个Boolean类型的变量，用于表示当前节点是否为一个单词的末尾;
     */
    public class TrieNode{
        public TrieNode[] childNodes;

        public static final int R = 26;

        public boolean flag = false;

        public TrieNode(){
            childNodes = new TrieNode[R];
        }

        public boolean contains(char c){
            return childNodes[c - 'a'] != null;
        }

        public void put(char c){
            childNodes[c - 'a'] = new TrieNode();
        }

        public TrieNode get(char c){
            return childNodes[c - 'a'];
        }

        public void setF(){
            flag = true;
        }

        public boolean getF(){
            return flag;
        }
    }

    public TrieNode root;

    /** Initialize your data structure here. */
    public TrieTree_208() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!cur.contains(c)){
                cur.put(c);
            }
            cur = cur.get(c);
        }
        cur.setF();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!cur.contains(c)){
                return false;
            }
            cur = cur.get(c);
        }
        return cur.getF();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(!cur.contains(c)){
                return false;
            }
            cur = cur.get(c);
        }
        return true;
    }
}
