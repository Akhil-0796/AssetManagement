package com.assetmanagement.controller;

import com.assetmanagement.modal.Asset;
import com.assetmanagement.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/addAsset")
    public String addAsset(@RequestBody Asset asset){

        return assetService.addAsset(asset);

    }

    @GetMapping("/getAsset/{name}")
    public Asset getAsset(@PathVariable String name){
            return assetService.findAsset(name);
    }

    @GetMapping("/getAllAsset")
    public List<Asset> getAllAsset(){
        return assetService.listAll();
    }

    @PutMapping("/updateAsset/{id}")
    public String update(@PathVariable String id,@RequestBody Asset asset){
        try{
            return assetService.update(Long.valueOf(id),asset);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/assignAsset/{name}/{empId}")
    public String assignAsset(@PathVariable String name,@PathVariable  Long empId){

        System.out.println(Long.valueOf(empId));
        return assetService.assignAsset(name,Long.valueOf(empId));
    }

    @PutMapping("/recoverAsset/{name}/{empId}")
    public String recoverAsset(@PathVariable String name,@PathVariable String empId){

        return assetService.recoverAsset(name,Long.valueOf(empId));
    }

    @DeleteMapping("/deleteAsset/{name}")
    public String deleteAsset(@PathVariable String name){
        return assetService.deleteAsset(name);
    }
}
