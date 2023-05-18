package org.inventory.util;

import org.inventory.dto.StatusDTO;
import org.springframework.stereotype.Component;

@Component
public class Utils {


  public StatusDTO createStatusDTO(String message){
    return StatusDTO.builder().message(message).build();
  }

}
