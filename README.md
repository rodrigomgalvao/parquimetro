# Relatório Técnico:

**Sistema de Parquímetro **
---

## Introdução

>Esta documentação tem como objetivo demonstrar as requisições e persistência dos dados no banco de dados, para tal, utilizaremos o banco de dados H2 e realizaremos as requisições de POST, PUT e DELETE.

## ⚙ Funcionalidades do Sistema

* ### Condutor

<p align="justify">
Este microsserviço permite o cadastro, consulta, atualização e exclusão de informações relacionada aos condultores.
</p>

<details>
<summary>1. Criação, consulta e persistência</summary>

> Cadastro de Condutor.

![cadastrar_condutor_postman.png](images%2Fcondutor%2Fcadastro-consulta-condutor%2Fcadastrar_condutor_postman.png)

---


> Consulta do condultor por CPF

![consulta_condutor_postman.png](images%2Fcondutor%2Fcadastro-consulta-condutor%2Fconsulta_condutor_postman.png)

---

> Consulta da lista de condutores

![consulta_condutores_postman.png](images%2Fcondutor%2Fcadastro-consulta-condutor%2Fconsulta_condutores_postman.png)

---

> Tentativa de criação com CPF inválido

![erro_cpf_invalido_postman.png](images%2Fcondutor%2Fcadastro-consulta-condutor%2Ferro_cpf_invalido_postman.png)
---

> Tentativa de criação com CPF já cadastrado

![erro_cpf_ja_cadastrado.png](images%2Fcondutor%2Fcadastro-consulta-condutor%2Ferro_cpf_ja_cadastrado.png)
---

> Consulta no banco de dados H2

![consulta_condutor_H2.png](images%2Fcadastro-consulta-condutor%2Fconsulta_condutor_H2.png)


</details>

<details>
<summary>2. Atualização de dados</summary>

> Atualização de cadastro de condutor

![atualizacao_cadastro_condutor_postman.png](images%2Fcondutor%2Fatualizacao-cadastro-condutor%2Fatualizacao_cadastro_condutor_postman.png)
___

> Consulta dos dados cadastrados no banco de dados H2

![consulta_dados_atualizados_H2.png](images%2Fcondutor%2Fatualizacao-cadastro-condutor%2Fconsulta_dados_atualizados_H2.png)

</details>

<details>
<summary>3. Deleção de dados</summary>

> Deleção de cadastro de condutor

![delecao_cadastro_postman.png](images%2Fcondutor%2Fdelecao-cadastro-condutor%2Fdelecao_cadastro_postman.png)

---

> Consulta com dado cadastrado no banco de dados

![consulta_dados_delecao_H2.png](images%2Fcondutor%2Fdelecao-cadastro-condutor%2Fconsulta_dados_delecao_H2.png)

</details>

--- 
* ### Veículo

<p align="justify">
Este microsserviço permite o cadastro, consulta, atualização e exclusão de informações relacionada aos veiculos.
</p>
<details>
<summary>1. Criação, consulta e persistência</summary>

>Cadastro de Veículo

![cadastrar_veiculo_postman.png](images%2Fveiculo%2Fcadastro-consulta-veiculo%2Fcadastrar_veiculo_postman.png)

---

>Consulta de veículos pelo cadastro de condutor

![consulta_veiculo_condutor_postman.png](images%2Fveiculo%2Fcadastro-consulta-veiculo%2Fconsulta_veiculo_condutor_postman.png)

___

>Consulta de veículo pela placa

![consulta_veiculo_placa_postman.png](images%2Fveiculo%2Fcadastro-consulta-veiculo%2Fconsulta_veiculo_placa_postman.png)

___

>Consulta de veículo e condutor

![consulta_veiculo_e_condutor_postman.png](images%2Fveiculo%2Fcadastro-consulta-veiculo%2Fconsulta_veiculo_e_condutor_postman.png)

___

>Consulta de lista de veículos

![consulta_list_veiculos.png](images%2Fveiculo%2Fcadastro-consulta-veiculo%2Fconsulta_list_veiculos.png)

___

>Consulta no banco de dados H2

![consulta_veiculo_H2.png](images%2Fveiculo%2Fcadastro-consulta-veiculo%2Fconsulta_veiculo_H2.png)

___
</details>
<details>
<summary>2. Atualização de dados</summary>

>Atualização de cadastro de veículos

![atualizacao_cadastro_veiculo_postman.png](images%2Fveiculo%2Fatualizacao-veiculo%2Fatualizacao_cadastro_veiculo_postman.png)

___

>Verificação dos dados atualizados no banco de dados H2

![consulta_dados_atualizados_H2.png](images%2Fveiculo%2Fatualizacao-veiculo%2Fconsulta_dados_atualizados_H2.png)
___
</details>
<details>
<summary>3. Deleção de dados</summary>

>Deleção de dados de Veículos

![delecao_cadastro_postman.png](images%2Fveiculo%2Fdelecao-veiculo%2Fdelecao_cadastro_postman.png)

___

>Verificação após deleção no banco de dados H2

![consulta_dados_delecao_H2.png](images%2Fveiculo%2Fdelecao-veiculo%2Fconsulta_dados_delecao_H2.png)

</details>

---
* ### Estacionamento

<p align="justify">
Este microsserviço permite o cadastro, consulta, atualização e exclusão de informações relacionada aos estacionamentos.
</p>
<details>
<summary>1. Criação, consulta e persistência</summary>

>Cadastramento de estacionamento

![cadastrar_estacionamento_postman.png](images%2Festacionamento%2Fcadastro-consulta-estacionamento%2Fcadastrar_estacionamento_postman.png)
___

>Consulta de estacionamento

