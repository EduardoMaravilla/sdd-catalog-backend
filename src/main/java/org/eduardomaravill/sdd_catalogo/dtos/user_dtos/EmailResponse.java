package org.eduardomaravill.sdd_catalogo.dtos.user_dtos;

import com.mailjet.client.transactional.SendContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponse {
    private String name;
    private List<SendContact> to;
    private SendContact from;
    private String subject;
    private String body;
    private List<SendContact> cc;
    private List<SendContact> bcc;
}