package com.example.blockchaindemo.service;

import com.example.blockchaindemo.empty.Block;
import com.example.blockchaindemo.empty.Transaction;

import java.util.List;

public interface BlockService {
    String createGenesisBlock();

    Block createNewBlock(int nonce, String hash, String newBlockHash, List<Transaction> tsaList);

    void replaceChain(List<Block> receiveBlockchain);

    boolean isValidChain(List<Block> receiveBlockchain);

    boolean addBlock(Block latestBlockReceived);

    String calculateHash(String hash, List<Transaction> tsaList, int nonce);

    boolean isValidHash(String newBlockHash);
}
