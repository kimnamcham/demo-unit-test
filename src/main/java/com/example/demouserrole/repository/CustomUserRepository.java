package com.example.demouserrole.repository;

import com.example.demouserrole.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomUserRepository {

    public static Specification<User> buildFilterSpecification(String keyword) {
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //always not list superadmin for api
            predicates.add(criteriaBuilder.equal(root.get("isSuperAdmin"), false));
            if (StringUtils.hasText(keyword)) {
                predicates.add(criteriaBuilder.like(root.get("user_name"), "%" + keyword + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
