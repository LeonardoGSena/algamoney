package com.example.algamoney.api.api.assembler;

import com.example.algamoney.api.api.model.CategoryModel;
import com.example.algamoney.api.api.model.input.CategoryInput;
import com.example.algamoney.api.domain.model.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CategoryModel> toCollectionModel(List<Category> allCategories) {
        return allCategories.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
