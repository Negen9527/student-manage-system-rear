package com.negen.repository;

        import com.negen.entity.Clazz;
        import com.negen.entity.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.util.List;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 17:51 2020/3/5
 * @ Description：班级repository
 * @ Modified By：
 * @Version: 1.0
 */
@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Long> {
    Clazz findByGradeAndClazz(String grade, String clazz);

    @Query(value = "SELECT DISTINCT(grade) FROM tb_clazz", nativeQuery=true)
    List<String> selectGrades();

    @Query(value = "SELECT DISTINCT(clazz) FROM tb_clazz where grade=?1", nativeQuery=true)
    List<String> selectClazzs(String grade);

}