package sia.tacocloud.mapping;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import sia.tacocloud.domain.Ingredient;
import sia.tacocloud.domain.Taco;
import sia.tacocloud.domain.TacoForm;
import sia.tacocloud.repository.IngredientRepository;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TacoMapper {
    @Autowired
    private IngredientRepository repository;

    @Mapping(target = "ingredients", ignore = true)
    public abstract Taco fromDto(TacoForm from);

    @AfterMapping
    protected void setListIngredients(@MappingTarget Taco to, TacoForm from) {
        List<Ingredient> list = new ArrayList<>();
        from.getIngredients().forEach(element -> list.add(repository.findOne(element)));
        to.setIngredients(list);
    }
}
