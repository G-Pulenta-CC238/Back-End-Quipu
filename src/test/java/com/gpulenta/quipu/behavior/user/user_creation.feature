Feature: Crear un nuevo usuario

  Scenario: Crear un usuario exitosamente
    Given la aplicación está en ejecución
    When el cliente envía una solicitud POST para crear un usuario con los siguientes datos
      | username  | password | name  | lastname | address       | email             | phone     |
      | testuser1 | password | John  | Doe      | 123 Test St   | test1@example.com | 123456789 |
    Then la respuesta debe contener el usuario creado
      | username  | name  | lastname | address       | email             | phone     |
      | testuser1 | John  | Doe      | 123 Test St   | test1@example.com | 123456789 |

