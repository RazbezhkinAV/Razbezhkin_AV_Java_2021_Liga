package ru.philosophyit.orm;

import ru.philosophyit.orm.config.JpaConfig;

public class Main {
  public static void main(String[] args) {
      JpaConfig.getEntityManagerFactory().close();
  }
}
