package org.kainovk.postdeliveryservice.repository;

import org.kainovk.postdeliveryservice.model.MailItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailItemRepository extends JpaRepository<MailItem, Long> {

    List<MailItem> findAllByRecipientName(String recipientName);
}
