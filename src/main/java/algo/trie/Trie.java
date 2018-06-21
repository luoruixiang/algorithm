package algo.trie;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * You may assume that all inputs are consist of lowercase letters a-z.
 */
public class Trie {

    private static final int radius = 26;
    private TrieNode root = new TrieNode(Boolean.FALSE, 'R');

    private class TrieNode {
        Boolean isEnd;
        Character character;
        TrieNode[] nextNodes = new TrieNode[radius];

        private TrieNode(Boolean _isEnd, Character _character){
            isEnd = _isEnd;
            character = _character;
        }
    }

    /**
     * Inserts a word into the trie.
     *
     * @param word
     */
    public void insert(String word) {
        TrieNode current = root;
        for(char c : word.toCharArray()){
            int index = c - 'a';
            if(current.nextNodes[index] == null){
                current.nextNodes[index] = new TrieNode(Boolean.FALSE, c);
            }
            current = current.nextNodes[index];
        }
        current.isEnd = true;
    }

    /**
     * search a word and return whether the word is in the trie
     *
     * @param word
     * @return
     */
    public boolean search(String word){
        TrieNode current = root;
        for(char c : word.toCharArray()){
            int index = c - 'a';
            if(current.nextNodes[index] == null){
                return false;
            }
            current = current.nextNodes[index];
        }
        return current.isEnd;
    }

    /**
     * search a prefix and return whether the trie contains the prefix
     *
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix){
        TrieNode current = root;
        for(char c : prefix.toCharArray()){
            int index = c - 'a';
            if(current.nextNodes[index] == null){
                return false;
            }
            current = current.nextNodes[index];
        }
        return true;
    }
}
