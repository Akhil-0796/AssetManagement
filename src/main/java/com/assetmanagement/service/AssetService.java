package com.assetmanagement.service;

import com.assetmanagement.modal.Asset;
import com.assetmanagement.modal.Employee;
import com.assetmanagement.repository.AssetRepository;
import com.assetmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    /***
     *  to add a new Asset in database.
     * @param asset  taking asset object .
     * @return
     */
    public String addAsset(Asset asset) {
        try {

            if(asset.getRecovered()!=null){
                   return "recovered should be null at this point ";
            }

            if(asset.getId()!=null){
                return "Id is auto generated";
            }

            if(asset.getAssigned()!=null){
                asset.setStatus(null);
            }

            assetRepository.save(asset);
            return "successfully added" + asset.getName() + "category";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /***
     *  to find the asset based on the given name
     * @param name  name of the asset which need to be find.
     * @return  here returning the asset object .
     */
    public Asset findAsset(String name) {

        Optional<Asset> asset= assetRepository.findByName(name);

        return asset.get();
    }


    /***
     * to list out all the asset present inside the database.
     * @return  returning list of asset .
     */
    public List<Asset> listAll(){
       return (List<Asset>) assetRepository.findAll();
    }

    /***
     * to update the already existing asset.
     * @param id taking id of the asset which needs to be updated .
     * @param asset  taking new details in asset object which needs a update.
     * @return  returning the status message.
     */

    public String update(Long id,Asset asset){

        Optional<Asset> asset1=assetRepository.findById(id);
        if(asset1.isPresent()){

            return this.updateAsset(asset,asset1.get());

        }else{
            return "there is no asset with ID - "+id;
        }
    }

    /***
     * this is basically the helping method of update asset method.
     * @param asset
     * @param asset1
     * @return
     */
    private String updateAsset(Asset asset, Asset asset1) {

        if(asset.getId()!=null){
            return "you can not update Id ";
        }
        if(asset.getName()!=null){
            asset1.setName(asset.getName());
        }

        if(asset.getPurchaseDate()!=null){
            asset1.setPurchaseDate(asset.getPurchaseDate());
        }

        if(asset.getCondition()!=null){
            asset1.setCondition(asset.getCondition());
        }

        if(asset.getCategory()!=null){
            asset1.setCategory(asset.getCategory());
        }

        if(asset.getStatus()!=null){
            asset1.setStatus(asset.getStatus());
        }

        if(asset.getAssigned()!=null){
            asset1.setAssigned(asset.getAssigned());
        }

        if(asset.getRecovered()!=null){
            asset1.setRecovered(asset.getRecovered());
        }

        assetRepository.save(asset1);
        return "asset successfully updated";

    }

    /***
     *  this method helps to assign the asset to employees.
     * @param assetName taking asset name which you want to assign.
     * @param empId  taking employee id to whom you are assigning the asset.
     * @return  status message.
     */
    public String assignAsset(String assetName,Long empId){
        try {

            Optional<Employee> e=employeeRepository.findById(empId);

            if (e.isPresent()) {
              Employee emp=e.get();
                String assign = emp.getFullName() + "-" + emp.getId();
                Optional<Asset> as = assetRepository.findByName(assetName);

                if(as.isPresent()){
                    Asset asset=as.get();
                    asset.setRecovered(asset.getAssigned());
                    asset.setAssigned(assign);
                    asset.setStatus("not-available");
                    assetRepository.save(asset);
                    return assetName + " is successfully assigned to "+emp.getFullName()+"-"+ empId;
                }else{
                    return "no asset found with name "+assetName;
                }


            } else {
                return "no employee found with id "+empId;
            }

        }catch (Exception e){
            return e.getMessage();
        }
            
    }


    /***
     *  to recover an asset from an employee.
     * @param assetName asset name which has to be recovered.
     * @param empId  employee id from whom we are recovering the asset.
     * @return returning the status message.
     */
    public String recoverAsset(String assetName,Long empId){
        try {
            System.out.println(empId);
            Optional<Employee> e=employeeRepository.findById(empId);

            if (e.isPresent()) {
                Employee emp=e.get();
                    Optional<Asset> as = assetRepository.findByName(assetName);
                    String assignedName=emp.getFullName()+"-"+emp.getId();


                    if (as.isPresent()) {
                        Asset asset=as.get();
                        if(!assignedName.equals(asset.getAssigned())){
                            return "this Employee doest not have this asset";
                        }

                        asset.setRecovered(asset.getAssigned());
                        asset.setAssigned("");
                        asset.setStatus("available");
                        assetRepository.save(asset);
                        return assetName + " is successfully recovered from " + emp.getFullName()+"-"+empId;
                    } else {
                        return "no asset found with name "+assetName;
                    }
            } else {
                return "no employee found with id "+empId;
            }

        }catch (Exception e){
            return e.getMessage();
        }

    }


    /***
     *  deleting the asset from the database.
     * @param assetName  taking name of the asset which has to be deleted .
     * @return  returning the status message.
     */
    public String deleteAsset(String assetName){
        Optional<Asset> as=assetRepository.findByName(assetName);
        if(as.isPresent()){
            Asset asset=as.get();
            if(asset.getAssigned()==null){
                assetRepository.deleteByName(assetName);
                return "asset deleted successfully";
            }else{
                return "this asset is assigned to "+asset.getAssigned()+". so can not deleted before recovering ";
            }
        }
        return "given asset is not present in database";
    }
}
