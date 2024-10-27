package dailychallenge.medium;

import java.util.*;

public class RemoveSubfolders1233 {
    public static void main(String[] args) {
        String[] folder = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
        System.out.println(removeSubfolders(folder));
    }

//    trie; time: O(N.L), space: O(N.L)
//    A Trie is well-suited for this problem because it allows us to build folder paths incrementally,
//    marking endpoints where folders end. With this structure, any folder that tries to extend beyond an
//    endpoint can be identified as a sub-folder.
    public static List<String> removeSubfolders(String[] folder) {
        TrieNode root = new TrieNode();
        List<String> result = new ArrayList<>();
//        build trie from folder paths
        for(String path : folder) {
            TrieNode currentNode = root;
            String[] folderNames = path.split("/");
            for(String folderName : folderNames) {
//                skip empty folder name
                if(folderName.isEmpty()) continue;
//                create new node if it doesn't exist
                if(!currentNode.children.containsKey(folderName))
                    currentNode.children.put(folderName, new TrieNode());
                currentNode = currentNode.children.get(folderName);
            }
//            mark the end of the folder path
            currentNode.isEndOfFolder = true;
        }
//        check each path of sub folders
        for(String path : folder) {
            TrieNode currentNode = root;
            String[] folderNames = path.split("/");
            boolean isSubFolder = false;
            for(int i = 0 ; i < folderNames.length ; i++) {
//                skip empty folder name
                if(folderNames[i].isEmpty()) continue;
                TrieNode nextNode = currentNode.children.get(folderNames[i]);
//                check if the current folder path is a sub folder of an existing path
                if(nextNode.isEndOfFolder && i != folderNames.length - 1) {
                    isSubFolder = true;
                    break; // found a sub folder
                }
                currentNode = nextNode;
            }
//            if not a sub folder, add to the result
            if(!isSubFolder) {
                result.add(path);
            }
        }

        return result;
    }

    static class TrieNode {
        boolean isEndOfFolder;
        Map<String, TrieNode> children;
        TrieNode() {
            isEndOfFolder = false;
            children = new HashMap<>();
        }
    }

//    sorting; time: O(N.LlogN), space: O(logn) [N - number of folders, L - maximum length of a folder path]
//    Sorting takes O(N⋅logN) comparisons, but each comparison can involve up to L characters (the maximum length of a folder path).
    public static List<String> removeSubfolders1(String[] folder) {
//        sort the folders alphabetically
        Arrays.sort(folder);
//        initialize the result list and add the first folder
        List<String> result = new ArrayList<>();
        result.add(folder[0]);
//        iterate through each folder and check if it's a sub folder of the last added folder in the result list
        for(int i = 1 ; i < folder.length ; i++) {
            String lastFolder = result.get(result.size() - 1);
            lastFolder += '/';
//            check if the current folder starts with the last added folder path
            if(!folder[i].startsWith(lastFolder))
                result.add(folder[i]);
        }
//        return the result containing only non sub folders
        return result;
    }

//    using set; time: O(N.L^2), space: O(N.L)
//    Constructing the unordered set folderSet from the input array folder takes O(N).
//    However, each string insertion requires O(L). So, initializing the set takes O(N⋅L).
//    The primary operation involves iterating over each folder path in the folder array, which is O(N).
//    For each folder, the algorithm checks all possible prefixes (up to L levels deep) in the folderSet.
//    This involves: Finding the position of the last '/' character in the prefix string, which takes O(L)
//    in the worst case. Creating a substring for each prefix level, which is also O(L). Searching for each
//    prefix in the set, which is O(L). Therefore, checking all prefixes of one folder takes O(L^2), and for N
//    folders, this results in O(N⋅L^2).
    public static List<String> removeSubfolders2(String[] folder) {
//        create a set to store all folder paths for fast lookup
        Set<String> folderSet = new HashSet<>(Arrays.asList(folder));
        List<String> result = new ArrayList<>();

//        iterate through each folder to check if it's a sub folder
        for(String path  : folder) {
            boolean isSubFolder = false;
            String prefix = path;
//            check all prefixes of the current folder path
            while(!prefix.isEmpty()) {
                int pos = prefix.lastIndexOf("/");
                if(pos == -1) break;
//                reduce the prefix to it's parent folder
                prefix = prefix.substring(0, pos);
//                if the parent folder exists in the set, mark it as a sub folder
                if(folderSet.contains(prefix)) {
                    isSubFolder = true;
                    break;
                }
            }
//            if not, add it to the result
            if(!isSubFolder)
                result.add(path);
        }

        return result;
    }
}

/*
Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".
The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.

Example 1:
Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
Example 2:
Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
Example 3:
Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]

Constraints:
1 <= folder.length <= 4 * 104
2 <= folder[i].length <= 100
folder[i] contains only lowercase letters and '/'.
folder[i] always starts with the character '/'.
Each folder name is unique.
 */