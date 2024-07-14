package Website2.speacification;

import Website2.model.entity.Category;
import Website2.model.request.FilterCategory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CategorySpecification {
    public static Specification<Category> buildSpec(FilterCategory form){
        if (form == null){
            return null;
        }
        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //Filter by Category name
                if (StringUtils.hasText(form.getSearch())){
                    predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("typeName"), "%" + form.getSearch()+ "%"
                    )
                    )
                    );
                }
                // Filter by category Id
                if(form.getMinId() != null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                            root.get("id"), form.getMinId()
                    ));
                }
                if (form.getMaxId() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(
                            root.get("id"), form.getMaxId()
                    ));
                }
                // Add sorting
                if (form.getIdAsc() != null && form.getIdAsc()) {
                    query.orderBy(criteriaBuilder.asc(root.get("Id")));
                }
                if (form.getIdDesc() != null && form.getIdDesc()) {
                    query.orderBy(criteriaBuilder.desc(root.get("Id")));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
