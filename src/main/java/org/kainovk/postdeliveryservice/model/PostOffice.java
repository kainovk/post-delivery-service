package org.kainovk.postdeliveryservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "post_office")
@NoArgsConstructor
public class PostOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "index")
    private String index;

    @Column(name = "name")
    private String name;

    @Column(name = "recipient_address")
    private String recipientAddress;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "arrival_dt")
    private LocalDateTime arrivalTime;

    @Column(name = "departure_dt")
    private LocalDateTime departureTime;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "mail_item_id")
    private MailItem mailItem;

    public PostOffice(String index, String name, String recipientAddress, Status status, LocalDateTime arrivalTime, MailItem mailItem) {
        this.index = index;
        this.name = name;
        this.recipientAddress = recipientAddress;
        this.status = status;
        this.arrivalTime = arrivalTime;
        this.mailItem = mailItem;
    }
}
