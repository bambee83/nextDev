package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.Trie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutoCompleteService {
    private final Trie<String, String> trie;

    // 저장
    public void addTrie(String word) {
        trie.put(word, null);
    }

    // 검색
    public List<String> autoComplete(String word) {
        return this.trie.prefixMap(word).keySet()
        .stream().collect(Collectors.toList());
    }

    // 삭제
    public void deleteTrie(String word) {
        this.trie.remove(word);
    }
}
