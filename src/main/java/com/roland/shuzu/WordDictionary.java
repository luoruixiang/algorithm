package com.roland.shuzu;

class WordDictionary {

    private static final int R = 26;
    private Node root;

    public static void main(String[] args){
        WordDictionary wordDictionary = new WordDictionary();
        System.out.println(wordDictionary.root.children.length);
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }

    private static class Node{
        char val;
        Node[] children = new Node[R];
        boolean isEnd = false;

        public Node(char value){
            val = value;
        }
    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node(' ');
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if(word != null && word.length() > 0 && !word.contains(".")){
            char[] array = word.toCharArray();
            Node current = root;
            for(char letter : array){
                int index = letter - 'a';
                Node[] children = current.children;
                if(children[index] == null){
                    children[index] = new Node(letter);
                }
                current = children[index];
            }
            current.isEnd = true;
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word == null || word.length() == 0){
            return false;
        }
        return search(word, 0, root, word.length());
    }

    public boolean search(String word, int index, Node current, int length){
        char letter = word.charAt(index);
        if(index < length - 1){
            if(letter != '.'){
                int indexNode = letter - 'a';
                Node[] children = current.children;
                if(children[indexNode] == null){
                    return false;
                }
                else{
                    return search(word, index+1, children[indexNode], length);
                }
            }
            else{
                for(Node node : current.children){
                    if(node != null && search(word, index+1, node, length)){
                        return true;
                    }
                }
                return false;
            }
        }
        else{
            if(letter == '.'){
                for(Node node : current.children){
                    if(node.isEnd){
                        return true;
                    }
                }
                return false;
            }
            else{
                int indexNode = letter - 'a';
                Node[] children = current.children;
                return children[indexNode] != null && children[indexNode].isEnd;
            }
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */