package org.kainovk.postdeliveryservice.repository;

import org.kainovk.postdeliveryservice.model.MailItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailItemRepository extends JpaRepository<MailItem, Long> {
}
