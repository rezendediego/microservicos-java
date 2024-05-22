#### Projeto Individual Disciplina Arquitetura de Microsserviços Java [23E4_3] - Pós-Graduação Engenharia de Software com Java | Instituto Infnet ####

**Aluno: Diego Henrique Cornelio de Rezende**

-------------------------------------------------------------------
#### ORGANIZAÇÃO DAS SOLUÇÕES - MICROSERVIÇOS

> Nota Informativa
> - O banco de dados utilizado MySQL através do MYSQL Workbench.
>
> - Para verificação, favor habilitar o workbench e inicializar o App como Java Application
>
> - Cada Solução está em um branch próprio.

#### Currency Conversion App
![Diagrama Microserviços](/imagens/dag-currency-conversion.png)

#### BRANCH QUOTE-SERVICE

Serviço que obtém de um provedor externo para obtenção de quotas de câmbio


#### BRANCH CONVERSION-SERVICE

Serviço que calcula conversão cambial

#### BRANCH EUREKA-SERVER

Servico de service discovery


#### BRANCH API-GATEWAY

Servico de gateway para conversion-app