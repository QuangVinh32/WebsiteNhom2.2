package Website2.speacification;

import Website2.model.entity.Category;
import Website2.model.entity.Order;
import Website2.model.request.FilterCategory;
import Website2.model.request.FilterOrder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {
    public static Specification<Order> buildSpec(FilterOrder form){
        if (form == null){
            return null;
        }
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasText(form.getSearch())){
                    predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("typeName"), "%" + form.getSearch()+ "%"
                                    )
                            )
                    );
                }
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
                if (form.getMinTotal() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("total"), form.getMinTotal()));
                }

                if (form.getMaxTotal() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("total"), form.getMaxTotal()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
