Feature: Obtener todos los productos

  Scenario: Se obtienen todos los productos
    Given la aplicación está en ejecución
    When el cliente solicita todos los productos
    Then la respuesta debe contener una lista de productos