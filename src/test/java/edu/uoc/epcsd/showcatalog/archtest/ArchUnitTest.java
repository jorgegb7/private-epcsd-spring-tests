package edu.uoc.epcsd.showcatalog.archtest;

import org.springframework.stereotype.Service;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "edu.uoc.epcsd.showcatalog", importOptions= ImportOption.DoNotIncludeTests.class)
public class ArchUnitTest {
    @ArchTest
    public static final ArchRule ArchUnitTest = classes()
            .that().resideInAPackage("..domain.service..")
            .and().areAnnotatedWith(Service.class)
            .should().haveSimpleNameEndingWith ("ServiceImpl");
}
