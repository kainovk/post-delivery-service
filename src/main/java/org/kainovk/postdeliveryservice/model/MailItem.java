package org.kainovk.postdeliveryservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mail_item")
@NoArgsConstructor
public class MailItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "recipient_index")
    private String recipientIndex;

    @Column(name = "recipient_address")
    private String recipientAddress;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mailItem")
    private List<PostOffice> postOffices;

    public MailItem(Type type, String recipientIndex, String recipientAddress, String recipientName, LocalDateTime createdAt, List<PostOffice> postOffices) {
        this.type = type;
        this.recipientIndex = recipientIndex;
        this.recipientAddress = recipientAddress;
        this.recipientName = recipientName;
        this.createdAt = createdAt;
        this.postOffices = postOffices;
    }
}
