package thrymr.net.hospital.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thrymr.net.hospital.management.entity.ExcelData;

@Repository
public interface ExcelRepo extends JpaRepository<ExcelData,Long> {
}
