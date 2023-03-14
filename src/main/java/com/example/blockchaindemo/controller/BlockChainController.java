package com.example.blockchaindemo.controller;


import com.alibaba.fastjson.JSON;
import com.example.blockchaindemo.service.BlockService;
import com.example.blockchaindemo.service.PowService;
import com.example.blockchaindemo.util.BlockCache;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlockChainController {
    @Resource
    BlockService blockService;
    @Resource
    PowService powService;
    @Autowired
    BlockCache blockCache;

    //查看当前节点区块链数据
    @GetMapping("/scan")
    @ResponseBody
    public String scanBlock() {
        return JSON.toJSONString(blockCache.getBlockChain());
    }

    //查看当前节点区块链数据
    @GetMapping("/data")
    @ResponseBody
    public String scanData() {
        return JSON.toJSONString(blockCache.getPackedTransactions());
    }

    //创建创世区块
    @GetMapping("/create")
    @ResponseBody
    public String createFirstBlock() {
        blockService.createGenesisBlock();
        return JSON.toJSONString(blockCache.getBlockChain());
    }


    //工作量证明PoW
    //挖矿生成新的区块

    @GetMapping("/mine")
    @ResponseBody
    public String createNewBlock() {
        powService.mine();
        return JSON.toJSONString(blockCache.getBlockChain());
    }
}
