package kz.logistic.logistic_server.repositories;

import kz.logistic.logistic_server.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    List<User> findAllByDeletedAtIsNull();

    List<User> findAllByDeletedAtIsNullAndRoleId(Long roleId);

    List<User> findAllByIdIn(List<Long> ids);


    @Query(value = "select u.id from users u join companies_users cu on u.id = cu.user_id " +
            "where u.role_id = 3 and u.deleted_at is null and cu.company_id = :cId ORDER BY u.id DESC", nativeQuery = true)
    List<Long> findAllByDeletedAtIsNullAndRoleIdAndCompanyId(@Param("cId") Long cId);

    @Query(value = "select u.id from users u left join companies_users cu on u.id = cu.user_id " +
            "where u.role_id = 3 and u.deleted_at is null and cu.company_id is NULL ORDER BY u.id DESC", nativeQuery = true)
    List<Long> findAllFreeDrivers();

}
