# -*- coding:utf-8 -*-


# Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
#
#
# Only one letter can be changed at a time
# Each intermediate word must exist in the word list
#
#
#
# For example,
#
#
# Given:
# beginWord = "hit"
# endWord = "cog"
# wordList = ["hot","dot","dog","lot","log"]
#
#
# Return
#
#   [
#     ["hit","hot","dot","dog","cog"],
#     ["hit","hot","lot","log","cog"]
#   ]
#
#
#
#
# Note:
#
# All words have the same length.
# All words contain only lowercase alphabetic characters.


public class Solution {

    ArrayList<List<String>> result = new ArrayList<List<String>>();
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        result = new ArrayList<List<String>>();
        if(beginWord == endWord){
            ArrayList<String> cur = new ArrayList<String>();
            cur.add(beginWord);
            result.add(cur);
            return result;
        }
        wordList.add(endWord);
        wordList.remove(beginWord);
        HashMap<String,Set> prev = new HashMap<String,Set>();
        HashSet<String> already = new HashSet<String>();
        already.add(beginWord);
        while(wordList.contains(endWord)){
            if(already.isEmpty()) return result;
            HashMap<String,Set> curMap = new HashMap<String,Set>();
            HashSet<String> newAlready = new HashSet<String>();
            for(String cur : already){
                StringBuilder curr = new StringBuilder(cur);
                for(int i = 0;i < cur.length();i++){
                    char c = curr.charAt(i);
                    for(int j = 0; j < 25;j++){
                        curr.setCharAt(i,(char)('a'+j)); 
                        if(wordList.contains(curr.toString())){
                            newAlready.add(curr.toString());
                            if(curMap.get(curr.toString()) == null){
                                HashSet<String> cur2curr = new HashSet<String>();
                                cur2curr.add(cur);
                                curMap.put(curr.toString(),cur2curr);
                            }else{
                                Set<String> cur2curr = curMap.get(curr.toString());
                                cur2curr.add(cur);
                            }
                        }
                    }
                    curr.setCharAt(i,c);
                }
            }
            for(String newAlreadyOne:newAlready){
                wordList.remove(newAlreadyOne);
                prev.put(newAlreadyOne,curMap.get(newAlreadyOne));
            }
            already = newAlready;
        }
        ArrayList<String> curList =new ArrayList<String>();
        curList.add(endWord);
        _form(prev,endWord,curList);
        return result;
    }
    public void _form(HashMap<String,Set> prev, String endWord,ArrayList<String> curList){
        if(prev.get(endWord) == null){
            result.add(new ArrayList<String>(curList));
        }else{
            Set<String> curPrev = prev.get(endWord);
            for(String s : curPrev){
                curList.add(0,s);
                _form(prev,s,curList);
                curList.remove(0);
            }
        }
    }
    
    
}