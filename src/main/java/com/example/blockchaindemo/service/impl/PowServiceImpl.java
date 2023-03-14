package com.example.blockchaindemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.blockchaindemo.empty.Block;
import com.example.blockchaindemo.empty.Message;
import com.example.blockchaindemo.empty.Transaction;
import com.example.blockchaindemo.service.BlockService;
import com.example.blockchaindemo.service.P2PService;
import com.example.blockchaindemo.service.PowService;
import com.example.blockchaindemo.util.BlockCache;
import com.example.blockchaindemo.util.BlockConstant;
import com.example.blockchaindemo.util.CommonUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PowServiceImpl implements PowService {
    @Resource
    BlockCache blockCache;
    @Resource
    BlockService blockService;
    @Resource
    P2PService p2PService;

    //通过“挖矿”进行工作量证明，实现节点间的共识
    public Block mine(){
        // 封装业务数据集合，记录区块产生的节点信息，临时硬编码实现
        List<Transaction> tsaList = new ArrayList<>();
        Transaction tsa1 = new Transaction();
        tsa1.setId("1");
        tsa1.setBusinessInfo("这是IP为："+ CommonUtil.getLocalIp()+"，端口号为："+blockCache.getP2pport()+"的节点挖矿生成的区块");
        tsaList.add(tsa1);
        Transaction tsa2 = new Transaction();
        tsa2.setId("2");
        tsa2.setBusinessInfo("区块链高度为："+(blockCache.getLatestBlock().getIndex()+1));
        tsaList.add(tsa2);

        // 定义每次哈希函数的结果
        String newBlockHash;
        int nonce = 0;
        long start = System.currentTimeMillis();
        System.out.println("开始挖矿");
        while (true) {
            // 计算新区块hash值
            newBlockHash = blockService.calculateHash(blockCache.getLatestBlock().getHash(), tsaList, nonce);
            // 校验hash值
            if (blockService.isValidHash(newBlockHash)) {
                System.out.println("挖矿完成，正确的hash值：" + newBlockHash);
                System.out.println("挖矿耗费时间：" + (System.currentTimeMillis() - start) + "ms");
                break;
            }
            System.out.println("第"+(nonce+1)+"次尝试计算的hash值：" + newBlockHash);
            nonce++;
        }
        // 创建新的区块
        Block block = blockService.createNewBlock(nonce, blockCache.getLatestBlock().getHash(), newBlockHash, tsaList);

        //创建成功后，全网广播出去
        Message msg = new Message();
        msg.setType(BlockConstant.RESPONSE_LATEST_BLOCK);
        msg.setData(JSON.toJSONString(block));
        //p2PService.broatcast(JSON.toJSONString(msg));
        return block;
    }
}
