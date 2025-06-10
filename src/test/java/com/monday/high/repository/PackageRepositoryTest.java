package com.monday.high.repository;

import com.monday.high.repository.packaze.PackageEntity;
import com.monday.high.repository.packaze.PackageRepository;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public class PackageRepositoryTest {
    @Autowired
    private PackageRepository packageRepository;

    @Test
    public void test_save() {
        // Given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("바디 챌린지 PT 12주");
        packageEntity.setPeriod(84);

        // When
        packageRepository.save(packageEntity);

        // Then
        assertNotNull(packageEntity.getPackageSeq());
    }
}
