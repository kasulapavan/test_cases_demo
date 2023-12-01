package thrymr.net.hospital.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class ExcelData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String resourceId;
    private String resourceName;
    private String resourceType;
    private String activityID;
    private String activityName;
    private String wbs;
    private String wbsName;
    private String soeID;
    private Date start;
    private Date finish;
    private String unitOfMeasure;
    private Long budgetedUnits;

    private String remainingUnits;

    private Long actualUnits;
    private String calendar;
    private String curve;
    private String spreadsheetField;
    @Column(columnDefinition = "TEXT")
    private String dates;
}