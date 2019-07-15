package ru.whitebite.Core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operation {

  public enum Type {
    SHIFT,
    ADD,
    ZERO,
    OUT,
    IN,
    BEGIN_WHILE,
    END_WHILE
  }

  private Type type = null; //тип операции
  private int arg = 1; //кол-во повторений
  private int position = 0; //кол-во повторений
  public Operation(Type type, int arg,int position) {
    this.type = type;
    this.arg = arg;
    this.position = position;
  }
  public Operation(Type type, int arg) {
    this.type = type;
    this.arg = arg;
  }
  public void incArg(int value){
    arg+=value;
  }

  public Operation(Type type) {
    this.type = type;
  }

  public Operation clone() {
    return new Operation(type, arg,position);
  }
}
