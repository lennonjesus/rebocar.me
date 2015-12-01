Feature: Cadastrar rebocados
  EU COMO dono de um carro em pessimo estado de conservacao
  DESEJO me cadastrar no Rebocar.me
  A FIM DE poder pedir um reboque pelo celular quando o carro parar!

  Scenario: Primeiro cadastro
    Given que o meu "login" eh "felipecao"
    And meu "nome" eh "Felipe Carvalho"
    And meu "email" eh "felipe@rebocar.me"
    And meu "celular" eh "992130056"
    And meu "endereco" eh "Rua das Laranjeiras"
    And meu "cep" eh "20270243"
    When eu executo o cadastro
    Then o cadastro eh bem sucedido

  Scenario: Ja existe alguem com o mesmo login
    Given que o meu "login" eh "felipecao"
    And meu "nome" eh "Felipe Carvalho"
    And meu "email" eh "felipe@rebocar.me"
    And meu "celular" eh "992130056"
    And meu "endereco" eh "Rua das Laranjeiras"
    And meu "cep" eh "20270243"
    And ja existe um usuario com o login "felipecao"
    When eu executo o cadastro
    Then o cadastro emite um erro

#  Scenario: Campo nao informado