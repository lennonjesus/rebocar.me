Feature: Buscar reboque mais proximo
  EU COMO dono de um carro que parou na pista
  DESEJO encontrar o reboque mais próximo da minha localização atual
  A FIM DE chegar em casa a tempo do jogo do Fogão!

  Scenario: Não há reboques nas minhas proximidades
    Given que a minha localização atual é (52.374601,9.74889)
    And há um reboque com a placa "Maracana" na localização (-22.9121039,-43.2323445)
    And há um reboque com a placa "Casa do Alemao - Jd Primavera" na localização (-22.6949728,-43.2898208)
    And há um reboque com a placa "Praia do Leblon" na localização (-22.9878367,-43.2235188)
    When eu busco pelo reboque mais próximo
    Then a busca não retorna nenhum resultado

  Scenario: Há um reboque nas minhas proximidades
    Given que a minha localização atual é (-22.9810913,-43.2169033)
    And há um reboque com a placa "Maracana" na localização (-22.9121039,-43.2323445)
    And há um reboque com a placa "Casa do Alemao - Jd Primavera" na localização (-22.6949728,-43.2898208)
    And há um reboque com a placa "Praia do Leblon" na localização (-22.9878367,-43.2235188)
    When eu busco pelo reboque mais próximo
    Then a busca retorna o reboque com a placa "Praia do Leblon"