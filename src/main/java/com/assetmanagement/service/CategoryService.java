package com.assetmanagement.service;

import com.assetmanagement.modal.Asset;
import com.assetmanagement.modal.Category;
import com.assetmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    /***
     * to add a new category in database.
     * @param category  taking a category object which need to be stored.
     * @return here  return a status message.
     */
    public String addCategory(Category category){
        try{
            categoryRepository.save(category);
            return "successfully added"+category.getName()+"category";
        }catch (Exception e){
            return "something went wrong : "+e.getMessage();
        }
    }

    /***
     *  listing all the categories present in the database.
     * @return this will return list of all the category present in the database.
     */

    public List<Category> listAllCategory(){
        return (List<Category>) categoryRepository.findAll();
    }


    /***
     *  update method to update a existing category in database.
     * @param id  this is the id of the category on which you want to perform update.
     * @param category  this will take an category object with all the new details which you wan to update .
     * @return  here returning the status message.
     */
    public String update(Long id, Category category){

        Optional<Category> category1=categoryRepository.findById(id);
        if(category1.isPresent()){

            return this.updateCategory(category,category1.get());

        }else{
            return "there is no asset with ID - "+id;
        }
    }

    /***
     * i have created this method to make code readable.
     *  this is basically the helping method of update method .
     * @param category
     * @param category1
     * @return
     */

    private String updateCategory(Category category, Category category1) {

        if(category.getId()!=null){
            return "you can not update Id ";
        }
        if(category.getName()!=null){
            category1.setName(category.getName());
        }

        if(category.getDescription()!=null){
            category1.setDescription(category.getDescription());
        }

        categoryRepository.save(category1);
        return "category successfully updated";

    }

}
