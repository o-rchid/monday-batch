package com.monday.high.repository;

import com.monday.high.repository.packaze.PackageEntity;
import com.monday.high.repository.packaze.PackageRepository;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void test_findByCreatedAfter() {

        // given
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(1);

        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("학생 전용 3개월");
        packageEntity.setPeriod(90);
        packageRepository.save(packageEntity);

        PackageEntity packageEntity1 = new PackageEntity();
        packageEntity.setPackageName("학생 전용 6개월");
        packageEntity.setPeriod(180);
        packageRepository.save(packageEntity1);

        // when
        final List<PackageEntity> packageEntities = packageRepository.findByCreatedAtAfter(dateTime, PageRequest.of(0, 1, Sort.by("packageSeq").descending()));

        // then
        assertEquals(1, packageEntities.size());
        assertEquals(packageEntity.getPackageSeq(), packageEntities.get(0).getPackageSeq());
    }

    @Test
    public void test_updateCountAndPeriod() {

        // given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("바디프로필 이벤트 4개월");
        packageEntity.setPeriod(90);
        packageRepository.save(packageEntity);

        // when
        final PackageEntity updatedPackageEntity = packageEntity.findById(packageEntity.getPackageSeq()).get();

        // then
        assertEquals(30, updatedPackageEntity.getCount());
    }
}
