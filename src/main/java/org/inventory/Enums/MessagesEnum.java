package org.inventory.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessagesEnum {
  BAD_REQUEST("Bad request"),
  SUCCESS("Registro exitoso"),
  NOT_FOUNDS("Fondos insuficientes"),
  ACCOUNT_TYPE_ERROR("La cuenta debe coinsidir con los valores Ahorros o Corriente"),
  NO_RESULTS("No hay resultados para la busqueda");

  private final String message;
}
