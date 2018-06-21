package uk.gov.ons.fwmt.staff_service.data.dto;

import lombok.Data;

@Data
public class UserDTO {

    public String authNo;
    public String tmUsername;
    public Boolean activeDate;
    public String deputyNo;
}
