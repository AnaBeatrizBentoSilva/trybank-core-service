# TryBank - Backend

Backend do sistema bancário digital **TryBank**, projetado para gerenciar contas, operações financeiras, cartões, investimentos e usuários. Este projeto oferece uma API robusta para suportar as funcionalidades essenciais de um banco digital moderno.

---

## 🚀 Sobre o projeto

TryBank é uma aplicação backend para um banco digital que permite:

- Gerenciamento de clientes (pessoas)
- Controle de contas bancárias
- Emissão e controle de cartões
- Registro e processamento de operações financeiras (depósitos, saques, transferências, investimentos e câmbio)
- Controle de investimentos vinculados às contas

O backend é responsável por garantir a segurança, integridade e rastreabilidade das operações realizadas.

---

## 📊 Diagrama do projeto

```plantuml
@startuml
enum TipoOperacao {
  DEPOSITO
  SAQUE
  TRANSFERENCIA
  INVESTIMENTO
  CAMBIO
}

class Pessoa {
  +id : String
  +nomeCompleto : String
  +dataNascimento : Date
  +cpf : String
  +email : String
  +telefone : String
  +endereco : String
  +username : String
  +senha : String
}

class Conta {
  +id : String
  +agencia : String
  +numeroConta : String
  +saldo : Decimal
}

class Cartao {
  +id : String
  +numeroCartao : String
  +nomeNoCartao : String
  +codigoSeguranca : String
  +validade : Date
}

class Operacao {
  +id : String
  +tipo : TipoOperacao
  +descricao : String
  +data : Date
  +valor : Decimal
}

class Investimento {
  +id : String
  +nomeAplicacao : String
  +valor : Decimal
  +data : Date
}

Pessoa "1" -- "1..*" Conta : possui >
Conta "1" -- "1..*" Cartao : possui >
Conta "1" -- "0..*" Operacao : realiza >
Conta "1" -- "0..*" Investimento : possui >
Operacao "0..1" -- "0..1" Conta : contaOrigem >
Operacao "0..1" -- "0..1" Conta : contaDestino >

@enduml
