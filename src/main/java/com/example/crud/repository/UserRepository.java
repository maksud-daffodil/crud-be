package com.example.crud.repository;

import com.example.crud.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailAndIsActiveAndIsDeleted(String email, Boolean isActive, Boolean isDeleted);
    Optional<User> findByIdAndIsDeleted(UUID id, Boolean isDeleted);

    @Query(
            "SELECT u FROM User u " +
                    "WHERE u.firstName LIKE %:name%" +
                    " AND u.isActive = :isActive" +
                    " AND u.isDeleted = :isDeleted"
    )
    Page<User> findByNameLike(
            @Param("name") String name,
            @Param("isActive") Boolean isActive,
            @Param("isDeleted") Boolean isDeleted,
            Pageable paging
    );
}
