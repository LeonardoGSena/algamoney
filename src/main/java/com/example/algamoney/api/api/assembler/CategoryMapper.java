package com.example.algamoney.api.api.assembler;

import com.example.algamoney.api.api.model.CategoryModel;
import com.example.algamoney.api.api.model.input.CategoryInput;
import com.example.algamoney.api.domain.model.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CategoryMapper {

    private ModelMapper modelMapper;

    public CategoryModel toModel(Category category) {
        return modelMapper.map(category, CategoryModel.class);
    }

    public Category toEntity(CategoryInput categoryInput) {
        return modelMapper.map(categoryInput, Category.class);
    }
}
