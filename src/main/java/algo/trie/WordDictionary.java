package algo.trie;

/**
 * search(word) can search a literal word or a regular expression string containing only letters a-z or ..
 * A . means it can represent any one letter.
 *
 */
class WordDictionary {


    private static final int radius = 26;
    private final TrieNode root = new TrieNode('R', false);

    private class TrieNode {
        char character;
        boolean isEnd;
        TrieNode[] nextNodes = new TrieNode[radius];
        private TrieNode(char _character, boolean _isEnd){
            character = _character;
            isEnd = _isEnd;
        }
    }

    public void addWord(String word) {
        TrieNode currrent = root;
        for(char c : word.toCharArray()){
            int index = c - 'a';
            if(currrent.nextNodes[index] == null){
                currrent.nextNodes[index] = new TrieNode(c, false);
            }
            currrent = currrent.nextNodes[index];
        }
        currrent.isEnd = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int startIndex, TrieNode parent) {
        if(parent == null){
            return false;
        }
        else if(startIndex == word.length()){
            return parent.isEnd;
        }
        else{
            if(word.charAt(startIndex) == '.'){
                for(TrieNode node : parent.nextNodes){
                    if(search(word, startIndex + 1, node)){
                        return true;
                    }
                }
                return false;
            }
            else{
                int index = word.charAt(startIndex) - 'a';
                if(parent.nextNodes[index] == null){
                    return false;
                }
                else{
                    return search(word, startIndex + 1, parent.nextNodes[index]);
                }
            }
        }
    }
}