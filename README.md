# RPG — Destino da Coroa

Projeto acadêmico desenvolvido para a disciplina de **Engenharia de Software**.

---

## Sobre o Projeto

Destino da Coroa é um RPG de texto em Java onde o jogador cria um personagem, enfrenta inimigos em batalhas por turnos e tenta derrotar o chefe final — o Dragão.

---

## Como Executar

**Pré-requisitos:** Java 17 ou superior

```bash
javac -d bin src/**/*.java
java -cp bin Main
```

---

## Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)**:

```
src/
├── model/
├── view/
├── controller/
└── util/
```

---

## Funcionalidades

- Sistema de combate por turnos com críticos, poderes especiais e fuga
- Inventário com CRUD de itens
- Loja com poções e sistema de ouro
- Exploração de áreas com inimigos aleatórios
- Sistema de XP, Level Up e progressão por fases
- Histórico de logs com CRUD
- Save/Load do inventário

---

## Integrantes

- Eduardo Menegon
- João Vitor Mazzoli
- Kevin Dominguez
- Pedro Furtado

---

Projeto acadêmico desenvolvido para fins educacionais — Engenharia de Software.
