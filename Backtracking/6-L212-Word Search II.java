// ✅ Core Idea
// Combine:
// Trie (Prefix Tree) → fast lookup & pruning
// DFS + Backtracking → explore board

// ✅ Why Trie?
// Instead of checking every word separately. We:
// * Insert all words into Trie
// * Traverse board **once**
// * Follow only valid prefixes ✅

// 🌳 Trie Concept (Super Short)
// * Tree where:
//   * Each edge = character
//   * Path = prefix
//   * Leaf node = complete word

// Example words: ["oath", "eat"]
// Trie structure:
// root
//  ├── o → a → t → h (word: "oath")
//  └── e → a → t (word: "eat")


public class Solution {

    // 🌳 Trie node: each node represents a character
    static class TrieNode {
        TrieNode[] children = new TrieNode[26]; // 26 lowercase letters
        String word = null; // stores complete word at terminal node
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();

        // ✅ Build Trie from all words → enables prefix pruning
        TrieNode root = buildTrie(words);

        // 🔍 Try DFS from every cell (like Word Search I)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {

        char c = board[i][j];

        // ❌ Stop if:
        // - already visited
        // - no matching Trie path (prefix pruning)
        if (c == '#' || p.children[c - 'a'] == null) return;

        // ✅ Move along Trie path
        p = p.children[c - 'a'];

        // ✅ Found a complete word
        if (p.word != null) {
            res.add(p.word);

            // 🔥 Avoid duplicates → mark word as used
            p.word = null;
        }

        // ✅ Mark visited (backtracking pattern)
        board[i][j] = '#';

        // 🚀 Explore all 4 directions
        if (i > 0) dfs(board, i - 1, j, p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);

        // 🔄 Restore cell (backtrack)
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {

        TrieNode root = new TrieNode();

        for (String w : words) {
            TrieNode p = root;

            // ✅ Insert each character into Trie
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.children[i] == null) p.children[i] = new TrieNode();
                p = p.children[i];
            }

            // ✅ Mark end of word
            p.word = w;
        }
        return root;
    }
}
