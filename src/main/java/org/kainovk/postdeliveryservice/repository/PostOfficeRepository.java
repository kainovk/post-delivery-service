package org.kainovk.postdeliveryservice.repository;

import org.kainovk.postdeliveryservice.model.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
}