![consulta_estacionamento_postman.png](images%2Festacionamento%2Fcadastro-consulta-estacionamento%2Fconsulta_estacionamento_postman.png)
___

>Consulta de lista de estacionamentos

![consulta_lista_estacionamentos_postman.png](images%2Festacionamento%2Fcadastro-consulta-estacionamento%2Fconsulta_lista_estacionamentos_postman.png)
___

>Consulta de estacionamentos no banco de dados H2

![consulta_estacionamento_H2.png](images%2Festacionamento%2Fcadastro-consulta-estacionamento%2Fconsulta_estacionamento_H2.png)
___
</details>
<details>
<summary>2. Atualização de dados</summary>

>Atualização de dados de estacionamento

![atualizacao_cadastro_estacionamento_postman.png](images%2Festacionamento%2Fatualizacao-estacionamento%2Fatualizacao_cadastro_estacionamento_postman.png)
___

>Consulta após atualização no banco de dados H2

![consulta_dados_atualizados_H2.png](images%2Festacionamento%2Fatualizacao-estacionamento%2Fconsulta_dados_atualizados_H2.png)

</details>
<details>
<summary>3. Deleção de dados</summary>

>Deleção de estacionamento

![delecao_cadastro_postman.png](images%2Festacionamento%2Fdelecao-estacionamento%2Fdelecao_cadastro_postman.png)
___

>Verificação após deleção no banco de dados H2

![consulta_dados_delecao_H2.png](images%2Festacionamento%2Fdelecao-estacionamento%2Fconsulta_dados_delecao_H2.png)
___
</details>

---
* ### Forma de Pagamento

<p align="justify">
Este microsserviço permite o cadastro, consulta, atualização e exclusão de informações relacionada as formas de pagamento.
</p>

<details>
<summary>1. Criação, consulta e persistência</summary>

>Cadastro de forma de pagamento

![cadastrar_forma_pagamento_postman.png](images%2Fforma-de-pagamento%2Fcadastro-consulta-forma-de-pagamento%2Fcadastrar_forma_pagamento_postman.png)
___

>Consulta de forma de pagamento por condutor

![consulta_forma_pagamento_condutor_postman.png](images%2Fforma-de-pagamento%2Fcadastro-consulta-forma-de-pagamento%2Fconsulta_forma_pagamento_condutor_postman.png)
___

>Consulta de forma de pagamento por CPF

![consulta_forma_pagamento_por_CPF_postman.png](images%2Fforma-de-pagamento%2Fcadastro-consulta-forma-de-pagamento%2Fconsulta_forma_pagamento_por_CPF_postman.png)
___

>Consulta de lista de formas de pagamento

![consulta_lista_forma_pagamento_postman.png](images%2Fforma-de-pagamento%2Fcadastro-consulta-forma-de-pagamento%2Fconsulta_lista_forma_pagamento_postman.png)

>Consulta das formas de pagamento no banco de dados H2

![consulta_forma_pagamento_H2.png](images%2Fforma-de-pagamento%2Fcadastro-consulta-forma-de-pagamento%2Fconsulta_forma_pagamento_H2.png)
___
</details>
<details>
<summary>2. Atualização de dados</summary>

>Atualização de forma de pagamento

![atualizacao_forma_pagamento_postman.png](images%2Fforma-de-pagamento%2Fatualizacao-forma-de-pagamento%2Fatualizacao_forma_pagamento_postman.png)
___

>Consulta após atualização da forma de pagamento no banco de dados H2

![consulta_dados_atualizados_H2.png](images%2Fforma-de-pagamento%2Fatualizacao-forma-de-pagamento%2Fconsulta_dados_atualizados_H2.png)
___
</details>
<details>
<summary>3. Deleção de dados</summary>

>Deleção de forma de pagamento

![delecao_cadastro_postman.png](images%2Fforma-de-pagamento%2Fdelecao-forma-de-pagamento%2Fdelecao_cadastro_postman.png)
___

>Consulta após deleção de forma de pagamento no banco de dados H2

![consulta_dados_delecao_H2.png](images%2Fforma-de-pagamento%2Fdelecao-forma-de-pagamento%2Fconsulta_dados_delecao_H2.png)

</details>

---
* ### Controle de Estacionamento

<p align="justify">
Este microsserviço permite o cadastro de informações relacionada ao controle de estacionamento.
</p>

<details>
<summary>Cadastro e verificação</summary>

>Cadastro de controle de estacionamento

![cadastro_controle_estacionamento_postman.png](images%2Fcontrole-de-estacionamento%2Fcadastro-consulta-controle-estacionamento%2Fcadastro_controle_estacionamento_postman.png)
___

>Consulta do controle de estacionamento no banco de dados H2

![consulta_dados_cadastrado_H2.png](images%2Fcontrole-de-estacionamento%2Fcadastro-consulta-controle-estacionamento%2Fconsulta_dados_cadastrado_H2.png)
___

>Erro ao tentar cadastrar com algum ID inexistente

![erro_cadastro_ID_inexistente_postman.png](images%2Fcontrole-de-estacionamento%2Fcadastro-consulta-controle-estacionamento%2Ferro_cadastro_ID_inexistente_postman.png)

</details>

---

## 💾Modelo lógico de dados

![modelo_logico.png](images%2Fmodelo-logico%2Fmodelo_logico.png)

___

## 👨‍🎓 Projeto desenvolvido por:

[Jeferson Alves ](https://github.com/jefsantos)•
[Rodrigo Cotrim ](https://github.com/rdgmv)•
[Rodrigo Maciel ](https://github.com/rodrigomgalvao)•
[William Sousa ](https://github.com/willrsousa94)